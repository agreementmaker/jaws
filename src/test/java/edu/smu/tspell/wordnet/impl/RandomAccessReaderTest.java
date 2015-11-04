package edu.smu.tspell.wordnet.impl;

import edu.smu.tspell.wordnet.TestUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class RandomAccessReaderTest {

    private File sampleFile;
    private RandomAccessReader reader;

    @Before
    public void setUp() throws IOException, URISyntaxException {
        sampleFile = TestUtil.fileFromClasspath("sample_file.txt");
        reader = new RandomAccessReader(sampleFile) {};
    }

    @Test
    public void file_size() {
        assertThat(reader.getFileSize(), is(85l));
    }

    @Test
    public void read_entire_file() throws URISyntaxException, IOException {
        assertThat(reader.readToNextEndOfLine(), is(equalTo("This is a simple test file.")));
        assertThat(reader.readToNextEndOfLine(), is(equalTo("Contains some test lines.")));
        assertThat(reader.readToNextEndOfLine(), is(equalTo("And a third line.")));
        assertThat(reader.readToNextEndOfLine(), is(equalTo("And a fourth.")));
    }

    @Test
    public void file_pointer_is_consistent() throws IOException {
        reader.readToNextEndOfLine();
        final long filePointerAfterFirstLine = reader.getFilePointer();
        reader.seek(4);
        reader.readToNextEndOfLine();
        assertThat(reader.getFilePointer(), is(filePointerAfterFirstLine));
    }

    @Test
    public void file_pointer_starts_at_0() throws IOException {
        assertThat(reader.getFilePointer(), is(0l));
    }

    @Test
    public void file_pointer_increments_correctly() throws IOException {
        reader.readNextCharacter();
        assertThat(reader.getFilePointer(), is(1l));
        reader.readNextCharacter();
        assertThat(reader.getFilePointer(), is(2l));
        reader.seek(40);
        assertThat(reader.getFilePointer(), is(40l));
    }

    @Test
    public void seek_to_0() throws IOException {
        reader.seek(0);
        assertThat(reader.getFilePointer(), is(0l));
    }

    @Test
    public void seek_to_same_position() throws IOException {
        reader.seek(35);
        assertThat(reader.getFilePointer(), is(35l));
        reader.seek(35);
        assertThat(reader.getFilePointer(), is(35l));
    }

    @Test
    public void read_characters() throws IOException {
        assertThat(reader.readNextCharacter(), is('T'));
        assertThat(reader.readNextCharacter(), is('h'));
        reader.seek(35);
        assertThat(reader.readNextCharacter(), is('s'));
        assertThat(reader.readNextCharacter(), is(' '));
        reader.seek(27);
        assertThat(reader.readNextCharacter(), is('\n'));
    }

    @Test
    public void read_char_at_eol() throws IOException {
        reader.seek(85);
        assertThat(reader.readNextCharacter(), is((char)-1));
    }

    @Test
    public void finalize_closes_random_access_file() throws Throwable {
        RandomAccessFile raf = reader.getAccessor();
        assertTrue(raf.getChannel().isOpen());
        //noinspection FinalizeCalledExplicitly
        reader.finalize();
        assertFalse(raf.getChannel().isOpen());
    }

    private class MaliciousSubclass extends RandomAccessReader {
        protected MaliciousSubclass(File file) throws IOException {
            super(file);
            this.accessor = null;
        }
    }

    @Test
    public void finalize_with_null_accessor() throws Throwable {
        MaliciousSubclass subClass = new MaliciousSubclass(sampleFile);
        //noinspection FinalizeCalledExplicitly
        subClass.finalize();
    }

    private class MaliciousSubclass2 extends RandomAccessReader {
        private final File originalFile;
        protected MaliciousSubclass2(File file) throws IOException {
            super(file);
            this.originalFile = file;
            this.accessor = null;
        }

        @Override
        protected RandomAccessFile getAccessor() {
            try {
                return new RandomAccessFile(originalFile, ACCESS_MODE) {
                    @Override
                    public void close() throws IOException {
                        throw new IOException("This exception should be ignored, it is part of the test.");
                    }
                };
            } catch(FileNotFoundException ex) {
                return null;
            }
        }
    }

    @Test
    public void finalize_doesnt_blow_up_on_IOException() throws Throwable {
        RandomAccessReader rar = new MaliciousSubclass2(sampleFile);
        //noinspection FinalizeCalledExplicitly
        rar.finalize();
    }

}
package edu.smu.tspell.wordnet.impl;

import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class LineLocatorTest {
    private File sampleFile;
    private LineLocator locator;

    public void setUp(String file) throws Exception {
        URL fileUrl = Thread.currentThread().getContextClassLoader().getResource(file);
        assert fileUrl != null;
        sampleFile = new File(fileUrl.toURI());
        locator = new LineLocator(sampleFile);
    }

    @Test
    public void finds_first_line() throws Exception {
        setUp("linelocator/somewords.txt");
        assertThat(locator.getLine("a"), is(equalTo("abacus")));
        assertThat(locator.getFilePointer(), is(8l));
    }

    @Test
    public void finds_last_line() throws Exception {
        setUp("linelocator/somewords.txt");
        assertThat(locator.getLine("zo"), is(equalTo("zoo")));
        assertThat(locator.getFilePointer(), is(locator.getFileSize()));
    }

    @Test
    public void finds_exact_word() throws Exception {
        setUp("linelocator/somewords.txt");
        assertThat(locator.getLine("garden"), is(equalTo("garden")));
        assertThat(locator.getFilePointer(), is(19l));
    }

    @Test
    public void doesnt_find_nonexisting_word() throws Exception {
        setUp("linelocator/somewords.txt");
        assertNull(locator.getLine("beer"));
        assertNull(locator.getLine("supercalifragilisticexpialidocious"));
    }

    @Test
    public void finds_single_letter_at_beginning() throws Exception {
        setUp("linelocator/oneletter.txt");
        assertThat(locator.getLine("a"), is(equalTo("a")));
        assertThat(locator.getFilePointer(), is(locator.getFileSize()));
    }

    @Test
    public void handles_jumping_to_last_line() throws Exception {
        setUp("linelocator/twolines.txt");
        assertThat(locator.getLine("r"), is(equalTo("reallylongword")));
        assertThat(locator.getFilePointer(), is(locator.getFileSize()));
    }
}
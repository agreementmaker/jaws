package edu.smu.tspell.wordnet.api;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class WordNetDatabaseTest {
    @Test
    public void simple_instantiation() throws IOException {
        String workingDir = System.getProperty("user.dir");
        File wordnetDatabaseDir = new File(workingDir, "target/WordNet-3.0/dict");

        assertTrue("The WordNet database should be downloaded to '" + wordnetDatabaseDir.getCanonicalPath() +
                   "'.  Please run maven generate-test-resources.",
                   wordnetDatabaseDir.exists());

        System.setProperty("wordnet.database.dir", wordnetDatabaseDir.getCanonicalPath());
        WordNetDatabase wndb = WordNetDatabase.getFileInstance();

        Synset[] toDo = wndb.getSynsets("do", SynsetType.VERB);
        assertTrue(toDo.length > 0);
    }
}
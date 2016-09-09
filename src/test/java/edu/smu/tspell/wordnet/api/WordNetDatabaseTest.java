package edu.smu.tspell.wordnet.api;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class WordNetDatabaseTest {
    private static WordNetDatabase wndb;

    @BeforeClass
    public static void setUp() throws IOException {
        String workingDir = System.getProperty("user.dir");
        File wordnetDatabaseDir = new File(workingDir, "target/WordNet-3.0/dict");

        assertTrue("The WordNet database should be downloaded to '" + wordnetDatabaseDir.getCanonicalPath() +
                        "'.  Please run maven generate-test-resources.",
                wordnetDatabaseDir.exists());

        System.setProperty("wordnet.database.dir", wordnetDatabaseDir.getCanonicalPath());
        wndb = WordNetDatabase.getFileInstance();
    }

    @Test
    public void noun_lookup() {
        Synset[] result = wndb.getSynsets("dog", SynsetType.NOUN);
        assertTrue(result.length > 0);
    }

    @Test
    public void verb_lookup() {
        Synset[] result = wndb.getSynsets("do", SynsetType.VERB);
        assertTrue(result.length > 0);
    }

    @Test
    public void adjective_lookup() {
        Synset[] result = wndb.getSynsets("free", SynsetType.ADJECTIVE);
        assertTrue(result.length > 0);
    }

    @Test
    public void adjective_sattelite_lookup() {
        Synset[] result = wndb.getSynsets("free", SynsetType.ADJECTIVE_SATELLITE);
        assertTrue(result.length > 0);
    }

    @Test
    public void adverb_lookup() {
        Synset[] result = wndb.getSynsets("freely", SynsetType.ADVERB);
        assertTrue(result.length > 0);
    }
}
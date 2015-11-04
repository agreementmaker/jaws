package edu.smu.tspell.wordnet;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class TestUtil {
    public static File fileFromClasspath(String location) {
        try {
            URL fileUrl = Thread.currentThread().getContextClassLoader().getResource(location);
            assert fileUrl != null;
            return new File(fileUrl.toURI());
        } catch(URISyntaxException ex) {
            throw new AssertionError("The URI should not be invalid.", ex);
        }
    }
}
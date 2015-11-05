package edu.smu.tspell.wordnet.impl;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class ExampleSentencesTest {
    @Test
    public void senses() {
        ExampleSentences sentences = new ExampleSentences();
        sentences.addFrame("This is some text.", "test");
        sentences.addFrame("This is more text.", "test");

        assertThat(sentences.getFrames("test").length, is(2));
        assertThat(sentences.getFrames("test")[0], is(equalTo("This is some text.")));
        assertThat(sentences.getFrames("test")[1], is(equalTo("This is more text.")));
    }

    @Test
    public void common_senses() {
        ExampleSentences sentences = new ExampleSentences();
        sentences.addCommonFrame("This is some text.");
        sentences.addCommonFrame("This is more text.");

        assertThat(sentences.getCommonFrames().length, is(2));
        assertThat(sentences.getCommonFrames()[0], is(equalTo("This is some text.")));
        assertThat(sentences.getCommonFrames()[1], is(equalTo("This is more text.")));
    }

    @Test
    public void missing_sense() {
        ExampleSentences sentences = new ExampleSentences();
        assertNotNull(sentences.getFrames("test"));
        assertThat(sentences.getFrames("test").length, is(0));
    }

    @Test
    public void templates() {
        String[] templates = {"Template {0}", "Another {0} template"};
        ExampleSentences sentences = new ExampleSentences();
        sentences.setTemplates("test", templates);
        String[] formattedTemplates = sentences.getFormattedTemplates("test");
        assertThat(formattedTemplates.length, is(2));
        assertThat(formattedTemplates[0], is(equalTo("Template test")));
        assertThat(formattedTemplates[1], is(equalTo("Another test template")));
    }
}
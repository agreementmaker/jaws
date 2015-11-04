package edu.smu.tspell.wordnet.impl;

import edu.smu.tspell.wordnet.TestUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class MultipleLineLocatorTest {
    private MultipleLineLocator mll;

    @Before
    public void setUp() throws IOException {
        mll = new MultipleLineLocator(TestUtil.fileFromClasspath("linelocator/somewords.txt")) {};
    }

    @Test
    public void single_line_return() throws IOException {
        String[] expectedResult = {
                "abacus"
        };

        assertThat(mll.getLines("aba"), is(equalTo(expectedResult)));
    }

    @Test
    public void multi_line_return_with_single_prefix() throws IOException {
        String[] expectedResult = {
            "giraffe", "girl"
        };

        assertThat(mll.getLines("gi"), is(equalTo(expectedResult)));
    }

    @Test
    public void multi_line_return_with_two_prefixes() throws IOException {
        String[] expectedResult = {
            "bear", "garden", "giraffe", "girl", "year"
        };

        assertThat(mll.getLines("be", "ye"), is(equalTo(expectedResult)));
    }

    @Test
    public void entire_file() throws IOException {
        String[] expectedResult = {
            "abacus", "bear", "garden", "giraffe", "girl", "year", "zebra", "zoo"
        };

        assertThat(mll.getLines("a", "z"), is(equalTo(expectedResult)));
    }

    @Test
    public void find_upper_part_of_file() throws IOException {
        String[] expectedResult = {
                "abacus", "bear", "garden", "giraffe", "girl"
        };

        assertThat(mll.getLines("a", "g"), is(equalTo(expectedResult)));
    }

    @Test
    public void find_lower_part_of_file() throws IOException {
        String[] expectedResult = {
                "garden", "giraffe", "girl", "year", "zebra", "zoo"
        };

        assertThat(mll.getLines("g", "z"), is(equalTo(expectedResult)));
    }
}
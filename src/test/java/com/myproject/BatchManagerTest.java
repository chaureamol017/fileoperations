package com.myproject;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;

public class BatchManagerTest {


    @Test
    public void shouldHandleZeroRows() {
        final List<String> passed = new ArrayList<>();
        final List<String> expected = new ArrayList<>();
        Object[][] data = {};
        final int count = BatchManager.of(1)
        		.adapt(r->"hello")
        		.apply(passed::addAll)
        		.process(data);
        assertThat(count, is(0));
        assertThat(passed, is(expected));
    }

    @Test
    public void shouldSkipFirstRow() {
        final List<String> passed = new ArrayList<>();
        final List<String> expected = new ArrayList<>();
        Object[][] data = {{"header"}};
        final int count = BatchManager.of(100).adapt(r->"hello").apply(passed::addAll).process(data);
        assertThat(count, is(0));
        assertThat(passed, is(expected));
    }

    @Test
    public void shouldAdaptSecondRow() {
        final List<String> actual = new ArrayList<>();
        final List<String> expected = new ArrayList<>();
        expected.add("helloone");
        Object[][] data = {{"header"}, {"one"}};
        final int count = BatchManager.of(100).adapt(r->"hello"+r[0]).apply(actual::addAll).process(data);
        assertThat(count, is(1));
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldAdaptThirdRow() {
        final List<String> actual = new ArrayList<>();
        final List<String> expected = new ArrayList<>();
        expected.add("helloone");
        expected.add("hellotwo");
        Object[][] data = {{"header"}, {"one"}, {"two"}};
        final int count = BatchManager.of(100).adapt(r->"hello"+r[0]).apply(actual::addAll).process(data);
        assertThat(count, is(2));
        assertThat(actual, is(expected));
    }


    @Test
    public void shouldProcessBatchesOfOneRow() {
        final List<String> actual = new ArrayList<>();
        final List<String> expected = new ArrayList<>();
        expected.add("helloone");
        expected.add("hellotwo");
        expected.add("hellothree");
        Object[][] data = {{"header"}, {"one"}, {"two"}, {"three"}};
        final int count = BatchManager.of(1).adapt(r->"hello"+r[0]).apply(c -> {
            assertThat(c.size(), is(1));
            actual.addAll(c);
        }).process(data);
        assertThat(count, is(3));
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldProcessBatchesOfAtMostTwoRows() {
        final List<String> actual = new ArrayList<>();
        final List<String> expected = new ArrayList<>();
        expected.add("helloone");
        expected.add("hellotwo");
        expected.add("hellothree");
        Object[][] data = {{"header"}, {"one"}, {"two"}, {"three"}};
        final int count = BatchManager.of(2)
        		.adapt(r->
        		"hello"+r[0]
        				)
        		.apply(c -> {
		            assertThat(c.size(), Matchers.lessThanOrEqualTo(2));
		            actual.addAll(c);
		        })
        		.process(data);
        assertThat(count, is(3));
        assertThat(actual, is(expected));
    }

}
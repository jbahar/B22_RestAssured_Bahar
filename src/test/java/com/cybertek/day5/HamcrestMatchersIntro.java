package com.cybertek.day5;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


public class HamcrestMatchersIntro {

    @Test
    public void test1() {

        //actual 5+5
        MatcherAssert.assertThat(5 + 5, Matchers.is(10));
        assertThat(5 + 5, equalTo(10));

        //matchers has 2 overload version
        //first that accept the actual value
        //second that accept another matchers
        //below example is method ias accepting another matchers equal to make it readable
        assertThat(5 + 5, is(equalTo(10)));

        assertThat(5 + 5, not(9));
        assertThat(5 + 5, is(not(9)));


        //number comparison
        //greaterThan()
        //greaterThanOrEqualTo()
        //lessThan()
        //lessThanOrEqualTo()

        assertThat(5 + 5, is(greaterThan(9)));
    }

    @DisplayName("Assertion with string")
    @Test
    public void stringHamcrest() {

        String text = "B22 is learning hamcrest";

        assertThat(text, is("B22 is learning hamcrest"));
        assertThat(text, equalTo("B22 is learning hamcrest"));
        assertThat(text,is(equalTo("B22 is learning hamcrest")));

        // check if text start with B22
        assertThat(text, startsWith("B22"));
        assertThat(text, is(startsWith("B22")));
        assertThat(text, startsWithIgnoringCase("b22"));
        assertThat(text, endsWith("rest"));

        // check if text contain string learning
        assertThat(text, containsString("learning"));
        assertThat(text, containsStringIgnoringCase("LEARNING"));

        String str = " ";
        // check is above str is blank
        assertThat(str, blankString());

        //check if trimmed str is empty string
        assertThat(str.trim(), emptyString());

    }

    @DisplayName("Hamcrest for Collection")
    @Test
    public void testCollection() {

        List<Integer> listOfNumber = Arrays.asList(1,4,5,6,35,54,66,77,45,23);

        // check size of the list
        assertThat(listOfNumber, hasSize(10));

        // check if this list has item 77
        assertThat(listOfNumber, hasItem(77));

        // check if this list has 54,77,23
        assertThat(listOfNumber, hasItems(77,54,23));

        // check all number grater than 0
        assertThat(listOfNumber, everyItem(greaterThan(0)));

    }
}

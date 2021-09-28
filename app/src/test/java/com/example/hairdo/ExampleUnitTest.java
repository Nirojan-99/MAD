package com.example.hairdo;

import com.example.hairdo.Helper.GetRating;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void getrating() {
        GetRating getRating = new GetRating();
        Float result = getRating.calRating(12.0f,3);
        assertEquals(4.0f,result,3.0f);
    }
}
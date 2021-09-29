package com.example.hairdo;

import com.example.hairdo.Helper.DateCompare;
import com.example.hairdo.Helper.GetRating;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.text.ParseException;
import static org.junit.Assert.*;


public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testGetRating() {
        //case true
        GetRating getRating = new GetRating();
        Float result = getRating.calRating(12.0f,3);
        assertEquals(4.0f,result,3.0f);

        //case 2
        Float result1 = getRating.calRating(12.0f,2);
        assertEquals(6.0f,result1,3.0f);
    }

    @Test
    public void testCompareDate() throws ParseException {
        //case true
        String date1 = "12/02/2021";
        String date2 = "12/2/2021";
        Boolean result = DateCompare.comparedates(date1,date2);

        assertEquals(result,true);

        //case false
        String date3 = "02/02/2021";
        String date4 = "20/2/2021";
        Boolean result1 = DateCompare.comparedates(date3,date4);

        assertEquals(result1,false);
    }

    @Test
    public void testCompareFutureDate() throws ParseException {
        //case one
        String date1 = "12/02/2021";
        String date2 = "13/2/2021";
        Boolean result = DateCompare.comparefuturedates(date1,date2);

        assertEquals(result,false);

        //case two
        String date3 = "20/02/2021";
        String date4 = "20/2/2021";
        Boolean result1 = DateCompare.comparefuturedates(date3,date4);

        assertEquals(result1,true);
    }

}
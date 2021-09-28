package com.example.hairdo;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.filters.MediumTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
@LargeTest
public class EditDetailsTest {

    @Rule
    public ActivityTestRule<EditDetails> rule  = new  ActivityTestRule<>(EditDetails.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void inteTest() {

//        Espresso.onView(withText("hello")).check(matches())
    }

    @After
    public void tearDown() throws Exception {
    }
}
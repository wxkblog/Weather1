package com.wang.administrator.myapplication;

import android.util.Log;

import org.junit.Test;

import java.net.URLEncoder;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        try {
            Log.e("lll", URLEncoder.encode("常德","UTF-8"));
        }
        assertEquals(4, 2 + 2);
    }
}
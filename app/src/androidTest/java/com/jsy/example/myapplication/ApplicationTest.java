package com.jsy.example.myapplication;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.lang.annotation.Target;

import dalvik.annotation.TestTarget;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
    public void test(){
        System.out.print("test");
    }

}
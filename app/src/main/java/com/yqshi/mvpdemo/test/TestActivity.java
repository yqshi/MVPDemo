package com.yqshi.mvpdemo.test;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.yqshi.mvpdemo.R;

/**
 * Project Name: MVPDemo
 * Describe:
 * Copyright: Copyright (C) 2016 All Rights Reserved.
 * Company: 利伊享
 * Author: shiyq
 * Create Time： 2016/7/26 18:17
 */


public class TestActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common);
        TestFragment testFragment = (TestFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);
        if (testFragment == null) {
            testFragment = TestFragment.newInstance();
            Log.e("app_test", "testActivity");
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, testFragment);
            transaction.commit();
        }
    }
}

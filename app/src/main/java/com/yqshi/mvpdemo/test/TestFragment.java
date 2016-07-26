package com.yqshi.mvpdemo.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yqshi.mvpdemo.BaseFragment;
import com.yqshi.mvpdemo.R;

/**
 * Project Name: MVPDemo
 * Describe:
 * Copyright: Copyright (C) 2016 All Rights Reserved.
 * Company: 利伊享
 * Author: shiyq
 * Create Time： 2016/7/26 18:17
 */


public class TestFragment extends BaseFragment<TestPresenter> implements TestContract.View {

    private TextView text;

    public static TestFragment newInstance() {

        Bundle args = new Bundle();

        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected TestPresenter initPresenter() {
        return new TestPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        text = (TextView) view.findViewById(R.id.text);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.showTitle();
        Log.e("app_test", "testFragment onResume");
    }

    @Override
    public void showTitle() {
        text.setText("you are very beautiful");
    }
}

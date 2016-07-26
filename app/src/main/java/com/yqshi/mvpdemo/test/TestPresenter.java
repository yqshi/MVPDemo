package com.yqshi.mvpdemo.test;

import com.yqshi.mvpdemo.BasePresenter;

/**
 * Project Name: MVPDemo
 * Describe: TestPresenter
 * Copyright: Copyright (C) 2016 All Rights Reserved.
 * Company: 利伊享
 * Author: shiyq
 * Create Time： 2016/7/26 18:17
 */


public class TestPresenter extends BasePresenter<TestContract.View> {

    public void showTitle(){
        if(!isViewAtttached())
            return;

        getView().showTitle();
    }


}

package com.yqshi.mvpdemo;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * BasePresenter
 * Created by shiyq on 2016/7/26.
 */
public abstract class BasePresenter<T> {
    protected Reference<T> mView;

    /**
     * 与传进来的view（Activity，Fragment）建立联系
     *
     * @param view Activity，Fragment
     */
    public void attachView(T view) {
        if (mView == null) {
            mView = new WeakReference<>(view);
        }

    }

    /**
     * 获取 View 的实例
     *
     * @return View 的实例
     */
    protected T getView() {
        return mView.get();
    }

    /**
     * 判断是否还存在联系
     *
     * @return if true 代表和View还是Attach的
     */
    public boolean isViewAtttached() {
        return mView != null && mView.get() != null;

    }


    /***
     * 关闭联系
     */
    public void detachView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }
}

package com.yqshi.mvpdemo;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * BaseFragment
 * Created by shiyq on 2016/7/26
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    protected T mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter = initPresenter();
        attachView();
    }


    /**
     * presenter 和View产生联系
     */
    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);

        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected abstract T initPresenter();

}

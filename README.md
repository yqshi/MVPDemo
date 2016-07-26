# Android MVP实战
时间      | 内容| 作者
--------- |- | ---
2016.7.26 | MVPAndroid实战1.0| yqshi
>大家都知道以前Android当中的框架模式当中已经有了mvc（Model View Control），那为什么还要引用MVP（Model View Presenter）；众所周知mvc是根据View的变化，会导致Control和Model都要做出相应的改变；这种设计在面对需求变化不大时是一种很适合的模式，但是面对需求的频繁变化。代码的改动量就大了，而MVP模式当中就很好的解决了此类变化；
###目录
 **[MVC](#mvc)**  
 **[MVP](#mvp)**  
 **[MVP与Activity的生命周期](#mvp_life_cycle)**  
 **[Github地址](#github_address)**

<a name="mvc"></a>
###MVC
MVC的模型图如下<br>
![MVC](http://images.cnitblog.com/i/380707/201403/151950595272107.jpg)<br/>
我们可以看到的是，界面被分到了View，数据分到了载体Model上由Model“携带”，业务集中在Controller中，而推动业务的事件由用户与View交互，通过View向Controller发动。

当然，实现由很多种，每种细节上都有不同，所以我才只讲也只能讲大致的MVC。MVC的其中一个缺点便是没有明确的定义，所以不同的实现细节上都是不一样的。<br/>
MVC的一般流程是这样的：View（界面）触发事件--》Controller（业务）处理了业务，然后触发了数据更新--》不知道谁更新了Model的数据--》Model（带着数据）回到了View--》View更新数据<br>
而在Android当中的MVC就有一些混乱，有的时候我们可以把xml（layout）文件当中view，Activity、fragment等组件当作Control，有的时候我们又可以把Activity当作View，自己定义的Control当作Control，所以在面对代码不统一的时候很容易出问题；  
<a name="mvp"></a>
###MVP
MVP的模型图如下<br/>
![MVP模型图](http://images.cnitblog.com/i/380707/201403/152105088404082.gif)<br/>
MVP相对于MVC来讲，最大的变化就是通过增加一个Presenter层去控制View提供逻辑接口，这个解除了View与model直接的耦合，使得Model层能够只负责对数据层的交互，MVP模式可以分离显示层和逻辑层，他们之间通过接口进行通信，降低了耦合，理想当中的MVP模式可以实现同一份逻辑代码搭配不同的显示页面，这是因为他们之间并不依赖具体，而是倚赖于抽象，这使得Presenter可以运用任何实现了View的逻辑接口的ＵＩ。当然MVP也不是没有缺点；首先是类的增加，不管是定义View的接口还是增加Presenter层都是类的增加；第二针对功能不是那么多，需求没有那么多会显得臃肿；
<a name="mvp_life_cycle"></a>
###MVP与Activity的生命周期
  在使用MVP框架时需要特别注意的是在Presenter调用View的逻辑接口时，要特别判断当前Activity或者Fragment是否还存在，否则就会导致NullPointException；当然在一个个的方法里判断的话，这样会显得太LOW了；我们可以将Fragment改造成这个样子；<br/>

    public abstract class WOBaseFragment<T extends BasePresenter> extends Fragment{

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

    /**
     * 实例化Presenter
     *
     * @return
     */
    protected abstract T initPresenter();

    }

   而Presenter则改造成  

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
其中isViewAttached这个方法就是判断当前Presenter是否还和View绑定；
<a name="github_address"></a>
###Github地址
  **[MVPDemo](  https://github.com/yqshi/MVPDemo)**

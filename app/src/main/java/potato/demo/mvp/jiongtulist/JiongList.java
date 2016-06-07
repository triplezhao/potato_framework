package potato.demo.mvp.jiongtulist;

import dagger.Component;
import dagger.Provides;
import potato.demo.data.result.JiongtuAlbumListEntity;
import potato.demo.mvp.util.BasePresenter;
import potato.demo.mvp.util.BaseView;
import potato.demo.mvp.util.FragmentScoped;

/**
 * 注入方法：
 * DaggerJiongList_C.builder().module(new JiongList.Module(this)).build().inject(this);
 * This is a Dagger module. We use this to pass in the View dependency to the
 * {@link JiongHomePresenter}.
 */

/**
 * Created by ztw on 2016/6/7.
 */
interface JiongList {


    /**
     * 1.dragger组件，将module、presenter注入给Activity用的。
     * 2.依赖modules，或者也可以依赖其他component
     * 3.不需要自己实现，dragger会自动生成实现类DraggerXXXComponent类。 activity或者fragment中调用生成的类进行注入。
     */
    @FragmentScoped
    @Component(modules = Module.class)
    interface C {

        void inject(JiongListFragment fm);
    }

    /**
     * 1.视图接口，定义ui层的一些方法，比如刷新列表等。presenter中会调用这些方法
     * 2.activity会实现这个接口
     */
    interface V extends BaseView {

        void onRefreshSucc(JiongtuAlbumListEntity entity);

        void onRefreshFail(String err);

        void onLoadMoreSucc(JiongtuAlbumListEntity entity);

        void onLoadMoreFail(String err);

        void onCacheLoaded(JiongtuAlbumListEntity entity);

        long getSectionId();

    }

    /**
     * 1.业务逻辑方法集合，比如网络加载数据，读取数据库等，通过调用v接口，来调用activity的方法
     * 2.需要单独写类实现这个接口。
     */
    interface P extends BasePresenter {
        void reqRefresh();

        void reqLoadMore(long maxPublicDate);
    }

    /**
     * 1.dragger的module类，是dragger组件里需要注入的实例的提供者。
     * 2.component会依赖module进行其他类的实例化
     */
    @dagger.Module
    class Module {

        private final V mView;

        public Module(V view) {
            mView = view;
        }

        @Provides
        V provideView() {
            return mView;
        }
    }
}

package potato.demo.mvp.yk;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.potato.library.util.L;
import com.potato.library.view.NormalEmptyView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import potato.demo.R;
import potato.demo.chips.base.BaseActivity;
import potato.demo.data.bean.YKCategoryBean;
import potato.demo.data.result.YKVideoCategoryEntity;

public class YKHomeActivity extends BaseActivity implements YKHome.V {

    public static final String TAG = YKHomeActivity.class.getSimpleName();

    @Bind(R.id.viewpager) ViewPager viewPager;
    @Bind(R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.empty_view) NormalEmptyView emptyView;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Inject YKHomePresenter presenter;
    private HeaderPageAdapter adapter;
    private List<YKCategoryBean> mList = new ArrayList<YKCategoryBean>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ykhome);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        DaggerYKHome_C.builder().module(new YKHome.Module(this)).build().inject(this);

        adapter = new HeaderPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        emptyView.setEmptyType(NormalEmptyView.EMPTY_TYPE_LOADING);
        tabLayout.setVisibility(View.GONE);
        viewPager.setVisibility(View.GONE);
        presenter.loadData();
    }

    @Override
    public void render(YKVideoCategoryEntity entity) {
        if (entity == null) {
            emptyView.setEmptyType(NormalEmptyView.EMPTY_TYPE_NOCONTENT);
            return;
        }
        tabLayout.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.VISIBLE);
        emptyView.setEmptyType(NormalEmptyView.EMPTY_TYPE_GONE);

        List<YKCategoryBean> list = entity.list;
        if (list != null && list.size() > 0) {
            mList.clear();
            mList.addAll(list);
            adapter.notifyDataSetChanged();
            tabLayout.setTabsFromPagerAdapter(adapter);
        }
    }

    @Override
    public void onClick(View v) {


    }

    private class HeaderPageAdapter extends FragmentStatePagerAdapter {

        public HeaderPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            YKCategoryBean obj = mList.get(position);
            L.d("In ViewPager#getItem, header: " + obj.getLabel() + ", position: "
                    + position);
            YKHomeListFragment pageFragement = YKHomeListFragment.instance(mContext, obj.getLabel(), obj.getLabel());
            return pageFragement;
        }

        @Override
        public int getCount() {
            return mList == null ? 0 : mList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mList.get(position).getLabel();
        }
    }

}

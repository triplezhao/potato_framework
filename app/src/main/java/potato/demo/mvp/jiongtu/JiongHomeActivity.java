package potato.demo.mvp.jiongtu;

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
import potato.demo.data.bean.JiongtuSection;
import potato.demo.data.result.JiongtuSectionListEntity;

/**
 * Created by ztw on 2015/7/3.
 */
public class JiongHomeActivity extends BaseActivity implements JiongHome.V {

    public static final String TAG = JiongHomeActivity.class.getSimpleName();

    @Bind(R.id.viewpager) ViewPager viewPager;
    @Bind(R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.empty_view) NormalEmptyView emptyView;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Inject JiongHomePresenter presenter;

    private HeaderPageAdapter adapter;
    private List<JiongtuSection> mList = new ArrayList<JiongtuSection>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiongtu);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        DaggerJiongHome_C.builder().module(new JiongHome.Module(this)).build().inject(this);
        adapter = new HeaderPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        emptyView.setEmptyType(NormalEmptyView.EMPTY_TYPE_LOADING);
        tabLayout.setVisibility(View.GONE);
        viewPager.setVisibility(View.GONE);
        presenter.loadData();
    }

    @Override
    public void updateListView(JiongtuSectionListEntity entity) {
        if (entity == null) {
            emptyView.setEmptyType(NormalEmptyView.EMPTY_TYPE_NOCONTENT);
            return;
        }
        tabLayout.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.VISIBLE);
        emptyView.setEmptyType(NormalEmptyView.EMPTY_TYPE_GONE);

        List<JiongtuSection> list = entity.list;
        if (list != null && list.size() > 0) {
            mList.clear(); mList.addAll(list); adapter.notifyDataSetChanged();
            tabLayout.setTabsFromPagerAdapter(adapter);
        }
    }

    private class HeaderPageAdapter extends FragmentStatePagerAdapter {

        public HeaderPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            JiongtuSection obj = mList.get(position);
            L.d("In ViewPager#getItem, header: " + obj.getTitle() + ", position: "
                    + position);
            Bundle args = new Bundle();
            args.putLong(JiongListFragment.EXTRARS_SECTION_ID, obj.getSectionId());
            args.putString(JiongListFragment.EXTRARS_TITLE, obj.getTitle());
            JiongListFragment pageFragement = (JiongListFragment) Fragment.instantiate(mContext, JiongListFragment.class.getName(), args);
            return pageFragement;
        }

        @Override
        public int getCount() {
            return mList == null ? 0 : mList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mList.get(position).getTitle();
        }
    }
}

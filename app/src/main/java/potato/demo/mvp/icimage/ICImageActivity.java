package potato.demo.mvp.icimage;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
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
import potato.demo.data.bean.ICImageCatBean;
import potato.demo.data.result.ICImageCatEntity;

public class ICImageActivity extends BaseActivity implements ICImage.V {

    public static final String TAG = ICImageActivity.class.getSimpleName();

    @Bind(R.id.viewpager) ViewPager viewPager;
    @Bind(R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.empty_view) NormalEmptyView emptyView;
    @Inject ICImagePresenter presenter;
    private HeaderPageAdapter adapter;
    private List<ICImageCatBean> mList = new ArrayList<ICImageCatBean>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icimage);
        ButterKnife.bind(this);

        DaggerICImage_C.builder().module(new ICImage.Module(this)).build().inject(this);

        adapter = new HeaderPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        emptyView.setEmptyType(NormalEmptyView.EMPTY_TYPE_LOADING);
        tabLayout.setVisibility(View.GONE);
        viewPager.setVisibility(View.GONE);
        presenter.loadData();
    }

    @Override
    public void render(ICImageCatEntity entity) {
        if (entity == null) {
            emptyView.setEmptyType(NormalEmptyView.EMPTY_TYPE_NOCONTENT);
            return;
        }
        tabLayout.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.VISIBLE);
        emptyView.setEmptyType(NormalEmptyView.EMPTY_TYPE_GONE);

        List<ICImageCatBean> list = entity.list;
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
            ICImageCatBean obj = mList.get(position);
            L.d("In ViewPager#getItem, header: " + obj.getCat_name() + ", position: "
                    + position);
            ICImageListFragment pageFragement = ICImageListFragment.instance(mContext, obj.getCid(), obj.getCat_name());
            return pageFragement;
        }

        @Override
        public int getCount() {
            return mList == null ? 0 : mList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mList.get(position).getCat_name();
        }
    }

}

package potato.demo.ui.jiongtu;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;

import com.potato.library.net.RequestWraper;
import com.potato.library.net.RequestManager;
import com.potato.library.util.L;

import java.util.ArrayList;
import java.util.List;

import potato.demo.R;
import potato.demo.chips.base.BaseActivity;
import potato.demo.data.bean.JiongtuSection;
import potato.demo.data.parser.JiongtuSectionListParser;
import potato.demo.data.request.JiongtuRequestBuilder;

/**
 * Created by ztw on 2015/7/3.
 */
public class JiongtuActivity extends BaseActivity {

    public static final String TAG = "ActivityJiongtu";
    /** extrars */
    /**
     * views
     */
    private TabLayout tabLayout;
    private ViewPager viewPager;
    /**
     * adapters
     */
    private HeaderPageAdapter adapter;
    /**
     * data
     */
    private List<JiongtuSection> mList = new ArrayList<JiongtuSection>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiongtu);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        bindData();
    }

    public void bindData() {
        adapter = new HeaderPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        RequestWraper request = JiongtuRequestBuilder.getSectionListRequest();
        RequestManager.requestData(request, RequestManager.CACHE_TYPE_IGNORE_TIME, new RequestManager.DataLoadListener() {
            @Override
            public void onCacheLoaded(String content) {
                updateUI(content);
            }

            @Override
            public void onSuccess(int statusCode, String content) {
                updateUI(content);
            }

            @Override
            public void onFailure(Throwable error, String errMsg) {
                L.i(TAG,errMsg+"");
            }
        });

    }


    private void updateUI(String content) {
        L.i(TAG,content+"");
        if (TextUtils.isEmpty(content)) {
            return;
        }
       List<JiongtuSection> list = JiongtuSectionListParser.parseToSectionList(content);
        if (list != null && list.size() > 0) {
            mList.clear();
            mList.addAll(list);
            adapter.notifyDataSetChanged();
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
            args.putLong(JiongTuListFragment.EXTRARS_SECTION_ID, obj.getSectionId());
            args.putString(JiongTuListFragment.EXTRARS_TITLE, obj.getTitle());
            JiongTuListFragment pageFragement = (JiongTuListFragment) Fragment.instantiate(mContext, JiongTuListFragment.class.getName(), args);
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

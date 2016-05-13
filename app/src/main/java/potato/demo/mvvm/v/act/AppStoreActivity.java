package potato.demo.mvvm.v.act;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.mozillaonline.providers.DownloadManager;
import com.mozillaonline.providers.downloads.DownloadService;
import com.mozillaonline.providers.downloads.ui.DownloadListActivity;
import com.potato.library.adapter.PotatoBaseRecyclerViewAdapter;
import com.potato.library.net.RequestWraper;

import java.util.ArrayList;
import java.util.Random;

import potato.demo.R;
import potato.demo.chips.base.BaseDefaultListActivity;
import potato.demo.chips.base.BaseParser;
import potato.demo.chips.common.AppPool;
import potato.demo.databinding.ActivityAppStoreBinding;
import potato.demo.mvvm.m.bean.AppBean;
import potato.demo.mvvm.m.bean.DataSource;
import potato.demo.mvvm.v.adapter.AppListAdapter;

public class AppStoreActivity extends BaseDefaultListActivity {

    private AppListAdapter mAdapter;
    private ActivityAppStoreBinding mBinding;


    private BroadcastReceiver mReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_app_store);
        setSupportActionBar(mBinding.toolbar);
        mAdapter = new AppListAdapter(mContext);

        initListView(mBinding.includeA.llSwipe);

        mSwipeContainer.setLayoutManager(new LinearLayoutManager(mContext));
        mSwipeContainer.showProgress();
        reqRefresh();

        startDownloadService();

        mReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                showDownloadList();
            }
        };

        registerReceiver(mReceiver, new IntentFilter(
                DownloadManager.ACTION_NOTIFICATION_CLICKED));
    }

    private void startDownloadService() {
        Intent intent = new Intent();
        intent.setClass(this, DownloadService.class);
        startService(intent);
    }

    private void showDownloadList() {
        Intent intent = new Intent();
        intent.setClass(this, DownloadListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    @Override
    public PotatoBaseRecyclerViewAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public RequestWraper getRefreshRequest() {
        return null;
    }

    @Override
    public RequestWraper getLoadMoreRequest() {
        return null;
    }

    public BaseParser getParser(String json) {
        return null;
    }

    @Override
    public void reqRefresh() {

        AppPool.FixedPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onRefreshSucc("");
                    }
                });

            }
        });


    }

    @Override
    public void onRefreshSucc(String json) {
        mList = getRandomSublist(5);
        mTotal = 1000;
        mSwipeContainer.showSucc();
        getAdapter().setDataList(mList);
        getAdapter().notifyDataSetChanged();
        if (mList != null && mList.size() != 0 && mList.size() < mTotal) {
            mSwipeContainer.setLoadEnable(true);
        } else {
            mSwipeContainer.setLoadEnable(false);
        }
    }

    @Override
    public void onLoadMoreSucc(String json) {
        mTotal = 1000;
        int lastPosition = mList.size();
        mList.addAll(getRandomSublist(5));
        if (mList != null && mList.size() != 0 && mList.size() < mTotal) {
            mSwipeContainer.setLoadEnable(true);
        } else {
            mSwipeContainer.setLoadEnable(false);
        }
        getAdapter().setDataList(mList);
        getAdapter().notifyItemInserted(lastPosition);
    }

    @Override
    public void reqLoadMore() {
        AppPool.FixedPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onLoadMoreSucc("");
                    }
                });
            }
        });
    }


    private ArrayList<AppBean> getRandomSublist(int amount) {

        ArrayList<AppBean> list = new ArrayList<AppBean>(amount);
        Random random = new Random();
        for (int i = 0; i < amount; i++) {
            list.add(new AppBean("title=" + DataSource.NAMES[i],
                    "content=" + DataSource.NAMES[random.nextInt(DataSource.NAMES.length)],
                    DataSource.IMAGES[random.nextInt(DataSource.NAMES.length)],
                    DataSource.URLS[random.nextInt(DataSource.URLS.length)]
            ));
        }
        return list;
    }


}

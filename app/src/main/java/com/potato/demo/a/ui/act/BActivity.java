package com.potato.demo.a.ui.act;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.potato.chips.base.BaseActivity;
import com.potato.demo.R;
import com.potato.demo.a.data.bean.ABean;
import com.potato.demo.a.ui.adapter.BListAdapter;
import com.potato.demo.a.ui.fragment.Cheeses;
import com.potato.demo.databinding.ActivityBBinding;
import com.potato.library.view.refresh.RecyclerSwipeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BActivity extends BaseActivity {

    ActivityBBinding binding;
    BListAdapter mAdapter;
    List<ABean> mValues = new ArrayList<ABean>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show);
//        ImageView iv = (ImageView)findViewById(R.id.iv_icon);
        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_b);
//        ImageLoaderUtil.displayImage("http://tvfan.kyodo.co.jp/wp-content/uploads/2015/01/15027b37a4104edd85fb5b79a6c9e3ac-344x516.jpg", binding.ivIcon, R.drawable.cheese_1);*/
        mAdapter = new BListAdapter(mContext);
        binding.list.setLayoutManager(new LinearLayoutManager(mContext));
        binding.list.setAdapter(mAdapter);


        binding.swipeContainer.setFooterView(mContext, binding.list, R.layout.listview_footer);

        binding.swipeContainer.setColorSchemeResources(R.color.google_blue,
                R.color.google_green,
                R.color.google_red,
                R.color.google_yellow);

        binding.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mValues = getRandomSublist(Cheeses.sCheeseStrings, 30);
                        mAdapter.setDataList(mValues);
//                        mAdapter.notifyDataSetChanged();
                        binding.swipeContainer.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        binding.swipeContainer.setOnLoadListener(new RecyclerSwipeLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mValues.addAll(getRandomSublist(Cheeses.sCheeseStrings, 30));
                        mAdapter.setDataList(mValues);
//                        mAdapter.notifyDataSetChanged();
                        binding.swipeContainer.setLoading(false);
                    }
                }, 3000);

            }
        });
        binding.swipeContainer.setRefreshing(true);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mValues = getRandomSublist(Cheeses.sCheeseStrings, 30);
                mAdapter.setDataList(mValues);
                mAdapter.notifyDataSetChanged();
                binding.swipeContainer.setRefreshing(false);
            }
        }, 3000);
    }

    private List<ABean> getRandomSublist(String[] array, int amount) {

        ArrayList<ABean> list = new ArrayList<ABean>(amount);
        Random random = new Random();
        for (int i = 0; i < amount; i++) {
            list.add(new ABean("title=" + i, Cheeses.getRandomIcon(), "content=" + array[random.nextInt(array.length)]));
        }
        return list;
    }

}

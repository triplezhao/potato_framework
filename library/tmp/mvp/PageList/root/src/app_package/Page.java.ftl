package ${packageName};

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
<#if applicationPackage??>import ${applicationPackage}.R;
</#if>
import javax.inject.Inject;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
<#if isFragment>
    <#assign baseName="BaseListFragment">
        <#else>
            <#assign baseName="BaseListActivity">
</#if>
public class ${className} extends ${baseName} {

        public static final String TAG = ${className}.class.getSimpleName();

    <#if isFragment>
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.${layoutName}, container, false);
            ButterKnife.bind(this, view);
            mId = getArguments() == null ? "" : getArguments().getString(EXTRA_ID);
            initListView();
            reqRefresh();
            return view;
        }
    <#else>
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.${layoutName});
            ButterKnife.bind(this);
            mId = getIntent().getExtras() == null ? "" : getIntent().getStringExtra(EXTRA_ID);
            initListView();
            reqRefresh();
        }
    </#if>

    @Override
    public PotatoBaseRecyclerViewAdapter getAdapter() {

        return new AAdapter(mContext);
    }

    @Override
    public void reqRefresh() {
        mSwipeContainer.showProgress();
        mPage = 1;
        new ${pageName}ListApi().req(CacheMode.NET_ONLY, "", mId, mPage + "", mPageSize, this);
    }

    @Override
    public void reqLoadMore() {
        new ${pageName}DetailApi().req(CacheMode.NET_ONLY, mId, this);
    }


<#if !isFragment>
    @OnClick({ R.id.iv_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
             onBackPressed();
        break;
        }
    }
</#if>

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RefreshEvent event) {
        reqRefresh();
    }

	public static class AAdapter extends PotatoBaseRecyclerViewAdapter<AAdapter.VH> {

        public AAdapter(Context context) {
            super(context);
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(
                    R.layout.${itemLayoutName},
                    parent,
                    false);
            VH holder = new VH(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(VH vh, int position) {
            final ${pageName}Bean bean = (${pageName}Bean) mData.get(position);

            vh.tv_title.setText(bean.getTitle());
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    PageCtrl.start2${pageName}DetailActivity(context, bean);
                }
            });
            ImageLoaderUtil.displayImage(bean.getBigCover(), vh.iv_pic, R.drawable.def_gray_big);

        }


        static class VH extends RecyclerView.ViewHolder {

            @Bind(R.id.iv_pic) ImageView iv_pic;
            @Bind(R.id.tv_title) TextView tv_title;

            public VH(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }


}

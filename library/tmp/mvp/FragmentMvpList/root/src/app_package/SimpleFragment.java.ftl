
package ${packageName};

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
<#if applicationPackage??>import ${applicationPackage}.R;</#if>
import javax.inject.Inject;

public class ${mvpClass}Fragment extends BaseDefaultListFragment implements ${mvpClass}.V {

    public static final String TAG = ${mvpClass}Fragment.class.getSimpleName();
    public static final String EXTRA_ID = "EXTRA_ID";

    @Inject ${mvpClass}Presenter presenter;

    public static ${mvpClass}Fragment instance(Context context, String cusid) {
        Bundle args = new Bundle();
        args.putString(EXTRA_ID, cusid);
        ${mvpClass}Fragment pageFragement = (${mvpClass}Fragment) Fragment.instantiate(context, ${mvpClass}Fragment.class.getName(), args);
        return pageFragement;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.${layoutFragmentName}, container, false);

        ButterKnife.bind(this, view);

        Dagger${mvpClass}_C.builder().module(new ${mvpClass}.Module(this)).build().inject(this);
        mId = getArguments() == null ? "" : getArguments().getString(EXTRA_ID);
        initListView();

        reqRefresh();

        return view;
    }


    @Override
    public PotatoBaseRecyclerViewAdapter getAdapter() {
        return mAdapter = new AAdapter(mContext);
    }

    @Override
    public void reqRefresh() {
        mSwipeContainer.showProgress();
        mPage = 1;
        presenter.reqRefresh("", "1", pageSize);
    }

    @Override
    public void reqLoadMore() {
        presenter.reqLoadMore("", mPage + 1 + "", pageSize);
    }


	public static class AAdapter extends PotatoBaseRecyclerViewAdapter<AAdapter.VH> {

        public AAdapter(Context context) {
            super(context);
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(
                    R.layout.${itemlayout},
                    parent,
                    false);
            VH holder = new VH(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(VH vh, int position) {
            final ${mvpClass}Bean bean = (${mvpClass}Bean) mData.get(position);

            vh.tv_title.setText(bean.getTitle());
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    PageCtrl.start${mvpClass}DetailFragment(context, bean);
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

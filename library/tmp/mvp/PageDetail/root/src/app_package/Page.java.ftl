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
    <#assign baseName="BaseDetailFragment">
        <#else>
            <#assign baseName="BaseDetailActivity">
</#if>

public class ${className} extends ${baseName} {

    public static final String TAG = ${className}.class.getSimpleName();
    ${pageName}Bean m${pageName}Bean;

<#if isFragment>
    public static ${className} instance(Context context, String id) {
        Bundle args = new Bundle();
        args.putString(EXTRA_ID, id);
        ${className} pageFragement = (${className}) Fragment.instantiate(context, ${className}.class.getName(), args);
        return pageFragement;
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.${layoutName}, container, false);
        ButterKnife.bind(this, view);
        initView();
        mId = getArguments() == null ? "" : getArguments().getString(EXTRA_ID);
        reqDetail();
        return view;
    }
        <#else>
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.${layoutName});
        ButterKnife.bind(this);
        initView();
        mId = getIntent() == null ? "" : getIntent().getStringExtra(EXTRA_ID);
        reqDetail();
    }
</#if>
      @Override
       public void render(){
                tvRealname.setText(bean.getClass());
                tvMobile.setText(bean.getClass());
       }

       @Override
       public void reqDetail() {
            mSwipeContainer.showProgress();
            new ${pageName}DetailApi().req(CacheMode.NET_ONLY, mId, this);
       }

        @Subscribe(threadMode = ThreadMode.MAIN)
        public void onMessageEvent(${pageName}SaveEvent event) {
            reqDetail();
        }
    <#if !isFragment>
             @OnClick({R.id.empty_view, R.id.iv_back})
             public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.empty_view:
                             reqDetail();
                        break;
                        case R.id.iv_back:
                             onBackPressed();
                        break;
                     }
             }
    </#if>
}

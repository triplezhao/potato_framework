
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
    <#assign baseName="BaseEditFragment">
        <#else>
            <#assign baseName="BaseEditActivity">
</#if>


public class ${className} extends ${baseName} {

    public static final String TAG = ${className}.class.getSimpleName();
    public static final String EXTRA_IS_EDIT = "EXTRA_IS_EDIT";
    public boolean           isEdit; //请求带过来的店家信息
    public ${pageName}ParamBean paramBean = new ${pageName}ParamBean();

<#if isFragment>
    public static ${pageName} instance(Context context, String id) {
        Bundle args = new Bundle();
        args.putString(EXTRA_ID, id);
        ${className} pageFragement = (${pageName}) Fragment.instantiate(context, ${className}.class.getName(), args);
        return pageFragement;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.${layoutName}, container, false);
        ButterKnife.bind(this, view);
        initView();
        isEdit = getIntent() == null ? false : getIntent().getBooleanExtra(EXTRA_IS_EDIT, false);
        if (isEdit) {
             tvTitle.setText("店家编辑");
        } else {
        }
        Object object = getIntent() == null ? null : getIntent().getSerializableExtra(EXTRA_OBJ);
        if (object != null) {
             paramBean = (${pageName}ParamBean) object;
             renderOld();
         }
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
        isEdit = getIntent() == null ? false : getIntent().getBooleanExtra(EXTRA_IS_EDIT, false);
        if (isEdit) {
            tvTitle.setText("店家编辑");
        } else {
            //如果是新建，则开发人员默认选中自己
        }
        Object object = getIntent() == null ? null : getIntent().getSerializableExtra(EXTRA_OBJ);
            if (object != null) {
                paramBean = (${pageName}ParamBean) object;
                renderOld();
             }
       }
    }
</#if>

    @Override
    public void renderOld() {
            //基本信息
            etRealname.setText(paramBean.realname);
            etMobile.setText(paramBean.mobile);
            etViceMobile.setText(paramBean.vice_mobile);
            etCusno.setText(paramBean.cusno);
        }

    @Override
    public boolean checkUIParams() {
            if (TextUtils.isEmpty(etChannelname
            .getText()
            .toString())) {
                UIUtils.toast(mContext, "请填写店家姓名");
                return false;
            }
            return false;
    }

    @Override
    public void reqCreate() {
        showProgressDialog("正在保存");
        new ${pageName}CreateApi().req(CacheMode.NET_ONLY, paramBean, this);
    }

    @Override
    public void onReqCreateSucc(BaseResultEntity entity) {
        dissmissProgressDialog();
        UIUtils.toast(mContext, "保存成功");
        EventBus
        .getDefault()
        .post(new YuyueSaveEvent(0));
        finish();
    }

    @Override
    public void onReqCreateFail(String msg) {
        dissmissProgressDialog();
        UIUtils.toast(mContext, msg);
    }

    @Override
    public void reqEdit() {

    }

    @Override
    public void onReqEditSucc(BaseResultEntity entity) {

    }

    @Override
    public void onReqEditFail(String msg) {

    }
    <#if !isFragment>
     @OnClick({R.id.iv_back, R.id.bt_save})
     public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_back:
                     finish();
                 break;
                case R.id.bt_save:
                    if (checkUIParams()) {
                        reqCreate();
                }
             }
     }
    </#if>
}

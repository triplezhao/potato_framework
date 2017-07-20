<#assign text>${json}</#assign>
<#assign json=text?eval />

package ${packageName};

//import


import android.content.ContentValues;

public class TempFile${objName} {
/**
0 为分割线
1 文本框
2 输入框
3 选择框
4 Radio选择
5 开关
*/

//keypslist
<#list json as item>
    <#if item.type == "1">
        @Bind(R.id.${item.vid}) TextView tv_${item.vid};
        <#elseif item.type == "2">
            @Bind(R.id.${item.vid}) EditText et_${item.vid};
            <#elseif item.type == "3">
                @Bind(R.id.${item.vid}) TextView tv_${item.vid};
                <#else>
                    </#if>
</#list>


public void render(${objName}DetailBean bean) {

<#list json as item>
    <#if item.type == "1">
        tv_${item.vid}.setText(bean.${item.vid});
        <#elseif item.type == "2">
            et_${item.vid}.setText(bean.${item.vid});
            <#elseif item.type == "3">
                tv_${item.vid}.setText(bean.${item.vid});
                <#else>
                    </#if>
</#list>

}

public void renderOld(${objName}ParamBean bean) {

<#list json as item>
    <#if item.type == "1">
        tv_${item.vid}.setText(bean.${item.vid});
        <#elseif item.type == "2">
            et_${item.vid}.setText(bean.${item.vid});
            <#elseif item.type == "3">
                tv_${item.vid}.setText(bean.${item.vid});
                <#else>
                    </#if>
</#list>
}


public static ${objName}ParamBean copyFrom(Y${objName}DetailBean bean) {
    ${objName}ParamBean paramBean = new ${objName}ParamBean();
    <#list json as item>
        <#if item.type != "0">
            paramBean.${item.vid}=bean.${item.vid};
        </#if>
    </#list>
}

}
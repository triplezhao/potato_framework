<?xml version="1.0"?>
<globals>
    <global id="manifestOut" value="${manifestDir}"/>
    <global id="srcOut" value="${srcDir}/${slashedPackageName(packageName)}"/>
    <global id="resOut" value="${resDir}"/>
    <#if isFragment>
            <global id="className" value="${pageName}Fragment"/>
             <global id="layoutName" value="fragment_${classToResource(pageName)}"/>
        <#else>
            <global id="className" value="${pageName}Activity"/>
            <global id="layoutName" value="activity_${classToResource(pageName)}"/>
    </#if>

</globals>

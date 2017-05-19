<?xml version="1.0"?>
<globals>
    <global id="manifestOut" value="${manifestDir}"/>
    <global id="appCompat" value="${(minApiLevel lt 14)?string('1','')}"/>
    <!-- e.g. getSupportActionBar vs. getActionBar -->
    <global id="Support" value="${(minApiLevel lt 14)?string('Support','')}"/>
    <global id="srcOut" value="${srcDir}/${slashedPackageName(packageName)}"/>
    <global id="resOut" value="${resDir}"/>
    <global id="app_name" value="Aiyouyun"/>
    <#if isFragment>
            <global id="pageName" value="${mvpClass}Fragment"/>
             <global id="layoutName" value="fragment_${classToResource(mvpClass)}"/>
        <#else>
            <global id="pageName" value="${mvpClass}Activity"/>
            <global id="layoutName" value="activity_${classToResource(mvpClass)}"/>
    </#if>

    <global id="presenterName" value="${(mvpClass)}Presenter"/>
</globals>

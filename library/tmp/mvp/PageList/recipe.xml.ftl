<?xml version="1.0"?>
<recipe>

    <merge from="AndroidManifest.xml.ftl"
           to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml"/>
    <!--Page-->
    <instantiate from="src/app_package/Page.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/${className}.java"/>
    <!--  Layout -->
    <instantiate from="res/layout/layout.xml.ftl"
                 to="${escapeXmlAttribute(resOut)}/layout/${layoutName}.xml"/>
    <!-- item layout -->
    <instantiate from="res/layout/item_layout.xml.ftl"
                 to="${escapeXmlAttribute(resOut)}/layout/${itemLayoutName}.xml"/>


    <open file="${escapeXmlAttribute(srcOut)}/${className}.java"/>


</recipe>

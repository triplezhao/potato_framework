<?xml version="1.0"?>
<recipe>

    <merge from="AndroidManifest.xml.ftl"
           to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml"/>

    <!-- add page file-->
    <instantiate from="src/app_package/Page.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/${className}.java" />
    <!-- layout(s) to add -->
    <instantiate from="res/layout/layout.xml.ftl"
              to="${escapeXmlAttribute(resOut)}/layout/${layoutName}.xml" />

    <open file="${escapeXmlAttribute(srcOut)}/${pageName}.java" />

</recipe>

<?xml version="1.0"?>
<recipe>

    <merge from="AndroidManifest.xml.ftl"
             to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />

    <!-- add mvp file-->
    <instantiate from="src/app_package/mvp.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/${mvpClass}.java" />
    <!-- add page file-->
    <instantiate from="src/app_package/page.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/${pageName}.java" />
    <!-- add presenter file-->
    <instantiate from="src/app_package/presenter.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/${presenterName}.java" />

    <!-- Decide what kind of layout(s) to add -->
    <instantiate from="res/layout/layout.xml.ftl"
              to="${escapeXmlAttribute(resOut)}/layout/${layoutName}.xml" />

    <open file="${escapeXmlAttribute(srcOut)}/${mvpClass}.java" />
    <open file="${escapeXmlAttribute(srcOut)}/${pageName}.java" />
	<open file="${escapeXmlAttribute(srcOut)}/${presenterName}.java" />

</recipe>

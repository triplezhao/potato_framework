<?xml version="1.0"?>
<recipe>

    <!-- Decide which activity code to add -->
    <instantiate from="src/app_package/views.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/TempFile${objName}.txt" />

    <instantiate from="res/layout/layout_detail.xml.ftl"
                 to="${escapeXmlAttribute(resOut)}/layout/${classToResource(objName)}_detail.xml" />
    <instantiate from="res/layout/layout_edit.xml.ftl"
                 to="${escapeXmlAttribute(resOut)}/layout/${classToResource(objName)}_edit.xml" />

    <open file="${escapeXmlAttribute(srcOut)}/TempFile${objName}.txt" />
    <open file="${escapeXmlAttribute(resOut)}/layout/${classToResource(objName)}_detail.xml" />
    <open file="${escapeXmlAttribute(resOut)}/layout/${classToResource(objName)}_edit.xml" />

</recipe>

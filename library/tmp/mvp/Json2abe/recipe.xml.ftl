<?xml version="1.0"?>
<recipe>

     <!-- Decide which activity code to add -->
    <instantiate from="src/app_package/api.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/${objName}Api.java" />

    <instantiate from="src/app_package/bean.java.ftl"
                       to="${escapeXmlAttribute(srcOut)}/${objName}Bean.java" />

    <instantiate from="src/app_package/entity.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/${objName}Entity.java" />

	<open file="${escapeXmlAttribute(srcOut)}/${objName}Api.java" />
    <open file="${escapeXmlAttribute(srcOut)}/${objName}Bean.java" />
    <open file="${escapeXmlAttribute(srcOut)}/${objName}Entity.java" />

   

</recipe>

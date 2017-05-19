<?xml version="1.0"?>
<recipe>

    <merge from="AndroidManifest.xml.ftl"
             to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />

    <!-- Fragment Layout -->
    <instantiate from="res/layout/fragment_simple.xml.ftl"
              to="${escapeXmlAttribute(resOut)}/layout/${layoutFragmentName}.xml" />
	<!-- item layout -->		  
	<instantiate from="res/layout/item_simple.xml.ftl"
              to="${escapeXmlAttribute(resOut)}/layout/item_${classToResource(mvpClass)}.xml" />

    <!-- Decide which fragment code to add -->
    <instantiate from="src/app_package/SimpleFragment.java.ftl"
                       to="${escapeXmlAttribute(srcOut)}/${fragmentClass}.java" />

    <instantiate from="src/app_package/mvp.java.ftl"
                       to="${escapeXmlAttribute(srcOut)}/${mvpClass}.java" />
					   
	<instantiate from="src/app_package/presenter.java.ftl"
                       to="${escapeXmlAttribute(srcOut)}/${presenterClass}.java" />

	<open file="${escapeXmlAttribute(srcOut)}/${fragmentClass}.java" />
    <open file="${escapeXmlAttribute(srcOut)}/${mvpClass}.java" />
	<open file="${escapeXmlAttribute(srcOut)}/${presenterClass}.java" />
   

</recipe>

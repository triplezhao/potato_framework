<#assign text>${json}</#assign>
<#assign jsonobj=text?eval />
<#assign keys = jsonobj?keys> 

package ${packageName};

//import
import java.util.Date;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;
import android.database.Cursor;


import android.content.ContentValues;

public class ${objName}Bean extends BaseBean implements Serializable{

	//keypslist
<#list keys as key>
	public String ${key};
</#list>


}
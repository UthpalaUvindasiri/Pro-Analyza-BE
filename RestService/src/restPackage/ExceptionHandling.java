package restPackage;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExceptionHandling {

JSONObject retObj;
	
	public JSONObject getException(int ExceptionsValue) throws JSONException{
		retObj = new JSONObject();
		
		JSONObject jo = new JSONObject();
	    Collection<JSONObject> items = new ArrayList<JSONObject>();
		
	    JSONObject item1 = new JSONObject();
	    item1.put("name", "Exception Handling Value");
	    item1.put("value", ExceptionsValue);
	    items.add(item1);

	    retObj.put("value", new JSONArray(items));
		//System.out.println(retObj);
		return retObj;
	}
	
	
	public JSONArray getException_Method(int ExceptionsValue) throws JSONException{
		retObj = new JSONObject();
		
		JSONObject jo = new JSONObject();
	    Collection<JSONObject> items = new ArrayList<JSONObject>();
		
	    JSONObject item1 = new JSONObject();
	    item1.put("name", "Exception Handling Value");
	    item1.put("value", ExceptionsValue);
	    items.add(item1);

	    retObj.put("value", new JSONArray(items));
		//System.out.println(retObj);
		return (new JSONArray(items));
	}
	
}

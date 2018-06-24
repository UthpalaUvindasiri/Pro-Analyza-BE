package restPackage;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CyclomaticComplexity {

	int CCValue;

	public CyclomaticComplexity() {
		
		
	}
	
	public JSONObject getCC(int ifstaements, int SwitchVale, int ForValue, int WhileValue) throws JSONException{
		JSONObject retObj = new JSONObject();
		
		JSONObject jo = new JSONObject();
	    Collection<JSONObject> items = new ArrayList<JSONObject>();
		
		CCValue = ifstaements + SwitchVale + ForValue + WhileValue + 1;
		
		JSONObject item1 = new JSONObject();
	    item1.put("name", "Cyclomatic Complexity Value");
	    item1.put("value", CCValue);
	    items.add(item1);

	    retObj.put("value", new JSONArray(items));
		//System.out.println(retObj);
		return retObj;
	}
	
	public JSONArray getCC_Method(int ifstaements, int SwitchVale, int ForValue, int WhileValue) throws JSONException{
		JSONObject retObj = new JSONObject();
		
		JSONObject jo = new JSONObject();
	    Collection<JSONObject> items = new ArrayList<JSONObject>();
		
		CCValue = ifstaements + SwitchVale + ForValue + WhileValue + 1;
		
		JSONObject item1 = new JSONObject();
	    item1.put("name", "Cyclomatic Complexity Value");
	    item1.put("value", CCValue);
	    items.add(item1);

	    retObj.put("value", new JSONArray(items));
		//System.out.println(retObj);
		return new JSONArray(items);
	}
	
}

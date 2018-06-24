package restPackage;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CWCM {

	JSONObject retObj;
	int CWCMValue;
	
	public JSONObject getCWCM(int ifstaements, int SwitchVale, int ForValue, int WhileValue) throws JSONException{
		retObj = new JSONObject();
		
		JSONObject jo = new JSONObject();
	    Collection<JSONObject> items = new ArrayList<JSONObject>();
		
		CWCMValue = (ifstaements * 2) + (SwitchVale * 3) + (ForValue * 3) + (WhileValue *3);
		
		JSONObject item1 = new JSONObject();
	    item1.put("name", "CWCM Value");
	    item1.put("value", CWCMValue);
	    items.add(item1);

	    retObj.put("value", new JSONArray(items));
		//System.out.println(retObj);
		return retObj;
	}
	
	public JSONArray getCWCM_Method(int ifstaements, int SwitchVale, int ForValue, int WhileValue) throws JSONException{
		retObj = new JSONObject();
		
		JSONObject jo = new JSONObject();
	    Collection<JSONObject> items = new ArrayList<JSONObject>();
		
		CWCMValue = (ifstaements * 2) + (SwitchVale * 3) + (ForValue * 3) + (WhileValue *3);
		
		JSONObject item1 = new JSONObject();
	    item1.put("name", "CWCM Value");
	    item1.put("value", CWCMValue);
	    items.add(item1);

	    retObj.put("value", item1);
		//System.out.println(retObj);
		return new JSONArray(items);
	}
	
}

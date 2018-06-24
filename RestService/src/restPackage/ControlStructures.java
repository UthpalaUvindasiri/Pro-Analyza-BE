package restPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jdt.core.dom.IfStatement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ControlStructures {
	
String part, ifcondition;


	
	public JSONObject Calculate(List<IfStatement> ifstaements, int SwitchVale, int ForValue, int WhileValue) throws JSONException{

		
		JSONObject jo = new JSONObject();
	    Collection<JSONObject> items = new ArrayList<JSONObject>();
	    
	    
	    
		
		JSONObject retObj = new JSONObject();
		for(IfStatement i : ifstaements){
			ifcondition = i.toString();
			part = ifcondition.substring(0, ifcondition.indexOf('{'));
			
			if(part.contains("&&")){
				System.out.println("&& is present");
			}
			if(part.contains("||")){
				System.out.println("|| is present");

			}
		}
		
		JSONObject item1 = new JSONObject();
	    item1.put("name", "If Count");
	    item1.put("value", ifstaements.size());
	    items.add(item1);
	    
	    JSONObject item2 = new JSONObject();
	    item2.put("name", "Switch Count");
	    item2.put("value", SwitchVale);
	    items.add(item2);
	    
	    JSONObject item3 = new JSONObject();
	    item3.put("name", "For Count");
	    item3.put("value", ForValue);
	    items.add(item3);
	    
	    JSONObject item4 = new JSONObject();
	    item4.put("name", "While Count");
	    item4.put("value", WhileValue);
	    items.add(item4);
		

		
		retObj.put("value", new JSONArray(items));
		//System.out.println(retObj);
		return retObj;
		
		
	}
	
	
public JSONArray Calculate_Method(List<IfStatement> ifstaements, int SwitchVale, int ForValue, int WhileValue) throws JSONException{

		JSONObject jo = new JSONObject();
	    Collection<JSONObject> items = new ArrayList<JSONObject>();
	    
		JSONObject retObj = new JSONObject();
		for(IfStatement i : ifstaements){
			ifcondition = i.toString();
			part = ifcondition.substring(0, ifcondition.indexOf('{'));
			
			if(part.contains("&&")){
				System.out.println("&& is present");
			}
			if(part.contains("||")){
				System.out.println("|| is present");

			}
		}
		
		JSONObject item1 = new JSONObject();
	    item1.put("name", "If Count");
	    item1.put("value", ifstaements.size());
	    items.add(item1);
	    
	    JSONObject item2 = new JSONObject();
	    item2.put("name", "Switch Count");
	    item2.put("value", SwitchVale);
	    items.add(item2);
	    
	    JSONObject item3 = new JSONObject();
	    item3.put("name", "For Count");
	    item3.put("value", ForValue);
	    items.add(item3);
	    
	    JSONObject item4 = new JSONObject();
	    item4.put("name", "While Count");
	    item4.put("value", WhileValue);
	    items.add(item4);
		

		
		retObj.put("value", new JSONArray(items));
//		System.out.println("Control = " +ForValue);
		return new JSONArray(items);
		
		
	}
	
}

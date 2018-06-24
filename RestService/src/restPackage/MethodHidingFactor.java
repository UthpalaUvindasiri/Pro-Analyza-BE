package restPackage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MethodHidingFactor {
	
	double visibleMethods = 0;
	double invisibleMethods = 0;
	double totalMethods = 0;
	double mhf = 0;
	JSONArray items = new JSONArray();
	public MethodHidingFactor() {
		
	}
	
	public JSONObject getMHF(List<MethodDeclaration> methods) throws JSONException{
		
		JSONObject retObj = new JSONObject();
		
		totalMethods = methods.size();
		System.out.println("mhf");
		for(MethodDeclaration m : methods){
			
			if(m.modifiers().toString().contains("public")){
				visibleMethods++;
			}
			else if(m.modifiers().toString().contains("private")){
				invisibleMethods++;
			}
			
		}
		totalMethods= visibleMethods + invisibleMethods;
 		
		
		mhf = invisibleMethods / totalMethods;
		mhf = Math.round(mhf * 100.0)/100.0;
		JSONObject item1 = new JSONObject();
	    item1.put("name", "Number of Visible Methods");
	    item1.put("value", visibleMethods);
	    items.put(item1);
	    
	    JSONObject item2 = new JSONObject();
	    item2.put("name", "Number of Invisible Methods");
	    item2.put("value", invisibleMethods);
	    items.put(item2);
	    
	    JSONObject item3 = new JSONObject();
	    item3.put("name", "Method Hiding Factor");
	    item3.put("value", mhf);
	    items.put(item3);
	    
	    /*retObj.put("name", "Method Hiding Factor");
	    retObj.put("value", items);*/
	    
		System.out.println(retObj);
		System.out.println("mhf : end");
		
		JSONObject ret = new JSONObject();
		ret.put("name", "Method Hiding Factor");
		ret.put("value", items);
		
		return ret;
	}
	
	
	
	

}

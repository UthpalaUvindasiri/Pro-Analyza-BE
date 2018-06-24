package restPackage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AccessModifiers {

	
	
	JSONObject jo = new JSONObject();
    Collection<JSONObject> items = new ArrayList<JSONObject>();
	
	JSONObject retObj ;
public JSONObject Calculate(List<VariableDeclarationFragment> accessmodifiers) throws JSONException{
		
		int valuePublic = 0;
		int valueProtected =0;
		int valuePrivate = 0;
		
		//System.out.println("Chandeesha am = " + accessmodifiers);
		
		retObj = new JSONObject();
		
		for(VariableDeclarationFragment am : accessmodifiers){
			
			//System.out.println("Chandeesha name = " + am.getName());
			
			if (am.getParent() instanceof VariableDeclarationExpression){
				//System.out.println(((VariableDeclarationExpression)am.getParent()).getModifiers()+" chandeesha");
				if(((VariableDeclarationExpression)am.getParent()).getModifiers() == 0){
					valuePublic++;
				}
				else if(((VariableDeclarationExpression)am.getParent()).getModifiers() == 2){
					valuePrivate++;
				}
				else if(((VariableDeclarationExpression)am.getParent()).getModifiers() == 4){
					valueProtected++;
				}
			}
			else if (am.getParent() instanceof VariableDeclarationStatement){
				//System.out.println(((VariableDeclarationStatement)am.getParent()).getModifiers()+" chandeesha");
				if(((VariableDeclarationStatement)am.getParent()).getModifiers() == 0){
					valuePublic++;
				}
				else if(((VariableDeclarationStatement)am.getParent()).getModifiers() == 2){
					valuePrivate++;
				}
				else if(((VariableDeclarationStatement)am.getParent()).getModifiers() == 4){
					valueProtected++;
				}
			}
		}
		

		
		/*for(String am : accessmodifiers){
			if(am.contains("public")){
				valuePublic ++;
			}
			else if(am.contains("protected") ){
				valueProtected++;
			}
			else if(am.contains("private")){
				valuePrivate++;
			}
		}*/
		
		
		
		JSONObject item1 = new JSONObject();
	    item1.put("name", "Number of Public Access Modifieres");
	    item1.put("value", valuePublic);
	    items.add(item1);
		
	    JSONObject item2 = new JSONObject();
	    item2.put("name", "Number of Protected Access Modifieres");
	    item2.put("value", valueProtected);
	    items.add(item2);
	    
	    JSONObject item3 = new JSONObject();
	    item3.put("name", "Number of Private Access Modifieres");
	    item3.put("value", valuePrivate);
	    items.add(item3);

	    retObj.put("value", new JSONArray(items));
		//System.out.println(retObj);
		return retObj;

	}
}

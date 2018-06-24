package restPackage;

import java.util.List;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.json.JSONException;
import org.json.JSONObject;

public class MethodComplexity {

	int Value;
	JSONObject retObj;
	
	
	public JSONObject getMethodComplexity(List<MethodDeclaration> methods) throws JSONException{
		retObj = new JSONObject();
		for(MethodDeclaration m :methods ){
			retObj.put("MethodName", m.getName());
			retObj.put("ReturnType", m.getReturnType2());
			retObj.put("MethodParameters", m.parameters().toString());
		}
			return retObj;
	}
	
}

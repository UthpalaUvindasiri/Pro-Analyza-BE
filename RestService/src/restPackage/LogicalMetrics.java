package restPackage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.internal.compiler.ast.IfStatement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LogicalMetrics {
	
	double binarydecisions = 0;
	double totaldecisions = 0;

	public LogicalMetrics() {
		// TODO Auto-generated constructor stub
	}
	
	public JSONObject getLogicalMetrics(List<org.eclipse.jdt.core.dom.IfStatement> ifvariables, List<SwitchStatement> switchstatements) throws JSONException{
		
		JSONObject retObj = new JSONObject();
		
		for(org.eclipse.jdt.core.dom.IfStatement f : ifvariables){
			System.out.println(f);
				if(! f.toString().contains("else if") && f.toString().contains("else")){
					binarydecisions++;
				}
			totaldecisions++;
		}
		
		for(SwitchStatement s:switchstatements){
			Scanner scanner = new Scanner(s.toString());
			String line;
			int cases = 0;
				while(scanner.hasNextLine()){
					line = scanner.nextLine();
					
						if(line.contains("case")){
							cases++;
						}
				}
				
				if(cases == 1){
					binarydecisions++;
				}
				
				totaldecisions++;
		}
		totaldecisions = totaldecisions + binarydecisions;
		System.out.println(totaldecisions);
		System.out.println(binarydecisions);
		
		JSONObject item1 = new JSONObject();
		item1.put("name", "Number of binary decisions");
		item1.put("value", binarydecisions);
		
		JSONObject item2 = new JSONObject();
		item2.put("name", "Number of total decisions");
		item2.put("value", totaldecisions);
		
		JSONObject item3 = new JSONObject();
		item3.put("name", "Ratio between binary decisions and total decisions");
		item3.put("value", binarydecisions / totaldecisions);
		
		JSONArray itemarray = new JSONArray();
		itemarray.put(item1);
		itemarray.put(item2);
		itemarray.put(item3);
		
		retObj.put("name", "Gilb's logical metrics");
		retObj.put("value", itemarray);
		
		System.out.println(retObj);
		return retObj;
		
	}

}

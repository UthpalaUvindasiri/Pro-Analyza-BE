package restPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CFS {
	
	public static void main(String[] args) throws IOException, JSONException{
		
		getCFSValue_Method("D:/4thyearBackEnd/temp_files/Decimal.java");
		
	}
	
	
	
	public static JSONObject getCFSValue(String filePath, String fName) throws IOException, JSONException{
		
		StringBuilder str = new StringBuilder();
		StringBuilder str2 = new StringBuilder();

		int inputValue = 0;
		int OutputValue = 0;
		int CFSValue = 0;
		int Weight = 1;
		
		JSONObject retObj = new JSONObject();
		
		JSONObject jo = new JSONObject();
	    Collection<JSONObject> items = new ArrayList<JSONObject>();
		
			File file = new File("D:/4thyearBackEnd/temp_files/"+fName);
			
			FileReader fileReader = new FileReader(file);
				if(file.getName().equals(fName)){
					
				
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			//StringBuffer stringBuffer = new StringBuffer();
			String line;
			String tempScannerName;
			String scnnerObjName= "";
			String[] operands;
			String operand="";
			while ((line = bufferedReader.readLine()) != null) {
				
				
				if( line.contains("new Scanner(System.in)")){
					
					tempScannerName = line.substring(line.indexOf("Scanner"), line.indexOf("="));
					scnnerObjName = tempScannerName.substring(8, tempScannerName.length());
					
				}
				/*if (line.contains(scnnerObjName) ||  line.contains("readLine()") ){
					inputValue++;
				}*/ //changed IF
				if (scnnerObjName!= "" &&  line.contains("readLine()") || line.contains(".nextDouble();") || line.contains("next")){
					inputValue++;
				}
				if(line.contains("System.out.println")){
					//System.out.println(line);
					OutputValue++;
					//System.out.println(OutputValue);
				}
				

			}
			
			CFSValue = (inputValue + OutputValue) * Weight;
			
			//System.out.println(CFSValue);
			
			JSONObject item1 = new JSONObject();
		    item1.put("name", "CFS Value");
		    item1.put("value", CFSValue);
		    items.add(item1);
				}

		    retObj.put("value", new JSONArray(items));
			System.out.println(retObj);
			return retObj;
			
	}
	
    static JSONArray itemsCFS= new JSONArray();
	
public static JSONArray getCFSValue_root(String filePath) throws IOException, JSONException{
		
		StringBuilder str = new StringBuilder();
		StringBuilder str2 = new StringBuilder();

		int inputValue = 0;
		int OutputValue = 0;
		int CFSValue = 0;
		int Weight = 1;
		
		JSONObject retObj = new JSONObject();
		
		JSONObject jo = new JSONObject();
	    Collection<JSONObject> items = new ArrayList<JSONObject>();
		
			File file = new File(filePath);
			
			FileReader fileReader = new FileReader(file);
		
					
				
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			//StringBuffer stringBuffer = new StringBuffer();
			String line;
			String tempScannerName;
			String scnnerObjName= "";
			String[] operands;
			String operand="";
			while ((line = bufferedReader.readLine()) != null) {
				
				
				if( line.contains("new Scanner(System.in)")){
					
					tempScannerName = line.substring(line.indexOf("Scanner"), line.indexOf("="));
					scnnerObjName = tempScannerName.substring(8, tempScannerName.length());
					
				}
				//if (line.contains(scnnerObjName) ||  line.contains("readLine()") ){
				else if (scnnerObjName!= "" &&  line.contains("readLine()") || line.contains(".nextDouble();") || line.contains("next")){
					inputValue++;
				}
				if(line.contains("System.out.println")){
					//System.out.println(line);
					OutputValue++;
					//System.out.println(OutputValue);
				}
				

			}
			
			CFSValue = (inputValue + OutputValue) * Weight;
			
			//System.out.println(CFSValue);
			
			JSONObject item1 = new JSONObject();
		    item1.put("name", "CFS Value");
		    item1.put("value", CFSValue);
		    items.add(item1);
				

		    retObj.put("value", new JSONArray(items));
			System.out.println(retObj);
			
			
			if(itemsCFS.length() ==0){
				JSONObject s = new JSONObject();
				try {
					//s = (JSONObject) ((JSONArray) items).get(0);
					itemsCFS.put(items);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			else{
				
				 for (int i = 0, size = itemsCFS.length(); i < size; i++)
				    {
					 try {
						JSONArray objectInArray =  itemsCFS.getJSONArray(i);
						for (int j = 0; j < objectInArray.length(); j++) {
							JSONObject object = objectInArray.getJSONObject(j);
							for(JSONObject irr : items){
								
								if(object.getString("name").equals(irr.getString("name"))){
									double x  = object.getInt("value") + irr.getInt("value");
									object.put("value", x);
								
								}
						}
						}
					
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
				    }	   
			}
			
			JSONArray newArray = new JSONArray();
			try {
				newArray = itemsCFS.getJSONArray(0);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return newArray;
			
			
			
	}
	
	
public static JSONObject getCFSValue_Method(String filePath) throws IOException, JSONException{
		
		StringBuilder str = new StringBuilder();
		StringBuilder str2 = new StringBuilder();

		int inputValue = 0;
		int OutputValue = 0;
		int CFSValue = 0;
		int Weight = 1;
		
		JSONObject retObj = new JSONObject();
		
		JSONObject jo = new JSONObject();
	    Collection<JSONObject> items = new ArrayList<JSONObject>();
		
			File file = new File(filePath);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			//StringBuffer stringBuffer = new StringBuffer();
			String line;
			String tempScannerName;
			String scnnerObjName= "";
			String[] operands;
			String operand="";
			while ((line = bufferedReader.readLine()) != null) {
				
				
				if( line.contains("new Scanner(System.in)")){
					
					tempScannerName = line.substring(line.indexOf("Scanner"), line.indexOf("="));
					scnnerObjName = tempScannerName.substring(8, tempScannerName.length());
					
				}
				else if (scnnerObjName!= "" &&  line.contains("readLine()") || line.contains(".nextDouble();") || line.contains("next")){
					inputValue++;
				}
				else if(line.contains("System.out.println")){
					//System.out.println(line);
					OutputValue++;
					//System.out.println(OutputValue);
				}
				

			}
			
			CFSValue = (inputValue + OutputValue) * Weight;
			
			//System.out.println(CFSValue);
			
			JSONObject item1 = new JSONObject();
		    item1.put("name", "CFS Value");
		    item1.put("value", CFSValue);
		    items.add(item1);

		    retObj.put("value", item1);
			System.out.println(retObj);
			return item1;
			
	}
	
}

package restPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import org.apache.commons.collections.functors.IfClosure;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Efficiency {
	
	public static String filePath, file;
	public static int ifcount;
	public static int whilecount;
	public static int forcount;
	public static int switchcount;
	public static JSONArray devNames = new JSONArray();
	public static JSONArray clone;

	
	public static String devname;
	
	public static int CCValue;
	public static int CFSValue;
	public static int CWCMValue;
	



	public Efficiency() {
		ifcount = 0;
		whilecount = 0;
		forcount = 0;
		CCValue = 0;

	}

	public static void main(String[] args) throws IOException, JSONException {
		ParseFilesInDir();

	}
	
	public static void ParseFilesInDir() throws IOException, JSONException{
		File dirs = new File("G:/SLIIT/Year 4/testfiles/src/");
		String dirPath = dirs.getCanonicalPath() + File.separator;//+"src"+File.separator;
 
		File root = new File(dirPath);
		//System.out.println(rootDir.listFiles());
		File[] files = root.listFiles();
		filePath = null;
 
		 for (File f : files ) {
			 filePath = f.getAbsolutePath();
			 file = filePath;
			 if(f.isFile()){
				 parse(readFileToString(filePath));
			 }
		 }
	}
	
	
	public static String readFileToString(String filePath) throws IOException {
		StringBuilder fileData = new StringBuilder(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
 
		char[] buf = new char[10];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
 
		reader.close();
 
		return  fileData.toString();	
	}
	
	 static JSONArray itemsEfficiency= new JSONArray();

	public static JSONArray parse(String strFile) throws JSONException {
		String str3 = strFile;
		JSONObject	retObj ;
		JSONObject	retObj2 ;
		JSONObject	retObj3, retObj4 ;
		
		JSONObject	retObjfinal = new JSONObject();


		JSONArray	retArray = new JSONArray();
		JSONArray	retArrayfinal = new JSONArray();
		
	    Collection<JSONObject> items = new ArrayList<JSONObject>();


		String[] lines = str3.split(System.getProperty("line.separator"));
		
		StringBuilder str = new StringBuilder();
		StringBuilder str2 = new StringBuilder();
		
		Scanner scanner = new Scanner(str3);
		ifcount = 0;
		whilecount = 0;
		forcount = 0;
		CCValue = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			//line = line.replaceAll("\\s", "");
				if(line.contains("//") && line.contains("Developer:") && line.contains("Start")){
					 devname = line.substring(line.indexOf("Developer:"), line.indexOf("; "));
					 devNames.put(devname);
					System.out.println(devname);
					
					 do {
						 line = scanner.nextLine();
						
							if(line.contains("if") && line.contains("(") && line.contains(")") ){
								ifcount++;
							}
							if(line.contains("while")&& line.contains("(") && line.contains(")")){
								whilecount++;
							}
							if(line.contains("for") && line.contains("(") && line.contains("=") && (line.contains("<")|| line.contains(">"))){
								forcount++;
							}
							if(line.contains("switch")){
								switchcount++;
							}
							
					}while(!line.contains("//") && !line.contains("Developer:") && !line.contains("End"));
					 
					 line = scanner.nextLine();
					 retObj = new JSONObject();
					 retObj2 = new JSONObject();
					 retObj3 = new JSONObject();
					 retObj4 = new JSONObject();
					 
					 CCValue = ifcount + whilecount + forcount + switchcount + 1;
					 
					 try {
							retObj.put("name", "If Count");
							retObj.put("value", ifcount);
							
							retObj2.put("name", "For Count");
							retObj2.put("value", forcount);
							
							retObj3.put("name", "While Count");
							retObj3.put("value", whilecount);
							
							retObj4.put("name", "Cyclometric Complexity");
							retObj4.put("value", CCValue);
							
							
							retArray = new JSONArray();
							retArray.put(retObj);
							retArray.put(retObj2);
							retArray.put(retObj3);
							retArray.put(retObj4);
							
							retObjfinal = new JSONObject();
							retObjfinal.put("name", devname);
							//retObjfinal.put("value", retArray);
							retObjfinal.put("If Count", ifcount);
							retObjfinal.put("While Count", whilecount);
							retObjfinal.put("For Count", forcount);
							retObjfinal.put("Cyclometric Complexity", CCValue);
							
							ifcount = 0;
							whilecount = 0;
							forcount = 0;
							CCValue = 0;
							
							items.add(retObjfinal);
							 System.out.println(items);
							retArrayfinal.put(retObjfinal);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					// System.out.println(retObjfinal);
					 
				}
		}
		

		if(itemsEfficiency.length() ==0){
			JSONObject s = new JSONObject();
			try {
				//s = (JSONObject) ((JSONArray) items).get(0);
				itemsEfficiency.put(items);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
		else{
			
			 for (int i = 0, size = itemsEfficiency.length(); i < size; i++)
			    {
				 try {
					 JSONArray objectInArray =  itemsEfficiency;
					
					for (int j = 0; j < objectInArray.length(); j++) {
						
						JSONArray objectInArray2 =  itemsEfficiency.getJSONArray(j);
						JSONObject object = objectInArray2.getJSONObject(j);
						//for(JSONObject irr : items){
						for(JSONObject irr : items){
							if(object.getString("name").equals(irr.getString("name"))){
								double x1  = object.getInt("Cyclometric Complexity") + irr.getInt("Cyclometric Complexity");
								double x2  = object.getInt("While Count") + irr.getInt("While Count");
								double x3  = object.getInt("For Count") + irr.getInt("For Count");
								double x4  = object.getInt("If Count") + irr.getInt("If Count");
								object.put("Cyclometric Complexity", x1);
								object.put("While Count", x2);
								object.put("For Count", x3);
								object.put("If Count", x4);
//								jo.put("value", x);
								//for(int m = 0 ; m < object.length(); m++){
									
									//JSONObject object2 = objectInArray.getJSONObject(m);

//									if(object.getString("name").equals(irr.getString("name"))){
//								JSONArray ar = new JSONArray();
//								JSONArray ar2 = new JSONArray();
////								ar = object.getJSONArray("value");
//								ar2 = irr.getJSONArray("value");
//									for(int n = 0; n < ar.length() ; n++){
//										JSONObject jo = new JSONObject();
//										JSONObject jo2 = new JSONObject();
//										jo2 = ar2.getJSONObject(n);
//										jo = ar.getJSONObject(n);
//										double x  = jo.getInt("value") + jo2.getInt("value");
//										jo.put("value", x);
//									}
//								
										
										
										//System.out.println("fuck" +x);

								//	}
								//}
								
							}
					}
					}
				
				} catch (JSONException e) {
					System.out.println("err");
					e.printStackTrace();
				}
					
			    }	   
		}
		
//		for(int i = 0 ; i < itemsEfficiency.length() ; i++){
//			JSONObject n1 = new JSONObject();
//			itemsEfficiency.getString(i);
//			
//			n1.put("name", itemsEfficiency.getString("name"));
//		}
		
		
		//new
		JSONObject root = new JSONObject();
		root.put("name", "root");
		JSONObject rootChild = new JSONObject();

		JSONArray rootChild3 = new JSONArray();
		JSONObject devlevel2 = new JSONObject();
		devlevel2.put("name", "Engi");
		devlevel2.put("children", itemsEfficiency.get(0));
		//dsd
		//itemsEfficiency.get(0)
		//dsd
		rootChild3.put(devlevel2);
		root.put("children", rootChild3);
		System.out.println(root);
		System.out.println("Atheek");
		JSONArray rootChild2 = new JSONArray();

		for(int i = 0 ; i < itemsEfficiency.length() ; i++){
			JSONObject n1 = new JSONObject();
		itemsEfficiency.getString(i);
		
		rootChild3.put(itemsEfficiency.get(i)); 
		}
		//devlevel2.put("children", itemsEfficiency);

		//new
		JSONArray newArray = new JSONArray();
		//newArray = itemsEfficiency.getJSONArray(0);
		newArray.put(itemsEfficiency);
		System.out.println("new"+items);
		System.out.println("d"+devNames);
		return newArray;

	}
	
	public static JSONArray getDeveloperNames(){
		return devNames;
	}
	
	public static JSONObject getValue(String name) throws JSONException{
		JSONObject retObj = new JSONObject();
		//ParseFilesInDir();
		for(int i = 0 ; i < itemsEfficiency.length() ; i++){
			JSONObject obj = new JSONObject();
			obj = itemsEfficiency.getJSONObject(i);
			
			if(obj.getString("name").equals(name)){
				retObj = obj;
			}
		}
		return retObj;
	}

}

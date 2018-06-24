package restPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.server.RemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DIT {
	
	public static String filePath;
	//public static int dit ;
	public static JSONObject retObj = new JSONObject() ;
	public static JSONArray retArrayfinal  = new JSONArray();

	public DIT() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws IOException, JSONException {
		ParseFilesInDir();
		//retArrayfinal = new JSONArray();
		 retObj.put("name", "Number of Ancestors");
			retObj.put("vaule", retArrayfinal);
			System.out.println(retObj);
	}
	
	public JSONObject getNOA() throws JSONException, IOException{
		ParseFilesInDir();
		JSONObject obj = new JSONObject();
		obj.put("name",  "Number of Ancestors");
		obj.put("value", retArrayfinal);
		return obj;
	}
	
	public static void ParseFilesInDir() throws IOException, JSONException{
		File dirs = new File("G:/SLIIT/Year 4/testfiles/src/");
		String dirPath = dirs.getCanonicalPath() + File.separator;//+"src"+File.separator;
 
		File root = new File(dirPath);
		//System.out.println(rootDir.listFiles());
		File[] files = root.listFiles ( );
		filePath = null;
 
		 for (File f : files ) {
			 filePath = f.getAbsolutePath();
			 String filename = f.getName().toString();
			 //file = filePath;
			 if(f.isFile()){
				 
				 parse(readFileToString(filePath),filename);
				 
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
	
	public static JSONArray parse(String strFile, String filename) throws JSONException, IOException {
		int dit = 0;
		String str3 = strFile;
		//JSONObject	retObj = null ;
		JSONObject	retObj2 ;
		JSONObject	retObj3, retObj4 ;
		
		JSONObject	retObjfinal = new JSONObject();


		JSONArray	retArray = new JSONArray();
		//JSONArray	retArrayfinal = new JSONArray();
		
	    Collection<JSONObject> items = new ArrayList<JSONObject>();


		String[] lines = str3.split(System.getProperty("line.separator"));
		
		StringBuilder str = new StringBuilder();
		StringBuilder str2 = new StringBuilder();
		
		Scanner scanner = new Scanner(str3);
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			
			if(line.contains("extends")){
					
					dit++;

					String parentname = line.substring(line.indexOf("extends"),line.indexOf("{"));
					
					File dirs = new File("G:/SLIIT/Year 4/testfiles/src/");
					String dirPath = dirs.getCanonicalPath() + File.separator;//+"src"+File.separator;
			 
					File root = new File(dirPath);
					//System.out.println(rootDir.listFiles());
					File[] files = root.listFiles ( );
					filePath = null;
			 
					 for (File f : files ) {
						 filePath = f.getAbsolutePath();
						 //file = filePath;
						 if(f.isFile()){
							String test = parentname.substring(7, parentname.length()).trim();
							 if(f.getName().contains(test) ){ // error 
								 String file = readFileToString(filePath);
								 
								 Scanner scn = new Scanner(file);
								 
								
									 
									 while(scn.hasNext()){
										 String l = scn.nextLine();
										 
										
											 
											 if(l.contains("extends")){
												 
												 dit++;
												 
											 }
											 
										 
										 
										
									 }
									 
								 }
								 
								
							 
							
							
							
							 
						 }
						 
						
					 }
					
			
			}
		}
		
		JSONObject item1 = new JSONObject();
		item1.put("name", filename);
		item1.put("value", dit);
		
		
		JSONArray returnArray =  new JSONArray();
		retArrayfinal.put(item1);
		
		
		
		System.out.println(retObj);
		return returnArray;
		
		
	}
	
}

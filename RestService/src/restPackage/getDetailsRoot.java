package restPackage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class getDetailsRoot {
	
	public static void main(String []args) throws IOException, JSONException{
		getCFS_root("main");
		
	}

	public getDetailsRoot() {
		// TODO Auto-generated constructor stub
	}
	
	static String filePath;


	public static JSONObject getCFS_root(String fName) throws IOException, JSONException{
		File dirs = new File("D:/4thyearBackEnd/temp_files/");
		String dirPath = dirs.getCanonicalPath() + File.separator;//+"src"+File.separator;
		 System.out.println("Chandeesha *********************fName"+fName);
		JSONObject obj1 = new JSONObject();
		Collection<JSONObject> items = new ArrayList<JSONObject>();
		
		File root = new File(dirPath);
		//System.out.println(rootDir.listFiles());
		File[] files = root.listFiles ( );
		filePath = null;

		
		 for (File f : files ) {
			 filePath = f.getAbsolutePath();
			 //file = filePath;
			 if(f.isFile()){

				 System.out.println("Chandeesha *********************filePath"+filePath);
				 obj1.put("value", CFS.getCFSValue_root(filePath));;				 
			 }
			
			 
		 }
		 System.out.println(obj1);
		 return obj1;
		 }
	
//	public static JSONObject getCWCM_root(String fName) throws IOException, JSONException{
//		
//		File dirs = new File("G:/SLIIT/Year 4/testfiles/src");
//		String dirPath = dirs.getCanonicalPath() + File.separator;//+"src"+File.separator;
// 
//		JSONObject obj1 = new JSONObject();
//		Collection<JSONObject> items = new ArrayList<JSONObject>();
//		
//		File root = new File(dirPath);
//		//System.out.println(rootDir.listFiles());
//		File[] files = root.listFiles ( );
//		filePath = null;
//
//		
//		 for (File f : files ) {
//			 filePath = f.getAbsolutePath();
//			 
//		 }
//		 
//		 return obj1;
//		
//	}
	
	
	

}

package restPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class astLOC {
	
	static String filePath;
	static String file;
	
	public static void main(String[] args) throws IOException, JSONException{
		parseLOC_root("a");
		//System.out.println(parseLOC_File("Cal.java"));
		//System.out.println(parseLOC("methodC"));
	}
	
	public static JSONObject parseLOC(String fName, String mName) throws IOException, JSONException{
		File dirs = new File("D:/4thyearBackEnd/temp_files/");
		String dirPath = dirs.getCanonicalPath() + File.separator;//+"src"+File.separator;
 
		JSONObject obj1 = new JSONObject();
		Collection<JSONObject> items = new ArrayList<JSONObject>();
		
		File root = new File(dirPath);
		//System.out.println(rootDir.listFiles());
		File[] files = root.listFiles ( );
		filePath = null;
 
		 for (File f : files ) {
			 filePath = f.getAbsolutePath();
			 file = filePath;
			 if(f.isFile()){
				 	if(f.getName().equals(fName) && (f.getName() != "root")){
				 		 obj1.put("value", LOC(readFileToString(filePath), mName)); 
				 	}
								 
				 //obj1.put("value", LOC_file(readFileToString(filePath))); 

				 //System.out.println(obj1);
			 }
		 }
			
		//System.out.println(obj1);
		 
		return obj1;
	}
	
	public static JSONObject parseLOC_File(String fName) throws IOException, JSONException{
		File dirs = new File("D:/4thyearBackEnd/temp_files/");
		String dirPath = dirs.getCanonicalPath() + File.separator;//+"src"+File.separator;
 
		JSONObject obj1 = new JSONObject();
		Collection<JSONObject> items = new ArrayList<JSONObject>();
		
		File root = new File(dirPath);
		//System.out.println(rootDir.listFiles());
		File[] files = root.listFiles ( );
		filePath = null;
 
		 for (File f : files ) {
			 filePath = f.getAbsolutePath();
			 file = filePath;
			 if(f.isFile()){
				 //obj1.put("value", LOC(readFileToString(filePath), mName)); 
				 if(f.getName().equals(fName)&& (f.getName() != "root")){
				 obj1.put("value", LOC_file(readFileToString(filePath))); 
				 }
				 //System.out.println(obj1);
			 }
		 }
			
		//System.out.println(obj1);
		 
		return obj1;
	}
	
	
	public static JSONObject parseLOC_root(String fName) throws IOException, JSONException{
		File dirs = new File("D:/4thyearBackEnd/temp_files/");
		String dirPath = dirs.getCanonicalPath() + File.separator;//+"src"+File.separator;
 
		JSONObject obj1 = new JSONObject();
		Collection<JSONObject> items = new ArrayList<JSONObject>();
		
		File root = new File(dirPath);
		//System.out.println(rootDir.listFiles());
		File[] files = root.listFiles ( );
		filePath = null;
 
		 for (File f : files ) {
			 filePath = f.getAbsolutePath();
			 file = filePath;
			 if(f.isFile()){
				 //obj1.put("value", LOC(readFileToString(filePath), mName)); 
				 
				 obj1.put("value", LOC_root(readFileToString(filePath))); 
				 
				 //System.out.println(obj1);
			 }
		 }
			
		System.out.println(obj1);
		 
		return obj1;
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
	
	public static JSONArray LOC(String str, String mName) {
		
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(str.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
			
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		
		  
		JSONObject obj1 = new JSONObject();  
		
		Collection<JSONObject> items = new ArrayList<JSONObject>();		
		
		
		cu.accept(new ASTVisitor() {
 
			Set names = new HashSet();
			
			int lLoc = 0;
			int totLoc = 0;
			int Loc = 0;
			int commLines = 0;
			int hLoc = 0;
			
			public boolean visit(MethodDeclaration node){


				
				String str3 = node.getBody().toString();
				
				String[] lines = str3.split(System.getProperty("line.separator"));				
				
				StringBuilder str = new StringBuilder();
				StringBuilder str2 = new StringBuilder();
				
				Scanner scanner = new Scanner(str3);
				
				if(node.getName().toString().equals(mName)){
					while (scanner.hasNextLine()) {
						String currentLine = scanner.nextLine();
						
						if (!(currentLine.trim().isEmpty())) 
						{
							currentLine = currentLine.trim();
							//check comment lines (both // and /**/)
							if(currentLine.startsWith("//")) 
							{
								commLines += 1;
							}
							else if((currentLine.startsWith("/*")) && (currentLine.endsWith("*/"))) 
							{
								commLines += 1;
							}
							else if((currentLine.startsWith("/*"))) 
							{
								commLines += 1;
								while(!(currentLine.equals(scanner.nextLine().endsWith("*/"))))
								{
									commLines += 1;
								}
								commLines += 1;
							}
							
							//check Logical lines of code (lines having ; at the end including loops)
							if((currentLine.endsWith(";")) || ((currentLine.endsWith(":"))) )
							{
								lLoc += 1;
							}
							else if((currentLine.endsWith(")")) || (currentLine.endsWith("{"))) 
							{
								lLoc += 1;
							}
							
							//Physical Lines of code (includes lines only having curly brackets as well as all lLoc)
							//Here it will only add the lines with curly brackets.Later it will be added to the lLoc total
							if((currentLine.startsWith("{")) || ((currentLine.startsWith("}"))))
							{
								Loc += 1;
							}
						}
					}
					
					scanner.close();
					
					//current Loc will be added with lLoc to get total loc
					hLoc = Loc + lLoc;
					//total lines of code will be number of lines physically available including comments excluding white spaces
					totLoc = hLoc + commLines;
					
					try {
						JSONObject item1 = new JSONObject();
					    item1.put("name", "Commented LOC");
					    item1.put("value", commLines);
					    items.add(item1);
					    
					    JSONObject item2 = new JSONObject();
					    item2.put("name", "Logical LOC");
					    item2.put("value", lLoc);
					    items.add(item2);
					    
					    JSONObject item3 = new JSONObject();
					    item3.put("name", "Physical LOC");
					    item3.put("value", Loc);
					    items.add(item3);

					    JSONObject item4 = new JSONObject();
					    item4.put("name", "Current LOC");
					    item4.put("value", hLoc);
					    items.add(item4);
					    
					    JSONObject item5 = new JSONObject();
					    item5.put("name", "Total LOC");
					    item5.put("value", totLoc);
					    items.add(item5);
					    
//					    obj1.put("Method Name", node.getName().toString());
//					    obj1.put("value", new JSONArray(items));
					}
					catch(Exception e) {
						
					}
					
				}
				
				return true;
				
			}
			
		});
				
		return (new JSONArray(items));
		
	}

	public static JSONArray LOC_file(String strFile){
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(strFile.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
			
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		
		  
		JSONObject obj1 = new JSONObject();  
		
		Collection<JSONObject> items = new ArrayList<JSONObject>();	
		
	
			
			int lLoc = 0;
			int totLoc = 0;
			int Loc = 0;
			int commLines = 0;
			int hLoc = 0;
			
			String str3 = strFile;
			
			String[] lines = str3.split(System.getProperty("line.separator"));				
			
			StringBuilder str = new StringBuilder();
			StringBuilder str2 = new StringBuilder();
			
			Scanner scanner = new Scanner(str3);
			
			

			while (scanner.hasNextLine()) {
				String currentLine = scanner.nextLine();
				
				if (!(currentLine.trim().isEmpty())) 
				{
					currentLine = currentLine.trim();
					//check comment lines (both // and /**/)
					if(currentLine.startsWith("//")) 
					{
						commLines += 1;
					}
					else if((currentLine.startsWith("/*")) && (currentLine.endsWith("*/"))) 
					{
						commLines += 1;
					}
//					else if((currentLine.startsWith("/*"))) 
//					{
//						commLines += 1;
//						while(!(currentLine.equals(scanner.nextLine().endsWith("*/"))))
//						{
//							commLines += 1;
//						}
//						commLines += 1;
//					}
					
					//check Logical lines of code (lines having ; at the end including loops)
					if((currentLine.endsWith(";")) || ((currentLine.endsWith(":"))) )
					{
						lLoc += 1;
					}
					else if((currentLine.endsWith(")")) || (currentLine.endsWith("{"))) 
					{
						lLoc += 1;
					}
					
					//Physical Lines of code (includes lines only having curly brackets as well as all lLoc)
					//Here it will only add the lines with curly brackets.Later it will be added to the lLoc total
					if((currentLine.startsWith("{")) || ((currentLine.startsWith("}"))))
					{
						Loc += 1;
					}
				}
			}
			
			scanner.close();
			
			//current Loc will be added with lLoc to get total loc
			hLoc = Loc + lLoc;
			//total lines of code will be number of lines physically available including comments excluding white spaces
			totLoc = hLoc + commLines;
			
			try {
				JSONObject item1 = new JSONObject();
			    item1.put("name", "Commented LOC");
			    item1.put("value", commLines);
			    items.add(item1);
			    
			    JSONObject item2 = new JSONObject();
			    item2.put("name", "Logical LOC");
			    item2.put("value", lLoc);
			    items.add(item2);
			    
			    JSONObject item3 = new JSONObject();
			    item3.put("name", "Physical LOC");
			    item3.put("value", Loc);
			    items.add(item3);

			    JSONObject item4 = new JSONObject();
			    item4.put("name", "Current LOC");
			    item4.put("value", hLoc);
			    items.add(item4);
			    
			    JSONObject item5 = new JSONObject();
			    item5.put("name", "Total LOC");
			    item5.put("value", totLoc);
			    items.add(item5);
			    
//			    obj1.put("Method Name", node.getName().toString());
//			    obj1.put("value", new JSONArray(items));
			}
			catch(Exception e) {
				
			}
			
		
		return (new JSONArray(items));
	}
	
	
	 static JSONArray itemsLOC = new JSONArray();

	public static JSONArray LOC_root(String strFile){
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(strFile.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
			
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		
		  
		JSONObject obj1 = new JSONObject();  
		
		Collection<JSONObject> items = new ArrayList<JSONObject>();	
		
	
			
			int lLoc = 0;
			int totLoc = 0;
			int Loc = 0;
			int commLines = 0;
			int hLoc = 0;
			
			String str3 = strFile;
			
			String[] lines = str3.split(System.getProperty("line.separator"));				
			
			StringBuilder str = new StringBuilder();
			StringBuilder str2 = new StringBuilder();
			
			Scanner scanner = new Scanner(str3);
			
			

			while (scanner.hasNextLine()) {
				String currentLine = scanner.nextLine();
				
				if (!(currentLine.trim().isEmpty())) 
				{
					currentLine = currentLine.trim();
					//check comment lines (both // and /**/)
					if(currentLine.startsWith("//")) 
					{
						commLines += 1;
					}
					else if((currentLine.startsWith("/*")) && (currentLine.endsWith("*/"))) 
					{
						commLines += 1;
					}
//					else if((currentLine.startsWith("/*"))) 
//					{
//						commLines += 1;
//						while(!(currentLine.equals(scanner.nextLine().endsWith("*/"))))
//						{
//							commLines += 1;
//						}
//						commLines += 1;
//					}
					
					//check Logical lines of code (lines having ; at the end including loops)
					if((currentLine.endsWith(";")) || ((currentLine.endsWith(":"))) )
					{
						lLoc += 1;
					}
					else if((currentLine.endsWith(")")) || (currentLine.endsWith("{"))) 
					{
						lLoc += 1;
					}
					
					//Physical Lines of code (includes lines only having curly brackets as well as all lLoc)
					//Here it will only add the lines with curly brackets.Later it will be added to the lLoc total
					if((currentLine.startsWith("{")) || ((currentLine.startsWith("}"))))
					{
						Loc += 1;
					}
				}
			}
			
			scanner.close();
			
			//current Loc will be added with lLoc to get total loc
			hLoc = Loc + lLoc;
			//total lines of code will be number of lines physically available including comments excluding white spaces
			totLoc = hLoc + commLines;
			
			try {
				JSONObject item1 = new JSONObject();
			    item1.put("name", "Commented LOC");
			    item1.put("value", commLines);
			    items.add(item1);
			    
			    JSONObject item2 = new JSONObject();
			    item2.put("name", "Logical LOC");
			    item2.put("value", lLoc);
			    items.add(item2);
			    
			    JSONObject item3 = new JSONObject();
			    item3.put("name", "Physical LOC");
			    item3.put("value", Loc);
			    items.add(item3);

			    JSONObject item4 = new JSONObject();
			    item4.put("name", "Current LOC");
			    item4.put("value", hLoc);
			    items.add(item4);
			    
			    JSONObject item5 = new JSONObject();
			    item5.put("name", "Total LOC");
			    item5.put("value", totLoc);
			    items.add(item5);
			    
//			    obj1.put("Method Name", node.getName().toString());
//			    obj1.put("value", new JSONArray(items));
			}
			catch(Exception e) {
				
			}
			
			if(itemsLOC.length() ==0){
				JSONObject s = new JSONObject();
				try {
					//s = (JSONObject) ((JSONArray) items).get(0);
					itemsLOC.put(items);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			else{
				
				 for (int i = 0, size = itemsLOC.length(); i < size; i++)
				    {
					 try {
						JSONArray objectInArray =  itemsLOC.getJSONArray(i);
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
				newArray = itemsLOC.getJSONArray(0);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return newArray;
	}
}

package restPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class nestingLevel {
	
	static String filePath;
	static String file;
	
	public static void main(String ars[]) throws IOException, JSONException {
		
		//System.out.println(parseNesting_root("Cal.java","main"));
		//System.out.println(parseNesting("Cal.java","main"));
		
	}
	
	public static JSONObject parseNesting(String fName,String mName) throws IOException, JSONException{
		System.out.println("AtheekparseNestingn ********************fName "+ fName );
		System.out.println("Atheek parseNesting********************mName "+ mName );
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
				 //if(f.getName().equals(mName)){
				 	if(f.getName().equals(fName)){
				 obj1.put("value", Nesting(readFileToString(filePath), mName));
				 	}
				 System.out.println(obj1);
			 }
		 }	 
			
		System.out.println(obj1);
		 
		return obj1;
	}
	
	public static JSONObject parseNesting_root(String fName,String mName) throws IOException, JSONException{
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
				 	
				 obj1.put("value", Nesting_root(readFileToString(filePath), mName));
				 	
				 //System.out.println(obj1);
			 }
		 }	 
			
		//System.out.println(obj1);
		 
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

	public static JSONArray Nesting(String str, String mName) {
		
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(str.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
			
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		
//		classstartidx = cu.toString().indexOf("public");
//		classendidx =  cu.toString().indexOf("{");
//		classstruct.classname = cu.toString().substring(classstartidx, classendidx);
		 
		//	pass cu (Compilation Unit) for operand operator class
		  
		JSONObject obj1 = new JSONObject();  
		
		

	    Collection<JSONObject> items = new ArrayList<JSONObject>();
		
		
		cu.accept(new ASTVisitor() {
			public boolean visit(MethodDeclaration node){
				
				String str3 = node.getBody().toString();
			
				int nestLevel = 0;
				
				StringBuilder str = new StringBuilder();
				StringBuilder str2 = new StringBuilder();
				
				Scanner scanner = new Scanner(str3);
				System.out.println("Ateheekk asdas das d asd as + "+node.getName().toString());
				if(node.getName().toString().equals(mName)){
					while (scanner.hasNextLine()) {
						String line = scanner.nextLine();
						
						JSONObject item1 = new JSONObject();
						
						try {
							if(line.contains("{")){
								
								nestLevel++;		
								//line.split("{");
								item1.put("name", line);
							    item1.put("value", nestLevel);	
								items.add(item1);
//								System.out.println(line);
//								System.out.println("Nesting = " + nestLevel);
							}
							
							if(line.contains("}")){
								nestLevel--;							    
							    
//								System.out.println(line);
//								System.out.println("Nesting = " + nestLevel);
							}
						}
						catch(Exception e){
							
						}
						

					}
				}
				
				return true;
			}
		});
		
//		obj1.put("value", new JSONArray(items));
		return new JSONArray(items);	
	}
	
	 static JSONArray itemsNesting= new JSONArray();

public static JSONArray Nesting_root(String str, String mName) {
		
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(str.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
			
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		
//		classstartidx = cu.toString().indexOf("public");
//		classendidx =  cu.toString().indexOf("{");
//		classstruct.classname = cu.toString().substring(classstartidx, classendidx);
		 
		//	pass cu (Compilation Unit) for operand operator class
		  
		JSONObject obj1 = new JSONObject();  
		
		

	    Collection<JSONObject> items = new ArrayList<JSONObject>();
		
		
		cu.accept(new ASTVisitor() {
			public boolean visit(MethodDeclaration node){
				
				String str3 = node.getBody().toString();
			
				int nestLevel = 0;
				
				StringBuilder str = new StringBuilder();
				StringBuilder str2 = new StringBuilder();
				
				Scanner scanner = new Scanner(str3);
				
				if(node.getName().toString().equals(mName)){
					while (scanner.hasNextLine()) {
						String line = scanner.nextLine();
						
						JSONObject item1 = new JSONObject();
						
						try {
							if(line.contains("{")){
								
								nestLevel++;		
								//line.split("{");
								item1.put("Function Name", line);
							    item1.put("Nesting Level", nestLevel);	
								items.add(item1);
//								System.out.println(line);
//								System.out.println("Nesting = " + nestLevel);
							}
							
							if(line.contains("}")){
								nestLevel--;							    
							    
//								System.out.println(line);
//								System.out.println("Nesting = " + nestLevel);
							}
						}
						catch(Exception e){
							
						}
						

					}
				}
				
				if(itemsNesting.length() ==0){
					JSONObject s = new JSONObject();
					try {
						//s = (JSONObject) ((JSONArray) items).get(0);
						itemsNesting.put(items);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
				else{
					
					 for (int i = 0, size = itemsNesting.length(); i < size; i++)
					    {
						 try {
							JSONArray objectInArray =  itemsNesting.getJSONArray(i);
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
				
				return true;
			}
		});
		
//		obj1.put("value", new JSONArray(items));
		JSONArray newArray = new JSONArray();
		try {
			newArray = itemsNesting.getJSONArray(0);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newArray;
	}
			
			
	/*public static JSONObject Nesting(String filePath) throws IOException, JSONException {
		
		int nestLevel = 0;
		JSONObject obj1 = new JSONObject();
		Collection<JSONObject> items = new ArrayList<JSONObject>();
		
		try {
			File file = new File(filePath);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			//StringBuffer stringBuffer = new StringBuffer();
			String line;
			String[] operands;
			String operand="";
			while ((line = bufferedReader.readLine()) != null) {
				JSONObject item1 = new JSONObject();
				if(line.contains("{")){
					
					nestLevel++;		
					//line.split("{");
					item1.put("Function Name", line);
				    item1.put("Nesting Level", nestLevel);	
					items.add(item1);
//					System.out.println(line);
//					System.out.println("Nesting = " + nestLevel);
				}
				
				if(line.contains("}")){
					nestLevel--;							    
				    
//					System.out.println(line);
//					System.out.println("Nesting = " + nestLevel);
				}	
				
				
			}
		}
		catch (Exception e) {
				
		}
		
		obj1.put("value", new JSONArray(items));
		return obj1;		
	}*/
	
}

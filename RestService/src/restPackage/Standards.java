package restPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonArray;

public class Standards {
	
	static List<VariableDeclaration> variables = new ArrayList<VariableDeclaration>();
	static List<String> test = new ArrayList<String>();
	static JSONArray	finalArr;
	static JSONArray	retArray  = new JSONArray();

	static JSONArray finalArr2 ;
	static JSONObject file;
	static List<VariableDeclaration> defectiveVariables = new ArrayList<VariableDeclaration>();

	static List<VariableDeclaration> Mvariables = new ArrayList<VariableDeclaration>();
	static List<Integer> lines = new ArrayList<Integer>();


	public Standards() {
		// TODO Auto-generated constructor stub
	}
	

	public static void main(String[] args) throws IOException {
		String mName = "";
		String fName = "root";
		//ParseFilesInDir(fName,mName);
		ParseFilesInDir_root(mName);
		System.out.println("file"+variables);
		System.out.println("line"+lines);
		System.out.println("method"+Mvariables);
		
		evaluate(variables);
		
		JSONObject finalObj = new JSONObject();
		finalArr = new JSONArray();
		
		for(String str : test){
			
		}
		
		
		for(VariableDeclaration v : defectiveVariables){
			JSONObject obj = new JSONObject();
			
			try {
				obj.put("name", v);
				obj.put("value", v.getStartPosition());
				finalArr.put(obj);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		System.out.println(finalArr);
		System.out.println(test);
		

		

	}
	
	public static void evaluate(List<VariableDeclaration> var){
		
		for(VariableDeclaration v : var){
			
			boolean upperCase = false;
			char c;
			c = v.toString().charAt(0);
			for(int i = 1 ; i < v.toString().length() ; i++){
				if(Character.isUpperCase(v.toString().charAt(i))){
					upperCase = true;
				}
			}
			
			if(!Character.isLowerCase(c) || v.toString().contains("_") || upperCase==false){
				defectiveVariables.add(v);
			}
		}
		
		
		
	}
	public static void ParseFilesInDir(String fName,String mName) throws IOException{
		File dirs = new File("D:\\4thyearBackEnd\\temp_files\\");
		String dirPath = dirs.getCanonicalPath() + File.separator;//+"src"+File.separator;
 
		File root = new File(dirPath);
		//System.out.println(rootDir.listFiles());
		File[] files = root.listFiles ( );
		String filePath = null;
 
		 for (File f : files ) {
			 filePath = f.getAbsolutePath();
			 if(f.isFile()){
				 if(f.getName().equals(mName)){
				 parse(readFileToString(filePath), mName);
				 }
			 }
		 }
	}
	
	public static void ParseFilesInDir_root(String fName) throws IOException{
		File dirs = new File("D:\\4thyearBackEnd\\temp_files\\");
		String dirPath = dirs.getCanonicalPath() + File.separator;//+"src"+File.separator;
 
		File root = new File(dirPath);
		//System.out.println(rootDir.listFiles());
		File[] files = root.listFiles ( );
		String filePath = null;
		 
		 for (File f : files ) {
			 filePath = f.getAbsolutePath();
			 if(f.isFile()){
				 finalArr2 = new JSONArray();
				  file = new JSONObject();
				  variables.clear();
				 parse_root(readFileToString(filePath), f.getName().toString());
				 
			 }
		 }
	}
	
	public static String readFileToString(String filePath) throws IOException {
		StringBuilder fileData = new StringBuilder(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
 
		char[] buf = new char[10];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			//System.out.println(numRead);
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
 
		reader.close();
 
		return  fileData.toString();	
	}
	
	public static void parse(String str, String mName) {
		String a = "tessttt";
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(str.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		
		
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		
		
		//	pass cu (Compilation Unit) for operand operator class
		  
		  
		
		cu.accept(new ASTVisitor() {
 
			
 
			public boolean visit(VariableDeclarationFragment node) {
				variables.add(node);
				lines.add(node.getStartPosition());
				System.out.println(node.getName() +  " " +node.getStartPosition());
				return false;
			}
			
			public boolean visit(MethodDeclaration node){
				if(node.getName().toString().equals(mName)){
				node.getBody().accept(	(new ASTVisitor() {
					
					public boolean visit(VariableDeclaration node) {
						Mvariables.add(node);
						
						return false;
					}
						
					
				
				}));
				}
				return false;
				
			}
		});
		
		
	}
	

	public static void parse_root(String str, String fName) {
		
		file = new JSONObject();
		
		String a = "tessttt";
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(str.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		
		
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		
		
		//	pass cu (Compilation Unit) for operand operator class
		  
		  
		
		cu.accept(new ASTVisitor() {
 
 
			public boolean visit(VariableDeclarationFragment node) {
				String nodename = node.getName().toString();
				
//				if(nodename.contains("=")){
//					String varname = nodename.substring(0, nodename.indexOf("=")); 
//					test.add(varname);
//				}
				
				variables.add(node);
				int i = cu.getLineNumber(node.getStartPosition()) ;
				lines.add(i);

				System.out.println(node.getName() +  " " +node.getStartPosition());

				return false;
			}
			
			
		});
		
for(VariableDeclaration v : variables){
			
			boolean upperCase = false;
			char c;
			c = v.toString().charAt(0);
			for(int i = 1 ; i < v.toString().length() ; i++){
				if(Character.isUpperCase(v.toString().charAt(i))){
					upperCase = true;
				}
			}
			
			
			if(!Character.isLowerCase(c) || v.toString().contains("_") || upperCase==false){
				defectiveVariables.add(v);
			}
		}
variables.clear();
		
	for(VariableDeclaration v : defectiveVariables){
		JSONObject obj = new JSONObject();
		
		try {
			obj.put("name", v);
			obj.put("value", cu.getLineNumber(v.getStartPosition()));
			finalArr2.put(obj);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	defectiveVariables.clear();
	
	try {
		file.put("name", fName);
		file.put("value", finalArr2);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	retArray.put(file);
	
	System.out.println("final"+retArray);
		
	}
	
	public static JSONArray getStandard(String mName) throws IOException{
		ParseFilesInDir_root(mName);
		return retArray;
	}
	
	

}


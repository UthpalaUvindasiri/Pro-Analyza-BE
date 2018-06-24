package restPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//atheek
//import jdk.nashorn.internal.parser.JSONParser;

public class astCode {
	
	static String filePath;
	static String file;
	
	static List<MethodDeclaration> methods = new ArrayList<MethodDeclaration>();
	
	public static void main(String[] args) throws IOException, JSONException{
		//parseHalstead("main");
		//parseException("main");
		parseException_root("Cal.java");
	}
	
	public static JSONObject parseException(String fName,String mName) throws IOException, JSONException{
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
				 	if(f.getName().equals(fName)  ){
						 obj1.put("value", Exception(readFileToString(filePath), mName));
				 	}
				 //obj1.put("value", Exception_file(readFileToString(filePath)));
				 //System.out.println(obj1);
			 }
		 }
			
		//System.out.println(obj1);
		 
		return obj1;
	}
	
	
	public static JSONObject parseException_file(String fName) throws IOException, JSONException{
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
				 //obj1.put("value", Exception(readFileToString(filePath), mName));
				 if(f.getName().equals(fName) ){
				 obj1.put("value", Exception_file(readFileToString(filePath)));
				 }
				 //System.out.println(obj1);
			 }
		 }
			
		System.out.println(obj1);
		 
		return obj1;
	}
	
	public static JSONObject parseException_root(String fName) throws IOException, JSONException{
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
				 //obj1.put("value", Exception(readFileToString(filePath), mName));
				
				 obj1.put("value", Exception_root(readFileToString(filePath)));
				 
				 //System.out.println(obj1);
			 }
		 }
			
		System.out.println(obj1);
		 
		return obj1;
	}
	
	public static JSONObject parseHalstead(String fName,String mName) throws IOException, JSONException{
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
				 	if(f.getName().equals(fName)  ){
				 obj1.put("value", Halstead(readFileToString(filePath), mName));
				 	}
				 //System.out.println(obj1);
				 
				// if(mName == null){
					// obj1.put("value", Hasltead_File(readFileToString(filePath)));
				// }
			 }
		 }
			
		//System.out.println(obj1);
		 
		return obj1;
	}
	public static JSONObject parseHalstead_File_Root(String fName) throws IOException, JSONException{
		File dirs = new File("D:/4thyearBackEnd/temp_files/");
		String dirPath = dirs.getCanonicalPath() + File.separator;//+"src"+File.separator;
 
		JSONObject obj1 = new JSONObject();
		Collection<JSONObject> items = new ArrayList<JSONObject>();
		
		File root = new File(dirPath);
		//System.out.println(rootDir.listFiles());
		File[] files = root.listFiles ( );
		filePath = null;

		 itemsHasltead = new JSONArray();
		 for (File f : files ) {
			 filePath = f.getAbsolutePath();
			 file = filePath;
			 if(f.isFile()){
				 //obj1.put("value", Halstead(readFileToString(filePath), mName));
				 //System.out.println(obj1);
				 
				// if(mName == null){
				 // 
				 //	if(f.getName().equals(fName)){
				
					 obj1.put("value", Hasltead_FileRoot(readFileToString(filePath)));
				 	//}
				// }
			 }
		 }
			
		//System.out.println(obj1);
		 
		return obj1;
	}
	public static JSONObject parseHalstead_File(String fName) throws IOException, JSONException{
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
				 //obj1.put("value", Halstead(readFileToString(filePath), mName));
				 //System.out.println(obj1);
				 
				// if(mName == null){
				 // 
				 	if(f.getName().equals(fName)){
					 obj1.put("value", Hasltead_File(readFileToString(filePath)));
				 	}
				// }
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
	
	public static JSONArray Exception(String str, String mName) {
		
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
 
			Set names = new HashSet();
			
			public boolean visit(MethodDeclaration node){
				methods.add(node);
				
				String str3 = node.getBody().toString();
				
				String[] lines = str3.split(System.getProperty("line.separator"));
				
				int opsCount = 0;
				int uniqueOps = 0;
				
				int opdCount = 0;
				int uniqueOpd = 0;
				
				
				int arOps = 0;
				int reOps = 0;
				int logOps = 0;
				int bitOps = 0;
				int assOps = 0;
				int otherOps = 0;
				
				
				StringBuilder str = new StringBuilder();
				StringBuilder str2 = new StringBuilder();
				
				Scanner scanner = new Scanner(str3);
				
				if(node.getName().toString().equals(mName)){
					while (scanner.hasNextLine()) {
						String line = scanner.nextLine();
//						System.out.println(node.getName());
						
						//Other Operators
						if(line.contains("System.out.println")){
							opsCount += StringUtils.countMatches(line, "System.out.println");
							otherOps += StringUtils.countMatches(line, "System.out.println");

							if(!str.toString().contains("System.out.println")){
								uniqueOps++;
								str.append("System.out.println");							
							}	
						}
						
						if(line.contains(".")){
							opsCount += StringUtils.countMatches(line, ".");
							otherOps += StringUtils.countMatches(line, ".");
							if(!str.toString().contains(".")){
								uniqueOps++;
								str.append(".");							
							}	
						}if(line.contains("{")){
							opsCount += StringUtils.countMatches(line, "{");
							otherOps += StringUtils.countMatches(line, "{");
							if(!str.toString().contains("{")){
								uniqueOps++;
								str.append("{");							
							}	
						}if(line.contains("(")){
							opsCount += StringUtils.countMatches(line, "(");
							otherOps += StringUtils.countMatches(line, "(");
							if(!str.toString().contains("(")){
								uniqueOps++;
								str.append("(");							
							}	
						}if(line.contains("[")){
							opsCount += StringUtils.countMatches(line, "[");
							otherOps += StringUtils.countMatches(line, "[");
							if(!str.toString().contains("[")){
								uniqueOps++;
								str.append("[");							
							}	
						}if(line.contains("public")){
							opsCount += StringUtils.countMatches(line, "public");
							otherOps += StringUtils.countMatches(line, "public");
							if(!str.toString().contains("public")){
								uniqueOps++;
								str.append("public");							
							}
						}if(line.contains("private")){
							opsCount += StringUtils.countMatches(line, "private");
							otherOps += StringUtils.countMatches(line, "private");
							if(!str.toString().contains("private")){
								uniqueOps++;
								str.append("private");							
							}
						}if(line.contains("protected")){
							opsCount += StringUtils.countMatches(line, "protected");
							otherOps += StringUtils.countMatches(line, "protected");
							if(!str.toString().contains("protected")){
								uniqueOps++;
								str.append("protected");							
							}
						}if(line.contains("static")){
							opsCount += StringUtils.countMatches(line, "static");
							otherOps += StringUtils.countMatches(line, "static");
							if(!str.toString().contains("static")){
								uniqueOps++;
								str.append("static");							
							}
						}if(line.contains("local")){
							opsCount += StringUtils.countMatches(line, "local");
							otherOps += StringUtils.countMatches(line, "local");
							if(!str.toString().contains("local")){
								uniqueOps++;
								str.append("local");							
							}
						}if(line.contains("instance")){
							opsCount += StringUtils.countMatches(line, "instance");
							otherOps += StringUtils.countMatches(line, "instance");
							if(!str.toString().contains("instance")){
								uniqueOps++;
								str.append("instance");							
							}
						}if(line.contains("for(")){
							opsCount += StringUtils.countMatches(line, "for(");
							otherOps += StringUtils.countMatches(line, "for(");
							if(!str.toString().contains("for(")){
								uniqueOps++;
								str.append("for(");							
							}
						}if(line.contains("if(")){
							opsCount += StringUtils.countMatches(line, "if(");
							otherOps += StringUtils.countMatches(line, "if(");
							if(!str.toString().contains("if(")){
								uniqueOps++;
								str.append("if(");							
							}
						}if(line.contains(";")){
							opsCount += StringUtils.countMatches(line, ";");
							otherOps += StringUtils.countMatches(line, ";");
							if(!str.toString().contains(";")){
								uniqueOps++;
								str.append(";");							
							}
						}if(line.contains("while(")){
							opsCount += StringUtils.countMatches(line, "while(");
							otherOps += StringUtils.countMatches(line, "while(");
							if(!str.toString().contains("while(")){
								uniqueOps++;
								str.append("while(");							
							}
						}if(line.contains("try")){
							opsCount += StringUtils.countMatches(line, "try");
							otherOps += StringUtils.countMatches(line, "try");
							if(!str.toString().contains("while(")){
								uniqueOps++;
								str.append("try");							
							}
						}if(line.contains("do")){
							opsCount += StringUtils.countMatches(line, "do");
							otherOps += StringUtils.countMatches(line, "do");
							if(!str.toString().contains("do")){
								uniqueOps++;
								str.append("do");							
							}
						}
						
						//Arithmatic Operators
						if(line.contains("+") && !line.contains("+=") && !line.contains("++")){
							opsCount += StringUtils.countMatches(line, "+");
							arOps += StringUtils.countMatches(line, "+");
							if(!str.toString().contains("+")){
								uniqueOps++;
								str.append("+");							
							}
						}if(line.contains("-") && !line.contains("-=") && !line.contains("--")){
							opsCount += StringUtils.countMatches(line, "-");
							arOps += StringUtils.countMatches(line, "-");
							if(!str.toString().contains("-")){
								uniqueOps++;
								str.append("-");							
							}
						}if(line.contains("*") && !line.contains("*=")){
							opsCount += StringUtils.countMatches(line, "*");
							arOps += StringUtils.countMatches(line, "*");
							if(!str.toString().contains("*")){
								uniqueOps++;
								str.append("*");							
							}
						}if(line.contains("/") && !line.contains("/=")){
							opsCount += StringUtils.countMatches(line, "/");
							arOps += StringUtils.countMatches(line, "/");
							if(!str.toString().contains("/")){
								uniqueOps++;
								str.append("/");							
							}
						}if(line.contains("%") && !line.contains("%=")){
							opsCount += StringUtils.countMatches(line, "%");
							arOps += StringUtils.countMatches(line, "%");
							if(!str.toString().contains("%")){
								uniqueOps++;
								str.append("%");							
							}
						}if(line.contains("++")){
							opsCount += StringUtils.countMatches(line, "++");
							arOps += StringUtils.countMatches(line, "++");
							if(!str.toString().contains("++")){
								uniqueOps++;
								str.append("++");							
							}
						}
						if(line.contains("--")){
							opsCount += StringUtils.countMatches(line, "--");
							arOps += StringUtils.countMatches(line, "--");
							if(!str.toString().contains("--")){
								uniqueOps++;
								str.append("--");							
							}
						}
						
						//Relational Operators
						if(line.contains("!=")){
							opsCount += StringUtils.countMatches(line, "!=");
							reOps += StringUtils.countMatches(line, "!=");
							if(!str.toString().contains("!=")){
								uniqueOps++;
								str.append("!=");							
							}
						}if(line.contains("==")){
							opsCount += StringUtils.countMatches(line, "==");
							reOps += StringUtils.countMatches(line, "==");
							if(!str.toString().contains("==")){
								uniqueOps++;
								str.append("==");							
							}
						}if(line.contains(">") && !line.contains(">=")){
							opsCount += StringUtils.countMatches(line, ">");
							reOps += StringUtils.countMatches(line, ">");
							if(!str.toString().contains(">")){
								uniqueOps++;
								str.append(">");							
							}
						}if(line.contains("<") && !line.contains("<=")){
							opsCount += StringUtils.countMatches(line, "<");
							reOps += StringUtils.countMatches(line, "<");
							if(!str.toString().contains("<")){
								uniqueOps++;
								str.append("<");							
							}
						}if(line.contains(">=")){
							opsCount += StringUtils.countMatches(line, ">=");
							reOps += StringUtils.countMatches(line, ">=");
							if(!str.toString().contains(">=")){
								uniqueOps++;
								str.append(">=");							
							}
						}if(line.contains("<=")){
							opsCount += StringUtils.countMatches(line, "<=");
							reOps += StringUtils.countMatches(line, "<=");
							if(!str.toString().contains("<=")){
								uniqueOps++;
								str.append("<=");							
							}
						}
						
						
						//Logical Operators
						if(line.contains("&&")){
							opsCount += StringUtils.countMatches(line, "&&");
							logOps += StringUtils.countMatches(line, "&&");
							if(!str.toString().contains("&&")){
								uniqueOps++;
								str.append("&&");							
							}
						}if(line.contains("||")){
							opsCount += StringUtils.countMatches(line, "||");
							logOps += StringUtils.countMatches(line, "||");
							if(!str.toString().contains("||")){
								uniqueOps++;
								str.append("||");							
							}
						}if(line.contains("!") && !line.contains("!=")){
							opsCount += StringUtils.countMatches(line, "!");
							logOps += StringUtils.countMatches(line, "!");
							if(!str.toString().contains("!")){
								uniqueOps++;
								str.append("!");							
							}
						}
						
						
						//Bitwise Operators
						if(line.contains("&") && !line.contains("&=")){
							opsCount += StringUtils.countMatches(line, "&");
							bitOps += StringUtils.countMatches(line, "&");
							if(!str.toString().contains("&")){
								uniqueOps++;
								str.append("&");							
							}
						}if(line.contains("|") && !line.contains("||") && !line.contains("|=")){
							opsCount += StringUtils.countMatches(line, "|");
							bitOps += StringUtils.countMatches(line, "|");
							if(!str.toString().contains("|")){
								uniqueOps++;
								str.append("|");							
							}
						}if(line.contains("~")){
							opsCount += StringUtils.countMatches(line, "~");
							bitOps += StringUtils.countMatches(line, "~");
							if(!str.toString().contains("~")){
								uniqueOps++;
								str.append("~");							
							}
						}if(line.contains("<<") && !line.contains("<<=")){
							opsCount += StringUtils.countMatches(line, "<<");
							bitOps += StringUtils.countMatches(line, "<<");
							if(!str.toString().contains("<<")){
								uniqueOps++;
								str.append("<<");							
							}
						}if(line.contains(">>") && !line.contains(">>=")){
							opsCount += StringUtils.countMatches(line, ">>");
							bitOps += StringUtils.countMatches(line, ">>");
							if(!str.toString().contains(">>")){
								uniqueOps++;
								str.append(">>");							
							}
						}
						
						//Assignment Operators
						if(line.contains("=") && !line.contains("==")){
							opsCount += StringUtils.countMatches(line, "=");
							assOps += StringUtils.countMatches(line, "=");
							if(!str.toString().contains("=")){
								uniqueOps++;
								str.append("=");							
							}
						}if(line.contains("+=")){
							opsCount += StringUtils.countMatches(line, "+=");
							assOps += StringUtils.countMatches(line, "+=");
							if(!str.toString().contains("+=")){
								uniqueOps++;
								str.append("+=");							
							}
						}if(line.contains("-=")){
							opsCount += StringUtils.countMatches(line, "-=");
							assOps += StringUtils.countMatches(line, "-=");
							if(!str.toString().contains("-=")){
								uniqueOps++;
								str.append("-=");							
							}
						}if(line.contains("*=")){
							opsCount += StringUtils.countMatches(line, "*=");
							assOps += StringUtils.countMatches(line, "*=");
							if(!str.toString().contains("*=")){
								uniqueOps++;
								str.append("*=");							
							}
						}if(line.contains("/=")){
							opsCount += StringUtils.countMatches(line, "/=");
							assOps += StringUtils.countMatches(line, "/=");
							if(!str.toString().contains("/=")){
								uniqueOps++;
								str.append("/=");							
							}
						}if(line.contains("%=")){
							opsCount += StringUtils.countMatches(line, "%=");
							assOps += StringUtils.countMatches(line, "%=");
							if(!str.toString().contains("%=")){
								uniqueOps++;
								str.append("%=");							
							}
						}if(line.contains("<<=")){
							opsCount += StringUtils.countMatches(line, "<<=");
							assOps += StringUtils.countMatches(line, "<<=");
							if(!str.toString().contains("<<=")){
								uniqueOps++;
								str.append("<<=");							
							}
						}if(line.contains(">>=")){
							opsCount += StringUtils.countMatches(line, ">>=");
							assOps += StringUtils.countMatches(line, ">>=");
							if(!str.toString().contains(">>=")){
								uniqueOps++;
								str.append(">>=");							
							}
						}if(line.contains("&=")){
							opsCount += StringUtils.countMatches(line, "&=");
							assOps += StringUtils.countMatches(line, "&=");
							if(!str.toString().contains("&=")){
								uniqueOps++;
								str.append("&=");							
							}
						}if(line.contains("^=")){
							opsCount += StringUtils.countMatches(line, "^=");
							assOps += StringUtils.countMatches(line, "^=");
							if(!str.toString().contains("^=")){
								uniqueOps++;
								str.append("^=");							
							}
						}if(line.contains("|=")){
							opsCount += StringUtils.countMatches(line, "|=");
							assOps += StringUtils.countMatches(line, "|=");
							if(!str.toString().contains("|=")){
								uniqueOps++;
								str.append("|=");							
							}
						}
						
						
						//Operands
						if(line.contains("int ")){
							opdCount += StringUtils.countMatches(line, "int ");
							if(!str2.toString().contains("int ")){
								uniqueOpd++;
								str2.append("int ");							
							}
						}if(line.contains("string ")){
							opdCount += StringUtils.countMatches(line, "string ");
							if(!str2.toString().contains("string ")){
								uniqueOpd++;
								str2.append("string ");							
							}
						}if(line.contains("float ")){
							opdCount += StringUtils.countMatches(line, "float ");
							if(!str2.toString().contains("float ")){
								uniqueOpd++;
								str2.append("float ");							
							}
						}if(line.contains("void ")){
							opdCount += StringUtils.countMatches(line, "void ");
							if(!str2.toString().contains("void ")){
								uniqueOpd++;
								str2.append("void ");							
							}
						}if(line.contains("double ")){
							opdCount += StringUtils.countMatches(line, "double ");

							if(!str2.toString().contains("double ")){
								uniqueOpd++;
								str2.append("double ");							
							}
						}if(line.contains("\"")){
							opdCount += StringUtils.countMatches(line, "\"");
//							operands = operand.split("\"");
							if(!str2.toString().contains("\"")){
								uniqueOpd++;
								str2.append("\"");							
							}
						}if(line.contains("Exception")){
							opdCount += StringUtils.countMatches(line, "Exception");
//							operands = operand.split("Exception");
							if(!str2.toString().contains("Exception")){
								uniqueOpd++;
								str2.append("Exception");							
							}
						}
						
					}
					
					scanner.close();
					
					try {
						JSONObject item1 = new JSONObject();
					    item1.put("name", "Operator Count");
					    item1.put("value", opsCount);
					    items.add(item1);
						
					    JSONObject item2 = new JSONObject();
					    item2.put("name", "Unique Operator Count");
					    item2.put("value", uniqueOps);
					    items.add(item2);
					    

					    JSONObject item3 = new JSONObject();
					    item3.put("name", "Artihmatic Operator Count");
					    item3.put("value", arOps);
					    items.add(item3);
					    
					    JSONObject item4 = new JSONObject();
					    item4.put("name", "Relational Operator Count");
					    item4.put("value", reOps);
					    items.add(item4);
					    

					    JSONObject item5 = new JSONObject();
					    item5.put("name", "Bitwise Operator Count");
					    item5.put("value", bitOps);
					    items.add(item5);
					    
					    JSONObject item6 = new JSONObject();
					    item6.put("name", "Logical Operator Count");
					    item6.put("value", logOps);
					    items.add(item6);

					    JSONObject item7 = new JSONObject();
					    item7.put("name", "Assignment Operator Count");
					    item7.put("value", assOps);
					    items.add(item7);
					    
					    JSONObject item8 = new JSONObject();
					    item8.put("name", "Other Operator Count");
					    item8.put("value", otherOps);
					    items.add(item8);
					    

					    JSONObject item9 = new JSONObject();
					    item9.put("name", "Operand Count");
					    item9.put("value", opdCount);
					    items.add(item9);
					    
					    JSONObject item10 = new JSONObject();
					    item10.put("name", "Unique Operand Count");
					    item10.put("value", uniqueOpd);
					    items.add(item10);
					    
//					    obj1.put("Method Name", node.getName().toString());
//						obj1.put("value", new JSONArray(items));
					    
					}
					catch(Exception e) {
						
					}
					
				}
			
				System.out.println(obj1);
				return true;
				
			}
			
		});
		
		
		System.out.println(items);
		return (new JSONArray(items));
	}
	
	
	public static JSONArray Exception_file(String strFile){
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(strFile.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
			
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		

		JSONObject obj1 = new JSONObject();  

	    Collection<JSONObject> items = new ArrayList<JSONObject>();
	    
	    
	    String str3 = strFile;
		
		String[] lines = str3.split(System.getProperty("line.separator"));
		
		int opsCount = 0;
		int uniqueOps = 0;
		
		int opdCount = 0;
		int uniqueOpd = 0;
		
		
		int arOps = 0;
		int reOps = 0;
		int logOps = 0;
		int bitOps = 0;
		int assOps = 0;
		int otherOps = 0;
		
		
		StringBuilder str = new StringBuilder();
		StringBuilder str2 = new StringBuilder();
		
		Scanner scanner = new Scanner(str3);
		
		
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
//				System.out.println(node.getName());
				
				//Other Operators
				if(line.contains("System.out.println")){
					opsCount += StringUtils.countMatches(line, "System.out.println");
					otherOps += StringUtils.countMatches(line, "System.out.println");

					if(!str.toString().contains("System.out.println")){
						uniqueOps++;
						str.append("System.out.println");							
					}	
				}
				
				if(line.contains(".")){
					opsCount += StringUtils.countMatches(line, ".");
					otherOps += StringUtils.countMatches(line, ".");
					if(!str.toString().contains(".")){
						uniqueOps++;
						str.append(".");							
					}	
				}if(line.contains("{")){
					opsCount += StringUtils.countMatches(line, "{");
					otherOps += StringUtils.countMatches(line, "{");
					if(!str.toString().contains("{")){
						uniqueOps++;
						str.append("{");							
					}	
				}if(line.contains("(")){
					opsCount += StringUtils.countMatches(line, "(");
					otherOps += StringUtils.countMatches(line, "(");
					if(!str.toString().contains("(")){
						uniqueOps++;
						str.append("(");							
					}	
				}if(line.contains("[")){
					opsCount += StringUtils.countMatches(line, "[");
					otherOps += StringUtils.countMatches(line, "[");
					if(!str.toString().contains("[")){
						uniqueOps++;
						str.append("[");							
					}	
				}if(line.contains("public")){
					opsCount += StringUtils.countMatches(line, "public");
					otherOps += StringUtils.countMatches(line, "public");
					if(!str.toString().contains("public")){
						uniqueOps++;
						str.append("public");							
					}
				}if(line.contains("private")){
					opsCount += StringUtils.countMatches(line, "private");
					otherOps += StringUtils.countMatches(line, "private");
					if(!str.toString().contains("private")){
						uniqueOps++;
						str.append("private");							
					}
				}if(line.contains("protected")){
					opsCount += StringUtils.countMatches(line, "protected");
					otherOps += StringUtils.countMatches(line, "protected");
					if(!str.toString().contains("protected")){
						uniqueOps++;
						str.append("protected");							
					}
				}if(line.contains("static")){
					opsCount += StringUtils.countMatches(line, "static");
					otherOps += StringUtils.countMatches(line, "static");
					if(!str.toString().contains("static")){
						uniqueOps++;
						str.append("static");							
					}
				}if(line.contains("local")){
					opsCount += StringUtils.countMatches(line, "local");
					otherOps += StringUtils.countMatches(line, "local");
					if(!str.toString().contains("local")){
						uniqueOps++;
						str.append("local");							
					}
				}if(line.contains("instance")){
					opsCount += StringUtils.countMatches(line, "instance");
					otherOps += StringUtils.countMatches(line, "instance");
					if(!str.toString().contains("instance")){
						uniqueOps++;
						str.append("instance");							
					}
				}if(line.contains("for(")){
					opsCount += StringUtils.countMatches(line, "for(");
					otherOps += StringUtils.countMatches(line, "for(");
					if(!str.toString().contains("for(")){
						uniqueOps++;
						str.append("for(");							
					}
				}if(line.contains("if(")){
					opsCount += StringUtils.countMatches(line, "if(");
					otherOps += StringUtils.countMatches(line, "if(");
					if(!str.toString().contains("if(")){
						uniqueOps++;
						str.append("if(");							
					}
				}if(line.contains(";")){
					opsCount += StringUtils.countMatches(line, ";");
					otherOps += StringUtils.countMatches(line, ";");
					if(!str.toString().contains(";")){
						uniqueOps++;
						str.append(";");							
					}
				}if(line.contains("while(")){
					opsCount += StringUtils.countMatches(line, "while(");
					otherOps += StringUtils.countMatches(line, "while(");
					if(!str.toString().contains("while(")){
						uniqueOps++;
						str.append("while(");							
					}
				}if(line.contains("try")){
					opsCount += StringUtils.countMatches(line, "try");
					otherOps += StringUtils.countMatches(line, "try");
					if(!str.toString().contains("while(")){
						uniqueOps++;
						str.append("try");							
					}
				}if(line.contains("do")){
					opsCount += StringUtils.countMatches(line, "do");
					otherOps += StringUtils.countMatches(line, "do");
					if(!str.toString().contains("do")){
						uniqueOps++;
						str.append("do");							
					}
				}
				
				//Arithmatic Operators
				if(line.contains("+") && !line.contains("+=") && !line.contains("++")){
					opsCount += StringUtils.countMatches(line, "+");
					arOps += StringUtils.countMatches(line, "+");
					if(!str.toString().contains("+")){
						uniqueOps++;
						str.append("+");							
					}
				}if(line.contains("-") && !line.contains("-=") && !line.contains("--")){
					opsCount += StringUtils.countMatches(line, "-");
					arOps += StringUtils.countMatches(line, "-");
					if(!str.toString().contains("-")){
						uniqueOps++;
						str.append("-");							
					}
				}if(line.contains("*") && !line.contains("*=")){
					opsCount += StringUtils.countMatches(line, "*");
					arOps += StringUtils.countMatches(line, "*");
					if(!str.toString().contains("*")){
						uniqueOps++;
						str.append("*");							
					}
				}if(line.contains("/") && !line.contains("/=")){
					opsCount += StringUtils.countMatches(line, "/");
					arOps += StringUtils.countMatches(line, "/");
					if(!str.toString().contains("/")){
						uniqueOps++;
						str.append("/");							
					}
				}if(line.contains("%") && !line.contains("%=")){
					opsCount += StringUtils.countMatches(line, "%");
					arOps += StringUtils.countMatches(line, "%");
					if(!str.toString().contains("%")){
						uniqueOps++;
						str.append("%");							
					}
				}if(line.contains("++")){
					opsCount += StringUtils.countMatches(line, "++");
					arOps += StringUtils.countMatches(line, "++");
					if(!str.toString().contains("++")){
						uniqueOps++;
						str.append("++");							
					}
				}
				if(line.contains("--")){
					opsCount += StringUtils.countMatches(line, "--");
					arOps += StringUtils.countMatches(line, "--");
					if(!str.toString().contains("--")){
						uniqueOps++;
						str.append("--");							
					}
				}
				
				//Relational Operators
				if(line.contains("!=")){
					opsCount += StringUtils.countMatches(line, "!=");
					reOps += StringUtils.countMatches(line, "!=");
					if(!str.toString().contains("!=")){
						uniqueOps++;
						str.append("!=");							
					}
				}if(line.contains("==")){
					opsCount += StringUtils.countMatches(line, "==");
					reOps += StringUtils.countMatches(line, "==");
					if(!str.toString().contains("==")){
						uniqueOps++;
						str.append("==");							
					}
				}if(line.contains(">") && !line.contains(">=")){
					opsCount += StringUtils.countMatches(line, ">");
					reOps += StringUtils.countMatches(line, ">");
					if(!str.toString().contains(">")){
						uniqueOps++;
						str.append(">");							
					}
				}if(line.contains("<") && !line.contains("<=")){
					opsCount += StringUtils.countMatches(line, "<");
					reOps += StringUtils.countMatches(line, "<");
					if(!str.toString().contains("<")){
						uniqueOps++;
						str.append("<");							
					}
				}if(line.contains(">=")){
					opsCount += StringUtils.countMatches(line, ">=");
					reOps += StringUtils.countMatches(line, ">=");
					if(!str.toString().contains(">=")){
						uniqueOps++;
						str.append(">=");							
					}
				}if(line.contains("<=")){
					opsCount += StringUtils.countMatches(line, "<=");
					reOps += StringUtils.countMatches(line, "<=");
					if(!str.toString().contains("<=")){
						uniqueOps++;
						str.append("<=");							
					}
				}
				
				
				//Logical Operators
				if(line.contains("&&")){
					opsCount += StringUtils.countMatches(line, "&&");
					logOps += StringUtils.countMatches(line, "&&");
					if(!str.toString().contains("&&")){
						uniqueOps++;
						str.append("&&");							
					}
				}if(line.contains("||")){
					opsCount += StringUtils.countMatches(line, "||");
					logOps += StringUtils.countMatches(line, "||");
					if(!str.toString().contains("||")){
						uniqueOps++;
						str.append("||");							
					}
				}if(line.contains("!") && !line.contains("!=")){
					opsCount += StringUtils.countMatches(line, "!");
					logOps += StringUtils.countMatches(line, "!");
					if(!str.toString().contains("!")){
						uniqueOps++;
						str.append("!");							
					}
				}
				
				
				//Bitwise Operators
				if(line.contains("&") && !line.contains("&=")){
					opsCount += StringUtils.countMatches(line, "&");
					bitOps += StringUtils.countMatches(line, "&");
					if(!str.toString().contains("&")){
						uniqueOps++;
						str.append("&");							
					}
				}if(line.contains("|") && !line.contains("||") && !line.contains("|=")){
					opsCount += StringUtils.countMatches(line, "|");
					bitOps += StringUtils.countMatches(line, "|");
					if(!str.toString().contains("|")){
						uniqueOps++;
						str.append("|");							
					}
				}if(line.contains("~")){
					opsCount += StringUtils.countMatches(line, "~");
					bitOps += StringUtils.countMatches(line, "~");
					if(!str.toString().contains("~")){
						uniqueOps++;
						str.append("~");							
					}
				}if(line.contains("<<") && !line.contains("<<=")){
					opsCount += StringUtils.countMatches(line, "<<");
					bitOps += StringUtils.countMatches(line, "<<");
					if(!str.toString().contains("<<")){
						uniqueOps++;
						str.append("<<");							
					}
				}if(line.contains(">>") && !line.contains(">>=")){
					opsCount += StringUtils.countMatches(line, ">>");
					bitOps += StringUtils.countMatches(line, ">>");
					if(!str.toString().contains(">>")){
						uniqueOps++;
						str.append(">>");							
					}
				}
				
				//Assignment Operators
				if(line.contains("=") && !line.contains("==")){
					opsCount += StringUtils.countMatches(line, "=");
					assOps += StringUtils.countMatches(line, "=");
					if(!str.toString().contains("=")){
						uniqueOps++;
						str.append("=");							
					}
				}if(line.contains("+=")){
					opsCount += StringUtils.countMatches(line, "+=");
					assOps += StringUtils.countMatches(line, "+=");
					if(!str.toString().contains("+=")){
						uniqueOps++;
						str.append("+=");							
					}
				}if(line.contains("-=")){
					opsCount += StringUtils.countMatches(line, "-=");
					assOps += StringUtils.countMatches(line, "-=");
					if(!str.toString().contains("-=")){
						uniqueOps++;
						str.append("-=");							
					}
				}if(line.contains("*=")){
					opsCount += StringUtils.countMatches(line, "*=");
					assOps += StringUtils.countMatches(line, "*=");
					if(!str.toString().contains("*=")){
						uniqueOps++;
						str.append("*=");							
					}
				}if(line.contains("/=")){
					opsCount += StringUtils.countMatches(line, "/=");
					assOps += StringUtils.countMatches(line, "/=");
					if(!str.toString().contains("/=")){
						uniqueOps++;
						str.append("/=");							
					}
				}if(line.contains("%=")){
					opsCount += StringUtils.countMatches(line, "%=");
					assOps += StringUtils.countMatches(line, "%=");
					if(!str.toString().contains("%=")){
						uniqueOps++;
						str.append("%=");							
					}
				}if(line.contains("<<=")){
					opsCount += StringUtils.countMatches(line, "<<=");
					assOps += StringUtils.countMatches(line, "<<=");
					if(!str.toString().contains("<<=")){
						uniqueOps++;
						str.append("<<=");							
					}
				}if(line.contains(">>=")){
					opsCount += StringUtils.countMatches(line, ">>=");
					assOps += StringUtils.countMatches(line, ">>=");
					if(!str.toString().contains(">>=")){
						uniqueOps++;
						str.append(">>=");							
					}
				}if(line.contains("&=")){
					opsCount += StringUtils.countMatches(line, "&=");
					assOps += StringUtils.countMatches(line, "&=");
					if(!str.toString().contains("&=")){
						uniqueOps++;
						str.append("&=");							
					}
				}if(line.contains("^=")){
					opsCount += StringUtils.countMatches(line, "^=");
					assOps += StringUtils.countMatches(line, "^=");
					if(!str.toString().contains("^=")){
						uniqueOps++;
						str.append("^=");							
					}
				}if(line.contains("|=")){
					opsCount += StringUtils.countMatches(line, "|=");
					assOps += StringUtils.countMatches(line, "|=");
					if(!str.toString().contains("|=")){
						uniqueOps++;
						str.append("|=");							
					}
				}
				
				
				//Operands
				if(line.contains("int ")){
					opdCount += StringUtils.countMatches(line, "int ");
					if(!str2.toString().contains("int ")){
						uniqueOpd++;
						str2.append("int ");							
					}
				}if(line.contains("string ")){
					opdCount += StringUtils.countMatches(line, "string ");
					if(!str2.toString().contains("string ")){
						uniqueOpd++;
						str2.append("string ");							
					}
				}if(line.contains("float ")){
					opdCount += StringUtils.countMatches(line, "float ");
					if(!str2.toString().contains("float ")){
						uniqueOpd++;
						str2.append("float ");							
					}
				}if(line.contains("void ")){
					opdCount += StringUtils.countMatches(line, "void ");
					if(!str2.toString().contains("void ")){
						uniqueOpd++;
						str2.append("void ");							
					}
				}if(line.contains("double ")){
					opdCount += StringUtils.countMatches(line, "double ");

					if(!str2.toString().contains("double ")){
						uniqueOpd++;
						str2.append("double ");							
					}
				}if(line.contains("\"")){
					opdCount += StringUtils.countMatches(line, "\"");
//					operands = operand.split("\"");
					if(!str2.toString().contains("\"")){
						uniqueOpd++;
						str2.append("\"");							
					}
				}if(line.contains("Exception")){
					opdCount += StringUtils.countMatches(line, "Exception");
//					operands = operand.split("Exception");
					if(!str2.toString().contains("Exception")){
						uniqueOpd++;
						str2.append("Exception");							
					}
				}
				
			}
			
			scanner.close();
			
			try {
				JSONObject item1 = new JSONObject();
			    item1.put("name", "Operator Count");
			    item1.put("value", opsCount);
			    items.add(item1);
				
			    JSONObject item2 = new JSONObject();
			    item2.put("name", "Unique Operator Count");
			    item2.put("value", uniqueOps);
			    items.add(item2);
			    

			    JSONObject item3 = new JSONObject();
			    item3.put("name", "Artihmatic Operator Count");
			    item3.put("value", arOps);
			    items.add(item3);
			    
			    JSONObject item4 = new JSONObject();
			    item4.put("name", "Relational Operator Count");
			    item4.put("value", reOps);
			    items.add(item4);
			    

			    JSONObject item5 = new JSONObject();
			    item5.put("name", "Bitwise Operator Count");
			    item5.put("value", bitOps);
			    items.add(item5);
			    
			    JSONObject item6 = new JSONObject();
			    item6.put("name", "Logical Operator Count");
			    item6.put("value", logOps);
			    items.add(item6);

			    JSONObject item7 = new JSONObject();
			    item7.put("name", "Assignment Operator Count");
			    item7.put("value", assOps);
			    items.add(item7);
			    
			    JSONObject item8 = new JSONObject();
			    item8.put("name", "Other Operator Count");
			    item8.put("value", otherOps);
			    items.add(item8);
			    

			    JSONObject item9 = new JSONObject();
			    item9.put("name", "Operand Count");
			    item9.put("value", opdCount);
			    items.add(item9);
			    
			    JSONObject item10 = new JSONObject();
			    item10.put("name", "Unique Operand Count");
			    item10.put("value", uniqueOpd);
			    items.add(item10);
			    
//			    obj1.put("Method Name", node.getName().toString());
//				obj1.put("value", new JSONArray(items));
			    
			}
			catch(Exception e) {
				
			}
			
		
	
		System.out.println(items);
			
			return (new JSONArray(items));
		
		
	}
	
	//start
	 static JSONArray itemsOperands = new JSONArray();

	
	public static JSONArray Exception_root(String strFile){

		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(strFile.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
			
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		

		JSONObject obj1 = new JSONObject();  

	    Collection<JSONObject> items = new ArrayList<JSONObject>();
	    
	    
	    String str3 = strFile;
		
		String[] lines = str3.split(System.getProperty("line.separator"));
		
		int opsCount = 0;
		int uniqueOps = 0;
		
		int opdCount = 0;
		int uniqueOpd = 0;
		
		
		int arOps = 0;
		int reOps = 0;
		int logOps = 0;
		int bitOps = 0;
		int assOps = 0;
		int otherOps = 0;
		
		
		StringBuilder str = new StringBuilder();
		StringBuilder str2 = new StringBuilder();
		
		Scanner scanner = new Scanner(str3);
		
		
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
//				System.out.println(node.getName());
				
				//Other Operators
				if(line.contains("System.out.println")){
					opsCount += StringUtils.countMatches(line, "System.out.println");
					otherOps += StringUtils.countMatches(line, "System.out.println");

					if(!str.toString().contains("System.out.println")){
						uniqueOps++;
						str.append("System.out.println");							
					}	
				}
				
				if(line.contains(".")){
					opsCount += StringUtils.countMatches(line, ".");
					otherOps += StringUtils.countMatches(line, ".");
					if(!str.toString().contains(".")){
						uniqueOps++;
						str.append(".");							
					}	
				}if(line.contains("{")){
					opsCount += StringUtils.countMatches(line, "{");
					otherOps += StringUtils.countMatches(line, "{");
					if(!str.toString().contains("{")){
						uniqueOps++;
						str.append("{");							
					}	
				}if(line.contains("(")){
					opsCount += StringUtils.countMatches(line, "(");
					otherOps += StringUtils.countMatches(line, "(");
					if(!str.toString().contains("(")){
						uniqueOps++;
						str.append("(");							
					}	
				}if(line.contains("[")){
					opsCount += StringUtils.countMatches(line, "[");
					otherOps += StringUtils.countMatches(line, "[");
					if(!str.toString().contains("[")){
						uniqueOps++;
						str.append("[");							
					}	
				}if(line.contains("public")){
					opsCount += StringUtils.countMatches(line, "public");
					otherOps += StringUtils.countMatches(line, "public");
					if(!str.toString().contains("public")){
						uniqueOps++;
						str.append("public");							
					}
				}if(line.contains("private")){
					opsCount += StringUtils.countMatches(line, "private");
					otherOps += StringUtils.countMatches(line, "private");
					if(!str.toString().contains("private")){
						uniqueOps++;
						str.append("private");							
					}
				}if(line.contains("protected")){
					opsCount += StringUtils.countMatches(line, "protected");
					otherOps += StringUtils.countMatches(line, "protected");
					if(!str.toString().contains("protected")){
						uniqueOps++;
						str.append("protected");							
					}
				}if(line.contains("static")){
					opsCount += StringUtils.countMatches(line, "static");
					otherOps += StringUtils.countMatches(line, "static");
					if(!str.toString().contains("static")){
						uniqueOps++;
						str.append("static");							
					}
				}if(line.contains("local")){
					opsCount += StringUtils.countMatches(line, "local");
					otherOps += StringUtils.countMatches(line, "local");
					if(!str.toString().contains("local")){
						uniqueOps++;
						str.append("local");							
					}
				}if(line.contains("instance")){
					opsCount += StringUtils.countMatches(line, "instance");
					otherOps += StringUtils.countMatches(line, "instance");
					if(!str.toString().contains("instance")){
						uniqueOps++;
						str.append("instance");							
					}
				}if(line.contains("for(")){
					opsCount += StringUtils.countMatches(line, "for(");
					otherOps += StringUtils.countMatches(line, "for(");
					if(!str.toString().contains("for(")){
						uniqueOps++;
						str.append("for(");							
					}
				}if(line.contains("if(")){
					opsCount += StringUtils.countMatches(line, "if(");
					otherOps += StringUtils.countMatches(line, "if(");
					if(!str.toString().contains("if(")){
						uniqueOps++;
						str.append("if(");							
					}
				}if(line.contains(";")){
					opsCount += StringUtils.countMatches(line, ";");
					otherOps += StringUtils.countMatches(line, ";");
					if(!str.toString().contains(";")){
						uniqueOps++;
						str.append(";");							
					}
				}if(line.contains("while(")){
					opsCount += StringUtils.countMatches(line, "while(");
					otherOps += StringUtils.countMatches(line, "while(");
					if(!str.toString().contains("while(")){
						uniqueOps++;
						str.append("while(");							
					}
				}if(line.contains("try")){
					opsCount += StringUtils.countMatches(line, "try");
					otherOps += StringUtils.countMatches(line, "try");
					if(!str.toString().contains("while(")){
						uniqueOps++;
						str.append("try");							
					}
				}if(line.contains("do")){
					opsCount += StringUtils.countMatches(line, "do");
					otherOps += StringUtils.countMatches(line, "do");
					if(!str.toString().contains("do")){
						uniqueOps++;
						str.append("do");							
					}
				}
				
				//Arithmatic Operators
				if(line.contains("+") && !line.contains("+=") && !line.contains("++")){
					opsCount += StringUtils.countMatches(line, "+");
					arOps += StringUtils.countMatches(line, "+");
					if(!str.toString().contains("+")){
						uniqueOps++;
						str.append("+");							
					}
				}if(line.contains("-") && !line.contains("-=") && !line.contains("--")){
					opsCount += StringUtils.countMatches(line, "-");
					arOps += StringUtils.countMatches(line, "-");
					if(!str.toString().contains("-")){
						uniqueOps++;
						str.append("-");							
					}
				}if(line.contains("*") && !line.contains("*=")){
					opsCount += StringUtils.countMatches(line, "*");
					arOps += StringUtils.countMatches(line, "*");
					if(!str.toString().contains("*")){
						uniqueOps++;
						str.append("*");							
					}
				}if(line.contains("/") && !line.contains("/=")){
					opsCount += StringUtils.countMatches(line, "/");
					arOps += StringUtils.countMatches(line, "/");
					if(!str.toString().contains("/")){
						uniqueOps++;
						str.append("/");							
					}
				}if(line.contains("%") && !line.contains("%=")){
					opsCount += StringUtils.countMatches(line, "%");
					arOps += StringUtils.countMatches(line, "%");
					if(!str.toString().contains("%")){
						uniqueOps++;
						str.append("%");							
					}
				}if(line.contains("++")){
					opsCount += StringUtils.countMatches(line, "++");
					arOps += StringUtils.countMatches(line, "++");
					if(!str.toString().contains("++")){
						uniqueOps++;
						str.append("++");							
					}
				}
				if(line.contains("--")){
					opsCount += StringUtils.countMatches(line, "--");
					arOps += StringUtils.countMatches(line, "--");
					if(!str.toString().contains("--")){
						uniqueOps++;
						str.append("--");							
					}
				}
				
				//Relational Operators
				if(line.contains("!=")){
					opsCount += StringUtils.countMatches(line, "!=");
					reOps += StringUtils.countMatches(line, "!=");
					if(!str.toString().contains("!=")){
						uniqueOps++;
						str.append("!=");							
					}
				}if(line.contains("==")){
					opsCount += StringUtils.countMatches(line, "==");
					reOps += StringUtils.countMatches(line, "==");
					if(!str.toString().contains("==")){
						uniqueOps++;
						str.append("==");							
					}
				}if(line.contains(">") && !line.contains(">=")){
					opsCount += StringUtils.countMatches(line, ">");
					reOps += StringUtils.countMatches(line, ">");
					if(!str.toString().contains(">")){
						uniqueOps++;
						str.append(">");							
					}
				}if(line.contains("<") && !line.contains("<=")){
					opsCount += StringUtils.countMatches(line, "<");
					reOps += StringUtils.countMatches(line, "<");
					if(!str.toString().contains("<")){
						uniqueOps++;
						str.append("<");							
					}
				}if(line.contains(">=")){
					opsCount += StringUtils.countMatches(line, ">=");
					reOps += StringUtils.countMatches(line, ">=");
					if(!str.toString().contains(">=")){
						uniqueOps++;
						str.append(">=");							
					}
				}if(line.contains("<=")){
					opsCount += StringUtils.countMatches(line, "<=");
					reOps += StringUtils.countMatches(line, "<=");
					if(!str.toString().contains("<=")){
						uniqueOps++;
						str.append("<=");							
					}
				}
				
				
				//Logical Operators
				if(line.contains("&&")){
					opsCount += StringUtils.countMatches(line, "&&");
					logOps += StringUtils.countMatches(line, "&&");
					if(!str.toString().contains("&&")){
						uniqueOps++;
						str.append("&&");							
					}
				}if(line.contains("||")){
					opsCount += StringUtils.countMatches(line, "||");
					logOps += StringUtils.countMatches(line, "||");
					if(!str.toString().contains("||")){
						uniqueOps++;
						str.append("||");							
					}
				}if(line.contains("!") && !line.contains("!=")){
					opsCount += StringUtils.countMatches(line, "!");
					logOps += StringUtils.countMatches(line, "!");
					if(!str.toString().contains("!")){
						uniqueOps++;
						str.append("!");							
					}
				}
				
				
				//Bitwise Operators
				if(line.contains("&") && !line.contains("&=")){
					opsCount += StringUtils.countMatches(line, "&");
					bitOps += StringUtils.countMatches(line, "&");
					if(!str.toString().contains("&")){
						uniqueOps++;
						str.append("&");							
					}
				}if(line.contains("|") && !line.contains("||") && !line.contains("|=")){
					opsCount += StringUtils.countMatches(line, "|");
					bitOps += StringUtils.countMatches(line, "|");
					if(!str.toString().contains("|")){
						uniqueOps++;
						str.append("|");							
					}
				}if(line.contains("~")){
					opsCount += StringUtils.countMatches(line, "~");
					bitOps += StringUtils.countMatches(line, "~");
					if(!str.toString().contains("~")){
						uniqueOps++;
						str.append("~");							
					}
				}if(line.contains("<<") && !line.contains("<<=")){
					opsCount += StringUtils.countMatches(line, "<<");
					bitOps += StringUtils.countMatches(line, "<<");
					if(!str.toString().contains("<<")){
						uniqueOps++;
						str.append("<<");							
					}
				}if(line.contains(">>") && !line.contains(">>=")){
					opsCount += StringUtils.countMatches(line, ">>");
					bitOps += StringUtils.countMatches(line, ">>");
					if(!str.toString().contains(">>")){
						uniqueOps++;
						str.append(">>");							
					}
				}
				
				//Assignment Operators
				if(line.contains("=") && !line.contains("==")){
					opsCount += StringUtils.countMatches(line, "=");
					assOps += StringUtils.countMatches(line, "=");
					if(!str.toString().contains("=")){
						uniqueOps++;
						str.append("=");							
					}
				}if(line.contains("+=")){
					opsCount += StringUtils.countMatches(line, "+=");
					assOps += StringUtils.countMatches(line, "+=");
					if(!str.toString().contains("+=")){
						uniqueOps++;
						str.append("+=");							
					}
				}if(line.contains("-=")){
					opsCount += StringUtils.countMatches(line, "-=");
					assOps += StringUtils.countMatches(line, "-=");
					if(!str.toString().contains("-=")){
						uniqueOps++;
						str.append("-=");							
					}
				}if(line.contains("*=")){
					opsCount += StringUtils.countMatches(line, "*=");
					assOps += StringUtils.countMatches(line, "*=");
					if(!str.toString().contains("*=")){
						uniqueOps++;
						str.append("*=");							
					}
				}if(line.contains("/=")){
					opsCount += StringUtils.countMatches(line, "/=");
					assOps += StringUtils.countMatches(line, "/=");
					if(!str.toString().contains("/=")){
						uniqueOps++;
						str.append("/=");							
					}
				}if(line.contains("%=")){
					opsCount += StringUtils.countMatches(line, "%=");
					assOps += StringUtils.countMatches(line, "%=");
					if(!str.toString().contains("%=")){
						uniqueOps++;
						str.append("%=");							
					}
				}if(line.contains("<<=")){
					opsCount += StringUtils.countMatches(line, "<<=");
					assOps += StringUtils.countMatches(line, "<<=");
					if(!str.toString().contains("<<=")){
						uniqueOps++;
						str.append("<<=");							
					}
				}if(line.contains(">>=")){
					opsCount += StringUtils.countMatches(line, ">>=");
					assOps += StringUtils.countMatches(line, ">>=");
					if(!str.toString().contains(">>=")){
						uniqueOps++;
						str.append(">>=");							
					}
				}if(line.contains("&=")){
					opsCount += StringUtils.countMatches(line, "&=");
					assOps += StringUtils.countMatches(line, "&=");
					if(!str.toString().contains("&=")){
						uniqueOps++;
						str.append("&=");							
					}
				}if(line.contains("^=")){
					opsCount += StringUtils.countMatches(line, "^=");
					assOps += StringUtils.countMatches(line, "^=");
					if(!str.toString().contains("^=")){
						uniqueOps++;
						str.append("^=");							
					}
				}if(line.contains("|=")){
					opsCount += StringUtils.countMatches(line, "|=");
					assOps += StringUtils.countMatches(line, "|=");
					if(!str.toString().contains("|=")){
						uniqueOps++;
						str.append("|=");							
					}
				}
				
				
				//Operands
				if(line.contains("int ")){
					opdCount += StringUtils.countMatches(line, "int ");
					if(!str2.toString().contains("int ")){
						uniqueOpd++;
						str2.append("int ");							
					}
				}if(line.contains("string ")){
					opdCount += StringUtils.countMatches(line, "string ");
					if(!str2.toString().contains("string ")){
						uniqueOpd++;
						str2.append("string ");							
					}
				}if(line.contains("float ")){
					opdCount += StringUtils.countMatches(line, "float ");
					if(!str2.toString().contains("float ")){
						uniqueOpd++;
						str2.append("float ");							
					}
				}if(line.contains("void ")){
					opdCount += StringUtils.countMatches(line, "void ");
					if(!str2.toString().contains("void ")){
						uniqueOpd++;
						str2.append("void ");							
					}
				}if(line.contains("double ")){
					opdCount += StringUtils.countMatches(line, "double ");

					if(!str2.toString().contains("double ")){
						uniqueOpd++;
						str2.append("double ");							
					}
				}if(line.contains("\"")){
					opdCount += StringUtils.countMatches(line, "\"");
//					operands = operand.split("\"");
					if(!str2.toString().contains("\"")){
						uniqueOpd++;
						str2.append("\"");							
					}
				}if(line.contains("Exception")){
					opdCount += StringUtils.countMatches(line, "Exception");
//					operands = operand.split("Exception");
					if(!str2.toString().contains("Exception")){
						uniqueOpd++;
						str2.append("Exception");							
					}
				}
				
			}
			
			scanner.close();
			
			try {
				JSONObject item1 = new JSONObject();
			    item1.put("name", "Operator Count");
			    item1.put("value", opsCount);
			    items.add(item1);
				
			    JSONObject item2 = new JSONObject();
			    item2.put("name", "Unique Operator Count");
			    item2.put("value", uniqueOps);
			    items.add(item2);
			    

			    JSONObject item3 = new JSONObject();
			    item3.put("name", "Artihmatic Operator Count");
			    item3.put("value", arOps);
			    items.add(item3);
			    
			    JSONObject item4 = new JSONObject();
			    item4.put("name", "Relational Operator Count");
			    item4.put("value", reOps);
			    items.add(item4);
			    

			    JSONObject item5 = new JSONObject();
			    item5.put("name", "Bitwise Operator Count");
			    item5.put("value", bitOps);
			    items.add(item5);
			    
			    JSONObject item6 = new JSONObject();
			    item6.put("name", "Logical Operator Count");
			    item6.put("value", logOps);
			    items.add(item6);

			    JSONObject item7 = new JSONObject();
			    item7.put("name", "Assignment Operator Count");
			    item7.put("value", assOps);
			    items.add(item7);
			    
			    JSONObject item8 = new JSONObject();
			    item8.put("name", "Other Operator Count");
			    item8.put("value", otherOps);
			    items.add(item8);
			    

			    JSONObject item9 = new JSONObject();
			    item9.put("name", "Operand Count");
			    item9.put("value", opdCount);
			    items.add(item9);
			    
			    JSONObject item10 = new JSONObject();
			    item10.put("name", "Unique Operand Count");
			    item10.put("value", uniqueOpd);
			    items.add(item10);
			    
//			    obj1.put("Method Name", node.getName().toString());
//				obj1.put("value", new JSONArray(items));
			    
			}
			catch(Exception e) {
				
			}
			
			if(itemsOperands.length() ==0){
				JSONObject s = new JSONObject();
				try {
					//s = (JSONObject) ((JSONArray) items).get(0);
					itemsOperands.put(items);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			else{
				
				 for (int i = 0, size = itemsOperands.length(); i < size; i++)
				    {
					 try {
						JSONArray objectInArray =  itemsOperands.getJSONArray(i);
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
			newArray = itemsOperands.getJSONArray(0);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newArray;
		
	
	}
	
	
	public static JSONArray Halstead(String str, String mName) {
		
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
			
			Set names = new HashSet();

			public boolean visit(MethodDeclaration node){
				methods.add(node);
				
				String str3 = node.getBody().toString();
				
				
				/*while (scanner.hasNextLine()) {
				  String line = scanner.nextLine();
				  System.out.println("line = " + line);
				}
				scanner.close();*/
				
				//String[] lines = str3.split(System.getProperty("line.separator"));
				
				int opsCount = 0;
				int uniqueOps = 0;
				
				int opdCount = 0;
				int uniqueOpd = 0;
				
				double aLength = 0.0;
				double eLength = 0.0;
				double pVocab = 0.0;
				double pVolume = 0.0;
				double pMinVol = 0.0;
				double pLevel = 0.0;
				double effort = 0.0;
				double pTimer = 0.0;
				double eLevel = 0.0;
				double bugs = 0.0;
				
				int arOps = 0;
				int reOps = 0;
				int logOps = 0;
				int bitOps = 0;
				int assOps = 0;
				int otherOps = 0;
				
				
				StringBuilder str = new StringBuilder();
				StringBuilder str2 = new StringBuilder();
				
				Scanner scanner = new Scanner(str3);
				
				if(node.getName().toString().equals(mName)){
					
					while (scanner.hasNextLine()) {
						String line = scanner.nextLine();
//						System.out.println("Line = " + line);
						
						//Other Operators
						if(line.contains("System.out.println")){
							opsCount += StringUtils.countMatches(line, "System.out.println");
							otherOps += StringUtils.countMatches(line, "System.out.println");

							if(!str.toString().contains("System.out.println")){
								uniqueOps++;
								str.append("System.out.println");							
							}	
						}
						
						if(line.contains(".")){
							opsCount += StringUtils.countMatches(line, ".");
							otherOps += StringUtils.countMatches(line, ".");
							if(!str.toString().contains(".")){
								uniqueOps++;
								str.append(".");							
							}	
						}if(line.contains("{")){
							opsCount += StringUtils.countMatches(line, "{");
							otherOps += StringUtils.countMatches(line, "{");
							if(!str.toString().contains("{")){
								uniqueOps++;
								str.append("{");							
							}	
						}if(line.contains("(")){
							opsCount += StringUtils.countMatches(line, "(");
							otherOps += StringUtils.countMatches(line, "(");
							if(!str.toString().contains("(")){
								uniqueOps++;
								str.append("(");							
							}	
						}if(line.contains("[")){
							opsCount += StringUtils.countMatches(line, "[");
							otherOps += StringUtils.countMatches(line, "[");
							if(!str.toString().contains("[")){
								uniqueOps++;
								str.append("[");							
							}	
						}if(line.contains("public")){
							opsCount += StringUtils.countMatches(line, "public");
							otherOps += StringUtils.countMatches(line, "public");
							if(!str.toString().contains("public")){
								uniqueOps++;
								str.append("public");							
							}
						}if(line.contains("private")){
							opsCount += StringUtils.countMatches(line, "private");
							otherOps += StringUtils.countMatches(line, "private");
							if(!str.toString().contains("private")){
								uniqueOps++;
								str.append("private");							
							}
						}if(line.contains("protected")){
							opsCount += StringUtils.countMatches(line, "protected");
							otherOps += StringUtils.countMatches(line, "protected");
							if(!str.toString().contains("protected")){
								uniqueOps++;
								str.append("protected");							
							}
						}if(line.contains("static")){
							opsCount += StringUtils.countMatches(line, "static");
							otherOps += StringUtils.countMatches(line, "static");
							if(!str.toString().contains("static")){
								uniqueOps++;
								str.append("static");							
							}
						}if(line.contains("local")){
							opsCount += StringUtils.countMatches(line, "local");
							otherOps += StringUtils.countMatches(line, "local");
							if(!str.toString().contains("local")){
								uniqueOps++;
								str.append("local");							
							}
						}if(line.contains("instance")){
							opsCount += StringUtils.countMatches(line, "instance");
							otherOps += StringUtils.countMatches(line, "instance");
							if(!str.toString().contains("instance")){
								uniqueOps++;
								str.append("instance");							
							}
						}if(line.contains("for(")){
							opsCount += StringUtils.countMatches(line, "for(");
							otherOps += StringUtils.countMatches(line, "for(");
							if(!str.toString().contains("for(")){
								uniqueOps++;
								str.append("for(");							
							}
						}if(line.contains("if(")){
							opsCount += StringUtils.countMatches(line, "if(");
							otherOps += StringUtils.countMatches(line, "if(");
							if(!str.toString().contains("if(")){
								uniqueOps++;
								str.append("if(");							
							}
						}if(line.contains(";")){
							opsCount += StringUtils.countMatches(line, ";");
							otherOps += StringUtils.countMatches(line, ";");
							if(!str.toString().contains(";")){
								uniqueOps++;
								str.append(";");							
							}
						}if(line.contains("while(")){
							opsCount += StringUtils.countMatches(line, "while(");
							otherOps += StringUtils.countMatches(line, "while(");
							if(!str.toString().contains("while(")){
								uniqueOps++;
								str.append("while(");							
							}
						}if(line.contains("try")){
							opsCount += StringUtils.countMatches(line, "try");
							otherOps += StringUtils.countMatches(line, "try");
							if(!str.toString().contains("while(")){
								uniqueOps++;
								str.append("try");							
							}
						}if(line.contains("do")){
							opsCount += StringUtils.countMatches(line, "do");
							otherOps += StringUtils.countMatches(line, "do");
							if(!str.toString().contains("do")){
								uniqueOps++;
								str.append("do");							
							}
						}
						
						//Arithmatic Operators
						if(line.contains("+") && !line.contains("+=") && !line.contains("++")){
							opsCount += StringUtils.countMatches(line, "+");
							arOps += StringUtils.countMatches(line, "+");
							if(!str.toString().contains("+")){
								uniqueOps++;
								str.append("+");							
							}
						}if(line.contains("-") && !line.contains("-=") && !line.contains("--")){
							opsCount += StringUtils.countMatches(line, "-");
							arOps += StringUtils.countMatches(line, "-");
							if(!str.toString().contains("-")){
								uniqueOps++;
								str.append("-");							
							}
						}if(line.contains("*") && !line.contains("*=")){
							opsCount += StringUtils.countMatches(line, "*");
							arOps += StringUtils.countMatches(line, "*");
							if(!str.toString().contains("*")){
								uniqueOps++;
								str.append("*");							
							}
						}if(line.contains("/") && !line.contains("/=")){
							opsCount += StringUtils.countMatches(line, "/");
							arOps += StringUtils.countMatches(line, "/");
							if(!str.toString().contains("/")){
								uniqueOps++;
								str.append("/");							
							}
						}if(line.contains("%") && !line.contains("%=")){
							opsCount += StringUtils.countMatches(line, "%");
							arOps += StringUtils.countMatches(line, "%");
							if(!str.toString().contains("%")){
								uniqueOps++;
								str.append("%");							
							}
						}if(line.contains("++")){
							opsCount += StringUtils.countMatches(line, "++");
							arOps += StringUtils.countMatches(line, "++");
							if(!str.toString().contains("++")){
								uniqueOps++;
								str.append("++");							
							}
						}
						if(line.contains("--")){
							opsCount += StringUtils.countMatches(line, "--");
							arOps += StringUtils.countMatches(line, "--");
							if(!str.toString().contains("--")){
								uniqueOps++;
								str.append("--");							
							}
						}
						
						//Relational Operators
						if(line.contains("!=")){
							opsCount += StringUtils.countMatches(line, "!=");
							reOps += StringUtils.countMatches(line, "!=");
							if(!str.toString().contains("!=")){
								uniqueOps++;
								str.append("!=");							
							}
						}if(line.contains("==")){
							opsCount += StringUtils.countMatches(line, "==");
							reOps += StringUtils.countMatches(line, "==");
							if(!str.toString().contains("==")){
								uniqueOps++;
								str.append("==");							
							}
						}if(line.contains(">") && !line.contains(">=")){
							opsCount += StringUtils.countMatches(line, ">");
							reOps += StringUtils.countMatches(line, ">");
							if(!str.toString().contains(">")){
								uniqueOps++;
								str.append(">");							
							}
						}if(line.contains("<") && !line.contains("<=")){
							opsCount += StringUtils.countMatches(line, "<");
							reOps += StringUtils.countMatches(line, "<");
							if(!str.toString().contains("<")){
								uniqueOps++;
								str.append("<");							
							}
						}if(line.contains(">=")){
							opsCount += StringUtils.countMatches(line, ">=");
							reOps += StringUtils.countMatches(line, ">=");
							if(!str.toString().contains(">=")){
								uniqueOps++;
								str.append(">=");							
							}
						}if(line.contains("<=")){
							opsCount += StringUtils.countMatches(line, "<=");
							reOps += StringUtils.countMatches(line, "<=");
							if(!str.toString().contains("<=")){
								uniqueOps++;
								str.append("<=");							
							}
						}
						
						
						//Logical Operators
						if(line.contains("&&")){
							opsCount += StringUtils.countMatches(line, "&&");
							logOps += StringUtils.countMatches(line, "&&");
							if(!str.toString().contains("&&")){
								uniqueOps++;
								str.append("&&");							
							}
						}if(line.contains("||")){
							opsCount += StringUtils.countMatches(line, "||");
							logOps += StringUtils.countMatches(line, "||");
							if(!str.toString().contains("||")){
								uniqueOps++;
								str.append("||");							
							}
						}if(line.contains("!") && !line.contains("!=")){
							opsCount += StringUtils.countMatches(line, "!");
							logOps += StringUtils.countMatches(line, "!");
							if(!str.toString().contains("!")){
								uniqueOps++;
								str.append("!");							
							}
						}
						
						
						//Bitwise Operators
						if(line.contains("&") && !line.contains("&=")){
							opsCount += StringUtils.countMatches(line, "&");
							bitOps += StringUtils.countMatches(line, "&");
							if(!str.toString().contains("&")){
								uniqueOps++;
								str.append("&");							
							}
						}if(line.contains("|") && !line.contains("||") && !line.contains("|=")){
							opsCount += StringUtils.countMatches(line, "|");
							bitOps += StringUtils.countMatches(line, "|");
							if(!str.toString().contains("|")){
								uniqueOps++;
								str.append("|");							
							}
						}if(line.contains("~")){
							opsCount += StringUtils.countMatches(line, "~");
							bitOps += StringUtils.countMatches(line, "~");
							if(!str.toString().contains("~")){
								uniqueOps++;
								str.append("~");							
							}
						}if(line.contains("<<") && !line.contains("<<=")){
							opsCount += StringUtils.countMatches(line, "<<");
							bitOps += StringUtils.countMatches(line, "<<");
							if(!str.toString().contains("<<")){
								uniqueOps++;
								str.append("<<");							
							}
						}if(line.contains(">>") && !line.contains(">>=")){
							opsCount += StringUtils.countMatches(line, ">>");
							bitOps += StringUtils.countMatches(line, ">>");
							if(!str.toString().contains(">>")){
								uniqueOps++;
								str.append(">>");							
							}
						}
						
						//Assignment Operators
						if(line.contains("=") && !line.contains("==")){
							opsCount += StringUtils.countMatches(line, "=");
							assOps += StringUtils.countMatches(line, "=");
							if(!str.toString().contains("=")){
								uniqueOps++;
								str.append("=");							
							}
						}if(line.contains("+=")){
							opsCount += StringUtils.countMatches(line, "+=");
							assOps += StringUtils.countMatches(line, "+=");
							if(!str.toString().contains("+=")){
								uniqueOps++;
								str.append("+=");							
							}
						}if(line.contains("-=")){
							opsCount += StringUtils.countMatches(line, "-=");
							assOps += StringUtils.countMatches(line, "-=");
							if(!str.toString().contains("-=")){
								uniqueOps++;
								str.append("-=");							
							}
						}if(line.contains("*=")){
							opsCount += StringUtils.countMatches(line, "*=");
							assOps += StringUtils.countMatches(line, "*=");
							if(!str.toString().contains("*=")){
								uniqueOps++;
								str.append("*=");							
							}
						}if(line.contains("/=")){
							opsCount += StringUtils.countMatches(line, "/=");
							assOps += StringUtils.countMatches(line, "/=");
							if(!str.toString().contains("/=")){
								uniqueOps++;
								str.append("/=");							
							}
						}if(line.contains("%=")){
							opsCount += StringUtils.countMatches(line, "%=");
							assOps += StringUtils.countMatches(line, "%=");
							if(!str.toString().contains("%=")){
								uniqueOps++;
								str.append("%=");							
							}
						}if(line.contains("<<=")){
							opsCount += StringUtils.countMatches(line, "<<=");
							assOps += StringUtils.countMatches(line, "<<=");
							if(!str.toString().contains("<<=")){
								uniqueOps++;
								str.append("<<=");							
							}
						}if(line.contains(">>=")){
							opsCount += StringUtils.countMatches(line, ">>=");
							assOps += StringUtils.countMatches(line, ">>=");
							if(!str.toString().contains(">>=")){
								uniqueOps++;
								str.append(">>=");							
							}
						}if(line.contains("&=")){
							opsCount += StringUtils.countMatches(line, "&=");
							assOps += StringUtils.countMatches(line, "&=");
							if(!str.toString().contains("&=")){
								uniqueOps++;
								str.append("&=");							
							}
						}if(line.contains("^=")){
							opsCount += StringUtils.countMatches(line, "^=");
							assOps += StringUtils.countMatches(line, "^=");
							if(!str.toString().contains("^=")){
								uniqueOps++;
								str.append("^=");							
							}
						}if(line.contains("|=")){
							opsCount += StringUtils.countMatches(line, "|=");
							assOps += StringUtils.countMatches(line, "|=");
							if(!str.toString().contains("|=")){
								uniqueOps++;
								str.append("|=");							
							}
						}
						
						
						//Operands
						if(line.contains("int ")){
							opdCount += StringUtils.countMatches(line, "int ");
							if(!str2.toString().contains("int ")){
								uniqueOpd++;
								str2.append("int ");							
							}
						}if(line.contains("string ")){
							opdCount += StringUtils.countMatches(line, "string ");
							if(!str2.toString().contains("string ")){
								uniqueOpd++;
								str2.append("string ");							
							}
						}if(line.contains("float ")){
							opdCount += StringUtils.countMatches(line, "float ");
							if(!str2.toString().contains("float ")){
								uniqueOpd++;
								str2.append("float ");							
							}
						}if(line.contains("void ")){
							opdCount += StringUtils.countMatches(line, "void ");
							if(!str2.toString().contains("void ")){
								uniqueOpd++;
								str2.append("void ");							
							}
						}if(line.contains("double ")){
							opdCount += StringUtils.countMatches(line, "double ");

							if(!str2.toString().contains("double ")){
								uniqueOpd++;
								str2.append("double ");							
							}
						}if(line.contains("\"")){
							opdCount += StringUtils.countMatches(line, "\"");
//							operands = operand.split("\"");
							if(!str2.toString().contains("\"")){
								uniqueOpd++;
								str2.append("\"");							
							}
						}if(line.contains("Exception")){
							opdCount += StringUtils.countMatches(line, "Exception");
//							operands = operand.split("Exception");
							if(!str2.toString().contains("Exception")){
								uniqueOpd++;
								str2.append("Exception");							
							}
						}
						
					}
					
					scanner.close();
					
					try {
						
						
						
						aLength = opsCount + opdCount;
						aLength = testClass.round(aLength, 2);
						
						eLength = (uniqueOps*Math.log(uniqueOps)) + (uniqueOpd*Math.log(uniqueOpd));
						eLength = testClass.round(eLength, 2);
						
						pVocab = uniqueOps + uniqueOps;
						pVocab = testClass.round(pVocab, 2);
						
						pVolume = aLength * Math.log(pVocab);
						pVolume = testClass.round(pVolume, 2);
						
						pMinVol = (2 + uniqueOpd) * Math.log(2 + uniqueOpd);
						pMinVol = testClass.round(pMinVol, 2);
						
						pLevel = pMinVol / pVolume;
						pLevel = testClass.round(pLevel, 2);
						
						effort = (pVolume / pLevel) / 100.0;
						effort = testClass.round(effort, 2);
						
						pTimer = (effort / 18.0);
						pTimer = testClass.round(pTimer, 2);
						
						eLevel = (uniqueOps/ 2.0)*(opdCount/uniqueOpd);
						eLevel = testClass.round(eLevel, 2);
						
						bugs = (Math.pow(effort, (2/3)))/3000.0;
						bugs = testClass.round(bugs, 2);
						
						
						//System.out.println(opsCount + " " + opdCount );
						
						JSONObject item1 = new JSONObject();
					    item1.put("name", "Actual Length");
					    item1.put("value", aLength);
					    items.add(item1);
						
					    JSONObject item2 = new JSONObject();
					    item2.put("name", "Estimated Length");
					    item2.put("value", eLength);
					    items.add(item2);
					    
					    JSONObject item3 = new JSONObject();
					    item3.put("name", "Program Vocabulary");
					    item3.put("value", pVocab);
					    items.add(item3);

					    JSONObject item4 = new JSONObject();
					    item4.put("name", "Program Volume");
					    item4.put("value", pVolume);
					    items.add(item4);
					    
					    JSONObject item5 = new JSONObject();
					    item5.put("name", "Potential Minimum Volume");
					    item5.put("value", pMinVol);
					    items.add(item5);
					   
					    JSONObject item6 = new JSONObject();
					    item6.put("name", "Program Level");
					    item6.put("value", pLevel);
					    items.add(item6);
					    
					    JSONObject item7 = new JSONObject();
					    item7.put("name", "Effort");
					    item7.put("value", effort);
					    items.add(item7);
					    
					    JSONObject item8 = new JSONObject();
					    item8.put("name", "Program Time");
					    item8.put("value", pTimer);
					    items.add(item8);
					    
					    
					    JSONObject item9 = new JSONObject();
					    item9.put("name", "Effect Level");
					    item9.put("value", eLevel);
					    items.add(item9);
					    
					    JSONObject item10 = new JSONObject();
					    item10.put("name", "Number of delivered Bugs");
					    item10.put("value", bugs);
					    items.add(item10);
					    
//					    obj1.put("Method Name", node.getName().toString());
//						obj1.put("value", new JSONArray(items));
					    
					}
					catch(Exception e) {
						System.out.println(e);
					}
					
				}
				
				
				

				System.out.println(items);
				return true;
				
			}
			
		});
		
	
		System.out.println(items);
		return (new JSONArray(items));
	}

     //atheekz
      static JSONArray itemsHasltead = new JSONArray();
 	
    public static JSONArray Hasltead_FileRoot(String strFile){


		
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(strFile.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
			
		//final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		
//		classstartidx = cu.toString().indexOf("public");
//		classendidx =  cu.toString().indexOf("{");
//		classstruct.classname = cu.toString().substring(classstartidx, classendidx);
		 
		//	pass cu (Compilation Unit) for operand operator class
		  
		JSONObject obj1 = new JSONObject();  

	    Collection<JSONObject> items = new ArrayList<JSONObject>();
	
			Set names = new HashSet();
			
			String str3 = strFile;
			
			int opsCount = 0;
			int uniqueOps = 0;
			
			int opdCount = 0;
			int uniqueOpd = 0;
			
			double aLength = 0.0;
			double eLength = 0.0;
			double pVocab = 0.0;
			double pVolume = 0.0;
			double pMinVol = 0.0;
			double pLevel = 0.0;
			double effort = 0.0;
			double pTimer = 0.0;
			double eLevel = 0.0;
			double bugs = 0.0;
			
			int arOps = 0;
			int reOps = 0;
			int logOps = 0;
			int bitOps = 0;
			int assOps = 0;
			int otherOps = 0;
			
			
			StringBuilder str = new StringBuilder();
			StringBuilder str2 = new StringBuilder();
			
			Scanner scanner = new Scanner(strFile);
			
			
				
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
//					System.out.println("Line = " + line);
					
					//Other Operators
					if(line.contains("System.out.println")){
						opsCount += StringUtils.countMatches(line, "System.out.println");
						otherOps += StringUtils.countMatches(line, "System.out.println");

						if(!str.toString().contains("System.out.println")){
							uniqueOps++;
							str.append("System.out.println");							
						}	
					}
					
					if(line.contains(".")){
						opsCount += StringUtils.countMatches(line, ".");
						otherOps += StringUtils.countMatches(line, ".");
						if(!str.toString().contains(".")){
							uniqueOps++;
							str.append(".");							
						}	
					}if(line.contains("{")){
						opsCount += StringUtils.countMatches(line, "{");
						otherOps += StringUtils.countMatches(line, "{");
						if(!str.toString().contains("{")){
							uniqueOps++;
							str.append("{");							
						}	
					}if(line.contains("(")){
						opsCount += StringUtils.countMatches(line, "(");
						otherOps += StringUtils.countMatches(line, "(");
						if(!str.toString().contains("(")){
							uniqueOps++;
							str.append("(");							
						}	
					}if(line.contains("[")){
						opsCount += StringUtils.countMatches(line, "[");
						otherOps += StringUtils.countMatches(line, "[");
						if(!str.toString().contains("[")){
							uniqueOps++;
							str.append("[");							
						}	
					}if(line.contains("public")){
						opsCount += StringUtils.countMatches(line, "public");
						otherOps += StringUtils.countMatches(line, "public");
						if(!str.toString().contains("public")){
							uniqueOps++;
							str.append("public");							
						}
					}if(line.contains("private")){
						opsCount += StringUtils.countMatches(line, "private");
						otherOps += StringUtils.countMatches(line, "private");
						if(!str.toString().contains("private")){
							uniqueOps++;
							str.append("private");							
						}
					}if(line.contains("protected")){
						opsCount += StringUtils.countMatches(line, "protected");
						otherOps += StringUtils.countMatches(line, "protected");
						if(!str.toString().contains("protected")){
							uniqueOps++;
							str.append("protected");							
						}
					}if(line.contains("static")){
						opsCount += StringUtils.countMatches(line, "static");
						otherOps += StringUtils.countMatches(line, "static");
						if(!str.toString().contains("static")){
							uniqueOps++;
							str.append("static");							
						}
					}if(line.contains("local")){
						opsCount += StringUtils.countMatches(line, "local");
						otherOps += StringUtils.countMatches(line, "local");
						if(!str.toString().contains("local")){
							uniqueOps++;
							str.append("local");							
						}
					}if(line.contains("instance")){
						opsCount += StringUtils.countMatches(line, "instance");
						otherOps += StringUtils.countMatches(line, "instance");
						if(!str.toString().contains("instance")){
							uniqueOps++;
							str.append("instance");							
						}
					}if(line.contains("for(")){
						opsCount += StringUtils.countMatches(line, "for(");
						otherOps += StringUtils.countMatches(line, "for(");
						if(!str.toString().contains("for(")){
							uniqueOps++;
							str.append("for(");							
						}
					}if(line.contains("if(")){
						opsCount += StringUtils.countMatches(line, "if(");
						otherOps += StringUtils.countMatches(line, "if(");
						if(!str.toString().contains("if(")){
							uniqueOps++;
							str.append("if(");							
						}
					}if(line.contains(";")){
						opsCount += StringUtils.countMatches(line, ";");
						otherOps += StringUtils.countMatches(line, ";");
						if(!str.toString().contains(";")){
							uniqueOps++;
							str.append(";");							
						}
					}if(line.contains("while(")){
						opsCount += StringUtils.countMatches(line, "while(");
						otherOps += StringUtils.countMatches(line, "while(");
						if(!str.toString().contains("while(")){
							uniqueOps++;
							str.append("while(");							
						}
					}if(line.contains("try")){
						opsCount += StringUtils.countMatches(line, "try");
						otherOps += StringUtils.countMatches(line, "try");
						if(!str.toString().contains("while(")){
							uniqueOps++;
							str.append("try");							
						}
					}if(line.contains("do")){
						opsCount += StringUtils.countMatches(line, "do");
						otherOps += StringUtils.countMatches(line, "do");
						if(!str.toString().contains("do")){
							uniqueOps++;
							str.append("do");							
						}
					}
					
					//Arithmatic Operators
					if(line.contains("+") && !line.contains("+=") && !line.contains("++")){
						opsCount += StringUtils.countMatches(line, "+");
						arOps += StringUtils.countMatches(line, "+");
						if(!str.toString().contains("+")){
							uniqueOps++;
							str.append("+");							
						}
					}if(line.contains("-") && !line.contains("-=") && !line.contains("--")){
						opsCount += StringUtils.countMatches(line, "-");
						arOps += StringUtils.countMatches(line, "-");
						if(!str.toString().contains("-")){
							uniqueOps++;
							str.append("-");							
						}
					}if(line.contains("*") && !line.contains("*=")){
						opsCount += StringUtils.countMatches(line, "*");
						arOps += StringUtils.countMatches(line, "*");
						if(!str.toString().contains("*")){
							uniqueOps++;
							str.append("*");							
						}
					}if(line.contains("/") && !line.contains("/=")){
						opsCount += StringUtils.countMatches(line, "/");
						arOps += StringUtils.countMatches(line, "/");
						if(!str.toString().contains("/")){
							uniqueOps++;
							str.append("/");							
						}
					}if(line.contains("%") && !line.contains("%=")){
						opsCount += StringUtils.countMatches(line, "%");
						arOps += StringUtils.countMatches(line, "%");
						if(!str.toString().contains("%")){
							uniqueOps++;
							str.append("%");							
						}
					}if(line.contains("++")){
						opsCount += StringUtils.countMatches(line, "++");
						arOps += StringUtils.countMatches(line, "++");
						if(!str.toString().contains("++")){
							uniqueOps++;
							str.append("++");							
						}
					}
					if(line.contains("--")){
						opsCount += StringUtils.countMatches(line, "--");
						arOps += StringUtils.countMatches(line, "--");
						if(!str.toString().contains("--")){
							uniqueOps++;
							str.append("--");							
						}
					}
					
					//Relational Operators
					if(line.contains("!=")){
						opsCount += StringUtils.countMatches(line, "!=");
						reOps += StringUtils.countMatches(line, "!=");
						if(!str.toString().contains("!=")){
							uniqueOps++;
							str.append("!=");							
						}
					}if(line.contains("==")){
						opsCount += StringUtils.countMatches(line, "==");
						reOps += StringUtils.countMatches(line, "==");
						if(!str.toString().contains("==")){
							uniqueOps++;
							str.append("==");							
						}
					}if(line.contains(">") && !line.contains(">=")){
						opsCount += StringUtils.countMatches(line, ">");
						reOps += StringUtils.countMatches(line, ">");
						if(!str.toString().contains(">")){
							uniqueOps++;
							str.append(">");							
						}
					}if(line.contains("<") && !line.contains("<=")){
						opsCount += StringUtils.countMatches(line, "<");
						reOps += StringUtils.countMatches(line, "<");
						if(!str.toString().contains("<")){
							uniqueOps++;
							str.append("<");							
						}
					}if(line.contains(">=")){
						opsCount += StringUtils.countMatches(line, ">=");
						reOps += StringUtils.countMatches(line, ">=");
						if(!str.toString().contains(">=")){
							uniqueOps++;
							str.append(">=");							
						}
					}if(line.contains("<=")){
						opsCount += StringUtils.countMatches(line, "<=");
						reOps += StringUtils.countMatches(line, "<=");
						if(!str.toString().contains("<=")){
							uniqueOps++;
							str.append("<=");							
						}
					}
					
					
					//Logical Operators
					if(line.contains("&&")){
						opsCount += StringUtils.countMatches(line, "&&");
						logOps += StringUtils.countMatches(line, "&&");
						if(!str.toString().contains("&&")){
							uniqueOps++;
							str.append("&&");							
						}
					}if(line.contains("||")){
						opsCount += StringUtils.countMatches(line, "||");
						logOps += StringUtils.countMatches(line, "||");
						if(!str.toString().contains("||")){
							uniqueOps++;
							str.append("||");							
						}
					}if(line.contains("!") && !line.contains("!=")){
						opsCount += StringUtils.countMatches(line, "!");
						logOps += StringUtils.countMatches(line, "!");
						if(!str.toString().contains("!")){
							uniqueOps++;
							str.append("!");							
						}
					}
					
					
					//Bitwise Operators
					if(line.contains("&") && !line.contains("&=")){
						opsCount += StringUtils.countMatches(line, "&");
						bitOps += StringUtils.countMatches(line, "&");
						if(!str.toString().contains("&")){
							uniqueOps++;
							str.append("&");							
						}
					}if(line.contains("|") && !line.contains("||") && !line.contains("|=")){
						opsCount += StringUtils.countMatches(line, "|");
						bitOps += StringUtils.countMatches(line, "|");
						if(!str.toString().contains("|")){
							uniqueOps++;
							str.append("|");							
						}
					}if(line.contains("~")){
						opsCount += StringUtils.countMatches(line, "~");
						bitOps += StringUtils.countMatches(line, "~");
						if(!str.toString().contains("~")){
							uniqueOps++;
							str.append("~");							
						}
					}if(line.contains("<<") && !line.contains("<<=")){
						opsCount += StringUtils.countMatches(line, "<<");
						bitOps += StringUtils.countMatches(line, "<<");
						if(!str.toString().contains("<<")){
							uniqueOps++;
							str.append("<<");							
						}
					}if(line.contains(">>") && !line.contains(">>=")){
						opsCount += StringUtils.countMatches(line, ">>");
						bitOps += StringUtils.countMatches(line, ">>");
						if(!str.toString().contains(">>")){
							uniqueOps++;
							str.append(">>");							
						}
					}
					
					//Assignment Operators
					if(line.contains("=") && !line.contains("==")){
						opsCount += StringUtils.countMatches(line, "=");
						assOps += StringUtils.countMatches(line, "=");
						if(!str.toString().contains("=")){
							uniqueOps++;
							str.append("=");							
						}
					}if(line.contains("+=")){
						opsCount += StringUtils.countMatches(line, "+=");
						assOps += StringUtils.countMatches(line, "+=");
						if(!str.toString().contains("+=")){
							uniqueOps++;
							str.append("+=");							
						}
					}if(line.contains("-=")){
						opsCount += StringUtils.countMatches(line, "-=");
						assOps += StringUtils.countMatches(line, "-=");
						if(!str.toString().contains("-=")){
							uniqueOps++;
							str.append("-=");							
						}
					}if(line.contains("*=")){
						opsCount += StringUtils.countMatches(line, "*=");
						assOps += StringUtils.countMatches(line, "*=");
						if(!str.toString().contains("*=")){
							uniqueOps++;
							str.append("*=");							
						}
					}if(line.contains("/=")){
						opsCount += StringUtils.countMatches(line, "/=");
						assOps += StringUtils.countMatches(line, "/=");
						if(!str.toString().contains("/=")){
							uniqueOps++;
							str.append("/=");							
						}
					}if(line.contains("%=")){
						opsCount += StringUtils.countMatches(line, "%=");
						assOps += StringUtils.countMatches(line, "%=");
						if(!str.toString().contains("%=")){
							uniqueOps++;
							str.append("%=");							
						}
					}if(line.contains("<<=")){
						opsCount += StringUtils.countMatches(line, "<<=");
						assOps += StringUtils.countMatches(line, "<<=");
						if(!str.toString().contains("<<=")){
							uniqueOps++;
							str.append("<<=");							
						}
					}if(line.contains(">>=")){
						opsCount += StringUtils.countMatches(line, ">>=");
						assOps += StringUtils.countMatches(line, ">>=");
						if(!str.toString().contains(">>=")){
							uniqueOps++;
							str.append(">>=");							
						}
					}if(line.contains("&=")){
						opsCount += StringUtils.countMatches(line, "&=");
						assOps += StringUtils.countMatches(line, "&=");
						if(!str.toString().contains("&=")){
							uniqueOps++;
							str.append("&=");							
						}
					}if(line.contains("^=")){
						opsCount += StringUtils.countMatches(line, "^=");
						assOps += StringUtils.countMatches(line, "^=");
						if(!str.toString().contains("^=")){
							uniqueOps++;
							str.append("^=");							
						}
					}if(line.contains("|=")){
						opsCount += StringUtils.countMatches(line, "|=");
						assOps += StringUtils.countMatches(line, "|=");
						if(!str.toString().contains("|=")){
							uniqueOps++;
							str.append("|=");							
						}
					}
					
					
					//Operands
					if(line.contains("int ")){
						opdCount += StringUtils.countMatches(line, "int ");
						if(!str2.toString().contains("int ")){
							uniqueOpd++;
							str2.append("int ");							
						}
					}if(line.contains("string ")){
						opdCount += StringUtils.countMatches(line, "string ");
						if(!str2.toString().contains("string ")){
							uniqueOpd++;
							str2.append("string ");							
						}
					}if(line.contains("float ")){
						opdCount += StringUtils.countMatches(line, "float ");
						if(!str2.toString().contains("float ")){
							uniqueOpd++;
							str2.append("float ");							
						}
					}if(line.contains("void ")){
						opdCount += StringUtils.countMatches(line, "void ");
						if(!str2.toString().contains("void ")){
							uniqueOpd++;
							str2.append("void ");							
						}
					}if(line.contains("double ")){
						opdCount += StringUtils.countMatches(line, "double ");

						if(!str2.toString().contains("double ")){
							uniqueOpd++;
							str2.append("double ");							
						}
					}if(line.contains("\"")){
						opdCount += StringUtils.countMatches(line, "\"");
//						operands = operand.split("\"");
						if(!str2.toString().contains("\"")){
							uniqueOpd++;
							str2.append("\"");							
						}
					}if(line.contains("Exception")){
						opdCount += StringUtils.countMatches(line, "Exception");
//						operands = operand.split("Exception");
						if(!str2.toString().contains("Exception")){
							uniqueOpd++;
							str2.append("Exception");							
						}
					}
					
				}
				
				scanner.close();
				
				try {
					
					
					
					aLength = opsCount + opdCount;
					aLength = testClass.round(aLength, 2);
					
					eLength = (uniqueOps*Math.log(uniqueOps)) + (uniqueOpd*Math.log(uniqueOpd));
					eLength = testClass.round(eLength, 2);
					
					pVocab = uniqueOps + uniqueOps;
					pVocab = testClass.round(pVocab, 2);
					
					pVolume = aLength * Math.log(pVocab);
					pVolume = testClass.round(pVolume, 2);
					
					pMinVol = (2 + uniqueOpd) * Math.log(2 + uniqueOpd);
					pMinVol = testClass.round(pMinVol, 2);
					
					pLevel = pMinVol / pVolume;
					//pLevel = testClass.round(pLevel, 2);
					
					effort = (pVolume / pLevel) / 100.0;
					//effort = testClass.round(effort, 2);
					
					pTimer = (effort / 18.0);
					//pTimer = testClass.round(pTimer, 2);
					
					eLevel = (uniqueOps/ 2.0)*(opdCount/uniqueOpd);
					eLevel = testClass.round(eLevel, 2);
					
					bugs = (Math.pow(effort, (2/3)))/3000.0;
					bugs = testClass.round(bugs, 2);
					
					
					//System.out.println(opsCount + " " + opdCount );
					
					JSONObject item1 = new JSONObject();
				    item1.put("name", "Actual Length");
				    item1.put("value", aLength);
				    items.add(item1);
					
				    JSONObject item2 = new JSONObject();
				    item2.put("name", "Estimated Length");
				    item2.put("value", eLength);
				    items.add(item2);
				    
				    JSONObject item3 = new JSONObject();
				    item3.put("name", "Program Vocabulary");
				    item3.put("value", pVocab);
				    items.add(item3);

				    JSONObject item4 = new JSONObject();
				    item4.put("name", "Program Volume");
				    item4.put("value", pVolume);
				    items.add(item4);
				    
				    JSONObject item5 = new JSONObject();
				    item5.put("name", "Potential Minimum Volume");
				    item5.put("value", pMinVol);
				    items.add(item5);
				   
				    JSONObject item6 = new JSONObject();
				    item6.put("name", "Program Level");
				    item6.put("value", pLevel);
				    items.add(item6);
				    
				    JSONObject item7 = new JSONObject();
				    item7.put("name", "Effort");
				    item7.put("value", effort);
				    items.add(item7);
				    
				    JSONObject item8 = new JSONObject();
				    item8.put("name", "Program Time");
				    item8.put("value", pTimer);
				    items.add(item8);
				    
				    
				    JSONObject item9 = new JSONObject();
				    item9.put("name", "Effect Level");
				    item9.put("value", eLevel);
				    items.add(item9);
				    
				    JSONObject item10 = new JSONObject();
				    item10.put("name", "Number of delivered Bugs");
				    item10.put("value", bugs);
				    items.add(item10);
				    
//				    obj1.put("Method Name", node.getName().toString());
//					obj1.put("value", new JSONArray(items));
				    
				}
				catch(Exception e) {
					System.out.println(e);
				}
			
			
//atheekz
		
				if(itemsHasltead.length() ==0){
					JSONObject s = new JSONObject();
					try {
						//s = (JSONObject) ((JSONArray) items).get(0);
						itemsHasltead.put(items);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
				else{
					
					 for (int i = 0, size = itemsHasltead.length(); i < size; i++)
					    {
						 try {
							JSONArray objectInArray =  itemsHasltead.getJSONArray(i);
							for (int j = 0; j < objectInArray.length(); j++) {
								JSONObject object = objectInArray.getJSONObject(j);
								for(JSONObject irr : items){
									
									if(object.getString("name").equals(irr.getString("name"))){
										double x  = object.getInt("value") + irr.getInt("value");
										object.put("value", x);
										System.out.println("fuck" +x);
									}
							}
							}
						
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							
					    }	    		  
			    	  

				/*	 for (int i = 0, size = itemsHasltead.length(); i < size; i++)
					    {
					      try {
					    	  Object objectInArray =  itemsHasltead.get(i);
							 for(JSONObject irr : objectInArray){
												    		  
												    	  }
					    	  for(JSONObject irr : items){
					    		  
					    	  }
							System.out.println(objectInArray);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					  
					    }*/
					
				}
						System.out.println(itemsHasltead);
		//return (new JSONArray(items)));
						//atheekz
						JSONArray newArray = new JSONArray();
						try {
							newArray = itemsHasltead.getJSONArray(0);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return newArray;
						
    	
    }
public static JSONArray Hasltead_File(String strFile){

		
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(strFile.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
			
		//final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		
//		classstartidx = cu.toString().indexOf("public");
//		classendidx =  cu.toString().indexOf("{");
//		classstruct.classname = cu.toString().substring(classstartidx, classendidx);
		 
		//	pass cu (Compilation Unit) for operand operator class
		  
		JSONObject obj1 = new JSONObject();  

	    Collection<JSONObject> items = new ArrayList<JSONObject>();
	
			Set names = new HashSet();
			
			String str3 = strFile;
			
			int opsCount = 0;
			int uniqueOps = 0;
			
			int opdCount = 0;
			int uniqueOpd = 0;
			
			double aLength = 0.0;
			double eLength = 0.0;
			double pVocab = 0.0;
			double pVolume = 0.0;
			double pMinVol = 0.0;
			double pLevel = 0.0;
			double effort = 0.0;
			double pTimer = 0.0;
			double eLevel = 0.0;
			double bugs = 0.0;
			
			int arOps = 0;
			int reOps = 0;
			int logOps = 0;
			int bitOps = 0;
			int assOps = 0;
			int otherOps = 0;
			
			
			StringBuilder str = new StringBuilder();
			StringBuilder str2 = new StringBuilder();
			
			Scanner scanner = new Scanner(strFile);
			
			
				
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
//					System.out.println("Line = " + line);
					
					//Other Operators
					if(line.contains("System.out.println")){
						opsCount += StringUtils.countMatches(line, "System.out.println");
						otherOps += StringUtils.countMatches(line, "System.out.println");

						if(!str.toString().contains("System.out.println")){
							uniqueOps++;
							str.append("System.out.println");							
						}	
					}
					
					if(line.contains(".")){
						opsCount += StringUtils.countMatches(line, ".");
						otherOps += StringUtils.countMatches(line, ".");
						if(!str.toString().contains(".")){
							uniqueOps++;
							str.append(".");							
						}	
					}if(line.contains("{")){
						opsCount += StringUtils.countMatches(line, "{");
						otherOps += StringUtils.countMatches(line, "{");
						if(!str.toString().contains("{")){
							uniqueOps++;
							str.append("{");							
						}	
					}if(line.contains("(")){
						opsCount += StringUtils.countMatches(line, "(");
						otherOps += StringUtils.countMatches(line, "(");
						if(!str.toString().contains("(")){
							uniqueOps++;
							str.append("(");							
						}	
					}if(line.contains("[")){
						opsCount += StringUtils.countMatches(line, "[");
						otherOps += StringUtils.countMatches(line, "[");
						if(!str.toString().contains("[")){
							uniqueOps++;
							str.append("[");							
						}	
					}if(line.contains("public")){
						opsCount += StringUtils.countMatches(line, "public");
						otherOps += StringUtils.countMatches(line, "public");
						if(!str.toString().contains("public")){
							uniqueOps++;
							str.append("public");							
						}
					}if(line.contains("private")){
						opsCount += StringUtils.countMatches(line, "private");
						otherOps += StringUtils.countMatches(line, "private");
						if(!str.toString().contains("private")){
							uniqueOps++;
							str.append("private");							
						}
					}if(line.contains("protected")){
						opsCount += StringUtils.countMatches(line, "protected");
						otherOps += StringUtils.countMatches(line, "protected");
						if(!str.toString().contains("protected")){
							uniqueOps++;
							str.append("protected");							
						}
					}if(line.contains("static")){
						opsCount += StringUtils.countMatches(line, "static");
						otherOps += StringUtils.countMatches(line, "static");
						if(!str.toString().contains("static")){
							uniqueOps++;
							str.append("static");							
						}
					}if(line.contains("local")){
						opsCount += StringUtils.countMatches(line, "local");
						otherOps += StringUtils.countMatches(line, "local");
						if(!str.toString().contains("local")){
							uniqueOps++;
							str.append("local");							
						}
					}if(line.contains("instance")){
						opsCount += StringUtils.countMatches(line, "instance");
						otherOps += StringUtils.countMatches(line, "instance");
						if(!str.toString().contains("instance")){
							uniqueOps++;
							str.append("instance");							
						}
					}if(line.contains("for(")){
						opsCount += StringUtils.countMatches(line, "for(");
						otherOps += StringUtils.countMatches(line, "for(");
						if(!str.toString().contains("for(")){
							uniqueOps++;
							str.append("for(");							
						}
					}if(line.contains("if(")){
						opsCount += StringUtils.countMatches(line, "if(");
						otherOps += StringUtils.countMatches(line, "if(");
						if(!str.toString().contains("if(")){
							uniqueOps++;
							str.append("if(");							
						}
					}if(line.contains(";")){
						opsCount += StringUtils.countMatches(line, ";");
						otherOps += StringUtils.countMatches(line, ";");
						if(!str.toString().contains(";")){
							uniqueOps++;
							str.append(";");							
						}
					}if(line.contains("while(")){
						opsCount += StringUtils.countMatches(line, "while(");
						otherOps += StringUtils.countMatches(line, "while(");
						if(!str.toString().contains("while(")){
							uniqueOps++;
							str.append("while(");							
						}
					}if(line.contains("try")){
						opsCount += StringUtils.countMatches(line, "try");
						otherOps += StringUtils.countMatches(line, "try");
						if(!str.toString().contains("while(")){
							uniqueOps++;
							str.append("try");							
						}
					}if(line.contains("do")){
						opsCount += StringUtils.countMatches(line, "do");
						otherOps += StringUtils.countMatches(line, "do");
						if(!str.toString().contains("do")){
							uniqueOps++;
							str.append("do");							
						}
					}
					
					//Arithmatic Operators
					if(line.contains("+") && !line.contains("+=") && !line.contains("++")){
						opsCount += StringUtils.countMatches(line, "+");
						arOps += StringUtils.countMatches(line, "+");
						if(!str.toString().contains("+")){
							uniqueOps++;
							str.append("+");							
						}
					}if(line.contains("-") && !line.contains("-=") && !line.contains("--")){
						opsCount += StringUtils.countMatches(line, "-");
						arOps += StringUtils.countMatches(line, "-");
						if(!str.toString().contains("-")){
							uniqueOps++;
							str.append("-");							
						}
					}if(line.contains("*") && !line.contains("*=")){
						opsCount += StringUtils.countMatches(line, "*");
						arOps += StringUtils.countMatches(line, "*");
						if(!str.toString().contains("*")){
							uniqueOps++;
							str.append("*");							
						}
					}if(line.contains("/") && !line.contains("/=")){
						opsCount += StringUtils.countMatches(line, "/");
						arOps += StringUtils.countMatches(line, "/");
						if(!str.toString().contains("/")){
							uniqueOps++;
							str.append("/");							
						}
					}if(line.contains("%") && !line.contains("%=")){
						opsCount += StringUtils.countMatches(line, "%");
						arOps += StringUtils.countMatches(line, "%");
						if(!str.toString().contains("%")){
							uniqueOps++;
							str.append("%");							
						}
					}if(line.contains("++")){
						opsCount += StringUtils.countMatches(line, "++");
						arOps += StringUtils.countMatches(line, "++");
						if(!str.toString().contains("++")){
							uniqueOps++;
							str.append("++");							
						}
					}
					if(line.contains("--")){
						opsCount += StringUtils.countMatches(line, "--");
						arOps += StringUtils.countMatches(line, "--");
						if(!str.toString().contains("--")){
							uniqueOps++;
							str.append("--");							
						}
					}
					
					//Relational Operators
					if(line.contains("!=")){
						opsCount += StringUtils.countMatches(line, "!=");
						reOps += StringUtils.countMatches(line, "!=");
						if(!str.toString().contains("!=")){
							uniqueOps++;
							str.append("!=");							
						}
					}if(line.contains("==")){
						opsCount += StringUtils.countMatches(line, "==");
						reOps += StringUtils.countMatches(line, "==");
						if(!str.toString().contains("==")){
							uniqueOps++;
							str.append("==");							
						}
					}if(line.contains(">") && !line.contains(">=")){
						opsCount += StringUtils.countMatches(line, ">");
						reOps += StringUtils.countMatches(line, ">");
						if(!str.toString().contains(">")){
							uniqueOps++;
							str.append(">");							
						}
					}if(line.contains("<") && !line.contains("<=")){
						opsCount += StringUtils.countMatches(line, "<");
						reOps += StringUtils.countMatches(line, "<");
						if(!str.toString().contains("<")){
							uniqueOps++;
							str.append("<");							
						}
					}if(line.contains(">=")){
						opsCount += StringUtils.countMatches(line, ">=");
						reOps += StringUtils.countMatches(line, ">=");
						if(!str.toString().contains(">=")){
							uniqueOps++;
							str.append(">=");							
						}
					}if(line.contains("<=")){
						opsCount += StringUtils.countMatches(line, "<=");
						reOps += StringUtils.countMatches(line, "<=");
						if(!str.toString().contains("<=")){
							uniqueOps++;
							str.append("<=");							
						}
					}
					
					
					//Logical Operators
					if(line.contains("&&")){
						opsCount += StringUtils.countMatches(line, "&&");
						logOps += StringUtils.countMatches(line, "&&");
						if(!str.toString().contains("&&")){
							uniqueOps++;
							str.append("&&");							
						}
					}if(line.contains("||")){
						opsCount += StringUtils.countMatches(line, "||");
						logOps += StringUtils.countMatches(line, "||");
						if(!str.toString().contains("||")){
							uniqueOps++;
							str.append("||");							
						}
					}if(line.contains("!") && !line.contains("!=")){
						opsCount += StringUtils.countMatches(line, "!");
						logOps += StringUtils.countMatches(line, "!");
						if(!str.toString().contains("!")){
							uniqueOps++;
							str.append("!");							
						}
					}
					
					
					//Bitwise Operators
					if(line.contains("&") && !line.contains("&=")){
						opsCount += StringUtils.countMatches(line, "&");
						bitOps += StringUtils.countMatches(line, "&");
						if(!str.toString().contains("&")){
							uniqueOps++;
							str.append("&");							
						}
					}if(line.contains("|") && !line.contains("||") && !line.contains("|=")){
						opsCount += StringUtils.countMatches(line, "|");
						bitOps += StringUtils.countMatches(line, "|");
						if(!str.toString().contains("|")){
							uniqueOps++;
							str.append("|");							
						}
					}if(line.contains("~")){
						opsCount += StringUtils.countMatches(line, "~");
						bitOps += StringUtils.countMatches(line, "~");
						if(!str.toString().contains("~")){
							uniqueOps++;
							str.append("~");							
						}
					}if(line.contains("<<") && !line.contains("<<=")){
						opsCount += StringUtils.countMatches(line, "<<");
						bitOps += StringUtils.countMatches(line, "<<");
						if(!str.toString().contains("<<")){
							uniqueOps++;
							str.append("<<");							
						}
					}if(line.contains(">>") && !line.contains(">>=")){
						opsCount += StringUtils.countMatches(line, ">>");
						bitOps += StringUtils.countMatches(line, ">>");
						if(!str.toString().contains(">>")){
							uniqueOps++;
							str.append(">>");							
						}
					}
					
					//Assignment Operators
					if(line.contains("=") && !line.contains("==")){
						opsCount += StringUtils.countMatches(line, "=");
						assOps += StringUtils.countMatches(line, "=");
						if(!str.toString().contains("=")){
							uniqueOps++;
							str.append("=");							
						}
					}if(line.contains("+=")){
						opsCount += StringUtils.countMatches(line, "+=");
						assOps += StringUtils.countMatches(line, "+=");
						if(!str.toString().contains("+=")){
							uniqueOps++;
							str.append("+=");							
						}
					}if(line.contains("-=")){
						opsCount += StringUtils.countMatches(line, "-=");
						assOps += StringUtils.countMatches(line, "-=");
						if(!str.toString().contains("-=")){
							uniqueOps++;
							str.append("-=");							
						}
					}if(line.contains("*=")){
						opsCount += StringUtils.countMatches(line, "*=");
						assOps += StringUtils.countMatches(line, "*=");
						if(!str.toString().contains("*=")){
							uniqueOps++;
							str.append("*=");							
						}
					}if(line.contains("/=")){
						opsCount += StringUtils.countMatches(line, "/=");
						assOps += StringUtils.countMatches(line, "/=");
						if(!str.toString().contains("/=")){
							uniqueOps++;
							str.append("/=");							
						}
					}if(line.contains("%=")){
						opsCount += StringUtils.countMatches(line, "%=");
						assOps += StringUtils.countMatches(line, "%=");
						if(!str.toString().contains("%=")){
							uniqueOps++;
							str.append("%=");							
						}
					}if(line.contains("<<=")){
						opsCount += StringUtils.countMatches(line, "<<=");
						assOps += StringUtils.countMatches(line, "<<=");
						if(!str.toString().contains("<<=")){
							uniqueOps++;
							str.append("<<=");							
						}
					}if(line.contains(">>=")){
						opsCount += StringUtils.countMatches(line, ">>=");
						assOps += StringUtils.countMatches(line, ">>=");
						if(!str.toString().contains(">>=")){
							uniqueOps++;
							str.append(">>=");							
						}
					}if(line.contains("&=")){
						opsCount += StringUtils.countMatches(line, "&=");
						assOps += StringUtils.countMatches(line, "&=");
						if(!str.toString().contains("&=")){
							uniqueOps++;
							str.append("&=");							
						}
					}if(line.contains("^=")){
						opsCount += StringUtils.countMatches(line, "^=");
						assOps += StringUtils.countMatches(line, "^=");
						if(!str.toString().contains("^=")){
							uniqueOps++;
							str.append("^=");							
						}
					}if(line.contains("|=")){
						opsCount += StringUtils.countMatches(line, "|=");
						assOps += StringUtils.countMatches(line, "|=");
						if(!str.toString().contains("|=")){
							uniqueOps++;
							str.append("|=");							
						}
					}
					
					
					//Operands
					if(line.contains("int ")){
						opdCount += StringUtils.countMatches(line, "int ");
						if(!str2.toString().contains("int ")){
							uniqueOpd++;
							str2.append("int ");							
						}
					}if(line.contains("string ")){
						opdCount += StringUtils.countMatches(line, "string ");
						if(!str2.toString().contains("string ")){
							uniqueOpd++;
							str2.append("string ");							
						}
					}if(line.contains("float ")){
						opdCount += StringUtils.countMatches(line, "float ");
						if(!str2.toString().contains("float ")){
							uniqueOpd++;
							str2.append("float ");							
						}
					}if(line.contains("void ")){
						opdCount += StringUtils.countMatches(line, "void ");
						if(!str2.toString().contains("void ")){
							uniqueOpd++;
							str2.append("void ");							
						}
					}if(line.contains("double ")){
						opdCount += StringUtils.countMatches(line, "double ");

						if(!str2.toString().contains("double ")){
							uniqueOpd++;
							str2.append("double ");							
						}
					}if(line.contains("\"")){
						opdCount += StringUtils.countMatches(line, "\"");
//						operands = operand.split("\"");
						if(!str2.toString().contains("\"")){
							uniqueOpd++;
							str2.append("\"");							
						}
					}if(line.contains("Exception")){
						opdCount += StringUtils.countMatches(line, "Exception");
//						operands = operand.split("Exception");
						if(!str2.toString().contains("Exception")){
							uniqueOpd++;
							str2.append("Exception");							
						}
					}
					
				}
				
				scanner.close();
				
				try {
					
					
					
					aLength = opsCount + opdCount;
					aLength = testClass.round(aLength, 2);
					
					eLength = (uniqueOps*Math.log(uniqueOps)) + (uniqueOpd*Math.log(uniqueOpd));
					eLength = testClass.round(eLength, 2);
					
					pVocab = uniqueOps + uniqueOps;
					pVocab = testClass.round(pVocab, 2);
					
					pVolume = aLength * Math.log(pVocab);
					pVolume = testClass.round(pVolume, 2);
					
					pMinVol = (2 + uniqueOpd) * Math.log(2 + uniqueOpd);
					pMinVol = testClass.round(pMinVol, 2);
					
					pLevel = pMinVol / pVolume;
					pLevel = testClass.round(pLevel, 2);
					
					effort = (pVolume / pLevel) / 100.0;
					effort = testClass.round(effort, 2);
					
					pTimer = (effort / 18.0);
					pTimer = testClass.round(pTimer, 2);
					
					eLevel = (uniqueOps/ 2.0)*(opdCount/uniqueOpd);
					eLevel = testClass.round(eLevel, 2);
					
					bugs = (Math.pow(effort, (2/3)))/3000.0;
					bugs = testClass.round(bugs, 2);
					
					
					//System.out.println(opsCount + " " + opdCount );
					
					JSONObject item1 = new JSONObject();
				    item1.put("name", "Actual Length");
				    item1.put("value", aLength);
				    items.add(item1);
					
				    JSONObject item2 = new JSONObject();
				    item2.put("name", "Estimated Length");
				    item2.put("value", eLength);
				    items.add(item2);
				    
				    JSONObject item3 = new JSONObject();
				    item3.put("name", "Program Vocabulary");
				    item3.put("value", pVocab);
				    items.add(item3);

				    JSONObject item4 = new JSONObject();
				    item4.put("name", "Program Volume");
				    item4.put("value", pVolume);
				    items.add(item4);
				    
				    JSONObject item5 = new JSONObject();
				    item5.put("name", "Potential Minimum Volume");
				    item5.put("value", pMinVol);
				    items.add(item5);
				   
				    JSONObject item6 = new JSONObject();
				    item6.put("name", "Program Level");
				    item6.put("value", pLevel);
				    items.add(item6);
				    
				    JSONObject item7 = new JSONObject();
				    item7.put("name", "Effort");
				    item7.put("value", effort);
				    items.add(item7);
				    
				    JSONObject item8 = new JSONObject();
				    item8.put("name", "Program Time");
				    item8.put("value", pTimer);
				    items.add(item8);
				    
				    
				    JSONObject item9 = new JSONObject();
				    item9.put("name", "Effect Level");
				    item9.put("value", eLevel);
				    items.add(item9);
				    
				    JSONObject item10 = new JSONObject();
				    item10.put("name", "Number of delivered Bugs");
				    item10.put("value", bugs);
				    items.add(item10);
				    
//				    obj1.put("Method Name", node.getName().toString());
//					obj1.put("value", new JSONArray(items));
				    
				}
				catch(Exception e) {
					System.out.println(e);
				}
			
			
			

						System.out.println(items);
		return (new JSONArray(items));
	}
	

}
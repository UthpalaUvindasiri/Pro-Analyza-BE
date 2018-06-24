package restPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import com.google.gson.Gson;
import org.json.*;

public class Parser {

	static JSONObject h;
	
	static List<VariableDeclarationFragment> variables = new ArrayList<VariableDeclarationFragment>();
	static List<VariableDeclaration> Mvariables = new ArrayList<VariableDeclaration>();
	static List<VariableDeclarationFragment> Mvariables2 = new ArrayList<VariableDeclarationFragment>();
	
	static List<TryStatement> trystatements = new ArrayList<TryStatement>();
	static List<TryStatement> Mtrystatements = new ArrayList<TryStatement>();
	
	static List<SwitchStatement> switchstatments = new ArrayList<SwitchStatement>();
	static List<SwitchStatement> Mswitchstatments = new ArrayList<SwitchStatement>();
	
	static List<IfStatement> ifstatements = new ArrayList<IfStatement>();
	static List<IfStatement> Mifstatements = new ArrayList<IfStatement>();
	
	static List<WhileStatement> whilestatements = new ArrayList<WhileStatement>();
	static List<WhileStatement> Mwhilestatements = new ArrayList<WhileStatement>();
	
	static List<ForStatement> forstatements = new ArrayList<ForStatement>();
	static List<ForStatement> Mforstatements = new ArrayList<ForStatement>();
	
	static List<MethodDeclaration> methods = new ArrayList<MethodDeclaration>();
	static List<String> methodnames = new ArrayList<String>();
	static List<String> returntypes = new ArrayList<String>();
	static String Json;
	static Gson gson = new Gson();
	static String file;
	
	static int ControlStructureComplx;
	static int VariableComplx;
	static int TotalComplx;
	static int classstartidx, classendidx, i;
	
	static Hierarchy classstruct = new Hierarchy();
	static CyclomaticComplexity CC= new CyclomaticComplexity();
	static CWCM CWCM= new CWCM();
	static ExceptionHandling ExceptionHandling = new ExceptionHandling();
	static MethodComplexity MethodComplexity = new MethodComplexity();
	static CFS CFS = new CFS();
	static ControlStructures controlstructures = new ControlStructures();
	
	static JSONObject	ControlStructJson;
	static JSONArray ControlStructArray = new JSONArray();
	static JSONObject	CCJson;
	static JSONArray CCArray = new JSONArray();
	static JSONObject	CWCMJson;
	static JSONArray CWCMArray = new JSONArray();
	static JSONObject	ExceptionHandlingJson;
//	static Collection<JSONObject> ExceptionHandlingArray = new ArrayList<JSONObject>();
	static JSONArray ExceptionHandlingArray = new JSONArray();
	static JSONObject	MethodComplexityJson;
	static JSONObject	CFSJson;
	static JSONObject	AccessModifiersComplxJson;
	
	static List<CatchClause> catches = new ArrayList<CatchClause>();
	
	static String filePath;
	
	public Parser(String mName) throws IOException{
		System.out.println("Athe-*****************mName"+mName);
//		ParseFilesInDir();
	//	ParseFilesInDir2(mName);
		if(mName.contains("root")){
			ParseFilesInDir();
		}else
		{
			ParseFilesInDir2(mName);
		}
		
		for(MethodDeclaration method : methods){
			
			methodnames.add(method.getName().toString());
			
			if(mName.contains(".java")){
				returntypes.add(method.modifiers().toString());
			}
			else{
				//if(method.getName().toString().equals(mName))	
					returntypes.add(method.modifiers().toString());
			}
			}
		
		for(VariableDeclarationFragment var : variables){
			Mvariables2.add(var);
		}
		
			
		
		
		classstruct.methodnames =  methodnames;
		//classstruct.createJson(classstruct); ast
		
		
		
	}
	
	public static void main(String[] args) throws IOException, JSONException {
		
		//ParseFilesInDir2("Cal.java");	//get IF, while  for single file
		ParseFilesInDir();	//get IF while count for all files
		System.out.println(getCCJSON("main"));
		
		for(MethodDeclaration method : methods){
			
			methodnames.add(method.getName().toString());
			//returntypes.add(method.modifiers().toString());
			
		}
		
		classstruct.methodnames =  methodnames;
		//classstruct.createJson(classstruct); ast
		
		
		for(VariableDeclarationFragment var : variables){
			Mvariables2.add(var);
		}
		
		
		
		
		
				
		AccessModifiers accessmodifiers = new AccessModifiers();
		AccessModifiersComplxJson = accessmodifiers.Calculate(Mvariables2);
		
		VariableComplx = variables.size();
		
		
		CCJson = CC.getCC(ifstatements.size(), switchstatments.size(), whilestatements.size(),forstatements.size());
		CWCMJson = CWCM.getCWCM(ifstatements.size(), switchstatments.size(), whilestatements.size(),forstatements.size());
		ExceptionHandlingJson = ExceptionHandling.getException(switchstatments.size());
		MethodComplexityJson = MethodComplexity.getMethodComplexity(methods);
		CFSJson	=	CFS.getCFSValue(file, "");
		
	}
	
	public JSONObject getControlStructJson() throws JSONException{
		ControlStructJson = controlstructures.Calculate(ifstatements, switchstatments.size(),
				whilestatements.size(),forstatements.size());
		
		return ControlStructJson;
	}
	
	
	
	
	public static JSONObject getCCJSON(String fName) throws JSONException{
		CCJson = CC.getCC(ifstatements.size(), switchstatments.size(), whilestatements.size(),forstatements.size());
		return CCJson;
	}
	
	public static JSONObject getCWCMJson(String fName) throws JSONException{
		CWCMJson = CWCM.getCWCM(ifstatements.size(), switchstatments.size(), whilestatements.size(),forstatements.size());
		return CWCMJson;
	}
	
	public JSONArray getCWCMJson_Method() throws JSONException{
		CWCMArray = CWCM.getCWCM_Method(Mifstatements.size(), Mswitchstatments.size(), Mwhilestatements.size(),Mforstatements.size());
		return CWCMArray;
	}
	
	public JSONObject getExceptionHandlingJson() throws JSONException{
		ExceptionHandlingJson = ExceptionHandling.getException(trystatements.size());
		return ExceptionHandlingJson;
	}
	
	public JSONObject getCFSJson(String fName) throws JSONException, IOException{
		CFSJson	=	CFS.getCFSValue(file, fName);
		return CFSJson;
	}
	

		 
	
	public JSONObject getControlStructuresJSON() throws JSONException, IOException{
		ControlStructures controlstructures = new ControlStructures();
		ControlStructJson = controlstructures.Calculate(ifstatements, switchstatments.size(),
				whilestatements.size(),forstatements.size());
		return ControlStructJson;
	}
	
	public JSONObject getAccessModifiersJSON() throws JSONException, IOException{
		AccessModifiers accessmodifiers = new AccessModifiers();
		AccessModifiersComplxJson = accessmodifiers.Calculate(Mvariables2);
		return AccessModifiersComplxJson;
	}
	
	
	
	
	//Method to read file directory
		public static void ParseFilesInDir() throws IOException{
			File dirs = new File("D:/4thyearBackEnd/temp_files/");
			String dirPath = dirs.getCanonicalPath() + File.separator;//+"src"+File.separator;
	 
			File root = new File(dirPath);
			//System.out.println(rootDir.listFiles());
			File[] files = root.listFiles ( );
			filePath = null;
	 
			 for (File f : files ) {
				 filePath = f.getAbsolutePath();
				 file = filePath;
				 if(f.isFile()){
					 parse(readFileToString(filePath));
					 
				 }
			 }
		}
		
		public static void ParseFilesInDir2(String fName) throws IOException{
			File dirs = new File("D:/4thyearBackEnd/temp_files/");
			String dirPath = dirs.getCanonicalPath() + File.separator;//+"src"+File.separator;
	 
			File root = new File(dirPath);
			//System.out.println(rootDir.listFiles());
			File[] files = root.listFiles ( );
			filePath = null;
	 
			 for (File f : files ) {
				 filePath = f.getAbsolutePath();
				 file = filePath;
				 if(f.isFile()){
					 if(f.getName().equals(fName)){
					 parse(readFileToString(filePath));
					 }
				 }
			 }
		}
		
		public static JSONObject ParseCWCM(String fName,String mName) throws IOException, JSONException{
			File dirs = new File("D:/4thyearBackEnd/temp_files/");
			String dirPath = dirs.getCanonicalPath() + File.separator;//+"src"+File.separator;
	 
			File root = new File(dirPath);
			//System.out.println(rootDir.listFiles());
			File[] files = root.listFiles ( );
			filePath = null;
			JSONObject obj1 = new JSONObject();
			Collection<JSONObject> items = new ArrayList<JSONObject>();
	 
			 for (File f : files ) {
				 filePath = f.getAbsolutePath();
				 file = filePath;
				 if(f.isFile()){
					 	if(f.getName().equals(fName) && (f.getName() != "root")){
					 obj1.put("value", parseCWCM(readFileToString(filePath),mName));
					 	}
				 }
			 }
			 return obj1;
		}
		public static JSONObject ParseCFS(String fName,String mName) throws IOException, JSONException{
			File dirs = new File("D:/4thyearBackEnd/temp_files/");
			String dirPath = dirs.getCanonicalPath() + File.separator;//+"src"+File.separator;
	 
			File root = new File(dirPath);
			//System.out.println(rootDir.listFiles());
			File[] files = root.listFiles ( );
			filePath = null;
			JSONObject obj1 = new JSONObject();
			Collection<JSONObject> items = new ArrayList<JSONObject>();
	 
			 for (File f : files ) {
				 filePath = f.getAbsolutePath();
				 file = filePath;
				 if(f.isFile()){
					 	if(f.getName().equals(fName) ){
					 obj1.put("value", parseCFS(readFileToString(filePath), mName));
					 	}
				 }
			 }
			 return obj1;
		}
		
		
		public static JSONObject ParseControlStrucures(String fName,String mName) throws IOException, JSONException{
			File dirs = new File("D:/4thyearBackEnd/temp_files/");
			String dirPath = dirs.getCanonicalPath() + File.separator;//+"src"+File.separator;
	 
			File root = new File(dirPath);
			//System.out.println(rootDir.listFiles());
			File[] files = root.listFiles ( );
			filePath = null;
			JSONObject obj1 = new JSONObject();
			Collection<JSONObject> items = new ArrayList<JSONObject>();
	 
			 for (File f : files ) {
				 filePath = f.getAbsolutePath();
				 file = filePath;
				 if(f.isFile()){
					 	if((f.getName().equals(fName)) ){
					 		 obj1.put("value", parseControlStructures(readFileToString(filePath), mName));
					 	}
					
				 }
			 }
			 return obj1;
		}
		
		public static JSONObject ParseCC(String fName,String mName) throws IOException, JSONException{
			File dirs = new File("D:/4thyearBackEnd/temp_files/");
			String dirPath = dirs.getCanonicalPath() + File.separator;//+"src"+File.separator;
	 
			File root = new File(dirPath);
			//System.out.println(rootDir.listFiles());
			File[] files = root.listFiles ( );
			filePath = null;
			JSONObject obj1 = new JSONObject();
			Collection<JSONObject> items = new ArrayList<JSONObject>();
	 
			 for (File f : files ) {
				 filePath = f.getAbsolutePath();
				 file = filePath;
				 if(f.isFile()){
					 if(f.getName().equals(fName) && (f.getName() != "root")){
					 obj1.put("value", parseCC(readFileToString(filePath),mName));
					 }
				 }
			 }
			 return obj1;
		}
		
		public static JSONObject ParseException(String fName,String mName) throws IOException, JSONException{
			File dirs = new File("D:/4thyearBackEnd/temp_files/");
			String dirPath = dirs.getCanonicalPath() + File.separator;//+"src"+File.separator;
	 
			File root = new File(dirPath);
			//System.out.println(rootDir.listFiles());
			File[] files = root.listFiles ( );
			filePath = null;
			JSONObject obj1 = new JSONObject();
			Collection<JSONObject> items = new ArrayList<JSONObject>();
	 
			 for (File f : files ) {
				 filePath = f.getAbsolutePath();
				 file = filePath;
				 if(f.isFile()){
					 if(f.getName().equals(fName) ){
						 obj1.put("value", parseException(readFileToString(filePath),mName));
					 }
				 }
			 }
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
		
		
		public static void parse(String str) {
			
			ASTParser parser = ASTParser.newParser(AST.JLS3);
			parser.setSource(str.toCharArray());
			parser.setKind(ASTParser.K_COMPILATION_UNIT);
				
			final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
			
			classstartidx = cu.toString().indexOf("class");
			classendidx =  cu.toString().indexOf("{");
			classstruct.classname = cu.toString().substring(classstartidx, classendidx);
			 
			//	pass cu (Compilation Unit) for operand operator class
			  
			JSONObject obj1 = new JSONObject();   
			
			cu.accept(new ASTVisitor() {
	 
				Set names = new HashSet();
	 
				public boolean visit(VariableDeclarationFragment node) {
					SimpleName name = node.getName();
					this.names.add(name.getIdentifier());
//					System.out.println("Declaration of '" + name + "' at line"
//							+ cu.getLineNumber(name.getStartPosition()));
					variables.add(node);
					return false; // do not continue 
				}
				
				public boolean visit(MethodDeclaration node){
					methods.add(node);
							//Get Data MethodWise
					Collection<JSONObject> items = new ArrayList<JSONObject>();

					Mvariables.clear(); 
					Mtrystatements.clear(); 
					Mifstatements.clear(); 
					Mforstatements.clear(); 
					Mwhilestatements.clear(); 
					Mswitchstatments.clear(); 
							node.getBody().accept(	(new ASTVisitor() {
								
									
								public boolean visit(VariableDeclarationFragment node) {
									Mvariables.add(node);
								return false;
								}
								
								public boolean visit(TryStatement node){
									node.catchClauses().toString();
									Mtrystatements.add(node);
								return false;
								}
								
								public boolean visit(IfStatement node){
									Mifstatements.add(node);
								return false;
								}
								
								public boolean visit(ForStatement node){
									Mforstatements.add(node);
								return false;
								}
								
								public boolean visit(WhileStatement node){
									Mwhilestatements.add(node);
								return false;
								}
								
								public boolean visit(SwitchStatement node){
									
									Mswitchstatments.add(node);
								return false;
								}
								
								
							}));
							try {
								CWCMArray = CWCM.getCWCM_Method(Mifstatements.size(), Mswitchstatments.size(), Mwhilestatements.size(),Mforstatements.size());
								ExceptionHandlingJson = ExceptionHandling.getException(Mswitchstatments.size());
								items.add(CWCMJson);
								obj1.put("Method Name", node.getName().toString());
								obj1.put("value", items);
								
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println(obj1);
							return true;
				}
				
				
				
				public boolean visit(TryStatement node){
					trystatements.add(node);
					return false;
				}
				
				public boolean visit(IfStatement node){
					ifstatements.add(node);
					return false;
				}
				
				public boolean visit(ForStatement node){
					forstatements.add(node);
					return false;
				}
				
				public boolean visit(WhileStatement node){
					whilestatements.add(node);
					return false;
				}
				
				public boolean visit(SwitchStatement node){
					switchstatments.add(node);
					return false;
				}
	 
				public boolean visit(SimpleName node) {
					if (this.names.contains(node.getIdentifier())) {
						
//						System.out.println("Usage of '" + node + "' at line "
//								+ cu.getLineNumber(node.getStartPosition()));
					}
					return true;
				}
			});
		}
		
public static JSONArray parseCWCM(String str, String mName) {
			
			ASTParser parser = ASTParser.newParser(AST.JLS3);
			parser.setSource(str.toCharArray());
			parser.setKind(ASTParser.K_COMPILATION_UNIT);
				
			final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
			
			classstartidx = cu.toString().indexOf("public");
			classendidx =  cu.toString().indexOf("{");
			classstruct.classname = cu.toString().substring(classstartidx, classendidx);
			 
			//	pass cu (Compilation Unit) for operand operator class
			  
			JSONObject obj1 = new JSONObject();   
			
			cu.accept(new ASTVisitor() {
	 
				Set names = new HashSet();
	 
				public boolean visit(VariableDeclarationFragment node) {
					SimpleName name = node.getName();
					this.names.add(name.getIdentifier());
//					System.out.println("Declaration of '" + name + "' at line"
//							+ cu.getLineNumber(name.getStartPosition()));
					variables.add(node);
					return false; // do not continue 
				}
				
				public boolean visit(MethodDeclaration node){
					methods.add(node);
							//Get Data MethodWise
					Collection<JSONObject> items = new ArrayList<JSONObject>();

					Mvariables.clear(); 
					Mtrystatements.clear(); 
					Mifstatements.clear(); 
					Mforstatements.clear(); 
					Mwhilestatements.clear(); 
					Mswitchstatments.clear(); 
					
					if(node.getName().toString().equals(mName)){
						node.getBody().accept(	(new ASTVisitor() {
							
							
							public boolean visit(VariableDeclarationFragment node) {
								Mvariables.add(node);
							return false;
							}
							
							public boolean visit(TryStatement node){
								node.catchClauses().toString();
								Mtrystatements.add(node);
							return false;
							}
							
							public boolean visit(IfStatement node){
								Mifstatements.add(node);
							return false;
							}
							
							public boolean visit(ForStatement node){
								Mforstatements.add(node);
							return false;
							}
							
							public boolean visit(WhileStatement node){
								Mwhilestatements.add(node);
							return false;
							}
							
							public boolean visit(SwitchStatement node){
								
								Mswitchstatments.add(node);
							return false;
							}
							
							
						}));
						try {
							CWCMArray = CWCM.getCWCM_Method(Mifstatements.size(), Mswitchstatments.size(), Mwhilestatements.size(),Mforstatements.size());
							ExceptionHandlingJson = ExceptionHandling.getException(Mswitchstatments.size());
							//items.add(CWCMJson);
							obj1.put("Method Name", node.getName().toString());
							obj1.put("value", CWCMJson);
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(obj1);
						
					}
							
							return true;
				}
			});
			
			return CWCMArray;
}




public static JSONArray parseCFS(String str, String mName) {
	
	ASTParser parser = ASTParser.newParser(AST.JLS3);
	parser.setSource(str.toCharArray());
	parser.setKind(ASTParser.K_COMPILATION_UNIT);
		
	final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
	
	classstartidx = cu.toString().indexOf("public");
	classendidx =  cu.toString().indexOf("{");
	classstruct.classname = cu.toString().substring(classstartidx, classendidx);
	 
	//	pass cu (Compilation Unit) for operand operator class
	  
	JSONObject obj1 = new JSONObject();   
	Collection<JSONObject> items = new ArrayList<JSONObject>();
	
	cu.accept(new ASTVisitor() {

		Set names = new HashSet();

		public boolean visit(VariableDeclarationFragment node) {
			SimpleName name = node.getName();
			this.names.add(name.getIdentifier());
//			System.out.println("Declaration of '" + name + "' at line"
//					+ cu.getLineNumber(name.getStartPosition()));
			variables.add(node);
			return false; // do not continue 
		}
		
		public boolean visit(MethodDeclaration node){
			methods.add(node);
					//Get Data MethodWise
			

				if(node.getName().toString().equals(mName)){
					JSONObject retObj = new JSONObject();
					
					StringBuilder str = new StringBuilder();
					StringBuilder str2 = new StringBuilder();
					
					String str3 = node.getBody().toString();
					String[] lines = str3.split(System.getProperty("line.separator"));
					
					int inputValue = 0;
					int OutputValue = 0;
					int CFSValue = 0;
					int Weight = 1;
					
					for(String line : lines){
						String scnnerObjName = "";
						if( line.contains("new Scanner(System.in)")){
							
							String tempScannerName = line.substring(line.indexOf("Scanner"), line.indexOf("="));
							scnnerObjName = tempScannerName.substring(8, tempScannerName.length());
							
						}
						if (line.contains(scnnerObjName) ||  line.contains("readLine()") ){
							inputValue++;
						}
						if(line.contains("System.out.println")){
							//System.out.println(line);
							OutputValue++;
							//System.out.println(OutputValue);
						}
					}
					CFSValue = (inputValue + OutputValue) * Weight;
					
					JSONObject item1 = new JSONObject();
				    try {
						item1.put("name", "CFS Value");
						item1.put("value", CFSValue);
					    items.add(item1);
//					    obj1.put("Method Name", node.getName().toString());
//						obj1.put("value", new JSONArray(items));

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			
					   
					return true;
		}
	});
	
	return new JSONArray(items);
}

public static JSONArray parseCC(String str, String mName) {
	
	ASTParser parser = ASTParser.newParser(AST.JLS3);
	parser.setSource(str.toCharArray());
	parser.setKind(ASTParser.K_COMPILATION_UNIT);
		
	final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
	
	classstartidx = cu.toString().indexOf("public");
	classendidx =  cu.toString().indexOf("{");
	classstruct.classname = cu.toString().substring(classstartidx, classendidx);
	 
	//	pass cu (Compilation Unit) for operand operator class
	  
	JSONObject obj1 = new JSONObject();   
	
	cu.accept(new ASTVisitor() {

		Set names = new HashSet();

		public boolean visit(VariableDeclarationFragment node) {
			SimpleName name = node.getName();
			this.names.add(name.getIdentifier());
//			System.out.println("Declaration of '" + name + "' at line"
//					+ cu.getLineNumber(name.getStartPosition()));
			variables.add(node);
			return false; // do not continue 
		}
		
		public boolean visit(MethodDeclaration node){
			methods.add(node);
					//Get Data MethodWise
			Collection<JSONObject> items = new ArrayList<JSONObject>();

			Mvariables.clear(); 
			Mtrystatements.clear(); 
			Mifstatements.clear(); 
			Mforstatements.clear(); 
			Mwhilestatements.clear(); 
			Mswitchstatments.clear();
			
			if(node.getName().toString().equals(mName)){

				
				node.getBody().accept(	(new ASTVisitor() {
				public boolean visit(VariableDeclarationFragment node) {
					Mvariables.add(node);
				return false;
				}
				
				public boolean visit(TryStatement node){
					node.catchClauses().toString();
					Mtrystatements.add(node);
				return false;
				}
				
				public boolean visit(IfStatement node){
					Mifstatements.add(node);
				return false;
				}
				
				public boolean visit(ForStatement node){
					Mforstatements.add(node);
				return false;
				}
				
				public boolean visit(WhileStatement node){
					Mwhilestatements.add(node);
				return false;
				}
				
				public boolean visit(SwitchStatement node){
					
					Mswitchstatments.add(node);
				return false;
				}
				
				
			}));
			try {
				CCArray = CC.getCC_Method(Mifstatements.size(), Mswitchstatments.size(), Mwhilestatements.size(),Mforstatements.size());
//				obj1.put("Method Name", node.getName().toString());
//				obj1.put("value", CCJson);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			return true;
		}
	});
	
	return CCArray;
}
		
public static JSONArray parseControlStructures(String str, String mName) {
	
	ASTParser parser = ASTParser.newParser(AST.JLS3);
	parser.setSource(str.toCharArray());
	parser.setKind(ASTParser.K_COMPILATION_UNIT);
		
	final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
	
	classstartidx = cu.toString().indexOf("public");
	classendidx =  cu.toString().indexOf("{");
	classstruct.classname = cu.toString().substring(classstartidx, classendidx);
	 
	//	pass cu (Compilation Unit) for operand operator class
	  
	JSONObject obj1 = new JSONObject();   
	
	cu.accept(new ASTVisitor() {

		Set names = new HashSet();

		public boolean visit(VariableDeclarationFragment node) {
			SimpleName name = node.getName();
			this.names.add(name.getIdentifier());
//			System.out.println("Declaration of '" + name + "' at line"
//					+ cu.getLineNumber(name.getStartPosition()));
			variables.add(node);
			return false; // do not continue 
		}
		
		public boolean visit(MethodDeclaration node){
			methods.add(node);
					//Get Data MethodWise
			Collection<JSONObject> items = new ArrayList<JSONObject>();

			Mvariables.clear(); 
			Mtrystatements.clear(); 
			Mifstatements.clear(); 
			Mforstatements.clear(); 
			Mwhilestatements.clear(); 
			Mswitchstatments.clear(); 
			

			
			if(node.getName().toString().equals(mName)){
				node.getBody().accept(	(new ASTVisitor() {
					

					
					public boolean visit(VariableDeclarationFragment node) {
						Mvariables.add(node);
						System.out.println("Hello = " +Mvariables);
					return false;
					}
					
					public boolean visit(TryStatement node){
						node.catchClauses().toString();
						Mtrystatements.add(node);
					return false;
					}
					
					public boolean visit(IfStatement node){
						Mifstatements.add(node);
					return false;
					}
					
					public boolean visit(ForStatement node){
						Mforstatements.add(node);
						System.out.println("Hello = " +Mforstatements);
					return false;
					}
					
					public boolean visit(WhileStatement node){
						Mwhilestatements.add(node);
					return false;
					}
					
					public boolean visit(SwitchStatement node){
						
						Mswitchstatments.add(node);
					return false;
					}
					
					
				}));
				try {
					ControlStructArray = controlstructures.Calculate_Method(Mifstatements, Mswitchstatments.size(),
							Mwhilestatements.size(),Mforstatements.size());		
					//items.add(CWCMJson);
//					obj1.put("Method Name", node.getName().toString());
//					obj1.put("value", ControlStructJson);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(obj1);
				
			}
					
					return true;
		}
	});
	
	return ControlStructArray;
}		
	
public JSONObject getMHF() throws JSONException{ // new mhf
	MethodHidingFactor mhf = new MethodHidingFactor();
	return mhf.getMHF(methods);
}

//changed JSONarray to JSONObject
public JSONObject getGlib() throws JSONException{
	LogicalMetrics lm = new LogicalMetrics();
	return lm.getLogicalMetrics(ifstatements,switchstatments);
}

public JSONObject getNOA() throws JSONException, IOException{
	DIT numofancestors = new DIT();
	return numofancestors.getNOA();
}


public static JSONArray parseException(String str, String mName) throws JSONException {
	
	ASTParser parser = ASTParser.newParser(AST.JLS3);
	parser.setSource(str.toCharArray());
	parser.setKind(ASTParser.K_COMPILATION_UNIT);
		
	final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
	
	classstartidx = cu.toString().indexOf("public");
	classendidx =  cu.toString().indexOf("{");
	classstruct.classname = cu.toString().substring(classstartidx, classendidx);
	 
	//	pass cu (Compilation Unit) for operand operator class
	  
	JSONObject obj1 = new JSONObject();   
	
	cu.accept(new ASTVisitor() {

		Set names = new HashSet();

		public boolean visit(VariableDeclarationFragment node) {
			SimpleName name = node.getName();
			this.names.add(name.getIdentifier());
//			System.out.println("Declaration of '" + name + "' at line"
//					+ cu.getLineNumber(name.getStartPosition()));
			variables.add(node);
			return false; // do not continue 
		}
		
		public boolean visit(MethodDeclaration node){
			methods.add(node);
					//Get Data MethodWise
			Collection<JSONObject> items = new ArrayList<JSONObject>();

			Mvariables.clear(); 
			Mtrystatements.clear(); 
			Mifstatements.clear(); 
			Mforstatements.clear(); 
			Mwhilestatements.clear(); 
			Mswitchstatments.clear();
			
			
			
			if(node.getName().toString().equals(mName)){
				node.getBody().accept(	(new ASTVisitor() {
					
										
					public boolean visit(TryStatement node){
						node.catchClauses().toString();

						catches = node.catchClauses();

						for(int i=0; i<catches.size();i++){
							System.out.println("Chandeesha = " + catches.get(i));
						}
						
						Mtrystatements.add(node);
//						System.out.println("try = "+Mtrystatements);
					return false;
					}
				}));

				
				try {
					ExceptionHandlingArray = ExceptionHandling.getException_Method(catches.size());
											
					//items.add(CWCMJson);
					
					/*JSONObject item = new JSONObject();
				    item.put("Method Name", node.getName().toString());
				    item.put("value", totLoc);
				    
					obj1.put("Method Name", node.getName().toString());
					obj1.put("value", ExceptionHandlingJson);*/
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(obj1);
				
			}
					
					return true;
		}
	});
	
	return ExceptionHandlingArray;
}	





}
		

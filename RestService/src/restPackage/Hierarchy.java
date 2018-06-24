package restPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
//import java.util.Base64;
//import java.util.ba;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Hierarchy {
	String classname;
	static String filePath;
	static String json;
	static List<String> methodnames ;
	String methods;
	static StringBuilder sb;
	int classvalue, classstartidx, classendidx;
	int methodvalue;
	JSONObject retObj;
	static File[] listOfFiles;
	static int fileCount = 0 ;
	static int fileCount2 = 0 ;
	public Hierarchy(){
		fileCount = 0 ;
		 fileCount2 = 0 ;
	}
	public static void main(String[] args) throws IOException {
		
		File folder = new File("D:/4thyearBackEnd/temp_files");
		listOfFiles = folder.listFiles();

		    for (int i = 0; i < listOfFiles.length; i++) {
		    	fileCount ++;
		      if (listOfFiles[i].isFile()) {
		        System.out.println("File " + listOfFiles[i].getName());
		      } else if (listOfFiles[i].isDirectory()) {
		        System.out.println("Directory " + listOfFiles[i].getName());
		      }
		    }
		    sb = new StringBuilder(" { \n" +
					 " \"name\": "+    "\""+ "root"+"\""   +", \n " +"    \"children\" :  " + "[ \n");
	    	int loopCount = 0;
		    for(File f : listOfFiles){
		    	loopCount++;
		    	methodnames = new ArrayList<String>();
		    	methodnames.clear();
		    	
		    	ParseFilesInDir2(f);
		    	//ParseFilesInDir();
		    	//if()
		    	
		    	/*old
		    	 * sb = new StringBuilder(" { \n" +
						 " \"name\": "+    "\""+ f.getName()+"\""   +", \n " +"    \"children\" :  " + "[ \n");
		    	*/
		    	sb.append(" { \n" +
						 " \"name\": "+    "\""+ f.getName()+"\""   +", \n " +"    \"children\" :  " + "[ \n");
		    
		    	for(String m : methodnames){
					if ( methodnames.indexOf(m) != methodnames.size()-1){
						sb.append(		 	
						 		"{ \n"+
						 			" \"name\": "+  "\""+ m+"\""+ " , \n"+
						 			" \"size\": "+  "\""+ 256 +"\""+ "  \n"+
						 		"} , \n"
							);
					}
					else{
						sb.append(		 	
						 		"{ \n"+
						 			" \"name\": "+  "\""+ m+"\""+ " , \n"+
						 			" \"size\": "+  "\""+ 256 +"\""+ "  \n"+
						 		"}  \n"
							);
					}
					
				}
		    	
		    	if(fileCount == loopCount){
		    		sb.append(	"] \n" +" } ] } \n");
		    	}
		    	else{
		    		sb.append(	"] \n" +" } ,\n");
		    	}
		    		
				
				
				json = sb.toString();
		 		
				System.out.println("======="+json);
		    }
		    
		    
		    
		
	}
	public static String ast() throws IOException{

		
		File folder = new File("D:/4thyearBackEnd/temp_files");
		listOfFiles = folder.listFiles();

		    for (int i = 0; i < listOfFiles.length; i++) {
		    	fileCount2 ++;
		      if (listOfFiles[i].isFile()) {
		        System.out.println("File " + listOfFiles[i].getName());
		      } else if (listOfFiles[i].isDirectory()) {
		        System.out.println("Directory " + listOfFiles[i].getName());
		      }
		    }
		    sb = new StringBuilder(" { \n" +
					 " \"name\": "+    "\""+ "root"+"\""   +", \n " +"    \"children\" :  " + "[ \n");
	    	int loopCount = 0;
		    for(File f : listOfFiles){
		    	loopCount++;
		    	methodnames = new ArrayList<String>();
		    	methodnames.clear();
		    	
		    	ParseFilesInDir2(f);
		    	//ParseFilesInDir();
		    	//if()
		    	
		    	/*old
		    	 * sb = new StringBuilder(" { \n" +
						 " \"name\": "+    "\""+ f.getName()+"\""   +", \n " +"    \"children\" :  " + "[ \n");
		    	*/
		    	sb.append(" { \n" +
						 " \"name\": "+    "\""+ f.getName()+"\""   +", \n " +"    \"children\" :  " + "[ \n");
		    
		    	for(String m : methodnames){
					if ( methodnames.indexOf(m) != methodnames.size()-1){
						sb.append(		 	
						 		"{ \n"+
						 			" \"name\": "+  "\""+ m+"\""+ " , \n"+
						 			" \"size\": "+  "\""+ 256 +"\""+ "  \n"+
						 		"} , \n"
							);
					}
					else{
						sb.append(		 	
						 		"{ \n"+
						 			" \"name\": "+  "\""+ m+"\""+ " , \n"+
						 			" \"size\": "+  "\""+ 256 +"\""+ "  \n"+
						 		"}  \n"
							);
					}
					
				}
		    	
		    	if(fileCount2 == loopCount){
		    		sb.append(	"] \n" +" } ] } \n");
		    	}
		    	else{
		    		sb.append(	"] \n" +" } ,\n");
		    	}
		    		
				
				
				json = sb.toString();
		 		
				System.out.println("======="+json);
		    }
		    
		    
		return sb.toString();    
		
	
	}
	public static void ParseFilesInDir2(File f) throws IOException{

		
			 filePath = f.getAbsolutePath();
			 //file = filePath;
			 if(f.isFile()){
				 parse(readFileToString(filePath));
			 }
		 
	}
	
	public static void ParseFilesInDir() throws IOException{
		File dirs = new File("D:/4thyearBackEnd/temp_files");
		String dirPath = dirs.getCanonicalPath() + File.separator;//+"src"+File.separator;
 
		File root = new File(dirPath);
		//System.out.println(rootDir.listFiles());
		File[] files = root.listFiles ( );
		filePath = null;
 
		 for (File f : files ) {
			 filePath = f.getAbsolutePath();
			 //file = filePath;
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
			System.out.println(numRead);
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
		
		cu.accept(new ASTVisitor() {
			
			public boolean visit(MethodDeclaration node){
				
				methodnames.add(node.getName().toString());
				
				return false;
			}
			
		});
	}
	
	public static void createJson() throws IOException{
		String  line;
		//structure.methodname = structure.methodname.substring(1,structure.methodname.length()-1);
		
	
		for(File f : listOfFiles){
		    
		    StringBuilder fileData = new StringBuilder(1000);
			BufferedReader reader = new BufferedReader(new FileReader(f));
			

	 
			char[] buf = new char[10];
			int numRead = 0;
			while ((numRead = reader.read(buf)) != -1) {
				System.out.println(numRead);
				String readData = String.valueOf(buf, 0, numRead);
				fileData.append(readData);
				buf = new char[1024];
			}
	 
			reader.close();
			
			sb = new StringBuilder(" { \n" +
				 " \"name\": "+    "\""+ f.getName()+"\""   +", \n " +"    \"children\" :  " + "[ \n");
			
			
			
			
			ASTParser parser = ASTParser.newParser(AST.JLS3);
			parser.setSource(fileData.toString().toCharArray());
			parser.setKind(ASTParser.K_COMPILATION_UNIT);
				
			final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
			
 			cu.accept(new ASTVisitor() {
				public boolean visit(MethodDeclaration node){
					
					methodnames.add(node.getName().toString());
					return false;
				}
				
			});
			
			for(String m : methodnames){
				if ( methodnames.indexOf(m) != methodnames.size()-1){
					sb.append(		 	
					 		"{ \n"+
					 			" \"name\": "+  "\""+ m+"\""+ " , \n"+
					 			" \"size\": "+  "\""+ 256 +"\""+ "  \n"+
					 		"} , \n"
						);
				}
				else{
					sb.append(		 	
					 		"{ \n"+
					 			" \"name\": "+  "\""+ m+"\""+ " , \n"+
					 			" \"size\": "+  "\""+ 256 +"\""+ "  \n"+
					 		"}  \n"
						);
				}
				
			}
			
			sb.append(	"] \n" +" } \n");
			
		}
		
		
		
//		sb = new StringBuilder(" { \n" +
//				 " \"name\": "+    "\""+ structure.classname+"\""   +", \n " +"    \"children\" :  " + "[ \n");
//		
//		for(String m : methodnames){
//			
//			if ( methodnames.indexOf(m) != methodnames.size()-1)
//			{
//			sb.append(		 	
//				 		"{ \n"+
//				 			" \"name\": "+  "\""+ m+"\""+ " , \n"+
//				 			" \"size\": "+  "\""+ 256 +"\""+ "  \n"+
//				 		"} , \n"
//					);
//		}
//			else{
//				sb.append(		 	
//				 		"{ \n"+
//				 			" \"name\": "+  "\""+ m+"\""+ " , \n"+
//				 			" \"size\": "+  "\""+ 256 +"\""+ "  \n"+
//				 		"}  \n"
//					);
//			}
//				
//		}
//		
//		sb.append(	"] \n" +" } \n");
		
		json = sb.toString();
		 		
		System.out.println("======="+json);
	}
	
	public JSONObject methodDetails(MethodComplexity m){
		retObj = new JSONObject();
		
		return retObj;
	}
}

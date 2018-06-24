package restPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ast {

	public static void main(String[] args) throws IOException {

		//parseOperators("D:/SLIIT/4th Year 1st Sem/CDAP/Metrics Logic/Operators.txt");
		//System.out.println(parseTest());
		
	}
	
	public static String parseTest(String filePath) throws IOException {
		
		String str="";
		
		StringBuilder strBuilder = new StringBuilder();
		
		//File file = new File("D:/SLIIT/4th Year 1st Sem/CDAP/Metrics Logic/Operators.txt");
		File file = new File(filePath);
		String source = FileUtils.readFileToString(file);
		
		
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(source.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
 
 
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
 
		cu.accept(new ASTVisitor() {
 
			Set names = new HashSet();
 
			public boolean visit(VariableDeclarationFragment node) {
				SimpleName name = node.getName();
				this.names.add(name.getIdentifier());
				strBuilder.append("Declaration of '"+name+"' at line"+cu.getLineNumber(name.getStartPosition()) + "\n");
				return false; // do not continue to avoid usage info
			}
 
			public boolean visit(SimpleName node) {
				if (this.names.contains(node.getIdentifier())) {
					strBuilder.append("Usage of '" + node + "' at line " +	cu.getLineNumber(node.getStartPosition())+ "\n");
				}
				return true;
			}
 
		});
		
		str=strBuilder.toString();
		return str;
		
	}
	
	public static String parseTest() throws IOException {
		
		String str="";
		
		StringBuilder strBuilder = new StringBuilder();
		
		File file = new File("D:/SLIIT/4th Year 1st Sem/CDAP/Metrics Logic/Operators.txt");
		//File file = new File(filePath);
		String source = FileUtils.readFileToString(file);
		
		
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(source.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
 
 
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
 
		cu.accept(new ASTVisitor() {
 
			Set names = new HashSet();
 
			public boolean visit(VariableDeclarationFragment node) {
				SimpleName name = node.getName();
				this.names.add(name.getIdentifier());
				strBuilder.append("Declaration of '"+name+"' at line"+cu.getLineNumber(name.getStartPosition()) + "\n");
				return false; // do not continue to avoid usage info
			}
 
			public boolean visit(SimpleName node) {
				if (this.names.contains(node.getIdentifier())) {
					strBuilder.append("Usage of '" + node + "' at line " +	cu.getLineNumber(node.getStartPosition())+ "\n");
				}
				return true;
			}
 
		});
		
		str=strBuilder.toString();
		return str;
		
	}
	
	public static JSONObject parseOperators(String filePath) throws IOException, JSONException {
		
		JSONObject obj1 = new JSONObject();
		
		JSONObject jo = new JSONObject();
	    Collection<JSONObject> items = new ArrayList<JSONObject>();
		
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
		
		String ops = "[()=;{}[\\]+\\-*/&!%^|<>']";
		
		try {
			File file = new File(filePath);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			//StringBuffer stringBuffer = new StringBuffer();
			String line;
			String[] operands;
			String operand="";
			while ((line = bufferedReader.readLine()) != null) {
				
				
				//Other Operators
				if(line.contains("System.out.println")){
					opsCount += StringUtils.countMatches(line, "System.out.println");
					otherOps += StringUtils.countMatches(line, "System.out.println");
//					operands = line.split("System.out.println");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("System.out.println")){
						uniqueOps++;
						str.append("System.out.println");							
					}	
				}
				
				if(line.contains(".")){
					opsCount += StringUtils.countMatches(line, ".");
					otherOps += StringUtils.countMatches(line, ".");
//					operands = operand.split(".");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains(".")){
						uniqueOps++;
						str.append(".");							
					}	
				}if(line.contains("{")){
					opsCount += StringUtils.countMatches(line, "{");
					otherOps += StringUtils.countMatches(line, "{");
					/*operands = operand.split("{");
					for(int x=0; x<operands.length;x++){
						operand = operand + " " + operands[x];
					}*/
					if(!str.toString().contains("{")){
						uniqueOps++;
						str.append("{");							
					}	
				}if(line.contains("(")){
					opsCount += StringUtils.countMatches(line, "(");
					otherOps += StringUtils.countMatches(line, "(");
					/*operands = operand.split("(");
					for(int x=0; x<operands.length;x++){
						operand = operand + " " + operands[x];
					}*/
					if(!str.toString().contains("(")){
						uniqueOps++;
						str.append("(");							
					}	
				}if(line.contains("[")){
					opsCount += StringUtils.countMatches(line, "[");
					otherOps += StringUtils.countMatches(line, "[");
					/*operands = operand.split("[");
					for(int x=0; x<operands.length;x++){
						operand = operand + " " + operands[x];
					}*/
					if(!str.toString().contains("[")){
						uniqueOps++;
						str.append("[");							
					}	
				}if(line.contains("public")){
					opsCount += StringUtils.countMatches(line, "public");
					otherOps += StringUtils.countMatches(line, "public");
//					operands = operand.split("public");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("public")){
						uniqueOps++;
						str.append("public");							
					}
				}if(line.contains("private")){
					opsCount += StringUtils.countMatches(line, "private");
					otherOps += StringUtils.countMatches(line, "private");
//					operands = operand.split("private");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("private")){
						uniqueOps++;
						str.append("private");							
					}
				}if(line.contains("protected")){
					opsCount += StringUtils.countMatches(line, "protected");
					otherOps += StringUtils.countMatches(line, "protected");
//					operands = operand.split("protected");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("protected")){
						uniqueOps++;
						str.append("protected");							
					}
				}if(line.contains("static")){
					opsCount += StringUtils.countMatches(line, "static");
					otherOps += StringUtils.countMatches(line, "static");
//					operands = operand.split("static");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("static")){
						uniqueOps++;
						str.append("static");							
					}
				}if(line.contains("local")){
					opsCount += StringUtils.countMatches(line, "local");
					otherOps += StringUtils.countMatches(line, "local");
//					operands = operand.split("local");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("local")){
						uniqueOps++;
						str.append("local");							
					}
				}if(line.contains("instance")){
					opsCount += StringUtils.countMatches(line, "instance");
					otherOps += StringUtils.countMatches(line, "instance");
//					operands = operand.split("instance");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("instance")){
						uniqueOps++;
						str.append("instance");							
					}
				}if(line.contains("for(")){
					opsCount += StringUtils.countMatches(line, "for(");
					otherOps += StringUtils.countMatches(line, "for(");
//					operands = operand.split("for");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("for(")){
						uniqueOps++;
						str.append("for(");							
					}
				}if(line.contains("if(")){
					opsCount += StringUtils.countMatches(line, "if(");
					otherOps += StringUtils.countMatches(line, "if(");
//					operands = operand.split("if");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("if(")){
						uniqueOps++;
						str.append("if(");							
					}
				}if(line.contains(";")){
					opsCount += StringUtils.countMatches(line, ";");
					otherOps += StringUtils.countMatches(line, ";");
//					operands = operand.split(";");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains(";")){
						uniqueOps++;
						str.append(";");							
					}
				}if(line.contains("while(")){
					opsCount += StringUtils.countMatches(line, "while(");
					otherOps += StringUtils.countMatches(line, "while(");
//					operands = operand.split("while");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("while(")){
						uniqueOps++;
						str.append("while(");							
					}
				}if(line.contains("try")){
					opsCount += StringUtils.countMatches(line, "try");
					otherOps += StringUtils.countMatches(line, "try");
//					operands = operand.split("try");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("while(")){
						uniqueOps++;
						str.append("try");							
					}
				}if(line.contains("do")){
					opsCount += StringUtils.countMatches(line, "do");
					otherOps += StringUtils.countMatches(line, "do");
//					operands = operand.split("do");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("do")){
						uniqueOps++;
						str.append("do");							
					}
				}
				
				//Arithmatic Operators
				if(line.contains("+") && !line.contains("+=") && !line.contains("++")){
					opsCount += StringUtils.countMatches(line, "+");
					arOps += StringUtils.countMatches(line, "+");
//					operands = operand.split("+");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("+")){
						uniqueOps++;
						str.append("+");							
					}
				}if(line.contains("-") && !line.contains("-=") && !line.contains("--")){
					opsCount += StringUtils.countMatches(line, "-");
					arOps += StringUtils.countMatches(line, "-");
//					operands = operand.split("-");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("-")){
						uniqueOps++;
						str.append("-");							
					}
				}if(line.contains("*") && !line.contains("*=")){
					opsCount += StringUtils.countMatches(line, "*");
					arOps += StringUtils.countMatches(line, "*");
//					operands = operand.split("*");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("*")){
						uniqueOps++;
						str.append("*");							
					}
				}if(line.contains("/") && !line.contains("/=")){
					opsCount += StringUtils.countMatches(line, "/");
					arOps += StringUtils.countMatches(line, "/");
//					operands = operand.split("/");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
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
//					operands = operand.split("++");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("++")){
						uniqueOps++;
						str.append("++");							
					}
				}
				if(line.contains("--")){
					opsCount += StringUtils.countMatches(line, "--");
					arOps += StringUtils.countMatches(line, "--");
//					operands = operand.split("--");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("--")){
						uniqueOps++;
						str.append("--");							
					}
				}
				
				//Relational Operators
				if(line.contains("!=")){
					opsCount += StringUtils.countMatches(line, "!=");
					reOps += StringUtils.countMatches(line, "!=");
//					operands = operand.split("!=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("!=")){
						uniqueOps++;
						str.append("!=");							
					}
				}if(line.contains("==")){
					opsCount += StringUtils.countMatches(line, "==");
					reOps += StringUtils.countMatches(line, "==");
//					operands = operand.split("==");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("==")){
						uniqueOps++;
						str.append("==");							
					}
				}if(line.contains(">") && !line.contains(">=")){
					opsCount += StringUtils.countMatches(line, ">");
					reOps += StringUtils.countMatches(line, ">");
//					operands = operand.split(">");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains(">")){
						uniqueOps++;
						str.append(">");							
					}
				}if(line.contains("<") && !line.contains("<=")){
					opsCount += StringUtils.countMatches(line, "<");
					reOps += StringUtils.countMatches(line, "<");
//					operands = operand.split("<=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("<")){
						uniqueOps++;
						str.append("<");							
					}
				}if(line.contains(">=")){
					opsCount += StringUtils.countMatches(line, ">=");
					reOps += StringUtils.countMatches(line, ">=");
//					operands = operand.split(">=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains(">=")){
						uniqueOps++;
						str.append(">=");							
					}
				}if(line.contains("<=")){
					opsCount += StringUtils.countMatches(line, "<=");
					reOps += StringUtils.countMatches(line, "<=");
//					operands = operand.split("<=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("<=")){
						uniqueOps++;
						str.append("<=");							
					}
				}
				
				
				//Logical Operators
				if(line.contains("&&")){
					opsCount += StringUtils.countMatches(line, "&&");
					logOps += StringUtils.countMatches(line, "&&");
//					operands = operand.split("&&");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("&&")){
						uniqueOps++;
						str.append("&&");							
					}
				}if(line.contains("||")){
					opsCount += StringUtils.countMatches(line, "||");
					logOps += StringUtils.countMatches(line, "||");
//					operands = operand.split("||");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("||")){
						uniqueOps++;
						str.append("||");							
					}
				}if(line.contains("!") && !line.contains("!=")){
					opsCount += StringUtils.countMatches(line, "!");
					logOps += StringUtils.countMatches(line, "!");
//					operands = operand.split("!=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("!")){
						uniqueOps++;
						str.append("!");							
					}
				}
				
				
				//Bitwise Operators
				if(line.contains("&") && !line.contains("&=")){
					opsCount += StringUtils.countMatches(line, "&");
					bitOps += StringUtils.countMatches(line, "&");
//					operands = operand.split("&");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("&")){
						uniqueOps++;
						str.append("&");							
					}
				}if(line.contains("|") && !line.contains("||") && !line.contains("|=")){
					opsCount += StringUtils.countMatches(line, "|");
					bitOps += StringUtils.countMatches(line, "|");
//					operands = operand.split("|");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("|")){
						uniqueOps++;
						str.append("|");							
					}
				}if(line.contains("~")){
					opsCount += StringUtils.countMatches(line, "~");
					bitOps += StringUtils.countMatches(line, "~");
//					operands = operand.split("~");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("~")){
						uniqueOps++;
						str.append("~");							
					}
				}if(line.contains("<<") && !line.contains("<<=")){
					opsCount += StringUtils.countMatches(line, "<<");
					bitOps += StringUtils.countMatches(line, "<<");
//					operands = operand.split("<<");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("<<")){
						uniqueOps++;
						str.append("<<");							
					}
				}if(line.contains(">>") && !line.contains(">>=")){
					opsCount += StringUtils.countMatches(line, ">>");
					bitOps += StringUtils.countMatches(line, ">>");
//					operands = operand.split(">>");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains(">>")){
						uniqueOps++;
						str.append(">>");							
					}
				}
				
				//Assignment Operators
				if(line.contains("=") && !line.contains("==")){
					opsCount += StringUtils.countMatches(line, "=");
					assOps += StringUtils.countMatches(line, "=");
//					operands = operand.split("=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("=")){
						uniqueOps++;
						str.append("=");							
					}
				}if(line.contains("+=")){
					opsCount += StringUtils.countMatches(line, "+=");
					assOps += StringUtils.countMatches(line, "+=");
//					operands = operand.split("+=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("+=")){
						uniqueOps++;
						str.append("+=");							
					}
				}if(line.contains("-=")){
					opsCount += StringUtils.countMatches(line, "-=");
					assOps += StringUtils.countMatches(line, "-=");
//					operands = operand.split("-=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("-=")){
						uniqueOps++;
						str.append("-=");							
					}
				}if(line.contains("*=")){
					opsCount += StringUtils.countMatches(line, "*=");
					assOps += StringUtils.countMatches(line, "*=");
//					operands = operand.split("*=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("*=")){
						uniqueOps++;
						str.append("*=");							
					}
				}if(line.contains("/=")){
					opsCount += StringUtils.countMatches(line, "/=");
					assOps += StringUtils.countMatches(line, "/=");
//					operands = operand.split("/=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("/=")){
						uniqueOps++;
						str.append("/=");							
					}
				}if(line.contains("%=")){
					opsCount += StringUtils.countMatches(line, "%=");
					assOps += StringUtils.countMatches(line, "%=");
//					operands = operand.split("%=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("%=")){
						uniqueOps++;
						str.append("%=");							
					}
				}if(line.contains("<<=")){
					opsCount += StringUtils.countMatches(line, "<<=");
					assOps += StringUtils.countMatches(line, "<<=");
//					operands = operand.split("<<=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("<<=")){
						uniqueOps++;
						str.append("<<=");							
					}
				}if(line.contains(">>=")){
					opsCount += StringUtils.countMatches(line, ">>=");
					assOps += StringUtils.countMatches(line, ">>=");
//					operands = operand.split(">>=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains(">>=")){
						uniqueOps++;
						str.append(">>=");							
					}
				}if(line.contains("&=")){
					opsCount += StringUtils.countMatches(line, "&=");
					assOps += StringUtils.countMatches(line, "&=");
//					operands = operand.split("&=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("&=")){
						uniqueOps++;
						str.append("&=");							
					}
				}if(line.contains("^=")){
					opsCount += StringUtils.countMatches(line, "^=");
					assOps += StringUtils.countMatches(line, "^=");
//					operands = operand.split("^=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("^=")){
						uniqueOps++;
						str.append("^=");							
					}
				}if(line.contains("|=")){
					opsCount += StringUtils.countMatches(line, "|=");
					assOps += StringUtils.countMatches(line, "|=");
//					operands = operand.split("|=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("|=")){
						uniqueOps++;
						str.append("|=");							
					}
				}
				
				
				//Operands
				if(line.contains("int ")){
					opdCount += StringUtils.countMatches(line, "int ");
//					operands = operand.split("int  ");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str2.toString().contains("int ")){
						uniqueOpd++;
						str2.append("int ");							
					}
				}if(line.contains("string ")){
					opdCount += StringUtils.countMatches(line, "string ");
//					operands = operand.split("string ");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str2.toString().contains("string ")){
						uniqueOpd++;
						str2.append("string ");							
					}
				}if(line.contains("float ")){
					opdCount += StringUtils.countMatches(line, "float ");
//					operands = operand.split("float ");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str2.toString().contains("float ")){
						uniqueOpd++;
						str2.append("float ");							
					}
				}if(line.contains("void ")){
					opdCount += StringUtils.countMatches(line, "void ");
//					operands = operand.split("void ");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str2.toString().contains("void ")){
						uniqueOpd++;
						str2.append("void ");							
					}
				}if(line.contains("double ")){
					opdCount += StringUtils.countMatches(line, "double ");
//					operands = operand.split("double ");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str2.toString().contains("double ")){
						uniqueOpd++;
						str2.append("double ");							
					}
				}if(line.contains("\"")){
					opdCount += StringUtils.countMatches(line, "\"");
					operands = operand.split("\"");
					if(!str2.toString().contains("\"")){
						uniqueOpd++;
						str2.append("\"");							
					}
				}if(line.contains("Exception")){
					opdCount += StringUtils.countMatches(line, "Exception");
					operands = operand.split("Exception");
					if(!str2.toString().contains("Exception")){
						uniqueOpd++;
						str2.append("Exception");							
					}
				}
				
				//String[] words = operand.split(" ");
				
				//opdCount += words.length;
				//uniqueOpd += words.length;
				
				//System.out.println("Operator Count = " + opdCount);
				
			}
			fileReader.close();
			//System.out.println("Contents of file:");
			//System.out.println(stringBuffer.toString());
			
			/*System.out.println("Operator Count = " + opsCount);
			System.out.println("Unique Count = " + uniqueOps);
			System.out.println("Artihmatic Count = " + arOps);
			System.out.println("Relational Count = " + reOps);
			System.out.println("Logical Count = " + logOps);
			System.out.println("Bitwise Count = " + bitOps);
			System.out.println("Assignment Count = " + assOps);
			System.out.println("Other Count = " + otherOps);
			System.out.println("Operand Count = " + opdCount);
			System.out.println("Unique Count = " + uniqueOpd);*/
			
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
			
			
					
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		obj1.put("value", new JSONArray(items));
		//System.out.println(obj1);
		return obj1;
	}
	
public static JSONObject parseHalstead(String filePath) throws IOException, JSONException {
		
		JSONObject obj1 = new JSONObject();
		
		JSONObject jo = new JSONObject();
	    Collection<JSONObject> items = new ArrayList<JSONObject>();
		
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
		
		String ops = "[()=;{}[\\]+\\-*/&!%^|<>']";
		
		try {
			File file = new File(filePath);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			//StringBuffer stringBuffer = new StringBuffer();
			String line;
			String[] operands;
			String operand="";
			while ((line = bufferedReader.readLine()) != null) {
				
				
				//Other Operators
				if(line.contains("System.out.println")){
					opsCount += StringUtils.countMatches(line, "System.out.println");
					otherOps += StringUtils.countMatches(line, "System.out.println");
//					operands = line.split("System.out.println");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("System.out.println")){
						uniqueOps++;
						str.append("System.out.println");							
					}	
				}
				
				if(line.contains(".")){
					opsCount += StringUtils.countMatches(line, ".");
					otherOps += StringUtils.countMatches(line, ".");
//					operands = operand.split(".");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains(".")){
						uniqueOps++;
						str.append(".");							
					}	
				}if(line.contains("{")){
					opsCount += StringUtils.countMatches(line, "{");
					otherOps += StringUtils.countMatches(line, "{");
					/*operands = operand.split("{");
					for(int x=0; x<operands.length;x++){
						operand = operand + " " + operands[x];
					}*/
					if(!str.toString().contains("{")){
						uniqueOps++;
						str.append("{");							
					}	
				}if(line.contains("(")){
					opsCount += StringUtils.countMatches(line, "(");
					otherOps += StringUtils.countMatches(line, "(");
					/*operands = operand.split("(");
					for(int x=0; x<operands.length;x++){
						operand = operand + " " + operands[x];
					}*/
					if(!str.toString().contains("(")){
						uniqueOps++;
						str.append("(");							
					}	
				}if(line.contains("[")){
					opsCount += StringUtils.countMatches(line, "[");
					otherOps += StringUtils.countMatches(line, "[");
					/*operands = operand.split("[");
					for(int x=0; x<operands.length;x++){
						operand = operand + " " + operands[x];
					}*/
					if(!str.toString().contains("[")){
						uniqueOps++;
						str.append("[");							
					}	
				}if(line.contains("public")){
					opsCount += StringUtils.countMatches(line, "public");
					otherOps += StringUtils.countMatches(line, "public");
//					operands = operand.split("public");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("public")){
						uniqueOps++;
						str.append("public");							
					}
				}if(line.contains("private")){
					opsCount += StringUtils.countMatches(line, "private");
					otherOps += StringUtils.countMatches(line, "private");
//					operands = operand.split("private");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("private")){
						uniqueOps++;
						str.append("private");							
					}
				}if(line.contains("protected")){
					opsCount += StringUtils.countMatches(line, "protected");
					otherOps += StringUtils.countMatches(line, "protected");
//					operands = operand.split("protected");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("protected")){
						uniqueOps++;
						str.append("protected");							
					}
				}if(line.contains("static")){
					opsCount += StringUtils.countMatches(line, "static");
					otherOps += StringUtils.countMatches(line, "static");
//					operands = operand.split("static");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("static")){
						uniqueOps++;
						str.append("static");							
					}
				}if(line.contains("local")){
					opsCount += StringUtils.countMatches(line, "local");
					otherOps += StringUtils.countMatches(line, "local");
//					operands = operand.split("local");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("local")){
						uniqueOps++;
						str.append("local");							
					}
				}if(line.contains("instance")){
					opsCount += StringUtils.countMatches(line, "instance");
					otherOps += StringUtils.countMatches(line, "instance");
//					operands = operand.split("instance");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("instance")){
						uniqueOps++;
						str.append("instance");							
					}
				}if(line.contains("for(")){
					opsCount += StringUtils.countMatches(line, "for(");
					otherOps += StringUtils.countMatches(line, "for(");
//					operands = operand.split("for");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("for(")){
						uniqueOps++;
						str.append("for(");							
					}
				}if(line.contains("if(")){
					opsCount += StringUtils.countMatches(line, "if(");
					otherOps += StringUtils.countMatches(line, "if(");
//					operands = operand.split("if");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("if(")){
						uniqueOps++;
						str.append("if(");							
					}
				}if(line.contains(";")){
					opsCount += StringUtils.countMatches(line, ";");
					otherOps += StringUtils.countMatches(line, ";");
//					operands = operand.split(";");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains(";")){
						uniqueOps++;
						str.append(";");							
					}
				}if(line.contains("while(")){
					opsCount += StringUtils.countMatches(line, "while(");
					otherOps += StringUtils.countMatches(line, "while(");
//					operands = operand.split("while");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("while(")){
						uniqueOps++;
						str.append("while(");							
					}
				}if(line.contains("try")){
					opsCount += StringUtils.countMatches(line, "try");
					otherOps += StringUtils.countMatches(line, "try");
//					operands = operand.split("try");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("while(")){
						uniqueOps++;
						str.append("try");							
					}
				}if(line.contains("do")){
					opsCount += StringUtils.countMatches(line, "do");
					otherOps += StringUtils.countMatches(line, "do");
//					operands = operand.split("do");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("do")){
						uniqueOps++;
						str.append("do");							
					}
				}
				
				//Arithmatic Operators
				if(line.contains("+") && !line.contains("+=") && !line.contains("++")){
					opsCount += StringUtils.countMatches(line, "+");
					arOps += StringUtils.countMatches(line, "+");
//					operands = operand.split("+");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("+")){
						uniqueOps++;
						str.append("+");							
					}
				}if(line.contains("-") && !line.contains("-=") && !line.contains("--")){
					opsCount += StringUtils.countMatches(line, "-");
					arOps += StringUtils.countMatches(line, "-");
//					operands = operand.split("-");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("-")){
						uniqueOps++;
						str.append("-");							
					}
				}if(line.contains("*") && !line.contains("*=")){
					opsCount += StringUtils.countMatches(line, "*");
					arOps += StringUtils.countMatches(line, "*");
//					operands = operand.split("*");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("*")){
						uniqueOps++;
						str.append("*");							
					}
				}if(line.contains("/") && !line.contains("/=")){
					opsCount += StringUtils.countMatches(line, "/");
					arOps += StringUtils.countMatches(line, "/");
//					operands = operand.split("/");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
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
//					operands = operand.split("++");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("++")){
						uniqueOps++;
						str.append("++");							
					}
				}
				if(line.contains("--")){
					opsCount += StringUtils.countMatches(line, "--");
					arOps += StringUtils.countMatches(line, "--");
//					operands = operand.split("--");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("--")){
						uniqueOps++;
						str.append("--");							
					}
				}
				
				//Relational Operators
				if(line.contains("!=")){
					opsCount += StringUtils.countMatches(line, "!=");
					reOps += StringUtils.countMatches(line, "!=");
//					operands = operand.split("!=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("!=")){
						uniqueOps++;
						str.append("!=");							
					}
				}if(line.contains("==")){
					opsCount += StringUtils.countMatches(line, "==");
					reOps += StringUtils.countMatches(line, "==");
//					operands = operand.split("==");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("==")){
						uniqueOps++;
						str.append("==");							
					}
				}if(line.contains(">") && !line.contains(">=")){
					opsCount += StringUtils.countMatches(line, ">");
					reOps += StringUtils.countMatches(line, ">");
//					operands = operand.split(">");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains(">")){
						uniqueOps++;
						str.append(">");							
					}
				}if(line.contains("<") && !line.contains("<=")){
					opsCount += StringUtils.countMatches(line, "<");
					reOps += StringUtils.countMatches(line, "<");
//					operands = operand.split("<=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("<")){
						uniqueOps++;
						str.append("<");							
					}
				}if(line.contains(">=")){
					opsCount += StringUtils.countMatches(line, ">=");
					reOps += StringUtils.countMatches(line, ">=");
//					operands = operand.split(">=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains(">=")){
						uniqueOps++;
						str.append(">=");							
					}
				}if(line.contains("<=")){
					opsCount += StringUtils.countMatches(line, "<=");
					reOps += StringUtils.countMatches(line, "<=");
//					operands = operand.split("<=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("<=")){
						uniqueOps++;
						str.append("<=");							
					}
				}
				
				
				//Logical Operators
				if(line.contains("&&")){
					opsCount += StringUtils.countMatches(line, "&&");
					logOps += StringUtils.countMatches(line, "&&");
//					operands = operand.split("&&");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("&&")){
						uniqueOps++;
						str.append("&&");							
					}
				}if(line.contains("||")){
					opsCount += StringUtils.countMatches(line, "||");
					logOps += StringUtils.countMatches(line, "||");
//					operands = operand.split("||");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("||")){
						uniqueOps++;
						str.append("||");							
					}
				}if(line.contains("!") && !line.contains("!=")){
					opsCount += StringUtils.countMatches(line, "!");
					logOps += StringUtils.countMatches(line, "!");
//					operands = operand.split("!=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("!")){
						uniqueOps++;
						str.append("!");							
					}
				}
				
				
				//Bitwise Operators
				if(line.contains("&") && !line.contains("&=")){
					opsCount += StringUtils.countMatches(line, "&");
					bitOps += StringUtils.countMatches(line, "&");
//					operands = operand.split("&");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("&")){
						uniqueOps++;
						str.append("&");							
					}
				}if(line.contains("|") && !line.contains("||") && !line.contains("|=")){
					opsCount += StringUtils.countMatches(line, "|");
					bitOps += StringUtils.countMatches(line, "|");
//					operands = operand.split("|");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("|")){
						uniqueOps++;
						str.append("|");							
					}
				}if(line.contains("~")){
					opsCount += StringUtils.countMatches(line, "~");
					bitOps += StringUtils.countMatches(line, "~");
//					operands = operand.split("~");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("~")){
						uniqueOps++;
						str.append("~");							
					}
				}if(line.contains("<<") && !line.contains("<<=")){
					opsCount += StringUtils.countMatches(line, "<<");
					bitOps += StringUtils.countMatches(line, "<<");
//					operands = operand.split("<<");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("<<")){
						uniqueOps++;
						str.append("<<");							
					}
				}if(line.contains(">>") && !line.contains(">>=")){
					opsCount += StringUtils.countMatches(line, ">>");
					bitOps += StringUtils.countMatches(line, ">>");
//					operands = operand.split(">>");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains(">>")){
						uniqueOps++;
						str.append(">>");							
					}
				}
				
				//Assignment Operators
				if(line.contains("=") && !line.contains("==")){
					opsCount += StringUtils.countMatches(line, "=");
					assOps += StringUtils.countMatches(line, "=");
//					operands = operand.split("=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("=")){
						uniqueOps++;
						str.append("=");							
					}
				}if(line.contains("+=")){
					opsCount += StringUtils.countMatches(line, "+=");
					assOps += StringUtils.countMatches(line, "+=");
//					operands = operand.split("+=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("+=")){
						uniqueOps++;
						str.append("+=");							
					}
				}if(line.contains("-=")){
					opsCount += StringUtils.countMatches(line, "-=");
					assOps += StringUtils.countMatches(line, "-=");
//					operands = operand.split("-=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("-=")){
						uniqueOps++;
						str.append("-=");							
					}
				}if(line.contains("*=")){
					opsCount += StringUtils.countMatches(line, "*=");
					assOps += StringUtils.countMatches(line, "*=");
//					operands = operand.split("*=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("*=")){
						uniqueOps++;
						str.append("*=");							
					}
				}if(line.contains("/=")){
					opsCount += StringUtils.countMatches(line, "/=");
					assOps += StringUtils.countMatches(line, "/=");
//					operands = operand.split("/=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("/=")){
						uniqueOps++;
						str.append("/=");							
					}
				}if(line.contains("%=")){
					opsCount += StringUtils.countMatches(line, "%=");
					assOps += StringUtils.countMatches(line, "%=");
//					operands = operand.split("%=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("%=")){
						uniqueOps++;
						str.append("%=");							
					}
				}if(line.contains("<<=")){
					opsCount += StringUtils.countMatches(line, "<<=");
					assOps += StringUtils.countMatches(line, "<<=");
//					operands = operand.split("<<=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("<<=")){
						uniqueOps++;
						str.append("<<=");							
					}
				}if(line.contains(">>=")){
					opsCount += StringUtils.countMatches(line, ">>=");
					assOps += StringUtils.countMatches(line, ">>=");
//					operands = operand.split(">>=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains(">>=")){
						uniqueOps++;
						str.append(">>=");							
					}
				}if(line.contains("&=")){
					opsCount += StringUtils.countMatches(line, "&=");
					assOps += StringUtils.countMatches(line, "&=");
//					operands = operand.split("&=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("&=")){
						uniqueOps++;
						str.append("&=");							
					}
				}if(line.contains("^=")){
					opsCount += StringUtils.countMatches(line, "^=");
					assOps += StringUtils.countMatches(line, "^=");
//					operands = operand.split("^=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("^=")){
						uniqueOps++;
						str.append("^=");							
					}
				}if(line.contains("|=")){
					opsCount += StringUtils.countMatches(line, "|=");
					assOps += StringUtils.countMatches(line, "|=");
//					operands = operand.split("|=");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str.toString().contains("|=")){
						uniqueOps++;
						str.append("|=");							
					}
				}
				
				
				//Operands
				if(line.contains("int ")){
					opdCount += StringUtils.countMatches(line, "int ");
//					operands = operand.split("int  ");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str2.toString().contains("int ")){
						uniqueOpd++;
						str2.append("int ");							
					}
				}if(line.contains("string ")){
					opdCount += StringUtils.countMatches(line, "string ");
//					operands = operand.split("string ");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str2.toString().contains("string ")){
						uniqueOpd++;
						str2.append("string ");							
					}
				}if(line.contains("float ")){
					opdCount += StringUtils.countMatches(line, "float ");
//					operands = operand.split("float ");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str2.toString().contains("float ")){
						uniqueOpd++;
						str2.append("float ");							
					}
				}if(line.contains("void ")){
					opdCount += StringUtils.countMatches(line, "void ");
//					operands = operand.split("void ");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str2.toString().contains("void ")){
						uniqueOpd++;
						str2.append("void ");							
					}
				}if(line.contains("double ")){
					opdCount += StringUtils.countMatches(line, "double ");
//					operands = operand.split("double ");
//					for(int x=0; x<operands.length;x++){
//						operand = operand + " " + operands[x];
//					}
					if(!str2.toString().contains("double ")){
						uniqueOpd++;
						str2.append("double ");							
					}
				}if(line.contains("\"")){
					opdCount += StringUtils.countMatches(line, "\"");
					operands = operand.split("\"");
					if(!str2.toString().contains("\"")){
						uniqueOpd++;
						str2.append("\"");							
					}
				}if(line.contains("Exception")){
					opdCount += StringUtils.countMatches(line, "Exception");
					operands = operand.split("Exception");
					if(!str2.toString().contains("Exception")){
						uniqueOpd++;
						str2.append("Exception");							
					}
				}
				
				//String[] words = operand.split(" ");
				
				//opdCount += words.length;
				//uniqueOpd += words.length;
				
				//System.out.println("Operator Count = " + opdCount);
				
			}
			fileReader.close();
			
			aLength = opsCount + opdCount;
			eLength = (uniqueOps*Math.log(uniqueOps)) + (uniqueOpd*Math.log(uniqueOpd));
			pVocab = uniqueOps + uniqueOpd;
			pVolume = aLength * Math.log(pVocab);
			pMinVol = (2 + uniqueOpd) * Math.log(2 + uniqueOpd);
			pLevel = pMinVol / pVolume;
			effort = pVolume / pLevel;
			pTimer = effort / 18.0;
			eLevel = (uniqueOps/ 2.0)*(opdCount/uniqueOpd);
			bugs = (Math.pow(effort, (2/3)))/3000.0;
			
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
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		obj1.put("value", new JSONArray(items));
		//System.out.println(obj1);
		return obj1;
	}
	
}

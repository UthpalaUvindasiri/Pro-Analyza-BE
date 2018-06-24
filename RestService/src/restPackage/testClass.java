package restPackage;

import java.io.File;
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
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class testClass {

	public static void main(String[] args) throws IOException, JSONException {
		String filePath = "D:/SLIIT/4th Year 1st Sem/CDAP/Metrics Logic/Operators.txt";
		
		System.out.println(parseException(filePath));
		
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	public static JSONObject parseException(String filePath) throws IOException {
		
		String str="";
		
		JSONObject obj1 = new JSONObject(); 
		
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
			
			public boolean visit(MethodDeclaration node){
 
				
			    Collection<JSONObject> items = new ArrayList<JSONObject>();
				
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
				
				for(String line : lines){
//					System.out.println(node.getName());
					
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
				    
				    obj1.put("Method Name", node.getName().toString());
					obj1.put("value", new JSONArray(items));
				    //obj1.put("value", "tesing");
				    
				}
				catch(Exception e) {
					
				}

//				System.out.println(obj1);
				return true;
				
			}
			
		});
		
		//JSONObject obj2 = new JSONObject(); 
		
		return obj1;
	}


}

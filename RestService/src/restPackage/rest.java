package restPackage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.sql.*;
import com.google.gson.Gson;


@Path("/rest")
public class rest {

	@Path("/get")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getBook() throws IOException{
		
		String str2 = ast.parseTest();	
		
		Gson gson = new Gson();
		String json = gson.toJson(str2);
		
		System.out.println(str2);
		
		return json;
	}
	
	
	/*@Path("/post1")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBooks(Book book1) {
		List<Book> books = bookCreator.getBook();
		books.add(book1);
		
		return Response.ok(books).build();
	}*/
	@Path("/ast")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response ast(String jsonOject) throws IOException, JSONException {
		JSONObject ops = new JSONObject();
		//ops.put("factors", "metrics");
		JSONArray arrJSON = new JSONArray();
		
		JSONObject obj1 = new JSONObject(jsonOject);

		int pId = obj1.getInt("projectId");

		
		Gson gson = new Gson();
		Hierarchy h =new Hierarchy();
		String returnString = h.ast();
		JSONObject obj122 = new JSONObject();

		obj122.put("ast", returnString.toString());
		//JSONObject obj122 = new JSONObject(returnString);
		return Response.ok(returnString.toString()).build();
	}
	//DELETE & SAVE FILES 
		@Path("/saveFile")
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response saveFile(String jsonOject) throws IOException, JSONException {
			JSONObject ops = new JSONObject();
			//ops.put("factors", "metrics");
			JSONArray arrJSON = new JSONArray();
			
			JSONObject obj1 = new JSONObject(jsonOject);

			int pId = obj1.getInt("projectId");
			String ppId = pId+"";
			try {
				File ff = new File("D:\\4thyearBackEnd\\temp_files\\");
				FileUtils.cleanDirectory(ff);
				SaveFiles sf = new SaveFiles(ppId);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JSONObject opsss = new JSONObject();
			opsss.put("su", true);
				return Response.ok(opsss).build();

		}

//standards
@Path("/standards")
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response standardsFiles(String jsonOject) throws IOException, JSONException {

	JSONArray arrJSON = new JSONArray();
	Standards sd =new Standards();
	
	arrJSON =sd.getStandard("root");
		return Response.ok(arrJSON.toString()).build();

}

	
	@Path("/post2")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response testPost(String jsonOject) throws IOException, JSONException {
		
		JSONObject ops = new JSONObject();
		//ops.put("factors", "metrics");
		JSONArray arrJSON = new JSONArray();
		
		JSONObject obj1 = new JSONObject(jsonOject);

		String mName = obj1.getString("MethodName");
		String fName = obj1.getString("FileName");
//		String jsonPath = "D:/EclipseWorkspace/RestService/src/restPackage/Parser.java";

		
		//String jsonPath = "D:/SLIIT/4th Year 1st Sem/CDAP/Metrics Logic/Task3.txt";
		
		Parser	Parser = new Parser(mName);
		
		Gson gson = new Gson();
		/*
		 * 
		 * Atheek Root
		*/	
		//for all files
		if ( fName.equals("root") && mName.equals("root")){
		
					//Values for a file
					JSONArray jsonarray3 = obj1.getJSONArray("matrix");
					for (int i = 0; i < jsonarray3.length(); i++) {
//						JSONObject jsonObj = jsonarray2.getJSONObject(i);
//					    String jsonFactor = jsonObj.getString("metric");
						String jsonFactor = jsonarray3.getString(i);
							
						if(jsonFactor.equals("Halstead Metrics")){
							JSONObject ops1 = new JSONObject();
							ops1.put("name", "Halstead Metrics");
							ops1.put("value", astCode.parseHalstead_File_Root(mName));
							arrJSON.put(ops1);
						}else if(jsonFactor.equals("Cognitive functional Size")){
							JSONObject ops1 = new JSONObject();
							ops1.put("name", "Cognitive functional Size");
							ops1.put("value", getDetailsRoot.getCFS_root(mName));	//
							arrJSON.put(ops1);
						}else if(jsonFactor.equals("Cognitive Weight Complexity Measure")){				
							JSONObject ops1 = new JSONObject();
							ops1.put("name", "Cognitive Weight Complexity Measure");
							ops1.put("value", Parser.getCWCMJson(mName));
							arrJSON.put(ops1);
						}else if(jsonFactor.equals("Cyclomatic Complexity")){
							JSONObject ops1 = new JSONObject();
							ops1.put("name", "Cyclomatic Complexity");
							ops1.put("value", Parser.getCCJSON(mName));
							arrJSON.put(ops1);
						}
						else if(jsonFactor.equals("Method Hiding Factor")){ //mhf new
//							JSONObject ops1 = new JSONObject();
//							ops1.put("name", "Cyclomatic Complexity");
//							ops1.put("value", Parser.getCCJSON(mName));
//							arrJSON.put(ops1);
							JSONObject ops1 = new JSONObject();
							ops1.put("name", "Method Hiding Factor");
							ops1.put("value", Parser_File.getMHF());
							arrJSON.put(ops1);
						}
						else if(jsonFactor.equals("Glib")){ //glib new
//							JSONObject ops1 = new JSONObject();
//							ops1.put("name", "Cyclomatic Complexity");
//							ops1.put("value", Parser.getCCJSON(mName));
//							arrJSON.put(ops1);
							JSONObject ops1 = new JSONObject();
							ops1.put("name", "Glib");
							ops1.put("value", Parser_File.getLogicalMetrics());
							arrJSON.put(ops1);
							
						}
						else if(jsonFactor.equals("Number of Ancestors")){ //noa new
//							JSONObject ops1 = new JSONObject();
//							ops1.put("name", "Cyclomatic Complexity");
//							ops1.put("value", Parser.getCCJSON(mName));
//							arrJSON.put(ops1);
							
							JSONObject ops1 = new JSONObject();
							ops1 = Parser.getNOA();
							arrJSON.put(ops1);
						}
					}
					
					
					
					JSONArray jsonarray4 = obj1.getJSONArray("factors");
					for (int i = 0; i < jsonarray4.length(); i++) {
					    String jsonFactor = jsonarray4.getString(i);
//						JSONObject jsonObj = jsonarray.getJSONObject(i);
//					    String jsonFactor = jsonObj.getString("factor");
						//System.out.println(jsonFactor);

						
						if(jsonFactor.equals("LOC/CLOC/ELOC/Lines")){
							JSONObject ops2 = new JSONObject();
							ops2.put("name", "LOC/CLOC/ELOC/Lines");
							ops2.put("value", astLOC.parseLOC_root(mName));	
							arrJSON.put(ops2);
						}
						else if(jsonFactor.equals("Operands/Operators")){
							JSONObject ops2 = new JSONObject();
							ops2.put("name", "Operands/Operators");
							ops2.put("value", astCode.parseException_root(mName));	
							arrJSON.put(ops2);
						}		
						else if(jsonFactor.equals("Exception Handling")){
							JSONObject ops2 = new JSONObject();
							ops2.put("name", "Exception Handling");
							ops2.put("value", Parser.getExceptionHandlingJson()); 
							arrJSON.put(ops2);
						}
						else if(jsonFactor.equals("Control Structures")){
							JSONObject ops2 = new JSONObject();
							ops2.put("name", "Control Structures");
							ops2.put("value", Parser.getControlStructJson());	
							arrJSON.put(ops2);
						}
						else if(jsonFactor.equals("Access Flags")){
							JSONObject ops2 = new JSONObject();
							ops2.put("name", "Access Flags");
							ops2.put("value", Parser.getAccessModifiersJSON());
							arrJSON.put(ops2);
						}
						else if(jsonFactor.equals("Nesting Level")){
							JSONObject ops2 = new JSONObject();
							ops2.put("name", "Nesting Level");
							ops2.put("value", nestingLevel.parseNesting(fName,mName));	// to do
							arrJSON.put(ops2);
						}
					}
					
		}
		/*
		 * 
		 * Atheek Root
		*/
		//for single file
		if(fName.equals("root") && !mName.equals("root")){
			//==============================================================================================================
			//=========================================================================================================================
			Parser_File parser = new Parser_File(mName);
					//Values for a file
					JSONArray jsonarray3 = obj1.getJSONArray("matrix");
					for (int i = 0; i < jsonarray3.length(); i++) {
//						JSONObject jsonObj = jsonarray2.getJSONObject(i);
//					    String jsonFactor = jsonObj.getString("metric");
						String jsonFactor = jsonarray3.getString(i);
							
						if(jsonFactor.equals("Halstead Metrics")){
							JSONObject ops1 = new JSONObject();
							ops1.put("name", "Halstead Metrics");
							ops1.put("value", astCode.parseHalstead_File(mName));
							arrJSON.put(ops1);
						}else if(jsonFactor.equals("Cognitive functional Size")){
							JSONObject ops1 = new JSONObject();
							ops1.put("name", "Cognitive functional Size");
							ops1.put("value", Parser_File.getCFSJson(mName));
							arrJSON.put(ops1);
						}else if(jsonFactor.equals("Cognitive Weight Complexity Measure")){				
							JSONObject ops1 = new JSONObject();
							ops1.put("name", "Cognitive Weight Complexity Measure");
							ops1.put("value", Parser_File.getCWCMJson(mName));
							arrJSON.put(ops1);
						}else if(jsonFactor.equals("Cyclomatic Complexity")){
							JSONObject ops1 = new JSONObject();
							ops1.put("name", "Cyclomatic Complexity");
							ops1.put("value", Parser_File.getCCJSON(mName));
							arrJSON.put(ops1);
						}
						else if(jsonFactor.equals("Method Hiding Factor")){ //mhf new
							JSONObject ops1 = new JSONObject();
							ops1.put("name", "Method Hiding Factor");
							ops1.put("value", Parser_File.getMHF());
							arrJSON.put(ops1);
							
							/*JSONObject ops1 = new JSONObject();
							ops1 = Parser_File.getMHF();
							arrJSON.put(ops1);*/
						}
						else if(jsonFactor.equals("Glib")){ //glib new
							JSONObject ops1 = new JSONObject();
							ops1.put("name", "Glib");
							ops1.put("value", Parser_File.getLogicalMetrics());
							arrJSON.put(ops1);
							
							/*JSONObject ops1 = new JSONObject();
							ops1 = Parser_File.getLogicalMetrics();
							arrJSON.put(ops1);*/
						}
					}
					
					
					
					JSONArray jsonarray4 = obj1.getJSONArray("factors");
					for (int i = 0; i < jsonarray4.length(); i++) {
					    String jsonFactor = jsonarray4.getString(i);
//						JSONObject jsonObj = jsonarray.getJSONObject(i);
//					    String jsonFactor = jsonObj.getString("factor");
						//System.out.println(jsonFactor);

						
						if(jsonFactor.equals("LOC/CLOC/ELOC/Lines")){
							JSONObject ops2 = new JSONObject();
							ops2.put("name", "LOC/CLOC/ELOC/Lines");
							ops2.put("value", astLOC.parseLOC_File(mName));
							arrJSON.put(ops2);
						}
						else if(jsonFactor.equals("Operands/Operators")){
							JSONObject ops2 = new JSONObject();
							ops2.put("name", "Operands/Operators");
							ops2.put("value", astCode.parseException_file(mName));
							arrJSON.put(ops2);
						}		
						else if(jsonFactor.equals("Exception Handling")){
							JSONObject ops2 = new JSONObject();
							ops2.put("name", "Exception Handling");
							ops2.put("value", Parser_File.getExceptionHandlingJson()); 
							arrJSON.put(ops2);
						}
						else if(jsonFactor.equals("Control Structures")){
							JSONObject ops2 = new JSONObject();
							ops2.put("name", "Control Structures");
							ops2.put("value", Parser_File.getControlStructJson());	
							arrJSON.put(ops2);
						}
						else if(jsonFactor.equals("Access Flags")){
							//Parser_File p = new Parser_File(mName);
/*							if( fName.equals("root")){
								Parser_File p = new Parser_File(fName);
							}
							else{
								Parser_File p = new Parser_File(mName);
							}*/
							Parser_File p = new Parser_File(mName);
							JSONObject ops2 = new JSONObject();
							ops2.put("name", "Access Flags");
							ops2.put("value", Parser_File.getAccessModifiersJSON());
							arrJSON.put(ops2);
						}
						else if(jsonFactor.equals("Nesting Level")){
							JSONObject ops2 = new JSONObject();
							ops2.put("name", "Nesting Level");
							ops2.put("value", nestingLevel.parseNesting(fName,mName));
							arrJSON.put(ops2);
						}
					}
					}
		else if(!fName.equals("root") && !mName.equals("root")){
		JSONArray jsonarray = obj1.getJSONArray("factors");
		for (int i = 0; i < jsonarray.length(); i++) {
		    String jsonFactor = jsonarray.getString(i);
//			JSONObject jsonObj = jsonarray.getJSONObject(i);
//		    String jsonFactor = jsonObj.getString("factor");
			//System.out.println(jsonFactor);

			
			if(jsonFactor.equals("LOC/CLOC/ELOC/Lines")){
				JSONObject ops2 = new JSONObject();
				ops2.put("name", "LOC/CLOC/ELOC/Lines");
				ops2.put("value", astLOC.parseLOC(fName,mName));
				arrJSON.put(ops2);
			}
			else if(jsonFactor.equals("Operands/Operators")){
				JSONObject ops2 = new JSONObject();
				ops2.put("name", "Operands/Operators");
				ops2.put("value", astCode.parseException(fName,mName));
				arrJSON.put(ops2);
			}		
			else if(jsonFactor.equals("Exception Handling")){
				JSONObject ops2 = new JSONObject();
				ops2.put("name", "Exception Handling");
				ops2.put("value", Parser.ParseException(fName,mName));
				arrJSON.put(ops2);
			}
			else if(jsonFactor.equals("Control Structures")){
				JSONObject ops2 = new JSONObject();
				ops2.put("name", "Control Structures");
				ops2.put("value", Parser.ParseControlStrucures(fName,mName));
				arrJSON.put(ops2);
			}
			else if(jsonFactor.equals("Access Flags")){
				JSONObject ops2 = new JSONObject();
				ops2.put("name", "Access Flags");
				ops2.put("value", Parser.getAccessModifiersJSON());
				arrJSON.put(ops2);
			}
			else if(jsonFactor.equals("Nesting Level")){
				JSONObject ops2 = new JSONObject();
				ops2.put("name", "Nesting Level");
				ops2.put("value", nestingLevel.parseNesting(fName, mName));
				arrJSON.put(ops2);
			}
		}
		
		JSONArray jsonarray2 = obj1.getJSONArray("matrix");
		for (int i = 0; i < jsonarray2.length(); i++) {
//			JSONObject jsonObj = jsonarray2.getJSONObject(i);
//		    String jsonFactor = jsonObj.getString("metric");
			String jsonFactor = jsonarray2.getString(i);
				
			if(jsonFactor.equals("Halstead Metrics")){
				JSONObject ops1 = new JSONObject();
				ops1.put("name", "Halstead Metrics");
				ops1.put("value", astCode.parseHalstead(fName,mName));
				arrJSON.put(ops1);
			}else if(jsonFactor.equals("Cognitive functional Size")){
				JSONObject ops1 = new JSONObject();
				ops1.put("name", "Cognitive functional Size");
				ops1.put("value", Parser.ParseCFS(fName,mName));
				arrJSON.put(ops1);
			}else if(jsonFactor.equals("Cognitive Weight Complexity Measure")){				
				JSONObject ops1 = new JSONObject();
				ops1.put("name", "Cognitive Weight Complexity Measure");
				ops1.put("value", Parser.ParseCWCM(fName,mName));
				arrJSON.put(ops1);
			}else if(jsonFactor.equals("Cyclomatic Complexity")){
				JSONObject ops1 = new JSONObject();
				ops1.put("name", "Cyclomatic Complexity");
				ops1.put("value", Parser.ParseCC(fName,mName));
				arrJSON.put(ops1);
			}
		}
		}
		
		
//=================================================================================
		
		/*String jsonFactor = obj1.getString("factor");
		//String jsonMetric = obj1.getString("fileMetric");
		
		//String str2 = ast.parseTest(jsonPath);
		
		//System.out.println(jsonFactor);
		
		if(jsonFactor.equals("Operators")){
			ops = ast.parseOperators(jsonPath);
		}
		else if(jsonFactor.equals("Halstead")){
			ops = ast.parseHalstead(jsonPath);
		}
		else if(jsonFactor.equals("LOC")){
			ops = locAST.parseLoc(jsonPath);
		}*/
		
		//ops = ast.parseOperators(jsonPath);
		
		ops.put("Array", arrJSON);
		
		String jsonResponse = gson.toJson(ops);
		
		return Response.ok(jsonResponse).build();
		
		//return Response.ok(filePath).build();
	}
	
	@Path("/post3")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response testAST(String jsonOject) throws IOException, JSONException {
		
		//test ta = new test();
		
		//String str2 = ta.parseTest();
		
		JSONObject obj1 = new JSONObject(jsonOject);
		
		Gson gson = new Gson();
		
		File dirs = new File("D:/4thyearBackEnd/temp_files/");
		
		String dirPath = dirs.getCanonicalPath() + File.separator;//+"src"+File.separator;
		 
		File root = new File(dirPath);
		//System.out.println(rootDir.listFiles());
		File[] files = root.listFiles ( );
		String filePath = null;
		 double kloc =0;
		 double kloc2 =0;
		 for (File f : files ) {
			 filePath = f.getAbsolutePath();
			 kloc2 = (locAST.parseKLoc(filePath))/1000.0;
			 kloc = kloc+kloc2;
				
		 }
		
		String pType = obj1.getString("projectType");
		
		double effort = 0.0;		
		double dTime = 0.0;		
		double pReq = 0.0; 
		
		double a = 0.0;
		double b = 0.0;
		double c = 0.0;
		double d = 0.0;
		
		/*double kloc = (locAST.parseKLoc(jsonPath))/1000.0;
		*/
		if(pType.equals("Organic")){
			
			a = 2.4;
			b = 1.05;
			c = 2.5;
			d = 0.38;						
		}
		else if(pType.equals("SemiDetached")){
			a = 3.0;
			b = 1.12;
			c = 2.5;
			d = 0.35;	
		}
		else if(pType.equals("Embedded")){
			a = 3.6;
			b = 1.20;
			c = 2.5;
			d = 0.32;
		}
		
		effort = a * (Math.pow(kloc,b));
		effort = testClass.round(effort, 2);
		
		dTime = c * (Math.pow(effort,d));
		dTime = testClass.round(dTime, 2);
		
		pReq = effort / dTime;		
		pReq = testClass.round(pReq, 2);
		
		if(pReq<1){
			pReq = 1;
		}
		
		JSONObject ops = new JSONObject();
		
		JSONArray arrJSON = new JSONArray();
		
		JSONObject ops1 = new JSONObject();
		ops1.put("property", "Effort Applied (E)");
		ops1.put("value", effort);
		arrJSON.put(ops1);
		
		JSONObject ops2 = new JSONObject();
		ops2.put("property", "Development Time (D)");
		ops2.put("value", dTime);
		arrJSON.put(ops2);
		
		JSONObject ops3 = new JSONObject();
		ops3.put("property", "People required (P)");
		ops3.put("value", pReq);
		arrJSON.put(ops3);
		
		ops.put("Array", arrJSON);
		
		String jsonResponse = gson.toJson(ops);
		
		return Response.ok(jsonResponse).build();
	}
	
}

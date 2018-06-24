package restPackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class locAST {

	public static int parseKLoc(String filePath) throws IOException, JSONException {
		
		JSONObject obj1 = new JSONObject();
		
		JSONObject jo = new JSONObject();
	    Collection<JSONObject> items = new ArrayList<JSONObject>();
		
		int lLoc = 0;
		int totLoc = 0;
		int Loc = 0;
		int commLines = 0;
		int hLoc = 0;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String currentLine;
			while((currentLine = br.readLine())!= null) {
				//System.out.println("Current Line = " + currentLine);
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
						while(!(currentLine = br.readLine()).endsWith("*/")) 
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
			//current Loc will be added with lLoc to get total loc
			hLoc = Loc + lLoc;
			//total lines of code will be number of lines physically available including comments excluding white spaces
			totLoc = hLoc + commLines;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

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
	    
	    obj1.put("value", new JSONArray(items));
		//System.out.println(retObj);
		return totLoc;
		
	}
	
}

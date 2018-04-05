package utils.tma.web;

import static com.jayway.restassured.RestAssured.given;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.json.JSONObject;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpenJira {
	public static final Logger logger = LoggerFactory.getLogger("OpenJira");
	public static void RaiseJira(){
		String workingDir = System.getProperty("user.dir");
		//Read JSON file
		JSONObject obj = new JSONObject();
		String content = null;
		String tmp;
		String JiraContent = "";
                String JiraUrl = System.getProperty("jiraUrl");
                String JiraUser = System.getProperty("jiraUser");
                String JiraPassword = System.getProperty("jiraPassword");
		String pathFile = workingDir + File.separator + "data" + File.separator + "jira.txt";
		String JiraFile = workingDir + File.separator + "data" + File.separator + "JiraBody.txt";
		//Path path = Paths.get(JiraFile);
		try {
			BufferedReader br = new BufferedReader(new FileReader(pathFile));
			content = br.readLine(); 
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			BufferedReader br = new BufferedReader(new FileReader(JiraFile));
			while ((tmp=br.readLine())!=null) {
				JiraContent = JiraContent + tmp;
			};
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Jira Body: " + JiraContent );
		try {
			JSONObject obj_tmp = new JSONObject(content);
			obj = obj_tmp;
		} catch (Exception e) {
			obj = obj;
		}
		
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.addHeader("Content-Type", "application/json; charset=UTF-8");
		RequestSpecification responseSpec = builder.build();
		logger.info("Start login Jira!" + "{'username': '" + JiraUser + "','password': '"  + JiraPassword + "'}");
		Response resp = null;
		try {   
	    		resp = 	given().
					spec(responseSpec).		
					body("{'username': '" + JiraUser + "','password': '"  + JiraPassword + "'}").
				when().
					post(JiraUrl + "/rest/auth/1/session");
		} catch (Exception e) {
			logger.info("Unable to login Jira!" + e);
		}
		
		for (int i = 0; i < obj.length(); i = 3) {
			logger.info("START RAISE JIRA");
	    	//create Jira body
			String LogForJira = "";
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(workingDir + File.separator + "logs" + File.separator + obj.getString(String.valueOf(i + 1)) + ".log"));
				while ((tmp = br.readLine())!=null){
					LogForJira = LogForJira + "AAAAA" + tmp;
					}
                                logger.info("HHHHHHHHHHHHHHHHHHHHHHHH" + LogForJira);
				LogForJira = LogForJira.replaceAll("\"", "\'");
				LogForJira = LogForJira.replaceAll("\\\\", "\\\\\\\\");
				LogForJira = LogForJira.replaceAll("AAAAA", "\\\\n");
			} catch (Exception e) {
				e.printStackTrace();
			} 

			String JiraContent_tmp = JiraContent.replace("$cause$", obj.getString(String.valueOf(i)));
			JiraContent_tmp = JiraContent_tmp.replace("$JiraName$", obj.getString(String.valueOf(i + 2)));
			JiraContent_tmp = JiraContent_tmp.replaceAll("\n", "AAAAA").replaceAll("\\\\", "\\\\\\\\").replaceAll("AAAAA","\\\\n").replace("\\\\n","\\n").replace("$log_name$", LogForJira);
			System.out.println("Jira body is: " + JiraContent_tmp + LogForJira);


	    	//Raise Jira
	    	logger.info("Start raise Jira!");
	    	JSONObject jsonResponse = new JSONObject(resp.asString());
                logger.info("HUAN" + jsonResponse + " --------- " + resp);
		RequestSpecBuilder builder2 = new RequestSpecBuilder();
		builder2.addHeader("Content-Type", "application/json; charset=UTF-8");
		builder2.addHeader("cookie", "JSESSIONID=" + jsonResponse.getJSONObject("session").getString("value"));
		RequestSpecification responseSpec2 = builder2.build();
			
	    	Response resp2 = 	given().
					spec(responseSpec2).		
					body(JiraContent_tmp).
				when().
					post(JiraUrl + "/rest/api/2/issue");
	    	
	    	//Print
			String respInString = resp2.asString();
			logger.info("RETURN: " + respInString);
			//delete tmp log
			try {
				File f = new File(workingDir + File.separator + "logs" + File.separator + obj.getString(String.valueOf(i + 1)) + ".log");
				f.delete();
				logger.info("Delete log for Jira");
			} catch (Exception e) {
				logger.info("Unable to delete file");
			}
		}
		try {
			File f = new File(workingDir + File.separator + "data" + File.separator + "jira.txt");
			f.delete();
			logger.info("Delete file");
		} catch (Exception e) {
			logger.info("Unable to delete file");
		}
	}
}

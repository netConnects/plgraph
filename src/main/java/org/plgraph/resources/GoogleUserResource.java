package org.plgraph.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.plgraph.entity.GoogleUser;
import org.plgraph.utility.CommonUtilites;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/oauth2callback")
@Produces(MediaType.APPLICATION_JSON)
public class GoogleUserResource {
	
	@GET
	public Response getGoogleCode(@QueryParam("code") String code) {
		System.out.println(code);
		GoogleUser data = null;
		try{
			String urlParameters = CommonUtilites.createGoogleAuthorization(code);
			
			URL url = new URL(CommonUtilites.GOOGLE_TOKEN_URL);
	        URLConnection urlConn = url.openConnection();
	        urlConn.setDoOutput(true);
	        OutputStreamWriter writer = new OutputStreamWriter(urlConn.getOutputStream());
	        writer.write(urlParameters);
	        writer.flush();
	        
	        String line, outputString = "";
	        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
	        while ((line = reader.readLine()) != null) {
	            outputString += line;
	        }
	        
	        JsonObject json = (JsonObject )new JsonParser().parse(outputString);
			String access_token = json.get("access_token").getAsString();
	        System.out.println(access_token);

	        url = new URL(CommonUtilites.GOOGLE_USER_INFO_URL + "?access_token=" + access_token);
	        urlConn = url.openConnection();
	        outputString = "";
	        reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
	        while ((line = reader.readLine()) != null) {
	            outputString += line;
	        }
	        
	        System.out.println(outputString);
	        
	        data = new Gson().fromJson(outputString, GoogleUser.class);
	       
	        System.out.println(data);
	        writer.close();
	        reader.close();
		}catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
		
		
		return Response.status(Status.OK).entity(data).build();
	}
}

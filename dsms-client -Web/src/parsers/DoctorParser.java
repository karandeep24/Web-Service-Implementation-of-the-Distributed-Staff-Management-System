package parsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import user_metadata.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.json.*;
import java.net.URL;
import java.net.URLConnection;




public class DoctorParser  extends ParserUtility {

	
	
	
	
	
	
	
	public  ArrayList<GetDoctor_MetaData> getParsedListfromGetDoctor(String url) {

		ArrayList<GetDoctor_MetaData> getDoctorList = new ArrayList<GetDoctor_MetaData>();
		System.out.println("URL:::::::::::::::::::::" + url);
		
		JSONArray jsonArray = getJsonArrayFromURL(url);

		System.out.println("Json Array Size::::::::::::::" + jsonArray.length());
		if (jsonArray != null && jsonArray.length() > 0) {
			for (int i = 0; i < jsonArray.length(); i++) {
				try {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					GetDoctor_MetaData  doctor= new GetDoctor_MetaData();
					
					String firstName = jsonObject.getString("firstName");
					doctor.setFirstName(firstName);

					String lastName = jsonObject.getString("lastName");
					doctor.setLastName(lastName);
					
					String address = jsonObject.getString("address");
					doctor.setAddress(address);
					
					String location = jsonObject.getString("location");
					doctor.setLocation(location);
					
					String phone = jsonObject.getString("phone");	
					doctor.setPhone(phone);

					String specialization = jsonObject.getString("specialization");			
					doctor.setSpecialization(specialization);
					getDoctorList.add(doctor);

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
		return getDoctorList;
	}

	
	
	
	
	
}

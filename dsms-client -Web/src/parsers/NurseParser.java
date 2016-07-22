package parsers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import user_metadata.GetDoctor_MetaData;
import user_metadata.GetNurse_MetaData;

public class NurseParser extends ParserUtility {

	
	
	
	
	

	public  ArrayList<GetNurse_MetaData> getParsedListfromGetNurse(String url) {

		ArrayList<GetNurse_MetaData> getDoctorList = new ArrayList<GetNurse_MetaData>();
		System.out.println("URL:::::::::::::::::::::" + url);
		
		JSONArray jsonArray = getJsonArrayFromURL(url);

		System.out.println("Json Array Size::::::::::::::" + jsonArray.length());
		if (jsonArray != null && jsonArray.length() > 0) {
			for (int i = 0; i < jsonArray.length(); i++) {
				try {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					GetNurse_MetaData nurse= new GetNurse_MetaData();
					
					String firstName = jsonObject.getString("firstName");
					nurse.setFirstName(firstName);

					String lastName = jsonObject.getString("lastName");
					nurse.setLastName(lastName);
					
					String designation = jsonObject.getString("designation");
					nurse.setDesignation(designation);
					
					String status = jsonObject.getString("status");
					nurse.setStatus(status);
					
					String statusDate = jsonObject.getString("statusDate");
					nurse.setStatusDate(statusDate);
					
					getDoctorList.add(nurse);

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
		return getDoctorList;
	}
}

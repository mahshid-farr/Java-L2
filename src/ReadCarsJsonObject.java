

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Mahshid Farrahinia
 * 
 * This class read a json file called cars.json from matrix account
 *
 */
public class ReadCarsJsonObject {

	public static void main(String[] args) {


		String jsonString = readURL("https://matrix.senecacollege.ca/~mfarrahinia/cars.json");
		
		System.out.println("\n\njsonString: " + jsonString);

		try {
			JSONArray jsonArray = new JSONArray(jsonString);
			System.out.println("\n\njsonArray: " + jsonArray + "\n");
			
			// Iterate through JSONArray and display each JSONObjects
			int count = jsonArray.length(); // get totalCount of all jsonObjects
			for(int i=0 ; i< count; i++){   // iterate through jsonArray 
				JSONObject jsonObject = jsonArray.getJSONObject(i);  // get jsonObject @ i position 
				System.out.println("JSON Object : Sporty Car");
				System.out.println("================================");
				System.out.println(" Year: " + jsonObject.getString("Year"));
				System.out.println(" Make: " + jsonObject.getString("Make"));
				System.out.println(" Model: " + jsonObject.getString("Model"));
				System.out.println(" Price: " + jsonObject.getString("Price"));

				System.out.println();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static String readURL(String myURL) {
		System.out.println("Requested URL:" + myURL);
		StringBuilder stringBuilder = new StringBuilder();
		URLConnection urlConnection = null;
		InputStreamReader inputStream = null;
		try {
			URL url = new URL(myURL);
			urlConnection = url.openConnection();
			if (urlConnection != null)
				urlConnection.setReadTimeout(60 * 1000);
			if (urlConnection != null && urlConnection.getInputStream() != null) {
				inputStream = new InputStreamReader(urlConnection.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(inputStream);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						stringBuilder.append((char) cp);
					}
					bufferedReader.close();
				}
			}
			inputStream.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:" + myURL,
					e);
		}

		return stringBuilder.toString();
	}

}
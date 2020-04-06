import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SecretConverter {
	public static String GetSecret(String key) {
		JSONParser jsonParser = new JSONParser();
	      try {
	         JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("C:\\Users\\Eraza\\source\\repos\\java-email\\src\\secret.json"));
	         
	         return (String) jsonObject.get(key);
	      
	      
	      } catch (FileNotFoundException e) {
	            e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (ParseException e) {
	         e.printStackTrace();
	      }
	      return "";
	}
}

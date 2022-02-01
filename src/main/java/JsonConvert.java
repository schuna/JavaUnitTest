import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class JsonConvert {

    public static Video DeserializeObject(String readData) {
        try {
            JSONObject jsonObject = jsonObjectFromFileReader(readData);
            return videoFromJsonObject(jsonObject);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JSONObject jsonObjectFromFileReader(String readData) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(readData);
        return (JSONObject) obj;
    }

    private static Video videoFromJsonObject(JSONObject jsonObject) {
        return new Video(
                Integer.parseInt(String.valueOf(jsonObject.get("id"))),
                String.valueOf(jsonObject.get("title")),
                (boolean) jsonObject.get("isProcessed"));
    }
}

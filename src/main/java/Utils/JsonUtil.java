package Utils;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;

public class JsonUtil {
    public static String getJsonValue(String key) throws IOException, ParseException {
        FileInputStream fis = new FileInputStream("C:\\Users\\Nirojan Yogarajah\\IdeaProjects\\seleniumAssess\\src\\main\\java\\TestData\\testData.json");
        JSONTokener jsonTokener = new JSONTokener(fis);
        JSONObject jsonObject = new JSONObject(jsonTokener);
        return jsonObject.getString(key);
    }

}

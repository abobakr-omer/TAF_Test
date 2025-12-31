package com.automationexercises.utils.dataReader;

import com.automationexercises.utils.logs.LogsManager;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonReader {

    /* Dynamic - Static
    1.static (best el best)
      1.1. Database snapshot (restore)

    2.Dynamic
        2.1 Database queries (setup) //insert into users values ('toBeModified', 'toBeModified@gmail.com', '123456')
        2.2 API endpoint (setup) //POST /users { "email": "toBeModified@gmail.com", "password": "123456" }  //network request
        2.3 UI (setup) //CRUD
     */

    private final String TEST_DATA_PATH = "src/test/resources/test-data/";
    // excel - csv - json -properties
    String jsonReader;
    String jsonFileName;

    public JsonReader(String jsonFileName) {
        this.jsonFileName = jsonFileName;
        try {
            JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(TEST_DATA_PATH + jsonFileName + ".json"));
            jsonReader = data.toJSONString();
        } catch (Exception e) {
            LogsManager.error("Error reading json file:", jsonFileName, e.getMessage());
            jsonReader = "{}"; // Initialize to an empty JSON object to avoid null pointer exceptions
        }
    }

    //valid.username
    public String getJsonData(String jsonPath) {
        try {
            return JsonPath.read(jsonReader, jsonPath);
        } catch (Exception e) {
            LogsManager.error("Error reading json file for path:", jsonPath, e.getMessage());
            return "";
        }
    }
}

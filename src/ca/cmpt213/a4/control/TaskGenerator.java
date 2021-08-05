package ca.cmpt213.a4.control;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * TaskGenerator is responsible for providing the UI with
 * generated task information from boredapi. The class supports retrieving JSON string
 * from the api using CURL command and processing the JSON into proper string format.
 */
public class TaskGenerator {
    private final static String COMMAND = "curl -X GET https://www.boredapi.com/api/activity";

    public static String generateTaskInfo() {
        String toParse = request();
        JsonObject object = new Gson().fromJson(toParse, JsonObject.class);
        return object.get("activity").getAsString()
                + "-" + "type: " + object.get("type").getAsString() + ", "
                + "participants: " + object.get("participants").getAsString() + ", "
                + "price: " + object.get("price").getAsString();
    }

    private static String request() {
        String result = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(COMMAND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream stream = process.getInputStream();
        try {
            result = new String(stream.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

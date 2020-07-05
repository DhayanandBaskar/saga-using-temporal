package subscriber.event;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class SubscriptionEventMock {
    public String fetch() {
        JsonElement json;
        try {
            json = JsonParser.parseReader( new InputStreamReader(new FileInputStream("/Users/user/IdeaProjects/OceaInsightsSubscriptionChangeWorkflow/src/main/java/subscriber/event/SubscriptionEvent.json"), StandardCharsets.UTF_8));
            return json.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return "Empty message";
    }
}

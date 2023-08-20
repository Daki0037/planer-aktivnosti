package me.danilo.planeraktivnosti.utils;

import me.danilo.planeraktivnosti.models.Activity;
import me.danilo.planeraktivnosti.models.User;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

public class ActivityService {

    private User user = User.getInstance();

    public void addActivity(Activity activity) throws IOException {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            String url = "http://localhost:8080/api/activity/add";

            HttpPost httpPost = new HttpPost(url);

            httpPost.setHeader("Content-Type", "application/json");
            JSONObject jsonInput = new JSONObject();
            addToJson(activity, jsonInput);
            StringEntity entity = new StringEntity(jsonInput.toString());
            httpPost.setEntity(entity);

            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();

            String responseContent = EntityUtils.toString(responseEntity, "UTF-8");

            httpClient.close();
    }

    public void addToJson(Activity activity, JSONObject jsonInput) {
        jsonInput.put("name", activity.getName());
        jsonInput.put("description", activity.getDescription());
        jsonInput.put("priority", activity.getPriority());
        jsonInput.put("completed", activity.isCompleted());
        jsonInput.put("startDate", activity.getStartDate());
        jsonInput.put("endDate", activity.getEndDate());
        jsonInput.put("userId", activity.getUserId());
    }

    public void fetchActivityData() {

    }

}

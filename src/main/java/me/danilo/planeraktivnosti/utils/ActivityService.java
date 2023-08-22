package me.danilo.planeraktivnosti.utils;

import me.danilo.planeraktivnosti.controllers.ActivityList;
import me.danilo.planeraktivnosti.models.Activity;
import me.danilo.planeraktivnosti.models.User;
import me.danilo.planeraktivnosti.models.builders.ActivityBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ActivityService extends ActivityList {

    private User user = User.getInstance();
    private ActivityBuilder builder = new ActivityBuilder();

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
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String url = "http://localhost:8080/api/activity/user/" + user.getId();

        HttpGet httpGet = new HttpGet(url);

        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            String responseBody = EntityUtils.toString(response.getEntity());

            int statusCode = response.getStatusLine().getStatusCode();

            if(statusCode != 200)
                return;

            JSONArray jsonArray = new JSONArray(responseBody);
            createActivities(jsonArray);

            response.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void createActivities(JSONArray jsonArray) {
        this.clearActivityList();

        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            int id = obj.getInt("id");
            String name = obj.getString("name");
            String description = obj.getString("description");
            int priority = obj.getInt("priority");
            boolean completed = obj.getBoolean("completed");
            String startDate = obj.getString("startDate");
            String endDate = obj.getString("endDate");

            Activity activity = builder
                    .setId(id)
                    .setName(name)
                    .setDescription(description)
                    .setPriority(priority)
                    .setCompleted(completed)
                    .setStartDate(startDate)
                    .setEndDate(endDate)
                    .build();

            this.addActivityToList(activity);
        }
    }

    public void saveActivity(Activity activity) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = "http://localhost:8080/api/update";

        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("Content-Type", "application/json");
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("id", activity.getId());
        addToJson(activity, jsonInput);
        StringEntity entity = new StringEntity(jsonInput.toString());
        httpPost.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity responseEntity = response.getEntity();

        String responseContent = EntityUtils.toString(responseEntity, "UTF-8");

        httpClient.close();
    }

}

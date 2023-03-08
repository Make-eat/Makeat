package pangyo.makeat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

@RestController
public class NutrientController {

    @GetMapping("/nutrient")
    public static void getApi(String[] args) throws IOException, ParseException {
        StringBuilder urlBuilder = new StringBuilder("http://127.0.0.1:5000"); /*URL*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());

        //json parsing
        String info = sb.toString();
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(info);
        JSONArray foodlist = (JSONArray)jsonObject.get("foodlist");

        for(int i = 0; i < foodlist.size(); i++)
        {
            String food_name = "";
            String once = "";
            String tan = "";
            String dan = "";
            String zi = "";
            String na = "";
            String cal = "";

            JSONObject food_info = (JSONObject)foodlist.get(i);
            food_name += food_info.get("name") + " ";
            once += food_info.get("quan") + " ";
            tan += food_info.get("carb") + " ";
            dan += food_info.get("protein") + " ";
            zi += food_info.get("fat") + " ";
            na += food_info.get("na") + " ";
            cal += food_info.get("kcal") + " ";

            System.out.println("식품 이름: " + food_name);
            System.out.println("1회 제공량: " + once);
            System.out.println("탄수화물: " + tan);
            System.out.println("단백질: " + dan);
            System.out.println("지방: " + zi);
            System.out.println("나트륨: " + na);
            System.out.println("칼로리 : " + cal);
            System.out.println("");
        }
    }
}
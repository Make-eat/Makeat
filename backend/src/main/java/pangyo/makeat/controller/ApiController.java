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
import java.util.HashMap;

@RestController
public class ApiController {

    @GetMapping("/api")
    public static void getApi(String[] args) throws IOException, ParseException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/FoodNtrIrdntInfoService1/getFoodNtrItdntList1"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=zGlMo2YhDISGj2h%2B0JLPZyx2KZubBibtKxCdeMSXdQVbWSsv%2BXHmPbZs0uZOUulYXNn4V20sDH4V3RKoxxezwg%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("desc_kor","UTF-8") + "=" + URLEncoder.encode("닭가슴살", "UTF-8")); /*식품이름*/
//      urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
//      urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("3", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답데이터 형식(xml/json) Default: xml*/
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
        JSONObject body = (JSONObject)jsonObject.get("body");

        JSONArray items = (JSONArray)body.get("items");

        for(int i = 0; i < items.size(); i++)
        {
            String items_name = "";
            String once = "";
            String tan = "";
            String dan = "";
            String zi = "";
            String na = "";
            String cal = "";

            JSONObject items_info = (JSONObject)items.get(i);
            items_name += items_info.get("DESC_KOR") + " ";
            once += items_info.get("SERVING_WT") + " ";
            tan += items_info.get("NUTR_CONT2") + " ";
            dan += items_info.get("NUTR_CONT3") + " ";
            zi += items_info.get("NUTR_CONT4") + " ";
            na += items_info.get("NUTR_CONT6") + " ";
            cal += items_info.get("NUTR_CONT1") + " ";

            System.out.println("식품 이름: " + items_name);
            System.out.println("1회 제공량: " + once);
            System.out.println("탄수화물: " + tan);
            System.out.println("단백질: " + dan);
            System.out.println("지방: " + zi);
            System.out.println("나트륨: " + na);
            System.out.println("칼로리: " + cal);
            System.out.println("");
        }
    }
}
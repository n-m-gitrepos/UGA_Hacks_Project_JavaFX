package com.example.uga_hacks_project;
import java.net.URI;
import java.net.URLEncoder; 
import java.net.http.HttpClient; 
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.io.IOException; 
import java.lang.InterruptedException;

public class Api {
    static String url;
    static int dimentions;

    static String baseURL = "http://api.qrserver.com/v1/create-qr-code/";

    private static HttpClient HTTP_CLIENT = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .followRedirects(HttpClient.Redirect.NORMAL)
        .build();

    
    public static String apiCall(String input) {
        String stringQrResponse = "";
        try {
            String data = URLEncoder.encode(input, StandardCharsets.UTF_8);
            String query = String.format("?data=%s", data);

            String qrURI = baseURL + query;

            HttpRequest qrRequest = HttpRequest.newBuilder()
                .uri(URI.create(qrURI))
                .build();
            
            HttpResponse<String> qrResponse = HTTP_CLIENT
                .send(qrRequest, BodyHandlers.ofString());
     
            if (qrResponse.statusCode() != 200) {
                throw new IOException(qrResponse.toString());  
            } 

           
            stringQrResponse = qrResponse.body();
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace(); 
        }

        return stringQrResponse;
    }
} // Api

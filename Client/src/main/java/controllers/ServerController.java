package controllers;

import models.Id;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
//import spiffyUrlManipulator

public class ServerController {
    private String rootURL = "http://zipcode.rocks:8085";

  //  private ServerController svr = new ServerController();


  //  private ServerController() {}
    public ServerController(){}

//    public static shared() {
//        return svr;
//    }

    //public JsonString idGet() {
    public String sendReq(String s) throws IOException, InterruptedException{
        // url -> /ids/
        // send the server a get with url
        // return json from server

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(rootURL + s))
                .header("accept", "application/json")
                .build();


        // use the client to send the request
        HttpResponse<String> response = client.send(request,  HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public String sendPostIdsReq(String s1) throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(rootURL + "/ids"))
                .header("accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(s1))
                .build();


        // use the client to send the request
        HttpResponse<String> response = client.send(request,  HttpResponse.BodyHandlers.ofString());

        System.out.println("from Send post req" + response.body());

        return response.body();
    }

    public String sendPutIdsReq(String s1) throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(rootURL + "/ids"))
                .header("accept", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(s1))
                .build();


        // use the client to send the request
        HttpResponse<String> response = client.send(request,  HttpResponse.BodyHandlers.ofString());

        System.out.println("from Send post req" + response.body());

        return response.body();
    }

    //   public JsonString idPost(Id) {
    public String idPost(Id id){

        // url -> /ids/
        // create json from Id
        // request
        // reply
        // return json
        return null;
    }
    //public JsonString idPut(Id) {
    public String idPut(Id id){
        // url -> /ids/
        return null;
    }


}

// ServerController.shared.doGet()
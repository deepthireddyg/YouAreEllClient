package controllers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import models.Id;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Message;


public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private MessageController msgCtrl;
    private IdController idCtrl;


    public TransactionController(MessageController m, IdController j) {

        this.msgCtrl=m;
        this.idCtrl=j;
    }



    public List<Id> getIds() {


        return null;

    }
    public String postId(String idtoRegister, String githubName) {
        Id tid = new Id(idtoRegister, githubName);
        tid = idCtrl.postId(tid);
        return ("Id registered.");
    }


  //  public String makecall (String s, String get, String s1) throws IOException, InterruptedException {

    public ArrayList<Id> makecall (String s, String get, String s1) throws IOException, InterruptedException {

        ServerController sc = new ServerController();
        String reqResponse = sc.sendReq(s);

        //usage of Jackson package
        /*Jackson is a Java Json library that has a built-in ObjectMapper class. The ObjectMapper class is responsible
         for parsing the JSON files and deserializing them into Java objects.*/

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(reqResponse);

 //       System.out.println("my object" + actualObj.get(0).get("name") + ", " + actualObj.get(0).get("github"));

  //     Id id = new Id(actualObj.get(1).get("name").toString(), actualObj.get(1).get("github").toString());

   //     System.out.println("from id class" + id.getName() + ", " + id.getGithub());

        ArrayList<Id> id1 = new ArrayList<>();


        for(int i = 0; i < actualObj.size(); i++){

           id1.add(i, new Id(actualObj.get(i).get("name").toString(),actualObj.get(i).get("github").toString()));
       }

    //    return response.body();
        return id1;
    }
    public ArrayList<Id> makePostIdsCall(String s) throws IOException, InterruptedException{
        ServerController sc1 = new ServerController();
        String reqResponse = sc1.sendPostIdsReq(s);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(reqResponse);
        ArrayList<Id> id1 = new ArrayList<>();

        System.out.println("from Tran Controller - " + actualObj.size());
        System.out.println("from tran controller - " + actualObj);

       // System.out.println("from tran controller - " + actualObj.size() + "," + actualObj.get(0).get("name").toString());


        //for(int i = 0; i < actualObj.size(); i++){

            id1.add(0, new Id(actualObj.get("name").toString(),actualObj.get("github").toString()));
       // }

        return id1;
    }

    public ArrayList<Id> makePutIdsCall(String s) throws IOException, InterruptedException{
        ServerController sc1 = new ServerController();
        String reqResponse = sc1.sendPutIdsReq(s);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(reqResponse);
        ArrayList<Id> id1 = new ArrayList<>();

        System.out.println("from Tran Controller - " + actualObj.size());
        System.out.println("from tran controller - " + actualObj);

        // System.out.println("from tran controller - " + actualObj.size() + "," + actualObj.get(0).get("name").toString());


        //for(int i = 0; i < actualObj.size(); i++){

        id1.add(0, new Id(actualObj.get("name").toString(),actualObj.get("github").toString()));
        // }

        return id1;
    }

    public ArrayList<Message> makeMessageGetCall(String s) throws IOException, InterruptedException{
        ServerController sc1 = new ServerController();
        String reqResponse = sc1.sendReq(s);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(reqResponse);
        ArrayList<Message> msg1 = new ArrayList<>();

      //  System.out.println(actualObj.size());
      //  System.out.println(actualObj.get(0));
      //  System.out.println(actualObj.getNodeType().getClass().getSimpleName());


        for(int i = 0; i < actualObj.size(); i++){

            msg1.add(i, new Message(actualObj.get(i).get("message").toString(),actualObj.get(i).get("fromid").toString(),actualObj.get(i).get("toid").toString()));
        }

        return msg1;
    }
    //when sequence is added
    public ArrayList<Message> makeMessageGetCall1(String s) throws IOException, InterruptedException{
        ServerController sc1 = new ServerController();
        String reqResponse = sc1.sendReq(s);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(reqResponse);
        ArrayList<Message> msg1 = new ArrayList<>();

        //  System.out.println(actualObj.size());
        //  System.out.println(actualObj.get(0));
        //  System.out.println(actualObj.getNodeType().getClass().getSimpleName());

            msg1.add(0, new Message(actualObj.get("message").toString(),actualObj.get("fromid").toString(),actualObj.get("toid").toString(),actualObj.get("sequence").toString()));
        return msg1;
    }

    public ArrayList<Message> postMessageToWorld(String s,String postmsg) throws IOException, InterruptedException{
        ServerController sc1 = new ServerController();
        String reqResponse = sc1.sendPostMsg(s,postmsg);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(reqResponse);
        ArrayList<Message> msg1 = new ArrayList<>();

        //  System.out.println(actualObj.size());
        //  System.out.println(actualObj.get(0));
        //  System.out.println(actualObj.getNodeType().getClass().getSimpleName());




        msg1.add(0, new Message(actualObj.get("message").toString(),actualObj.get("fromid").toString(),actualObj.get("toid").toString(),actualObj.get("sequence").toString()));


        return msg1;
    }

    public ArrayList<Message> postMessageFromAndTo(String s,String postmsg) throws IOException, InterruptedException{
        ServerController sc1 = new ServerController();
        String reqResponse = sc1.sendPostMsg(s,postmsg);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(reqResponse);
        ArrayList<Message> msg1 = new ArrayList<>();

          System.out.println(actualObj.size());
         System.out.println(actualObj);
        //  System.out.println(actualObj.getNodeType().getClass().getSimpleName());




        msg1.add(0, new Message(actualObj.get("message").toString(),actualObj.get("fromid").toString(),actualObj.get("toid").toString(),actualObj.get("sequence").toString()));


        return msg1;
    }
// Exampe of HTTP URL Connection;

//    public  String makecall(String s,String get, String s1) throws IOException{
//
//          HttpURLConnection con;
//
//          StringBuilder content = new StringBuilder();;
//          try {
//
//            var myurl = new URL(rootURL + s);
//            con = (HttpURLConnection) myurl.openConnection();
//
//            con.setRequestMethod("GET");
//
//
//
//            try (BufferedReader in = new BufferedReader(
//                    new InputStreamReader(con.getInputStream()))) {
//
//                String line;
//
//
//                while ((line = in.readLine()) != null) {
//
//                    content.append(line);
//                    content.append(System.lineSeparator());
//                }
//            }
//
//          //  System.out.println(content.toString());
//
//        } catch (IOException e){
//            e.printStackTrace();
//
//        }
//        return content.toString();
//    }
}

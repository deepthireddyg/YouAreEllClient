package youareell;

import controllers.*;
import models.Id;
import models.Message;

import java.io.IOException;
import java.util.ArrayList;

public class YouAreEll {

    TransactionController tt;
    MessageController messageController;
    IdController idController;

    public YouAreEll (TransactionController t) {
        this.tt = t;
    }

    public YouAreEll(MessageController messageController, IdController idController) {
        this.messageController= messageController;
        this.idController=idController;
    }

    public static void main(String[] args) {
        // hmm: is this Dependency Injection?
        YouAreEll urlhandler = new YouAreEll(
            new TransactionController(
                new MessageController(), new IdController()
        ));
    //    System.out.println(urlhandler.MakeURLCall("/ids", "GET", ""));
     //   System.out.println(urlhandler.MakeURLCall("/messages", "GET", ""));
    }

    public ArrayList<Id> get_ids() throws IOException, InterruptedException {
        return tt.makecall("/ids", "GET", "");
    }
    public ArrayList<Id> post_ids(String nameid ,String githubid) throws IOException, InterruptedException {
        String str = String.format("%s\n%s%s%s%s\n%s%s%s%s%s%s\n%s%s%s%ss%s\n%s","{", "\"userid\"",": ","\"-\"",","
                ,"\"name\"",": ","\"",nameid,"\"",",","\"github\"",": ","\"",githubid,"\"","}");

        return tt.makePostIdsCall(str);
    }
    public ArrayList<Id> put_ids(String userid,String nameid ,String githubid) throws IOException, InterruptedException {
        String str1 = String.format("%s\n%s%s%s%s%s%s\n%s%s%s%s%s%s\n%s%s%s%s%s\n%s","{", "\"userid\"",": ","\"",userid,"\"",","
                ,"\"name\"",": ","\"",nameid,"\"",",","\"github\"",": ","\"",githubid,"\"","}") ;

        return tt.makePutIdsCall(str1);
    }


     public ArrayList<Message> get_messages() throws IOException, InterruptedException {
      //  return MakeURLCall("/messages", "GET", "");
         return tt.makeMessageGetCall("/messages");
    }
    public ArrayList<Message> get_messagesAndGitHubId(String s2) throws IOException, InterruptedException {

        return tt.makeMessageGetCall("/ids/"+ s2 + "/messages");
    }
    public ArrayList<Message> get_messagesAndGitHubIdAndSequence(String s2,String s3) throws IOException, InterruptedException {

        return tt.makeMessageGetCall1("/ids/"+ s2 + "/messages/" + s3);
    }

    public ArrayList<Message> get_messagesFromGitHubIdAndToGitHubId(String s2,String s3) throws IOException, InterruptedException {

        return tt.makeMessageGetCall("/ids/"+ s2 + "/from/" + s3);
    }
    public ArrayList<Message> post_messagesAndYourGitHubId(String s2,String s3) throws IOException, InterruptedException {
        String str2 = String.format("%s\n%s\n%s\n%s%s%s\n%s\n%s%s%s\n%s","{", "\"sequence\": \"-\",","\"timestamp\": \"2022-12-03T23:48:40.590470741Z\",","\"fromid\": \"",s2,"\",","\"toid\": \"\","
                ,"\"message\": \"",s3,"\"","}") ;


        return tt.postMessageToWorld("/ids/"+ s2 + "/messages",str2);
    }
    public ArrayList<Message> post_messagesAndFromGitHubIdToGitHubId(String s2fromid,String s3message,String s4toid) throws IOException, InterruptedException {
        String str3 = String.format("%s\n%s\n%s\n%s%s%s\n%s%s%s\n%s%s%s\n%s","{", "\"sequence\": \"-\",","\"timestamp\": \"2022-12-03T23:48:40.590470741Z\",","\"fromid\": \"",s2fromid,"\",","\"toid\": \"",s4toid,"\","
                ,"\"message\": \"",s3message,"\"","}") ;

        return tt.postMessageFromAndTo("/ids/"+ s2fromid+ "/messages",str3);
    }


}

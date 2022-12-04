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


}

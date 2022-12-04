package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import controllers.IdController;
import controllers.MessageController;
import controllers.TransactionController;

import models.Id;
import models.Message;
import youareell.YouAreEll;

// Simple Shell is a Console view for youareell.YouAreEll.
public class SimpleShell {


    public static void prettyPrint(String output) {
        // yep, make an effort to format things nicely, eh?
        System.out.println(output);
    }
    public static void main(String[] args) throws java.io.IOException {

       // YouAreEll urll = new YouAreEll(new MessageController(), new IdController());
        YouAreEll urll1 = new YouAreEll(new TransactionController(new MessageController(), new IdController()));
        
        String commandLine;
        BufferedReader console = new BufferedReader
                (new InputStreamReader(System.in));

        ProcessBuilder pb = new ProcessBuilder();
        List<String> history = new ArrayList<String>();
        int index = 0;
        //we break out with <ctrl c>
        while (true) {
            //read what the user enters
            System.out.println("cmd? ");
            commandLine = console.readLine();

            //input parsed into array of strings(command and arguments)
            String[] commands = commandLine.split(" ");
            List<String> list = new ArrayList<String>();

            //if the user entered a return, just loop again
            if (commandLine.equals(""))
                continue;
            if (commandLine.equals("exit")) {
                System.out.println("bye!");
                break;
            }

            //loop through to see if parsing worked
            for (int i = 0; i < commands.length; i++) {
                //System.out.println(commands[i]); //***check to see if parsing/split worked***
                list.add(commands[i]);

            }
            System.out.print(list); //***check to see if list was added correctly***
            history.addAll(list);
            try {
                //display history of shell with index
                if (list.get(list.size() - 1).equals("history")) {
                    for (String s : history)
                        System.out.println((index++) + " " + s);
                    continue;
                }

                // Specific Commands.

                // ids
                //ToExecute type the below
                //ids
                if (list.contains("ids")&&list.size()==1) {
               //     String results = webber.get_ids();
                    ArrayList<Id> myArrayList;// = new ArrayList<Id>();
                    myArrayList = urll1.get_ids();
                   // SimpleShell.prettyPrint(results);

                    System.out.println("number of elements - " + myArrayList.size());

                    for(int i =0; i < myArrayList.size();i++)
                        System.out.println(myArrayList.get(i).getName() + "," + myArrayList.get(i).getGithub());


                    continue;
                }

                // This is to register your name and githubid
                //ToExecute type the below
                //ids <nameValue> <githubidValue>
                if (list.contains("ids")&&list.size()==3) {

                //    System.out.println("I am checking is it ids post request" + list.get(0) + "," + list.get(1) + ","+list.get(2));
                    ArrayList<Id> myArrayList;// = new ArrayList<Id>();
                    myArrayList = urll1.post_ids(list.get(1),list.get(2));
                    // SimpleShell.prettyPrint(results);

                    System.out.println("number of elements - " + myArrayList.size());

                    for(int i =0; i < myArrayList.size();i++)
                        System.out.println(myArrayList.get(i).getName() + "," + myArrayList.get(i).getGithub());



                    continue;
                }
                //Put This is to update a name given userid
                //ToExecute type the below
                // ids <userid> <nameid> <githubid>
                if (list.contains("ids")&&list.size()==4) {

                    //    System.out.println("I am checking is it ids post request" + list.get(0) + "," + list.get(1) + ","+list.get(2));
                    ArrayList<Id> myArrayList;// = new ArrayList<Id>();
                    myArrayList = urll1.put_ids(list.get(1),list.get(2),list.get(3));
                    // SimpleShell.prettyPrint(results);

                    System.out.println("number of elements - " + myArrayList.size());

                    for(int i =0; i < myArrayList.size();i++)
                        System.out.println(myArrayList.get(i).getName() + "," + myArrayList.get(i).getGithub());



                    continue;
                }
                // This is to pull all the messages
                //ToExecute type the below
                // messages
                if (list.contains("messages") && list.size()==1) {
                  //  String results = webber.get_messages();
                 ArrayList<Message> results = urll1.get_messages();
                   // SimpleShell.prettyPrint(results);
                    System.out.println("number of elements - " + results.size());

                    for(int i =0; i < results.size();i++)
                        System.out.println(results.get(i).getMessage() + "," + results.get(i).getFromId() + "," + results.get(i).getToId());


                    continue;
                }
                //To get messages of a given githubid
                //To Exxecute write the below command for testing u can use this id(erdfcvs)
                // messages <githubid>
                if (list.contains("messages") && list.size()==2) {
                    //  String results = webber.get_messages();
                    ArrayList<Message> results = urll1.get_messagesAndGitHubId(list.get(1));
                    // SimpleShell.prettyPrint(results);
                    System.out.println("number of elements - " + results.size());

                    for(int i =0; i < results.size();i++)
                        System.out.println(results.get(i).getMessage() + "," + results.get(i).getFromId() + "," + results.get(i).getToId());


                    continue;
                }
                //when mygitid and sequence
                //to execute type below for example we can use this (erdfcvs),3ab9eb055ee8574cebd4abe0b01ff5e6f94cac93
                //messages sequence <mygitid> <sequence number>
                if (list.contains("messages") && list.contains("sequence") && list.size()==4) {
                    //  String results = webber.get_messages();
                    ArrayList<Message> results = urll1.get_messagesAndGitHubIdAndSequence(list.get(2),list.get(3));
                    // SimpleShell.prettyPrint(results);
                    System.out.println("number of elements - " + results.size());

                    for(int i =0; i < results.size();i++)
                        System.out.println(results.get(i).getMessage() + "," + results.get(i).getFromId() + "," + results.get(i).getToId() + ","+ results.get(i).getSeqId());


                    continue;
                }
                //messages fromgithubid and togithubid u can use (qrdtfgs) (erdfcvs)
                //To execute use below
                // messages from <fromgitid> <toGitid>

                if (list.contains("messages") && list.contains("from") && list.size()==4) {
                    //  String results = webber.get_messages();
                    ArrayList<Message> results = urll1.get_messagesFromGitHubIdAndToGitHubId(list.get(2),list.get(3));
                    // SimpleShell.prettyPrint(results);
                    System.out.println("number of elements - " + results.size());

                    for(int i =0; i < results.size();i++)
                        System.out.println(results.get(i).getMessage() + "," + results.get(i).getFromId() + "," + results.get(i).getToId() + ","+ results.get(i).getSeqId());


                    continue;
                }
                // posting message to the world u can use (qrdtfgs) (anymessage without space)
                // To execute use below
                // send <yourgithubid> <messageValue>
                if (list.contains("send") && list.size()==3) {
                    //  String results = webber.get_messages();
                    ArrayList<Message> results = urll1.post_messagesAndYourGitHubId(list.get(1),list.get(2));
                    // SimpleShell.prettyPrint(results);
                    System.out.println("number of elements - " + results.size());

                    for(int i =0; i < results.size();i++)
                        System.out.println(results.get(i).getMessage() + "," + results.get(i).getFromId() + "," + results.get(i).getToId() + ","+ results.get(i).getSeqId());


                    continue;
                }
                //post messages fromGitHubId to ToGitHubId use (qrdtfgs) (anymessage without spaces) (erdfcvs)
                //To execute use below
                //send <yourgithubid> <messageValue> <togithubid>
                if (list.contains("send") && list.size()==4) {
                    //  String results = webber.get_messages();
                    ArrayList<Message> results = urll1.post_messagesAndFromGitHubIdToGitHubId(list.get(1),list.get(2),list.get(3));
                    // SimpleShell.prettyPrint(results);
                    System.out.println("number of elements - " + results.size());

                    for(int i =0; i < results.size();i++)
                        System.out.println(results.get(i).getMessage() + "," + results.get(i).getFromId() + "," + results.get(i).getToId() + ","+ results.get(i).getSeqId());


                    continue;
                }


                // you need to add a bunch more.

                //!! command returns the last command in history
                if (list.get(list.size() - 1).equals("!!")) {
                    pb.command(history.get(history.size() - 2));

                }//!<integer value i> command
                else if (list.get(list.size() - 1).charAt(0) == '!') {
                    int b = Character.getNumericValue(list.get(list.size() - 1).charAt(1));
                    if (b <= history.size())//check if integer entered isn't bigger than history size
                        pb.command(history.get(b));
                } else {
                    pb.command(list);
                }

                // // wait, wait, what curiousness is this?
                // Process process = pb.start();

                // //obtain the input stream
                // InputStream is = process.getInputStream();
                // InputStreamReader isr = new InputStreamReader(is);
                // BufferedReader br = new BufferedReader(isr);

                // //read output of the process
                // String line;
                // while ((line = br.readLine()) != null)
                //     System.out.println(line);
                // br.close();


            }

            //catch ioexception, output appropriate message, resume waiting for input
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("Input Error, Please try again!");
            }
            // So what, do you suppose, is the meaning of this comment?
            /** The steps are:
             * 1. parse the input to obtain the command and any parameters
             * 2. create a ProcessBuilder object
             * 3. start the process
             * 4. obtain the output stream
             * 5. output the contents returned by the command
             */

        }


    }

}
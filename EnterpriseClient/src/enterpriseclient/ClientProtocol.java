package enterpriseclient;
//Thanks to: http://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html

import constants.Constants;
import gui.FormManager;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import utils.JsonHelper;
import utils.Sha1Helper;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

public class ClientProtocol {

    
    //states
    private static final int WAITING = 0;
    private static final int SENTKNOCKKNOCK = 1;
//    private static final int SENTCLUE = 2;
//    private static final int ANOTHER = 3;

    //current state
    private int state = WAITING;
//    private int currentJoke = 0;

    //additional data
//    private String[] clues = { "Turnip", "Little Old Lady", "Atch", "Who", "Who" };
//    private String[] answers = { "Turnip the heat, it's cold in here!",
//                                 "I didn't know you could yodel!",
//                                 "Bless you!",
//                                 "Is there an owl in here?",
//                                 "Is there an echo in here?" };
    String username, password;
    public ClientProtocol(String username, String password)
    {
        this.username=username;
        this.password=password;
    
    }
    String action="";

    public Map<String,Object> processInput(String theInput) {
        if(theInput==null)return null;
        Map<String,Object> inputmap=null;
        try {
            inputmap=JsonHelper.toMap(theInput);
        } catch (IOException ex) {
            Logger.getLogger(ClientProtocol.class.getName()).log(Level.SEVERE, null, ex);
        }

        String theOutput = null;

        //validation
        if(inputmap==null)return null;
        if(!inputmap.containsKey("program"))return null;
        if(!inputmap.containsKey("action"))return null;
        if(!inputmap.containsKey("data"))return null;

        String program=(String)inputmap.get("program");
        if(!program.contentEquals(Constants.programname))return null;

        action=(String)inputmap.get("action");
        Map<String,String> data=(Map<String,String>)inputmap.get("data");



        //if-else statement to generate output depending on input

        if(action.contentEquals("login")) {
            try {
                String password_hash=Sha1Helper.sha1(password);
                theOutput = "{\"program\": \""+Constants.programname+"\", \"action\":\"login\", \"data\": {\"username\": \""+username+"\", \"password_hash\": \""+password_hash +"\"}}";
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            }
        } 
        else if(action.contentEquals("accessgranted"))
        {
            System.out.println("Login Successful");
            //theOutput = "{\"program\": \""+Constants.programname+"\", \"action\":\"exit\", \"data\": {}}";

            FormManager.getInstance().getFrmLogin().setVisible(false);
            FormManager.getInstance().getFrmMain().setVisible(true);
            //FormManager.getInstance().getFrmAccountsReceivable().setVisible(true);

            //reply nothing
            theOutput=null;
        }
        else if(action.contentEquals("accessdenied"))
        {
            System.out.println("Login Failed");
            theOutput = "{\"program\": \""+Constants.programname+"\", \"action\":\"exit\", \"data\": {}}";
            JOptionPane.showMessageDialog(null, "Login failed: Invalid username or password");
        }


//        else if (state == SENTKNOCKKNOCK) {
//            if (theInput.equalsIgnoreCase("Who's there?")) {
//                theOutput = clues[currentJoke];
//                state = SENTCLUE;
//            } else {
//                theOutput = "You're supposed to say \"Who's there?\"! " +
//			    "Try again. Knock! Knock!";
//            }
//        } else if (state == SENTCLUE) {
//            if (theInput.equalsIgnoreCase(clues[currentJoke] + " who?")) {
//                theOutput = answers[currentJoke] + " Want another? (y/n)";
//                state = ANOTHER;
//            } else {
//                theOutput = "You're supposed to say \"" + 
//			    clues[currentJoke] + 
//			    " who?\"" + 
//			    "! Try again. Knock! Knock!";
//                state = SENTKNOCKKNOCK;
//            }
//        } else if (state == ANOTHER) {
//            if (theInput.equalsIgnoreCase("y")) {
//                theOutput = "Knock! Knock!";
//                if (currentJoke == (NUMJOKES - 1))
//                    currentJoke = 0;
//                else
//                    currentJoke++;
//                state = SENTKNOCKKNOCK;
//            } else {
//                theOutput = "Bye.";
//                state = WAITING;
//            }
//        }
        return theOutput==null?jsonStringToMap(theOutput):null;
    }
    public String getLoginString()
    {
        try {
            String password_hash=Sha1Helper.sha1(password);
            return "{\"program\": \""+Constants.programname+"\", \"action\":\"login\", \"data\": {\"username\": \""+username+"\", \"password_hash\": \""+password_hash +"\"}}";
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    private Map<String,Object> jsonStringToMap(String json)
    {
        if(json==null)return null;
        try {
            return JsonHelper.toMap(json);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    
    public static void sendExit()
    {
        EnterpriseClientThread.send("{\"program\": \"enterpriseprogram\", \"action\":\"exit\", \"data\": {}}");
    }
    public static void sendDbSelect(String table,String criteria)
    {
        EnterpriseClientThread.send("{\"program\": \"enterpriseprogram\", \"action\":\"dbselect\", \"data\": {\"table\": \""+table+"\", \"criteria\":\""+criteria+"\"}}");
    }
}

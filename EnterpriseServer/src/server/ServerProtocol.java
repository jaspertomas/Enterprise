package server;
//Thanks to: http://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html
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

import constants.Constants;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import models.query.AccountsReceivable;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import utils.JsonHelper;
import utils.MySqlDBHelper;

public class ServerProtocol {

    
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

    public Map<String,Object> processInput(String theInput, EnterpriseServerThread thread) {
        
        if(theInput==null)return null;
        try {
            Map<String,Object> inputmap=null;
                inputmap=JsonHelper.toMap(theInput);

            String theOutput = null;

            //validation
            if(inputmap==null)return null;
            if(!inputmap.containsKey("program"))return null;
            if(!inputmap.containsKey("action"))return null;
            if(!inputmap.containsKey("data"))return null;

            String program=(String)inputmap.get("program");
            if(!program.contentEquals(Constants.programname))return null;

            String action=(String)inputmap.get("action");
            Map<String,String> data=(Map<String,String>)inputmap.get("data");



            //if-else statement to generate output depending on input

            if(action.contentEquals("exit"))
            {
                theOutput = "{\"program\": \""+Constants.programname+"\", \"action\":\"exit\", \"data\": {}}";
                System.out.println(thread.getUsername()+": Logout Successful ");
            }
            else if(action.contentEquals("login"))
            {
                String username=(String)data.get("username");
                thread.setUsername(username);
                String password_hash=(String)data.get("password_hash");

                Connection conn=MySqlDBHelper.getInstance().getConnection();
                Statement st = null;
                ResultSet rs = null;
                try { 
                    st = conn.createStatement();
    //                rs = st.executeQuery("SELECT VERSION()");
                    rs = st.executeQuery("SELECT * from users where username='"+username+"' and  password_hash='"+password_hash+"'");

                    if (rs.next()) {
                        System.out.println(username+": Login successful ");
                        theOutput = "{\"program\": \""+Constants.programname+"\", \"action\":\"accessgranted\", \"data\": {}}";
                    }
                    else
                    {
                        System.out.println(username+": Login failed ");
                        theOutput = "{\"program\": \""+Constants.programname+"\", \"action\":\"accessdenied\", \"data\": {}}";
                    }
                } catch (SQLException ex) {
                    //Logger.getLogger(ServerProtocol.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }


            }
            else if(action.contentEquals("dbselect"))
            {
                String table=(String)data.get("table");
                String criteria=(String)data.get("criteria");
                
                if(table.contentEquals("AccountsReceivable"))
                {
                    ArrayList<AccountsReceivable> result=AccountsReceivable.select(criteria);
                    HashMap<String,String> hashmap=new HashMap<String,String>();
                    
                    ObjectMapper mapper = JsonHelper.mapper;

                    Integer counter=0;
                    for(AccountsReceivable item:result)
                    {
                        hashmap.put(counter.toString(), mapper.writeValueAsString(item));
                        counter++;
                    }
                    //theOutput = "{\"program\": \""+Constants.programname+"\", \"action\":\"accessdenied\", \"data\": {\"result\": "+JsonHelper.toJson(map)+"}}";
                    theOutput = "{\"program\": \""+Constants.programname+"\", \"action\":\"dbresult\", \"data\": {\"result\":"+mapper.writeValueAsString(hashmap) +"}}";
                }
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
            return jsonStringToMap(theOutput);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    private Map<String,Object> jsonStringToMap(String json)
    {
        try {
            return JsonHelper.toMap(json);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

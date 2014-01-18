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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private String action="";

    public String getAction() {
        return action;
    }
    
    public String processInput(String theInput) {
        
        if(theInput==null)return null;
        try {
            Map<String,Object> map=null;
                map=JsonHelper.toMap(theInput);

            String theOutput = null;

            //validation
            if(map==null)return null;
            if(!map.containsKey("program"))return null;
            if(!map.containsKey("action"))return null;
            if(!map.containsKey("data"))return null;

            String program=(String)map.get("program");
            if(!program.contentEquals(Constants.programname))return null;

            action=(String)map.get("action");
            Map<String,String> data=(Map<String,String>)map.get("data");



            //if-else statement to generate output depending on input

            if(action.contentEquals("exit"))
            {
                theOutput = "{\"program\": \""+Constants.programname+"\", \"action\":\"exit\", \"data\": {}}";
            }
            else if(action.contentEquals("login"))
            {
                String username=(String)data.get("username");
                String password_hash=(String)data.get("password_hash");

                Connection conn=MySqlDBHelper.getInstance().getConnection();
                Statement st = null;
                ResultSet rs = null;
                try { 
                    st = conn.createStatement();
    //                rs = st.executeQuery("SELECT VERSION()");
                    rs = st.executeQuery("SELECT * from users where username='"+username+"' and  password_hash='"+password_hash+"'");

                    if (rs.next()) {
                        theOutput = "{\"program\": \""+Constants.programname+"\", \"action\":\"welcome\", \"data\": {}}";
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ServerProtocol.class.getName()).log(Level.SEVERE, null, ex);
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
            return theOutput;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

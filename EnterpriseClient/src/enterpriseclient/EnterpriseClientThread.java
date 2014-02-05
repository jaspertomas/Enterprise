package enterpriseclient;
//Thanks to: http://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html
/*
 * Copyright (c) 1995, 2013, Oracle and/or its affiliates. All rights reserved.
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
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnterpriseClientThread extends Thread {
    public static final Integer accessdenied=1;
    public static final Integer accessgranted=2;
    public static final Integer loggedout=3;
    private static Integer state=0;
    
    String username, password;
    
    public EnterpriseClientThread(String username, String password){
        super("ClientThread");
        
        this.username=username;
        this.password=password;
    }
    
    public void run()
    {
        String hostName = "localhost";
        int portNumber = 4444;
        
        Socket kkSocket=null;
        PrintWriter out=null;
        BufferedReader in=null;
        try {
             kkSocket = new Socket(hostName, portNumber);
             out = new PrintWriter(kkSocket.getOutputStream(), true);
             in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));

            
            BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromClient;
            
            ClientProtocol protocol = new ClientProtocol(username,password);
            fromClient = protocol.processInput(ClientProtocol.getLoginString());
            out.println(fromClient);

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                
                fromClient = protocol.processInput(fromServer);
                if (protocol.getAction().contentEquals("accessdenied"))
                {
                    state=accessdenied;
                    break;
                }
                if (protocol.getAction().contentEquals("exit"))
                {
                    state=loggedout;
                    break;
                }
                if (fromClient != null) {
                    System.out.println("Client: " + fromClient);
                    out.println(fromClient);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } finally {
            try {
                if(kkSocket!=null)kkSocket.close();
                if(in!=null)in.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }        
            if(out!=null)out.close();
        }
    
    }
}
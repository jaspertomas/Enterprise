package server;
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
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.JsonHelper;

public class EnterpriseServerThread extends Thread {

    private Socket socket = null;
    
    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    

    public EnterpriseServerThread(Socket socket) {
        super("ServerThread");
        this.socket = socket;
    }

    public void run() {
        PrintWriter out=null;
        BufferedReader in=null;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String inputLine, outputLine;
            ServerProtocol kkp = new ServerProtocol();

            while ((inputLine = in.readLine()) != null) {
                outputLine = JsonHelper.toJson(kkp.processInput(inputLine,this));
                out.println(outputLine);
                if (kkp.getAction().contentEquals("exit")) {
                    break;
                }
            }
            socket.close();
        } catch (IOException e) {
            System.out.println("ServerThread: IOException");
            e.printStackTrace();
        } finally {
            try {
                if(in!=null)in.close();
                if(out!=null)out.close();
            } catch (IOException ex) {
//                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("ServerThread: IOException: error closing reader");
                ex.printStackTrace();

            }
        }
    }
}

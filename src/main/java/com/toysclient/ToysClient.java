/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toysclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.UnknownHostException;

/**
 *
 * @author Kagema
 */
public class ToysClient {

    public static void main(String[] args) throws IOException {

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        try (
            
            Socket clientSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            String toyResponse;
            String toyRequest;

            while ((toyResponse = in.readLine()) != null) {
                System.out.println("Server: " + toyResponse);
                if (toyResponse.equals("Thanks")) {
                    break;
                }

                toyRequest = stdIn.readLine();
                if (toyRequest != null) {
                    System.out.println("Client: " + toyRequest);
                    out.println(toyRequest);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}

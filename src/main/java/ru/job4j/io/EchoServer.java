package ru.job4j.io;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        BasicConfigurator.configure();
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write("HTTP/1.1 200 Hello, dear friend!\r\n\r\n".getBytes());
                    String str = in.readLine();
                    String hello = "Hello";
                    String exit = "Exit";
                    String any = "Any";
                        if (str.contains(hello)) {
                            out.write("HTTP/1.1 200 HELLO!\r\n\r\n".getBytes());
                        }
                        if (str.contains(exit)) {
                            server.close();
                        }
                        if (str.contains(any)) {
                            out.write("HTTP/1.1 200 WHAT?\r\n\r\n".getBytes());
                        }
                        System.out.println(str);
                        out.flush();
                    }
            }
        } catch (Exception e) {
            LOG.error("Server status: buh bah badumts", e);
        }
    }
}

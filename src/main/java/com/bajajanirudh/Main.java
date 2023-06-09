package com.bajajanirudh;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;


public class Main {
    public static void main(String[] args) throws Exception {
        int port = 9000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("server started at " + port);
        server.createContext("/", new RootHandler(port));
        server.setExecutor(null);
        server.start();
    }
}
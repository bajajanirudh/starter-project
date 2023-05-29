package com.bajajanirudh;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class RootHandler implements HttpHandler {
    private final int port;
    public RootHandler (int port){
        this.port = port;
    }
    @Override
    public void handle(HttpExchange he) throws IOException {
        String query = he.getRequestURI().getQuery();
        if (query.startsWith("num=")){
            int find = Integer.parseInt(query.substring(4));
            NthPrimeNumber ans = new NthPrimeNumber(find);
            int answer = NthPrimeNumber.num;
            System.out.println("The requested prime number is " + answer);
        }
        String response = "<h1>Server start success if you see this message</h1>" + "<h1>Port: " + port + "</h1>\n";
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

package com.bajajanirudh;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
public class RootHandler implements HttpHandler {
    private final int port;
    public RootHandler (int port){
        this.port = port;
    }
    @Override
    public void handle(HttpExchange he) throws IOException {
        try {
            QuickStartSample.publishMetric(1);
        }catch (Exception e){
            System.out.println("exception1: " + e);
            throw new IOException(e);
        }
        String query = he.getRequestURI().getQuery();
        int answer = -1;
        if (query.startsWith("num=")){
            int find = Integer.parseInt(query.substring(4));
            NthPrimeNumber ans = new NthPrimeNumber(find);
            answer = NthPrimeNumber.num;
        }
        String response = "The requested prime number is " + answer + "\n";
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        os.write(response.getBytes());
        os.close();
        try {
            QuickStartSample.publishMetric(0.5);
        }catch (Exception e){
            throw new IOException(e);
        }
    }
}

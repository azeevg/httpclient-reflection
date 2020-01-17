package com.azeevg;

import com.sun.jersey.api.client.ClientHandler;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;

import java.net.URI;

public aspect JerseyClientAspect {
    pointcut requestHandle(ClientRequest cr, ClientHandler client):
            (call(ClientResponse ClientHandler.handle(ClientRequest)) && args(cr) && target(client));

    private ClientResponse handle(long start, ClientResponse response, ClientRequest cr) {
        long end = System.currentTimeMillis();
        URI uri = cr.getURI();
        String host = uri.getHost();
        String path = uri.getPath();
        String method = cr.getMethod();
        TestStatisticsLogger.getLogger().log(new StatisticsData(host, path, method, response.getStatus(), start, end));
        return response;
    }

    ClientResponse around(ClientRequest cr, ClientHandler client): requestHandle(cr, client) {
        return handle(System.currentTimeMillis(), proceed(cr, client), cr);
    }
}

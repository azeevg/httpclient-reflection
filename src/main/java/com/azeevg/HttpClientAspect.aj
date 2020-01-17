package com.azeevg;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

import java.net.URI;

public aspect HttpClientAspect {
    pointcut executeA(HttpUriRequest request, HttpClient client):
            (call(HttpResponse HttpClient.execute(HttpUriRequest)) && args(request) && target(client));

    pointcut executeB(HttpHost host, HttpRequest request, HttpClient client):
            (call(HttpResponse HttpClient.execute(HttpHost, HttpRequest)) && args(host, request) && target(client));

    pointcut executeC(HttpHost host, HttpRequest request, HttpContext httpContext, HttpClient client):
            (call(HttpResponse HttpClient.execute(HttpHost, HttpRequest, HttpContext)) && args(host, request, httpContext) && target(client));

    pointcut executeD(HttpUriRequest request, HttpContext httpContext, HttpClient client):
            (call(HttpResponse HttpClient.execute(HttpUriRequest, HttpContext)) && args(request, httpContext) && target(client));


    private HttpResponse handle(long start, HttpResponse response, HttpRequest request) {
        URI uri;
        String method;

        if (request instanceof HttpUriRequest) {
            HttpUriRequest httpUriRequest = (HttpUriRequest) request;
            uri = httpUriRequest.getURI();
            method = httpUriRequest.getMethod();
        } else {
            uri = URI.create(request.getRequestLine().getUri());
            method = request.getRequestLine().getMethod();
        }

        long end = System.currentTimeMillis();
        String host = uri.getHost();
        String path = uri.getPath();
        int statusCode = response.getStatusLine().getStatusCode();
        StatisticsData statisticsData = new StatisticsData(host, path, method, statusCode, start, end);

        TestStatisticsLogger.getLogger().log(statisticsData);

        return response;
    }

    HttpResponse around(HttpUriRequest request, HttpClient client): executeA(request, client) {
        return handle(System.currentTimeMillis(), proceed(request, client), request);
    }

    HttpResponse around(HttpHost host, HttpRequest request, HttpClient client): executeB(host, request, client) {
        return handle(System.currentTimeMillis(), proceed(host, request, client), request);
    }

    HttpResponse around(HttpHost host, HttpRequest request, HttpContext httpContext, HttpClient client): executeC(host, request, httpContext, client) {
        return handle(System.currentTimeMillis(), proceed(host, request, httpContext, client), request);
    }

    HttpResponse around(HttpUriRequest request, HttpContext httpContext, HttpClient client): executeD(request, httpContext, client) {
        return handle(System.currentTimeMillis(), proceed(request, httpContext, client), request);
    }
}

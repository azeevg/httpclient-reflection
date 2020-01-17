package com.azeevg;

import org.apache.commons.httpclient.*;

public aspect LegacyHttpClientAspect {
    pointcut executeHttpMethodA(HttpMethod request, HttpClient client):
            (call(int HttpClient.executeMethod(HttpMethod)) && args(request) && target(client));
    pointcut executeHttpMethodB(HostConfiguration hostconfig, HttpMethod request, HttpClient client):
            (call(int HttpClient.executeMethod(HostConfiguration, HttpMethod)) && args(hostconfig, request) && target(client));
    pointcut executeHttpMethodC(HostConfiguration hostconfig, HttpMethod request, HttpState state, HttpClient client):
            (call(int HttpClient.executeMethod(HostConfiguration, HttpMethod, HttpState)) && args(hostconfig, request, state) && target(client));

    private int handle(long start, int code, HttpMethod request) {
        long end = System.currentTimeMillis();
        try {
            URI uri = request.getURI();
            String host = uri.getHost();
            String path = uri.getPath();
            String method = request.getName();
            TestStatisticsLogger.getLogger().log(new StatisticsData(host, path, method, code, start, end));
        } catch (URIException e) {
            e.printStackTrace();
        }
        return code;
    }

    int around(HttpMethod request, HttpClient client): executeHttpMethodA(request, client) {
        return handle(System.currentTimeMillis(), proceed(request, client), request);
    }

    int around(HostConfiguration hostconfig, HttpMethod request, HttpClient client): executeHttpMethodB(hostconfig, request, client) {
        return handle(System.currentTimeMillis(), proceed(hostconfig, request, client), request);
    }

    int around(HostConfiguration hostconfig, HttpMethod request, HttpState state, HttpClient client): executeHttpMethodC(hostconfig, request, state, client) {
        return handle(System.currentTimeMillis(), proceed(hostconfig, request, state, client), request);
    }
}

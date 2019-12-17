import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;

public aspect LegacyHttpClientAspect {
    pointcut executeHttpMethod(HttpMethod request, HttpClient client):
            call(int HttpClient.executeMethod(HttpUriRequest)) && args(request) && target(client);

    int around(HttpMethod request, HttpClient client):
            executeHttpMethod(request, client) {
        System.out.println(request);
        return proceed(request, client);
    }
}

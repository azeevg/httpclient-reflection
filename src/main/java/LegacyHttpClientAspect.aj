import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;

public aspect LegacyHttpClientAspect {
    pointcut executeHttpMethod(HttpMethod request, HttpClient client):
            call(int HttpClient.doExecute(HttpMethod)) && args(request) && target(client);

    int around(HttpMethod request, HttpClient client):
            executeHttpMethod(request, client) {
        long start = System.currentTimeMillis();
        int statusCode = proceed(request, client);
        long end = System.currentTimeMillis();
        try {
            URI uri = request.getURI();
            String host = uri.getHost();
            String path = uri.getPath();
            long time = end - start;
            String method = request.getName();
            System.out.println(String.format("%s %s %s %s %s", method, host, path, time, statusCode));
        } catch (URIException e) {
            e.printStackTrace();
        }


        return statusCode;
    }
}

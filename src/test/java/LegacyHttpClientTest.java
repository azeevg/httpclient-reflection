import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;

import java.io.IOException;

public class LegacyHttpClientTest {
    @Test
    public void legacyHttpClient() throws IOException, IllegalAccessException, NoSuchFieldException {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod("https://yandex.ru");
        client.executeMethod(method);
        byte[] responseBody = method.getResponseBody();
//        ReflectionUtils.HttpInter res = ReflectionUtils.getRes(method);
//        System.out.println(res);
        System.out.println(method.getQueryString());
        method.releaseConnection();
    }
}

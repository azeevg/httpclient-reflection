import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;

public class HttpClientTest {
    @Test
    public void testClosingQuietly() {
        HttpClient client = new HttpClient4Decorator(HttpClientBuilder.create().build());

        try {
            HttpUriRequest request = RequestBuilder.get().setUri("https://yandex.ru/assd").build();
            HttpResponse response = client.execute(request);
//            ReflectionUtils.HttpInter res = ReflectionUtils.getRes(response);
//            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;

import java.io.IOException;

public class LegacyHttpClientTest {
    @Test
    public void legacyHttpClient() throws IOException, IllegalAccessException, NoSuchFieldException {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod("https://yandex.ru/");
        client.executeMethod(method);
        client.executeMethod(new HostConfiguration(), method);
        client.executeMethod(new HostConfiguration(), method, new HttpState());
        method.releaseConnection();
    }
}

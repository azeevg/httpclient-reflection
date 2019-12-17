import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod("https://yandex.ru");
        client.executeMethod(method);
        method.releaseConnection();
    }
}

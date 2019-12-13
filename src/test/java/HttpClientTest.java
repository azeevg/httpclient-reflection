import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import sun.management.GcInfoBuilder;

import java.io.IOException;
import java.lang.reflect.Field;

public class HttpClientTest {
    static class StringResponseHandler implements ResponseHandler<String> {

        public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
//            ((CPoolProxy) ((HttpResponseProxy) )
//            .connHolder
//            .managedConn)
//            .poolEntry.route.targetHost.lcHostname

            try {
                Field f1 = response.getClass().getDeclaredField("connHolder");
                f1.setAccessible(true);Object o1 = f1.get(response);f1.setAccessible(false);

                Field f2 = o1.getClass().getDeclaredField("managedConn");
                f2.setAccessible(true);Object o2 = f2.get(o1);f2.setAccessible(false);

                Field f3 = o2.getClass().getDeclaredField("poolEntry");
                f3.setAccessible(true);Object o3 = f3.get(o2);f3.setAccessible(false);

                Field f4 = o3.getClass().getDeclaredField("route");
                f4.setAccessible(true);Object o4 = f4.get(o3);f4.setAccessible(false);

                Field f5 = o4.getClass().getDeclaredField("targetHost");
                f5.setAccessible(true);Object o5 = f5.get(o4);f5.setAccessible(false);

                Field f6 = o5.getClass().getDeclaredField("lcHostname");
                f6.setAccessible(true);Object o6 = f6.get(o5);f6.setAccessible(false);

                System.out.println(o6);

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


            return EntityUtils.toString(response.getEntity());
        }
    }

    @Test
    public void testClosingQuietly() {
        HttpClient client = HttpClientBuilder.create().build();

        try {
            HttpUriRequest request = RequestBuilder.get().setUri("https://yandex.com").build();
            String response = client.execute(request, new StringResponseHandler());
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

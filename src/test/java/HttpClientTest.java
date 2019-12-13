import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;

public class HttpClientTest {
    static class StringResponseHandler implements ResponseHandler<String> {

        static Object getFieldRecursively(Object o, String fieldName) throws IllegalAccessException {
            Class<?> clazz = o.getClass();
            for (int i = 0; i < 20 && !clazz.equals(Object.class); i++) {
                Field[] declaredFields = clazz.getDeclaredFields();
                for (Field field : declaredFields) {
                    if (field.getName().equals(fieldName)) {
                        field.setAccessible(true);
                        Object objectValue = field.get(o);
                        field.setAccessible(false);
                        return objectValue;
                    }
                }
                clazz = clazz.getSuperclass();
            }

            return null;
        }

        static Object field(Object o1, String fieldName) throws NoSuchFieldException, IllegalAccessException {
            if (o1 == null) {
                return null;
            }
            return getFieldRecursively(o1, fieldName);
        }

        public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            try {
                Object o1 = field(response, "connHolder");
                Object o2 = field(o1, "managedConn");
                Object o3 = field(o2, "poolEntry");
                Object o4 = field(o3, "route");
                Object o5 = field(o4, "targetHost");
                Object o6 = field(o5, "lcHostname");

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

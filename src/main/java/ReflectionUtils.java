import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.http.HttpResponse;

public class ReflectionUtils {

    @Getter
    @Setter
    @ToString
    static class HttpInter {
        private int code;
        private String host;

        public HttpInter(int code, String host) {
            this.code = code;
            this.host = host;
        }
    }


    public static FieldWrapper getField(Object object) {
        return new FieldWrapper(object);
    }

    public static HttpInter getRes(org.apache.commons.httpclient.HttpMethodBase method) {
        if (method.getHostConfiguration() != null) {
            return new HttpInter(method.getStatusCode(), method.getHostConfiguration().getHost());
        }
        Object obj = new FieldWrapper(method)
                .field("httphost")
                .field("hostname").obj;
        if (obj instanceof String) {
            return new HttpInter(method.getStatusCode(), (String) obj);
        }
        return new HttpInter(0, null);
    }

    public static HttpInter getRes(HttpResponse response) {
        Object obj = new FieldWrapper(response)
                .field("connHolder")
                .field("managedConn")
                .field("poolEntry")
                .field("route")
                .field("targetHost")
                .field("lcHostname")
                .obj;
        if (obj instanceof String) {
            return new HttpInter(response.getStatusLine().getStatusCode(), (String) obj);
        }
        return new HttpInter(0, null);
    }

}

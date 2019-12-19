import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;

public class JerseyClientDecorator extends Client {

    public JerseyClientDecorator(ClientConfig clientConfig) {
        super(new URLConnectionClientHandler(), clientConfig);
    }

    @Override
    public ClientResponse handle(ClientRequest request) throws ClientHandlerException {
        ClientResponse handle = super.handle(request);
        System.out.println(String.format("%s %s %s", request.getMethod(), request.getURI(), handle.getStatus()));
        return handle;
    }
}

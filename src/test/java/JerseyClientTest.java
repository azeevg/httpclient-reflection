import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

public class JerseyClientTest {
    @Test
    public void checkGetRequest() {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getClasses().add(JacksonJsonProvider.class);

        Client httpClient = new JerseyClientDecorator(Client.create(clientConfig));

        WebResource.Builder resource = httpClient.resource("https://www.googleapis.com/customsearch/v1")
                .queryParam("key", "AIzaSyBFTBoIacdRzw8h5aEgqRrZm2-rAQdANyQ")
                .queryParam("cx", "017576662512468239146:omuauf_lfve")
                .queryParam("q", "lectures")
                .type(MediaType.TEXT_HTML_TYPE);

        ClientResponse clientResponse = resource.get(ClientResponse.class);
    }
}

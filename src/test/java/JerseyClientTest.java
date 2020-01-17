import com.azeevg.TestStatisticsLogger;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

public class JerseyClientTest {
    private static Client httpClient = new Client();

    @Test
    public void checkGetRequest() {
        ObservableStatisticsLogger statisticsLogger = new ObservableStatisticsLoggerImpl();
        TestStatisticsLogger.setLogger(statisticsLogger);

        WebResource.Builder resource = httpClient.resource("https://www.googleapis.com/customsearch/v1")
                .queryParam("key", System.getenv("GOOGLE_SEARCH_API_KEY"))
                .queryParam("cx", "017576662512468239146:omuauf_lfve")
                .queryParam("q", "lectures")
                .type(MediaType.TEXT_HTML_TYPE);
        resource.get(ClientResponse.class);

        Assert.assertEquals(1, statisticsLogger.getStats().size());
    }
}

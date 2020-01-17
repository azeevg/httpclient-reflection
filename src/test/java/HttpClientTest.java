import com.azeevg.StatisticsData;
import com.azeevg.TestStatisticsLogger;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public class HttpClientTest {
    private static HttpClient client = HttpClients.createDefault();

    private void validateSingleRequest(Function<HttpUriRequest, HttpResponse> execute) {
        ObservableStatisticsLogger logger = new ObservableStatisticsLoggerImpl();

        TestStatisticsLogger.setLogger(logger);
        HttpUriRequest request = RequestBuilder.get().setUri("https://yandex.ru/assd").build();
        HttpResponse response = execute.apply(request);
        HttpClientUtils.closeQuietly(response);
        List<StatisticsData> stats = logger.getStats();
        Assert.assertEquals(1, stats.size());
        StatisticsData statisticsData = stats.get(0);

        Assert.assertEquals(response.getStatusLine().getStatusCode(), statisticsData.getCode());
        Assert.assertEquals("yandex.ru", statisticsData.getHost());
        Assert.assertEquals("GET", statisticsData.getMethod());
        Assert.assertEquals("/assd", statisticsData.getPathQuery());
    }

    @Test
    public void HttpUriRequest_method_test() {
        validateSingleRequest(ThrowingUtils.unchecked(request -> client.execute(request)));
    }

    @Test
    public void HttpHost_HttpUriRequest_method_test() {
        validateSingleRequest(ThrowingUtils.unchecked(request -> client.execute(new HttpHost("yandex.ru"), request)));
    }

    @Test
    public void HttpUriRequest_HttpContext_method_test() {
        validateSingleRequest(ThrowingUtils.unchecked(request -> client.execute(request, new HttpClientContext())));
    }

    @Test
    public void HttpHost_HttpRequest_HttpContext_method_test() {
        validateSingleRequest(ThrowingUtils.unchecked(request -> client.execute(new HttpHost("yandex.ru"), request, new HttpClientContext())));
    }

    @Test
    public void multipleRequestLoggingTest() {
        ObservableStatisticsLogger statisticsLogger = new ObservableStatisticsLoggerImpl();
        TestStatisticsLogger.setLogger(statisticsLogger);

        try {
            HttpUriRequest request = RequestBuilder.get().setUri("https://yandex.ru/assd").build();
            // 1
            HttpResponse response = client.execute(request);
            HttpClientUtils.closeQuietly(response);
            // 2
            response = client.execute(new HttpHost("yandex.ru"), request);
            HttpClientUtils.closeQuietly(response);
            // 3
            response = client.execute(request, new HttpClientContext());
            HttpClientUtils.closeQuietly(response);
            // 4
            response = client.execute(new HttpHost("yandex.ru"), request, new HttpClientContext());
            HttpClientUtils.closeQuietly(response);

            Assert.assertEquals(4, statisticsLogger.getStats().size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

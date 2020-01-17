import com.azeevg.StatisticsData;
import com.azeevg.TestStatisticsLogger;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public class LegacyHttpClientTest {
    private static HttpClient client = new HttpClient();

    private void validateSingleRequest(Function<HttpMethod, Integer> execute) {
        ObservableStatisticsLogger logger = new ObservableStatisticsLoggerImpl();
        TestStatisticsLogger.setLogger(logger);
        GetMethod method = new GetMethod("https://yandex.ru/path?22");
        execute.apply(method);
        method.releaseConnection();

        List<StatisticsData> stats = logger.getStats();
        Assert.assertEquals(1, stats.size());
        StatisticsData statisticsData = stats.get(0);

        Assert.assertTrue(statisticsData.getCode() > 0);
        Assert.assertEquals("yandex.ru", statisticsData.getHost());
        Assert.assertEquals("GET", statisticsData.getMethod());
        Assert.assertEquals("/path", statisticsData.getPathQuery());
    }

    @Test
    public void HttpMethod_method_test() {
        validateSingleRequest(ThrowingUtils.unchecked(method -> client.executeMethod(method)));
    }

    @Test
    public void HostConfiguration_HttpMethod_method_test() {
        validateSingleRequest(ThrowingUtils.unchecked(method -> client.executeMethod(new HostConfiguration(), method)));
    }

    @Test
    public void HostConfiguration_HttpMethod_HttpState_method_test() {
        validateSingleRequest(ThrowingUtils.unchecked(method -> client.executeMethod(new HostConfiguration(), method, new HttpState())));
    }

    @Test
    public void legacyHttpClient() throws IOException {
        ObservableStatisticsLogger statisticsLogger = new ObservableStatisticsLoggerImpl();
        TestStatisticsLogger.setLogger(statisticsLogger);

        GetMethod method = new GetMethod("https://yandex.ru/");
        client.executeMethod(method);
        client.executeMethod(new HostConfiguration(), method);
        client.executeMethod(new HostConfiguration(), method, new HttpState());
        method.releaseConnection();
        Assert.assertEquals(3, statisticsLogger.getStats().size());
    }
}

import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.client.proxy.ViewProxy;
import com.sun.jersey.core.spi.component.ioc.IoCComponentProviderFactory;
import com.sun.jersey.spi.MessageBodyWorkers;

import javax.ws.rs.ext.Providers;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class JerseyClientDecorator extends Client {
    public JerseyClientDecorator() {
        super();
    }

    public JerseyClientDecorator(ClientHandler root) {
        super(root);
    }

    public JerseyClientDecorator(ClientHandler root, ClientConfig config) {
        super(root, config);
    }

    public JerseyClientDecorator(ClientHandler root, ClientConfig config, IoCComponentProviderFactory provider) {
        super(root, config, provider);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public Providers getProviders() {
        return super.getProviders();
    }

    @Override
    public MessageBodyWorkers getMessageBodyWorkers() {
        return super.getMessageBodyWorkers();
    }

    @Override
    public WebResource resource(String u) {
        return super.resource(u);
    }

    @Override
    public WebResource resource(URI u) {
        return super.resource(u);
    }

    @Override
    public AsyncWebResource asyncResource(String u) {
        return super.asyncResource(u);
    }

    @Override
    public AsyncWebResource asyncResource(URI u) {
        return super.asyncResource(u);
    }

    @Override
    public ViewResource viewResource(String u) {
        return super.viewResource(u);
    }

    @Override
    public ViewResource viewResource(URI u) {
        return super.viewResource(u);
    }

    @Override
    public AsyncViewResource asyncViewResource(String u) {
        return super.asyncViewResource(u);
    }

    @Override
    public AsyncViewResource asyncViewResource(URI u) {
        return super.asyncViewResource(u);
    }

    @Override
    public <T> T view(String u, Class<T> type) {
        return super.view(u, type);
    }

    @Override
    public <T> T view(URI uri, Class<T> type) {
        return super.view(uri, type);
    }

    @Override
    public <T> T view(String u, T t) {
        return super.view(u, t);
    }

    @Override
    public <T> T view(URI uri, T t) {
        return super.view(uri, t);
    }

    @Override
    public <T> Future<T> asyncView(String u, Class<T> type) {
        return super.asyncView(u, type);
    }

    @Override
    public <T> Future<T> asyncView(URI uri, Class<T> type) {
        return super.asyncView(uri, type);
    }

    @Override
    public <T> Future<T> asyncView(String u, T t) {
        return super.asyncView(u, t);
    }

    @Override
    public <T> Future<T> asyncView(URI uri, T t) {
        return super.asyncView(uri, t);
    }

    @Override
    public <T> T view(Class<T> c, ClientResponse response) {
        return super.view(c, response);
    }

    @Override
    public <T> T view(T t, ClientResponse response) {
        return super.view(t, response);
    }

    @Override
    public <T> ViewProxy<T> getViewProxy(Class<T> c) {
        return super.getViewProxy(c);
    }

    @Override
    public void setExecutorService(ExecutorService es) {
        super.setExecutorService(es);
    }

    @Override
    public ExecutorService getExecutorService() {
        return super.getExecutorService();
    }

    @Override
    public Map<String, Object> getProperties() {
        return super.getProperties();
    }

    @Override
    public void setFollowRedirects(Boolean redirect) {
        super.setFollowRedirects(redirect);
    }

    @Override
    public void setReadTimeout(Integer interval) {
        super.setReadTimeout(interval);
    }

    @Override
    public void setConnectTimeout(Integer interval) {
        super.setConnectTimeout(interval);
    }

    @Override
    public void setChunkedEncodingSize(Integer chunkSize) {
        super.setChunkedEncodingSize(chunkSize);
    }

    @Override
    public ClientResponse handle(ClientRequest request) throws ClientHandlerException {
        ClientResponse handle = super.handle(request);
        System.out.println(String.format("%s %s %s", request.getMethod(), request.getURI(), handle.getStatus()));
        return handle;
    }

    @Override
    public void inject(Object o) {
        super.inject(o);
    }

    @Override
    public void addFilter(ClientFilter f) {
        super.addFilter(f);
    }

    @Override
    public void removeFilter(ClientFilter f) {
        super.removeFilter(f);
    }

    @Override
    public boolean isFilterPreset(ClientFilter filter) {
        return super.isFilterPreset(filter);
    }

    @Override
    public boolean isFilterPresent(ClientFilter filter) {
        return super.isFilterPresent(filter);
    }

    @Override
    public void removeAllFilters() {
        super.removeAllFilters();
    }

    @Override
    public ClientHandler getHeadHandler() {
        return super.getHeadHandler();
    }
}

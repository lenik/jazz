package net.bodz.bas.http;

import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import net.bodz.bas.err.LazyLoadException;

public class HttpClientConfig {

    boolean pool = true;
    int maxTotal = 200;
    int defaultMaxPerRoute = 20;

    HttpClientConnectionManager connectionManager;

    public HttpClientConnectionManager createConnectionManager() {
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getDefault();
        } catch (NoSuchAlgorithmException e) {
            throw new LazyLoadException("Can't get default SSLContext.", e);
        }

        LayeredConnectionSocketFactory sslSocketFactory = null;
        sslSocketFactory = new SSLConnectionSocketFactory(sslContext);
        PlainConnectionSocketFactory plainSocketFactory = new PlainConnectionSocketFactory();

        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create() //
                .register("https", sslSocketFactory) //
                .register("http", plainSocketFactory).build();

        if (pool) {
            PoolingHttpClientConnectionManager connectionManager;
            connectionManager = new PoolingHttpClientConnectionManager(registry);
            connectionManager.setMaxTotal(maxTotal);
            connectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
            return connectionManager;
        } else {
            BasicHttpClientConnectionManager connectionManager;
            connectionManager = new BasicHttpClientConnectionManager(registry);
            return connectionManager;
        }
    }

    public HttpClientConnectionManager getConnectionManager() {
        if (connectionManager == null) {
            synchronized (HttpClientConfig.class) {
                if (connectionManager == null) {
                    connectionManager = createConnectionManager();
                }
            }
        }
        return connectionManager;
    }

    public HttpClient newClient() {
        HttpClientConnectionManager connectionManager = getConnectionManager();
        CloseableHttpClient httpClient = HttpClients.custom() // HttpClientBuilder
                .setConnectionManager(connectionManager) //
                .build();
        return httpClient;
    }

    public static final HttpClientConfig DEFAULT = new HttpClientConfig();

}

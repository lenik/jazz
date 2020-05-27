package net.bodz.http;

public class HttpRequestConfig {

    int connectTimeout = 3000;
    int socketTimeout = 3000;

    public org.apache.http.client.config.RequestConfig compile() {
        return org.apache.http.client.config.RequestConfig.custom()//
                .setConnectTimeout(connectTimeout) //
                .setSocketTimeout(socketTimeout) //
                .build();
    }

    public static final HttpRequestConfig DEFAULT = new HttpRequestConfig();

}

package net.bodz.bas.search.elasticsearch;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.common.settings.ISettingsConsts;

import net.bodz.bas.ee.distrib.IEndpointConfig;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.pojo.Pair;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;

@ExcludedFromIndex
@IndexedType
public class EsClientConfig
        implements
            IEndpointConfig,
            ISettingsConsts {

    public static final String ATTRIBUTE_KEY = EsClientConfig.class.getName();

    private List<Pair<String, Integer>> addresses;

    public EsClientConfig() {
        addresses = new ArrayList<>();
    }

    public List<Pair<String, Integer>> getAddresses() {
        return addresses;
    }

    public void addAddress(String hostname)
            throws UnknownHostException {
        addAddress(hostname, 9300);
    }

    public void addAddress(String hostname, int port)
            throws UnknownHostException {
        addresses.add(Pair.of(hostname, port));
    }

    public ElasticsearchClient buildClient() {
        int n = addresses.size();
        HttpHost[] hosts = new HttpHost[n];
        for (int i = 0; i < n; i++) {
            Pair<String, Integer> addr = addresses.get(i);
            HttpHost host = new HttpHost(addr.first, addr.second);
            hosts[i] = host;
        }

        RestClient restClient = RestClient.builder(hosts).build();

        RestClientTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        ElasticsearchClient client = new ElasticsearchClient(transport);
        return client;
    }

}

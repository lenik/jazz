package net.bodz.bas.search.elasticsearch;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ISettingsConsts;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.plugins.Plugin;

import net.bodz.bas.ee.distrib.IEndpointConfig;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.meta.codegen.IndexedType;

@ExcludedFromIndex
@IndexedType
public class EsClientConfig
        implements IEndpointConfig, ISettingsConsts {

    public static final String ATTRIBUTE_KEY = EsClientConfig.class.getName();

    private Settings.Builder settingsBuilder;
    private List<Class<? extends Plugin>> plugins;
    private List<TransportAddress> addresses;

    public EsClientConfig() {
        settingsBuilder = Settings.builder();
        plugins = new ArrayList<>();
        addresses = new ArrayList<>();
    }

    public Settings.Builder getSettingsBuilder() {
        return settingsBuilder;
    }

    public List<Class<? extends Plugin>> getPlugins() {
        return plugins;
    }

    public List<TransportAddress> getAddresses() {
        return addresses;
    }

    public void addAddress(String hostname)
            throws UnknownHostException {
        addAddress(hostname, 9300);
    }

    public void addAddress(String hostname, int port)
            throws UnknownHostException {
        InetAddress address = InetAddress.getByName(hostname);
        addresses.add(new InetSocketTransportAddress(address, port));
    }

    public Client buildClient() {
        // settingsBuilder.put("shield.user", "elastic:_Elastic");

        Settings settings = settingsBuilder.build();

        TransportClient.Builder clientBuilder = TransportClient.builder().settings(settings);
        for (Class<? extends Plugin> plugin : plugins)
            clientBuilder.addPlugin(plugin);

        // clientBuilder.addPlugin(ShieldPlugin.class);

        TransportClient client = clientBuilder.build();
        for (TransportAddress address : addresses)
            client.addTransportAddress(address);

        return client;
    }

}

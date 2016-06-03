package net.bodz.bas.search.elasticsearch;

import java.util.List;
import java.util.ServiceLoader;

import net.bodz.bas.ee.distrib.AbstractEndpointRegistry;
import net.bodz.bas.t.iterator.Iterables;

public class EsClientRegistry
        extends AbstractEndpointRegistry<EsClientConfig> {

    public EsClientRegistry() {
        reload();
    }

    void reload() {
        for (EsClientConfig c : ServiceLoader.load(EsClientConfig.class)) {
            String name = c.getClass().getName();
            String[] tags = {};
            addConfig(name, c, tags);
        }

        List<IEsClientConfigurer> configurers = Iterables.toList(//
                ServiceLoader.load(IEsClientConfigurer.class));

        for (IEsClientConfigurer c : configurers) {
            for (EsClientConfig fig : getConfigs())
                c.config(fig);
        }

        for (IEsClientConfigurer c : configurers)
            c.postConfig(this);
    }

    private static EsClientRegistry instance = new EsClientRegistry();

    public static EsClientRegistry getInstance() {
        return instance;
    }

}

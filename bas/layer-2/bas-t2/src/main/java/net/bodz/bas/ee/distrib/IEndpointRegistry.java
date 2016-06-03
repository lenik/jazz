package net.bodz.bas.ee.distrib;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface IEndpointRegistry<config_t extends IEndpointConfig> {

    Set<String> getConfigNames();

    Collection<config_t> getConfigs();

    config_t getConfig(String name);

    Set<String> getConfigTags(String name);

    List<config_t> selectConfigs(String... tags);

    void addConfig(String name, config_t config, Iterable<String> tags);

    void addConfig(String name, config_t config, String... tags);

    config_t removeConfig(String name);

}

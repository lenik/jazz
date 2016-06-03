package net.bodz.bas.ee.distrib;

public interface IEndpointConfigurer<registry_t extends IEndpointRegistry<config_t>, config_t extends IEndpointConfig> {

    void config(config_t config);

    void postConfig(registry_t registry);

}

package net.bodz.bas.site.vhost;

public interface IVirtualHostBuilder {

    IVirtualHostBuilder name(String name);

    IVirtualHost build();

}

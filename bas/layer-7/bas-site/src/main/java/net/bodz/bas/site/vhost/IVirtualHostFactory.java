package net.bodz.bas.site.vhost;

public interface IVirtualHostFactory {

    IVirtualHost buildVirtualHost(String name, String databaseName);

    default IVirtualHost buildVirtualHost(String name) {
        return buildVirtualHost(name, null);
    }

}

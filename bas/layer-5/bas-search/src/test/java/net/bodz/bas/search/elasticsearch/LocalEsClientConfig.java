package net.bodz.bas.search.elasticsearch;

import java.net.UnknownHostException;

import net.bodz.bas.err.UnexpectedException;

public class LocalEsClientConfig
        extends EsClientConfig {

    public LocalEsClientConfig() {
        try {
            addAddress("localhost");
        } catch (UnknownHostException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

}

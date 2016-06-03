package net.bodz.bas.search.elasticsearch;

import net.bodz.bas.ee.distrib.IEndpointConfigurer;
import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface IEsClientConfigurer
        extends IEndpointConfigurer<EsClientRegistry, EsClientConfig> {

}

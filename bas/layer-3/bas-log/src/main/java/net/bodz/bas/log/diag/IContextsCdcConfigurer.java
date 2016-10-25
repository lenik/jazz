package net.bodz.bas.log.diag;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface IContextsCdcConfigurer {

    void configure(CompositeDiagContext contexts);

}

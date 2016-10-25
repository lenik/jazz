package net.bodz.bas.log;

import java.util.EventListener;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface ILoggingSystemConfigurer
        extends EventListener {

    void initLoggingSystem();

}

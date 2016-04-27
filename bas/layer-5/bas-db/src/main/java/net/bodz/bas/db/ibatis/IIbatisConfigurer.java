package net.bodz.bas.db.ibatis;

import org.apache.ibatis.session.Configuration;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;

@IndexedType
public interface IIbatisConfigurer
        extends IPriority {

    int PRIORITY_HIGH = -1;
    int PRIORITY_MEDIUM = 0;
    int PRIORITY_LOW = 1;

    void configure(Configuration config);

}

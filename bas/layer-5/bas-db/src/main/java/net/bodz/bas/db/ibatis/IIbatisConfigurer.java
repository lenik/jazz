package net.bodz.bas.db.ibatis;

import org.apache.ibatis.session.Configuration;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface IIbatisConfigurer {

    void configure(Configuration config);

}

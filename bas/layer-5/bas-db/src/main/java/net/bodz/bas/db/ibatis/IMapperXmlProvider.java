package net.bodz.bas.db.ibatis;

import java.util.Set;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface IMapperXmlProvider {

    Set<String> getNames();

    MapperXml getXml(String name);

}

package net.bodz.lily.security.login;

import java.util.List;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface ILoginResolverProvider {

    List<ILoginResolver> getResolvers();

}

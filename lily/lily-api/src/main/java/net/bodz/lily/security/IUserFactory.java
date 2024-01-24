package net.bodz.lily.security;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface IUserFactory {

    IMutableUser newUser();

    IMutableGroup newGroup();

}

package net.bodz.bas.vfs;

import java.util.Collection;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface IVfsDriverProvider {

    Collection<? extends IVfsDriver> getDrivers();

}

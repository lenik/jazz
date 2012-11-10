package net.bodz.bas.vfs;

import java.util.List;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface IVfsDriverProvider {

    List<? extends IVfsDriver> getDrivers();

}

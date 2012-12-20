package net.bodz.bas.vfs.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.bodz.bas.vfs.IVfsDriver;
import net.bodz.bas.vfs.IVfsDriverProvider;
import net.bodz.bas.vfs.impl.mem.MemoryVfsDriver;
import net.bodz.bas.vfs.impl.pojf.PojfVfsDriver;
import net.bodz.bas.vfs.impl.pseudo.PseudoVfsDriver;
import net.bodz.bas.vfs.impl.url.URLVfsDriver;

public class BuiltinVfsDriverProvider
        implements IVfsDriverProvider {

    @Override
    public Collection<? extends IVfsDriver> getDrivers() {
        List<IVfsDriver> drivers = new ArrayList<>();
        drivers.add(PojfVfsDriver.getInstance());
        drivers.add(URLVfsDriver.getInstance());
        // drivers.add(ApacheVfsDriver.getInstance());
        drivers.add(PseudoVfsDriver.getInstance());
        drivers.add(MemoryVfsDriver.getInstance());
        return drivers;
    }

}

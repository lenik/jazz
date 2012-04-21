package net.bodz.bas.vfs;

import net.bodz.bas.context.ContextLocalGroup;

public interface SystemColos
        extends ContextLocalGroup {

    CurrentDirectoryColo cwd = CurrentDirectoryColo.getInstance();

}

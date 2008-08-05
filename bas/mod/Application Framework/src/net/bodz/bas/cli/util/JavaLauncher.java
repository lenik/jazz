package net.bodz.bas.cli.util;

import java.lang.reflect.Method;

import net.bodz.bas.cli.CLIConfig;
import net.bodz.bas.cli.RunInfo;
import net.bodz.bas.cli._RunInfo;

@RunInfo(lib = "bodz_bas")
public abstract class JavaLauncher {

    private Method  mainf;

    @SuppressWarnings("unused")
    private boolean libLoaded;

    public JavaLauncher() {
        libLoaded = "1".equals(System
                .getProperty(CLIConfig.PROPERTY_LIB_LOADED));
    }

    protected abstract String getMainClassName();

    public void launch(String[] args) throws Exception {
        if (mainf == null)
            load();
        mainf.invoke(null, (Object) args);
    }

    protected void load() throws Exception {
        _RunInfo runInfo = _RunInfo.parse(getClass(), true);

        runInfo.loadBoot();

        // if (!libLoaded)
        runInfo.loadLibraries();

        String mainClassName = getMainClassName();
        Class<?> mainClass = Class.forName(mainClassName);
        mainf = mainClass.getMethod("main", String[].class);

        runInfo.loadDelayed();
    }

}

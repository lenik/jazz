package net.bodz.bas.program.meta;

import net.bodz.bas.meta.codegen.IEtcFilesEditor;
import net.bodz.bas.meta.codegen.IEtcFilesInstaller;

public class ProgramEtcFiles
        implements IEtcFilesInstaller {

    @Override
    public void install(Class<?> clazz, IEtcFilesEditor editor) {
        ProgramName _programName = clazz.getAnnotation(ProgramName.class);
        String name = _programName.value();
        String runner = "main";
        String fqcn = clazz.getCanonicalName();
        String line = String.format("%s = %s %s", name, runner, fqcn);
        editor.addLine("META-INF/programs", line);
    }

}

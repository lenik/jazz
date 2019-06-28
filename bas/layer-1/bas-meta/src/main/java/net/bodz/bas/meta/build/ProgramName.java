package net.bodz.bas.meta.build;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.meta.codegen.IEtcFilesEditor;
import net.bodz.bas.meta.codegen.IEtcFilesInstaller;
import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType(publishDir = "", etcFiles = ProgramName.ProgramEtcFiles.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ProgramName {

    String value();

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

}

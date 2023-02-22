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

    String mainSymsResource = "META-INF/programs";

    String value();

    public static class fn {

        public static String getValue(Class<?> type) {
            return getValue(type, null);
        }

        public static String getValue(Class<?> type, Boolean toUpperCase) {
            ProgramName pn = type.getAnnotation(ProgramName.class);
            if (pn != null)
                return pn.value();
            String name = type.getSimpleName();
            if (toUpperCase != null)
                name = toUpperCase ? name.toUpperCase() : name.toLowerCase();
            return name;
        }

    }

    public class ProgramEtcFiles
            implements
                IEtcFilesInstaller {

        @Override
        public void install(Class<?> clazz, IEtcFilesEditor editor) {
            ProgramName aProgramName = clazz.getAnnotation(ProgramName.class);
            String ns = "main";
            String sym = aProgramName.value();
            String fqcn = clazz.getCanonicalName();
            String line = String.format("%s %s = %s", ns, sym, fqcn);
            editor.addLine(mainSymsResource, line);
        }

    }

}

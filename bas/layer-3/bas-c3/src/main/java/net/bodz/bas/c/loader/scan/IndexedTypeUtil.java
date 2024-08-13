package net.bodz.bas.c.loader.scan;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;

import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.io.res.ContainerItems;
import net.bodz.bas.io.res.IContainerItem;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.pojo.Pair;

public class IndexedTypeUtil {

    public static ExcludeReason check(Class<?> clazz, Class<?> baseClass) {
        int modifiers = clazz.getModifiers();

        IndexedType aIndexedType = baseClass.getAnnotation(IndexedType.class);

        if (aIndexedType == null)
            return ExcludeReason.NotIndexed;

        if (! clazz.isInterface() && ! aIndexedType.includeClass())
            return ExcludeReason.NoNormalClass;

        if (Modifier.isAbstract(modifiers) && ! aIndexedType.includeAbstract())
            return ExcludeReason.NoAbstract;

        if (! Modifier.isPublic(modifiers) && ! aIndexedType.includeNonPublic())
            return ExcludeReason.NotPublic;

        if (clazz.isAnnotation() && ! aIndexedType.includeAnnotation())
            return ExcludeReason.NoAnnotation;

        return ExcludeReason.Accepted;
    }

    public static Pair<ExcludeReason, File> checkDir(Class<?> clazz) {
        MavenPomDir pomDir = MavenPomDir.fromClass(clazz);
        if (pomDir == null)
            // logger.debug("Not belongs to maven project. Maybe in the jar: " + clazz);
            return Pair.of(ExcludeReason.NotMaven, null);

        final File resourceDir = pomDir.getResourceDir(clazz);
        if (resourceDir == null)
            // logger.debug("Can't determine the corresponding resource dir for " + clazz);
            return Pair.of(ExcludeReason.NoResourceDir, null);

        return Pair.of(ExcludeReason.Accepted, resourceDir);
    }

    public static ExcludeReason checkScan(Class<?> clazz, IScanOptions scanOptions)
            throws IOException {
        if (clazz.getEnclosingClass() != null)
            return ExcludeReason.NoInnerClass;

        IContainerItem item = ContainerItems.findClass(clazz);

        File container = item.getContainerFile();
        if (item.isLocalFileItem()) {
            if (! scanOptions.acceptDirPath(container))
                return ExcludeReason.BadPath;
            if (! scanOptions.acceptLocalEntry(container, item.getItemFile()))
                return ExcludeReason.BadEntry;
        }
        if (item.isJarItem()) {
            if (! scanOptions.acceptJarPath(container))
                return ExcludeReason.BadPath;
            if (! scanOptions.acceptZipEntry(item.getItemEntry()))
                return ExcludeReason.BadEntry;
        }

        return ExcludeReason.Accepted;
    }

}

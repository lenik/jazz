package net.bodz.bas.site.file;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.http.ctx.IAnchor;

public abstract class AbstractFilePathMapping
        implements IFilePathMapping {

    @Override
    public File getLocalDir(HttpServletRequest req, Class<?> clazz) {
        return getLocalDir(req, getClassPathToken(clazz));
    }

    @Override
    public File getLocalDir(HttpServletRequest req, String schema) {
        File schemaDir = new File(getLocalRoot(req), schema);
        schemaDir.mkdirs();
        return schemaDir;
    }

    @Override
    public File getLocalFile(HttpServletRequest req, Class<?> clazz, String path) {
        File schemaDir = getLocalDir(req, clazz);
        return new File(schemaDir, path);
    }

    @Override
    public File getLocalFile(HttpServletRequest req, String schema, String path) {
        File schemaDir = getLocalDir(req, schema);
        return new File(schemaDir, path);
    }

    @Override
    public IAnchor getAnchor(Class<?> clazz) {
        return getAnchor(getClassPathToken(clazz));
    }

    @Override
    public IAnchor getAnchor(String schema) {
        IAnchor root = getRootAnchor();
        return root.join(schema);
    }

    protected String getClassPathToken(Class<?> clazz) {
        String name = clazz.getSimpleName();
        name = Strings.lcfirst(name);
        return name;
    }

}

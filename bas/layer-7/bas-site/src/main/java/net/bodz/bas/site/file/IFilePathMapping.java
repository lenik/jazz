package net.bodz.bas.site.file;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.http.ctx.IAnchor;

public interface IFilePathMapping {

    File getLocalRoot(HttpServletRequest req);

    File getLocalDir(HttpServletRequest req, Class<?> clazz);

    File getLocalDir(HttpServletRequest req, String schema);

    File getLocalFile(HttpServletRequest req, Class<?> clazz, String path);

    File getLocalFile(HttpServletRequest req, String schema, String path);

    String getServletPath();

    IAnchor getRootAnchor();

    IAnchor getAnchor(Class<?> clazz);

    IAnchor getAnchor(String schema);

}
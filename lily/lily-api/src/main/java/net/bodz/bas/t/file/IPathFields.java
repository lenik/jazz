package net.bodz.bas.t.file;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.meta.decl.Nullable;

public interface IPathFields
        extends IJsonForm,
                IXmlForm {

    String getPath();

    void setPath(String path);

    void setPath(String dirName, String fileName);

    void setPath(String dirName, String name, String extension);

    String[] toArray();

    int length();

    String getField(int index);

    void setField(int index, String field);

    String getDirName();

    void setDirName(String dirName);

    String getFileName();

    void setFileName(String fileName);

    String getName();

    void setName(String name);

    @Nullable
    String getExtension();

    default String getExtension(String fallback) {
        String extension = getExtension();
        return extension != null ? extension : fallback;
    }

    void setExtension(String extension);

    String K_PATH = "path";
    String K_DIR_NAME = "dirName";
    String K_FILE_NAME = "fileName";
    String K_NAME = "name";
    String K_EXTENSION = "extension";

    @Override
    default void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        String dirName = getDirName();
        String fileName = getFileName();
        if (dirName == null || fileName == null) {
            out.entryNotNull(K_DIR_NAME, dirName);
            String name = getName();
            String extension = getExtension();
            if (name == null || extension == null) {
                out.entryNotNull(K_NAME, name);
                out.entryNotNull(K_EXTENSION, extension);
            } else {
                out.entry(K_FILE_NAME, fileName);
            }
        } else {
            out.entry(K_PATH, getPath());
        }
    }

    @Override
    default void writeObject(IXmlOutput out)
            throws XMLStreamException {
        String dirName = getDirName();
        String fileName = getFileName();
        if (dirName == null || fileName == null) {
            out.attributeNotNull(K_DIR_NAME, dirName);
            String name = getName();
            String extension = getExtension();
            if (name == null || extension == null) {
                out.attributeNotNull(K_NAME, name);
                out.attributeNotNull(K_EXTENSION, extension);
            } else {
                out.attributeNotNull(K_FILE_NAME, fileName);
            }
        } else {
            out.attributeNotNull(K_PATH, getPath());
        }
    }

}

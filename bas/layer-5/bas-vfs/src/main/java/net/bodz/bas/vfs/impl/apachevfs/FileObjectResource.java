package net.bodz.bas.vfs.impl.apachevfs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.OpenOption;

import org.apache.commons.vfs.FileContent;
import org.apache.commons.vfs.FileObject;

import net.bodz.bas.c.java.nio.CommonOpenConfig;
import net.bodz.bas.io.resource.AbstractInputOutputStreamResource;

public class FileObjectResource
        extends AbstractInputOutputStreamResource {

    private final FileObject fileObject;

    public FileObjectResource(FileObject fileObject) {
        if (fileObject == null)
            throw new NullPointerException("fileObject");
        this.fileObject = fileObject;
    }

    @Override
    protected InputStream _newInputStream(OpenOption... options)
            throws IOException {
        FileContent content = fileObject.getContent();
        checkOpen(content);
        InputStream in = content.getInputStream();
        return in;
    }

    @Override
    protected OutputStream _newOutputStream(OpenOption... options)
            throws IOException {
        FileContent content = fileObject.getContent();
        checkOpen(content);

        CommonOpenConfig config = CommonOpenConfig.parse(options);

        OutputStream out = content.getOutputStream(config.isAppend());
        return out;
    }

    void checkOpen(FileContent fileContent) {
        if (fileContent.isOpen())
            throw new IllegalStateException("File is already opened: " + fileObject.getName());
    }

}
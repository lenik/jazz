package net.bodz.bas.make.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import net.bodz.bas.io.res.ResFn;
import net.bodz.bas.make.IDataRef;
import net.bodz.bas.meta.decl.NotNull;

public class FileCache
        implements IDataRef<byte[]> {

    protected final Path file;

    public FileCache(@NotNull Path file) {
        this.file = file;
    }

    @NotNull
    @Override
    public Class<byte[]> getDataType() {
        return byte[].class;
    }

    @Override
    public byte[] getData() {
        try {
            return ResFn.path(file).read().read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setData(byte[] data) {
        try {
            ResFn.path(file).write().write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean exists() {
        return Files.exists(file);
    }

    @Override
    public ZonedDateTime getLastModified() {
        FileTime time;
        try {
            time = Files.getLastModifiedTime(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LocalDateTime localDateTime = LocalDateTime.ofInstant(time.toInstant(), ZoneId.systemDefault());
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return zonedDateTime;
    }

}

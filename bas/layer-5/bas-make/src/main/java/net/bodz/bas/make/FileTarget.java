package net.bodz.bas.make;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class FileTarget
        implements IMakeTarget {

    private final Path file;

    public FileTarget(Path file) {
        if (file == null)
            throw new NullPointerException("file");
        this.file = file;
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

    @Override
    public String getDisplayName() {
        return file.getFileName().toString();
    }

    @Override
    public boolean exists() {
        return Files.exists(file);
    }

}

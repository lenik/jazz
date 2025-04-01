package net.bodz.bas.c.java.nio.file;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathCommands {

    static final List<FileSystem> fileSystems = new ArrayList<>();

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            for (FileSystem fs : fileSystems)
                try {
                    fs.close();
                } catch (IOException e) {
                    //
                }
            fileSystems.clear();
        }));
    }

    public static Path open(URI uri)
            throws IOException {
        switch (uri.getScheme()) {
            case "jar":
            case "zip":
                for (FileSystemProvider provider : FileSystemProvider.installedProviders()) {
                    if (provider.getScheme().equalsIgnoreCase("jar")) {
                        try {
                            provider.getFileSystem(uri);
                        } catch (FileSystemNotFoundException e) {
                            // in this case we need to initialize it first:
                            FileSystem fileSystem = provider.newFileSystem(uri, Collections.emptyMap());
                            fileSystems.add(fileSystem);
                        }
                    }
                }
                break;
        }
        return Paths.get(uri);
    }

}
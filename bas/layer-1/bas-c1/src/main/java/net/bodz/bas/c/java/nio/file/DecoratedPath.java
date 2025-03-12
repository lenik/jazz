package net.bodz.bas.c.java.nio.file;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.model.AbstractDecorator;

public class DecoratedPath
        extends AbstractDecorator<Path>
        implements Path {

    private static final long serialVersionUID = 1L;

    public DecoratedPath(Path _orig) {
        super(_orig);
    }


    @NotNull
    @Override
    public FileSystem getFileSystem() {
        return getWrapped().getFileSystem();
    }

    @Override
    public boolean isAbsolute() {
        return getWrapped().isAbsolute();
    }

    @Override
    public Path getRoot() {
        return getWrapped().getRoot();
    }

    @Override
    public Path getFileName() {
        return getWrapped().getFileName();
    }

    @Override
    public Path getParent() {
        return getWrapped().getParent();
    }

    @Override
    public int getNameCount() {
        return getWrapped().getNameCount();
    }

    @NotNull
    @Override
    public Path getName(int index) {
        return getWrapped().getName(index);
    }

    @NotNull
    @Override
    public Path subpath(int beginIndex, int endIndex) {
        return getWrapped().subpath(beginIndex, endIndex);
    }

    @Override
    public boolean startsWith(@NotNull Path other) {
        return getWrapped().startsWith(other);
    }

    @Override
    public boolean startsWith(@NotNull String other) {
        return getWrapped().startsWith(other);
    }

    @Override
    public boolean endsWith(@NotNull Path other) {
        return getWrapped().endsWith(other);
    }

    @Override
    public boolean endsWith(@NotNull String other) {
        return getWrapped().endsWith(other);
    }

    @NotNull
    @Override
    public Path normalize() {
        return getWrapped().normalize();
    }

    @NotNull
    @Override
    public Path resolve(@NotNull Path other) {
        return getWrapped().resolve(other);
    }

    @NotNull
    @Override
    public Path resolve(@NotNull String other) {
        return getWrapped().resolve(other);
    }

    @NotNull
    @Override
    public Path resolveSibling(@NotNull Path other) {
        return getWrapped().resolveSibling(other);
    }

    @NotNull
    @Override
    public Path resolveSibling(@NotNull String other) {
        return getWrapped().resolveSibling(other);
    }

    @NotNull
    @Override
    public Path relativize(@NotNull Path other) {
        return getWrapped().relativize(other);
    }

    @NotNull
    @Override
    public URI toUri() {
        return getWrapped().toUri();
    }

    @NotNull
    @Override
    public Path toAbsolutePath() {
        return getWrapped().toAbsolutePath();
    }

    @NotNull
    @Override
    public Path toRealPath(@NotNull LinkOption... options)
            throws IOException {
        return getWrapped().toRealPath(options);
    }

    @NotNull
    @Override
    public File toFile() {
        return getWrapped().toFile();
    }

    @NotNull
    @Override
    public WatchKey register(@NotNull WatchService watcher, @NotNull WatchEvent.Kind<?>[] events, @NotNull WatchEvent.Modifier... modifiers)
            throws IOException {
        return getWrapped().register(watcher, events, modifiers);
    }

    @NotNull
    @Override
    public WatchKey register(@NotNull WatchService watcher, @NotNull WatchEvent.Kind<?>... events)
            throws IOException {
        return getWrapped().register(watcher, events);
    }

    @NotNull
    @Override
    public Iterator<Path> iterator() {
        return getWrapped().iterator();
    }

    @Override
    public int compareTo(@NotNull Path other) {
        return getWrapped().compareTo(other);
    }

    @Override
    public boolean equals(Object other) {
        return getWrapped().equals(other);
    }

    @Override
    public int hashCode() {
        return getWrapped().hashCode();
    }

    @NotNull
    @Override
    public String toString() {
        return getWrapped().toString();
    }

    @Override
    public void forEach(Consumer<? super Path> action) {
        getWrapped().forEach(action);
    }

    @Override
    public Spliterator<Path> spliterator() {
        return getWrapped().spliterator();
    }
}

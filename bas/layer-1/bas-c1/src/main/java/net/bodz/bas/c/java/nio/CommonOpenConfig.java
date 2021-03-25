package net.bodz.bas.c.java.nio;

import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class CommonOpenConfig {

    boolean read;
    boolean write;
    boolean append;
    boolean truncateExisting;
    boolean create;
    boolean createNew;
    boolean deleteOnClose;
    boolean sparse;
    boolean sync;
    boolean dsync;

    boolean nofollowLinks;

    boolean createParents;

    static Map<Class<?>, IOpenConfigCollector<?>> collectorMap;
    static {
        collectorMap = new HashMap<Class<?>, IOpenConfigCollector<?>>();
        collectorMap.put(StandardOpenOption.class, new StandardOpenOptionCollector());
        collectorMap.put(LinkOption.class, new LinkOptionCollector());
        collectorMap.put(CreateOption.class, new InstallOptionCollector());
    }

    public static CommonOpenConfig parse(OpenOption... options) {
        CommonOpenConfig config = new CommonOpenConfig();
        config.receive(options);
        return config;
    }

    public void receive(OpenOption... options) {
        for (OpenOption option : options) {
            Class<? extends OpenOption> optionClass = option.getClass();
            @SuppressWarnings("unchecked")
            IOpenConfigCollector<Object> collector = (IOpenConfigCollector<Object>) collectorMap.get(optionClass);
            collector.collect(this, option);
        }
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isWrite() {
        return write;
    }

    public void setWrite(boolean write) {
        this.write = write;
    }

    public boolean isAppend() {
        return append;
    }

    public void setAppend(boolean append) {
        this.append = append;
    }

    public boolean isTruncateExisting() {
        return truncateExisting;
    }

    public void setTruncateExisting(boolean truncateExisting) {
        this.truncateExisting = truncateExisting;
    }

    public boolean isCreate() {
        return create;
    }

    public void setCreate(boolean create) {
        this.create = create;
    }

    public boolean isCreateNew() {
        return createNew;
    }

    public void setCreateNew(boolean createNew) {
        this.createNew = createNew;
    }

    public boolean isDeleteOnClose() {
        return deleteOnClose;
    }

    public void setDeleteOnClose(boolean deleteOnClose) {
        this.deleteOnClose = deleteOnClose;
    }

    public boolean isSparse() {
        return sparse;
    }

    public void setSparse(boolean sparse) {
        this.sparse = sparse;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public boolean isDsync() {
        return dsync;
    }

    public void setDsync(boolean dsync) {
        this.dsync = dsync;
    }

    public boolean isNofollowLinks() {
        return nofollowLinks;
    }

    public void setNofollowLinks(boolean nofollowLinks) {
        this.nofollowLinks = nofollowLinks;
    }

    public boolean isCreateParents() {
        return createParents;
    }

    public void setCreateParents(boolean createParents) {
        this.createParents = createParents;
    }

}

interface IOpenConfigCollector<T> {

    void collect(CommonOpenConfig config, T src);

}

class StandardOpenOptionCollector
        implements IOpenConfigCollector<StandardOpenOption> {

    @Override
    public void collect(CommonOpenConfig config, StandardOpenOption src) {
        switch (src) {
        case APPEND:
            config.append = true;
            break;
        case CREATE:
            config.create = true;
            break;
        case CREATE_NEW:
            config.createNew = true;
            break;
        case DELETE_ON_CLOSE:
            config.deleteOnClose = true;
            break;
        case DSYNC:
            config.dsync = true;
            break;
        case READ:
            config.read = true;
            break;
        case SPARSE:
            config.sparse = true;
            break;
        case SYNC:
            config.sync = true;
            break;
        case TRUNCATE_EXISTING:
            config.truncateExisting = true;
            break;
        case WRITE:
            config.write = true;
            break;
        default:
        }
    }

}

class LinkOptionCollector
        implements IOpenConfigCollector<LinkOption> {

    @Override
    public void collect(CommonOpenConfig config, LinkOption src) {
        switch (src) {
        case NOFOLLOW_LINKS:
            config.nofollowLinks = true;
            break;
        default:
        }
    }

}

class InstallOptionCollector
        implements IOpenConfigCollector<CreateOption> {

    @Override
    public void collect(CommonOpenConfig config, CreateOption src) {
        switch (src) {
        case CREATE_PARENTS:
            config.createParents = true;
            break;
        default:
        }
    }

}
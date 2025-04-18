package net.bodz.bas.net.io;

import java.nio.channels.SelectableChannel;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.record.BasicColumnType;
import net.bodz.bas.t.record.BasicRecordType;
import net.bodz.bas.t.record.ColumnChangeSupport;
import net.bodz.bas.t.record.IColumnChangeListener;
import net.bodz.bas.t.record.IColumnChangeSource;
import net.bodz.bas.t.record.IColumnChangeSupport;
import net.bodz.bas.t.record.IColumnType;
import net.bodz.bas.t.record.IKeyed;
import net.bodz.bas.t.record.IRecordType;

public class ChannelLink
        implements IKeyed<SelectableChannel>,
                   IColumnChangeSource {

    final SelectableChannel channel;
    String description;

    ISocketAccepter accepter;
    ISocketConnector connector;
    ISocketReader reader;
    ISocketWriter writer;

    private final IColumnChangeSupport ccs = new ColumnChangeSupport(this);

    public ChannelLink(@NotNull SelectableChannel channel) {
        this.channel = channel;
    }

    public ChannelLink(@NotNull SelectableChannel channel, String description) {
        this.channel = channel;
        this.description = description;
    }

    @NotNull
    @Override
    public SelectableChannel getKey() {
        return channel;
    }

    @NotNull
    public SelectableChannel getChannel() {
        return channel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ISocketAccepter getAccepter() {
        return accepter;
    }

    public void setAccepter(ISocketAccepter accepter) {
        ISocketAccepter old = this.accepter;
        this.accepter = accepter;
        ccs.fireColumnChange(ACCEPTER, old, accepter);
    }

    public ISocketConnector getConnector() {
        return connector;
    }

    public void setConnector(ISocketConnector connector) {
        ISocketConnector old = this.connector;
        this.connector = connector;
        ccs.fireColumnChange(CONNECTOR, old, connector);
    }

    public ISocketReader getReader() {
        return reader;
    }

    public void setReader(ISocketReader reader) {
        ISocketReader old = this.reader;
        this.reader = reader;
        ccs.fireColumnChange(READER, old, reader);
    }

    public ISocketWriter getWriter() {
        return writer;
    }

    public void setWriter(ISocketWriter writer) {
        ISocketWriter old = this.writer;
        this.writer = writer;
        ccs.fireColumnChange(WRITER, old, writer);
    }

    public void addAccepter(@NotNull ISocketAccepter accepter) {
        if (this.accepter != null)
            throw new IllegalStateException("too many accepter");
        setAccepter(accepter);
    }

    public void addConnector(@NotNull ISocketConnector connector) {
        if (this.connector != null)
            throw new IllegalStateException("too many connector");
        setConnector(connector);
    }

    public void addReader(@NotNull ISocketReader reader) {
        if (this.reader != null)
            throw new IllegalStateException("too many reader");
        setReader(reader);
    }

    public void addWriter(@NotNull ISocketWriter writer) {
        if (this.writer != null)
            throw new IllegalStateException("too many writer");
        setWriter(writer);
    }

    public void removeAccepter(ISocketAccepter accepter) {
        if (this.accepter == accepter)
            setAccepter(null);
    }

    public void removeConnector(ISocketConnector connector) {
        if (this.connector == connector)
            setConnector(null);
    }

    public void removeReader(ISocketReader reader) {
        if (this.reader == reader)
            setReader(null);
    }

    public void removeWriter(ISocketWriter writer) {
        if (this.writer == writer)
            setWriter(null);
    }

    @Override
    public void addColumnChangeListener(@NotNull IColumnChangeListener listener) {
        ccs.addColumnChangeListener(listener);
    }

    @Override
    public void addColumnChangeListener(IColumnType<?, ?> column, @NotNull IColumnChangeListener listener) {
        ccs.addColumnChangeListener(column, listener);
    }

    @Override
    public void removeColumnChangeListener(@NotNull IColumnChangeListener listener) {
        ccs.removeColumnChangeListener(listener);
    }

    @Override
    public void removeColumnChangeListener(IColumnType<?, ?> column, @NotNull IColumnChangeListener listener) {
        ccs.removeColumnChangeListener(column, listener);
    }

    public static final IColumnType<ChannelLink, SelectableChannel> CHANNEL = BasicColumnType.ofProperty(ChannelLink.class, "channel");
    public static final IColumnType<ChannelLink, ISocketAccepter> ACCEPTER = BasicColumnType.ofProperty(ChannelLink.class, "accepter");
    public static final IColumnType<ChannelLink, ISocketConnector> CONNECTOR = BasicColumnType.ofProperty(ChannelLink.class, "connector");
    public static final IColumnType<ChannelLink, ISocketReader> READER = BasicColumnType.ofProperty(ChannelLink.class, "reader");
    public static final IColumnType<ChannelLink, ISocketWriter> WRITER = BasicColumnType.ofProperty(ChannelLink.class, "writer");

    public static final IRecordType<ChannelLink> TYPE = BasicRecordType.ofList(ACCEPTER, CONNECTOR, READER, WRITER);

}

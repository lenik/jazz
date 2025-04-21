package net.bodz.bas.c.java.nio.channels;

import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

public class SelectableChannels {

    public static SelectionKey registerOr(SelectableChannel channel, Selector sel, int opsBitwiseOr)
            throws ClosedChannelException {
        return registerOr(channel, sel, opsBitwiseOr, null);
    }

    public static SelectionKey registerOr(SelectableChannel channel, Selector sel, int opsBitwiseOr, Object att)
            throws ClosedChannelException {
        SelectionKey key = channel.keyFor(sel);
        if (key != null) {
            int ops = key.interestOps();
            key.interestOps(ops | opsBitwiseOr);
            return key;
        } else {
            return channel.register(sel, opsBitwiseOr, att);
        }
    }

}

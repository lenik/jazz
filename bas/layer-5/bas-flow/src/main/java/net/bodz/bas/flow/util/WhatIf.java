package net.bodz.bas.flow.util;

import java.io.IOException;
import java.util.Collection;

import net.bodz.bas.flow.IOutPort;
import net.bodz.bas.flow.IReceiver;
import net.bodz.bas.flow.units.SISOUnit;
import net.bodz.bas.flow.units.builtin.util.GrabberUnit;

public class WhatIf {

    public static Collection<Object> send(IReceiver sendDst, IOutPort grabPort, boolean isolate, Object in,
            boolean flush)
            throws IOException {
        IReceiver grabDst0 = grabPort.getDst();
        if (isolate)
            grabPort.setDst(null);
        try {
            GrabberUnit grabber = GrabberUnit.insert(grabPort);
            Collection<Object> grabbed;
            try {
                sendDst.recv(in);
                if (flush)
                    sendDst.flush();
            } finally {
                if (flush)
                    grabPort.flush();
                grabbed = grabber.getBuffer();
            }
            return grabbed;
        } finally {
            grabPort.setDst(grabDst0);
        }
    }

    public static Collection<Object> send(IReceiver sendDst, IOutPort grabPort, Object in, boolean flush)
            throws IOException {
        return send(sendDst, grabPort, false, in, flush);
    }

    public static Collection<Object> send(IReceiver sendDst, IOutPort grabPort, boolean isolate, Object in)
            throws IOException {
        return send(sendDst, grabPort, isolate, in, false);
    }

    public static Collection<Object> send(IReceiver sendDst, IOutPort grabPort, Object in)
            throws IOException {
        return send(sendDst, grabPort, false, in, false);
    }

    public static Collection<Object> send(SISOUnit unit, boolean isolate, Object in, boolean flush)
            throws IOException {
        return send(unit, unit, isolate, in, flush);
    }

    public static Collection<Object> send(SISOUnit unit, boolean isolate, Object in)
            throws IOException {
        return send(unit, unit, isolate, in, false);
    }

    public static Collection<Object> send(SISOUnit unit, Object in, boolean flush)
            throws IOException {
        return send(unit, unit, false, in, flush);
    }

    public static Collection<Object> send(SISOUnit unit, Object in)
            throws IOException {
        return send(unit, unit, false, in, false);
    }

    public static Collection<Object> clearSend(SISOUnit unit, boolean isolated, Object in, boolean flush)
            throws IOException {
        unit.reset();
        return send(unit, unit, isolated, in, flush);
    }

    public static Collection<Object> clearSend(SISOUnit unit, boolean isolated, Object in)
            throws IOException {
        return clearSend(unit, isolated, in, false);
    }

    public static Collection<Object> clearSend(SISOUnit unit, Object in, boolean flush)
            throws IOException {
        return clearSend(unit, false, in, flush);
    }

    public static Collection<Object> clearSend(SISOUnit unit, Object in)
            throws IOException {
        unit.reset();
        return clearSend(unit, false, in, false);
    }

}

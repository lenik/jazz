package net.bodz.bas.comm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.SerialPort;

public class SerialSupport {

    String portName;

    SerialPort port;
    public final InputStream in;
    public final OutputStream out;

    public SerialSupport(String portName, int baudRate)
            throws IOException {
        try {
            port = SerialPorts.open(portName, baudRate);
        } catch (CommException e) {
            throw new RuntimeException("Failed to open " + portName, e);
        }
        in = port.getInputStream();
        out = port.getOutputStream();
    }

    public SerialSupport(int baudRate)
            throws IOException {
        this(firstPort(), baudRate);
    }

    static String firstPort() {
        for (String first : SerialPorts.list("USB"))
            return first;
        throw new RuntimeException("No available USB serial port.");
    }

    public void close() {
        port.close();
    }

    @Override
    protected void finalize()
            throws Throwable {
        close();
    }

}

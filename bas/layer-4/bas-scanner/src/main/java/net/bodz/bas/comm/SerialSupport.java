package net.bodz.bas.comm;

import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SerialSupport {

    String portName;

    SerialPort port;
    public final InputStream in;
    public final OutputStream out;

    public SerialSupport(int baudRate)
            throws IOException {
        for (String first : SerialPorts.list("USB")) {
            portName = first;
            break;
        }
        if (portName == null)
            throw new RuntimeException("No available USB serial port.");
        try {
            port = SerialPorts.open(portName, baudRate);
        } catch (CommException e) {
            throw new RuntimeException("Failed to open " + portName, e);
        }

        in = port.getInputStream();
        out = port.getOutputStream();
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

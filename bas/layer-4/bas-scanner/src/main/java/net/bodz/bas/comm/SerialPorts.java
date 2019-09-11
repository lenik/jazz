package net.bodz.bas.comm;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import net.bodz.bas.c.system.LibraryPath;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class SerialPorts {

    static final Logger logger = LoggerFactory.getLogger(SerialPorts.class);

    static {
        LibraryPath.getHacked().add("/usr/lib/jni");
    }

    public static List<String> list() {
        return list(null);
    }

    public static List<String> list(String pattern) {
        List<String> ports = new ArrayList<String>();
        Enumeration<?> portIdentifiers = CommPortIdentifier.getPortIdentifiers();
        while (portIdentifiers.hasMoreElements()) {
            CommPortIdentifier portId = (CommPortIdentifier) portIdentifiers.nextElement();
            String name = portId.getName();

            int portType = portId.getPortType();
            if (portType != CommPortIdentifier.PORT_SERIAL)
                continue;

            String currentOwner = portId.getCurrentOwner();

            logger.debugf("Port %s: type %d, owned by %s.", name, portType, currentOwner);
            if (pattern != null && !name.contains(pattern))
                continue;

            ports.add(name);
        }
        return ports;
    }

    public static SerialPort open(String portName, int baudRate)
            throws CommException {
        return open(portName, baudRate, 5000);
    }

    /**
     * Remember to close.
     *
     * @param timeout
     *            in ms.
     */
    public static SerialPort open(String portName, int baudRate, int timeout)
            throws CommException {
        try {
            CommPortIdentifier id = CommPortIdentifier.getPortIdentifier(portName);
            CommPort commPort = id.open(portName, timeout);
            if (!(commPort instanceof SerialPort))
                throw new IllegalArgumentException("Not a serial port");

            SerialPort port = (SerialPort) commPort;
            port.setSerialPortParams(baudRate, //
                    SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            return port;
        } catch (Exception e) {
            throw new CommException(e.getMessage(), e);
        }
    }

}

package net.bodz.bas.scanner.ztx965n;

import java.io.IOException;

import net.bodz.bas.io.BCharOut;

public class NetConf
        implements IRxParser {

    /** 读写器IP地址1 */
    byte[] netaddr = new byte[4];

    /** +16: 读写器端口 (big endian) */
    int port;

    /** 读写器掩码1 */
    byte[] netmask = new byte[4];

    /** 读写器地址网关1 */
    byte[] gateway = new byte[4];

    @Override
    public NetConf parse(RxPacket in)
            throws IOException {
        in.read(netaddr);
        port = in.readWord();
        in.read(netmask);
        in.read(gateway);
        return this;
    }

    public void send(TxPacket pkt)
            throws IOException {
        pkt.write(netaddr);
        // +16
        pkt.writeWord(port);
        pkt.write(netmask);
        pkt.write(gateway);
    }

    @Override
    public String toString() {
        BCharOut out = new BCharOut();
        out.println("netaddr: " + netCodec.encode(netaddr) + ":" + port + "/" + netCodec.encode(netmask));
        out.println("gateway: " + netCodec.encode(gateway));
        return out.toString();
    }

}

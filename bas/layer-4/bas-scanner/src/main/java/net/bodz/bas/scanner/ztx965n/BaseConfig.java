package net.bodz.bas.scanner.ztx965n;

import java.io.IOException;

import net.bodz.bas.io.BCharOut;

public class BaseConfig
        implements IRxParser {

    /** +0: 串口的通信速率,取值:00H~08H */
    int baudRate;

    /** +1: 发射功率值, 取值:20~30dbm。取值20~30。 */
    int emitPower;

    /** +2: 微波信号频率的起始点, 取值(缺省值为1): 1~63。 */
    int rfStart;

    /** +3: 发射微波信号频率的终止点,取值(缺省值为63): 1~63。 */
    int rfEnd;

    /** +4: 调制深度 */
    int modDepth;

    /** +5: 读写器工作方式:0-主动方式,1-命令方式 */
    int workMode;

    /** +6: 读写器的RS485地址:0和255为广播地址 */
    int rs485Addr;

    /** +7: 最多读卡数目 */
    int maxTags;

    /**
     * +8: 标 签 种 类 : 01H - ISO18000-6B , 02H - EPCC1 , 04H -EPCC1G2, 08H-EM4442。
     */
    int tagType;

    /**
     * +9: 读 卡 持 续 时 间 : 射 频 发 射 持 续 时 间 , 只 针 对 EM 标 签 有 效 ; 0 -10ms,1-20ms,2-30ms,3-40ms。
     */
    int keepReadTime;

    /** +10: 读卡次数M:收到上位机读卡命令,读写器执行M次此命令。 **/
    int repeatCount;

    /** +11: 1:使能蜂鸣器0:不使能蜂鸣器 */
    boolean beep;

    /** +12: 读写器IP地址1 */
    byte[] netaddr = new byte[4];

    /** +16: 读写器端口 (big endian) */
    int port;

    /** +18: 读写器掩码1 */
    byte[] netmask = new byte[4];

    /** +22: 读写器地址网关1 */
    byte[] gateway = new byte[4];

    /** +26: 读写器MAC1 */
    byte[] mac = new byte[6];

    @Override
    public BaseConfig parse(RxPacket in)
            throws IOException {
        baudRate = in.readByte();
        emitPower = in.readByte();
        rfStart = in.readByte();
        rfEnd = in.readByte();
        modDepth = in.readByte();
        workMode = in.readByte();
        rs485Addr = in.readByte();
        maxTags = in.readByte();
        tagType = in.readByte();
        keepReadTime = in.readByte();
        repeatCount = in.readByte();
        beep = in.readByte() != 0;
        in.read(netaddr);
        port = in.readWord();
        in.read(netmask);
        in.read(gateway);
        in.read(mac);
        return this;
    }

    public void send(TxPacket pkt)
            throws IOException {
        // +0
        pkt.writeByte(baudRate);
        pkt.writeByte(emitPower);
        pkt.writeByte(rfStart);
        pkt.writeByte(rfEnd);
        pkt.writeByte(modDepth);
        pkt.writeByte(workMode);
        pkt.writeByte(rs485Addr);
        pkt.writeByte(maxTags);
        // +8
        pkt.writeByte(tagType);
        pkt.writeByte(keepReadTime);
        pkt.writeByte(repeatCount);
        pkt.writeByte(beep ? 1 : 0);
        pkt.write(netaddr);
        // +16
        pkt.writeWord(port);
        pkt.write(netmask);
        pkt.write(gateway);
        pkt.write(mac);
    }

    @Override
    public String toString() {
        BCharOut out = new BCharOut();
        out.println("baud-rate: " + baudRate);
        out.println("emit-power: " + emitPower);
        out.println("rf-freq: " + rfStart + " to " + rfEnd);
        out.println("mod-depth: " + modDepth);
        out.println("work-mode: " + workMode);
        out.println("rs485: " + rs485Addr);
        out.println("max-tags: " + maxTags);
        out.println("tag-type: " + tagType);
        out.println("keep-read-time: " + keepReadTime);
        out.println("repeat-count: " + repeatCount);
        out.println("beep: " + beep);
        out.println("netaddr: " + netCodec.encode(netaddr) + ":" + port + "/" + netCodec.encode(netmask));
        out.println("gateway: " + netCodec.encode(gateway));
        out.println("mac: " + macCodec.encode(mac));
        return out.toString();
    }
}

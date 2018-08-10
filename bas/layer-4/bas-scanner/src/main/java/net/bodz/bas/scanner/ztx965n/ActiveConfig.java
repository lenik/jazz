package net.bodz.bas.scanner.ztx965n;

import java.io.IOException;

import net.bodz.bas.io.BCharOut;

public class ActiveConfig
        implements IRxParser {

    /** +0: 读标签模式:0-定时方式,1-触发方式 */
    int readTagMode;

    /** +1: 标签保留时间:单位:秒s。缺省值为1。 */
    int tagKeepTime;

    /**
     * +3: 0-10ms,1-20ms,2-30ms,3-50ms,4-100ms。
     *
     * 缺省值为2。每隔设定时间主动读取一次标签。
     */
    int tagReadInterval;

    /** +4: 标签保留数目:缺省值为1。已读取的标签ID在读写器内存中保留的数目. */
    int tagKeepCount;

    /** +6: 数据输出格式:0-简化格式,1-标准格式,2-XML格式。缺省值为0。 */
    int outputFormat;

    /** +7: 输出接口:0-RS232口,1-RS485口,2-RJ45口。缺省值为0。 3-Wiegand26 4- Wiegand34 */
    int outputInterface;

    /** +8: Wiegand输出脉冲宽度,缺省值为40。 */
    int wiegandPulseWidth;

    /** +9: Wiegand输出脉冲间隔,缺省值为200。 */
    int wiegandPulseInterval;

    /** +10: 设定输出卡号的起始位,取值0~4。缺省值为0。(Wiegand) **/
    int cardNumStart;

    /**
     * +11: 设定卡号在电子标签上的存放地址(缺省值为0):
     *
     * 0-标签本身ID号 1-用户自定义卡号(Wiegand)
     */
    int cardNumAddr;

    /**
     * +12: 通知间隔:单位秒s。缺省值为1。 每隔设定时间主动通知上位机一次。
     *
     * 缺省值为120秒,1~255。
     */
    int notifyInterval;

    /**
     * +13: 通知条件: 缺省值为1。
     *
     * 0-立即通知,1-定时通知,2-增加新标签,3-减少标签,4-标签数变化
     */
    int notifyCondition;

    /**
     * +14: 通知输出端,(长时间未读卡,给输出端发送0长度EPC号)
     *
     * 0—不使用 1—使用,时间由(”通知间隔”决定)
     */
    int notifyNop;

    /**
     * +15: 天线选择。1-ant1,2-ant2,4-ant4,8-ant8
     */
    int antenna;

    /**
     * +16: 设定触发模式(缺省值为0): 0-低电平 1-高电平
     */
    int triggerMode;

    /** +17: 上位机IP地址 */
    byte[] hostNetaddr = new byte[4];

    /** +21: 上位机端口 */
    int hostPort;

    /** +23 */
    byte[] reserved = new byte[6];

    /** +29: 0-不报警,1-报警。在定时和触发方式下是否检测报警。 */
    boolean checkAlarm;

    /** +30: */
    int reserved2;

    /** +31: 在自动状态是否控制继电器0-不控制 1-控制 */
    boolean controlRelay;

    @Override
    public ActiveConfig parse(RxPacket in)
            throws IOException {
        readTagMode = in.readByte();
        tagKeepTime = in.readWord();
        tagReadInterval = in.readByte();
        tagKeepCount = in.readWord();
        outputFormat = in.readByte();
        outputInterface = in.readByte();
        wiegandPulseWidth = in.readByte();
        wiegandPulseInterval = in.readByte();
        cardNumStart = in.readByte();
        cardNumAddr = in.readByte();
        notifyInterval = in.readByte();
        notifyCondition = in.readByte();
        notifyNop = in.readByte();
        antenna = in.readByte();
        triggerMode = in.readByte();
        in.read(hostNetaddr);
        hostPort = in.readWord();
        in.read(reserved);
        checkAlarm = in.readByte() != 0;
        reserved2 = in.readByte();
        controlRelay = in.readByte() != 0;
        return this;
    }

    public void send(TxPacket pkt)
            throws IOException {
        // +0
        pkt.writeByte(readTagMode);
        pkt.writeWord(tagKeepTime);
        pkt.writeByte(tagReadInterval);
        pkt.writeWord(tagKeepCount);
        pkt.writeByte(outputFormat);
        pkt.writeByte(outputInterface);
        // +8
        pkt.writeByte(wiegandPulseWidth);
        pkt.writeByte(wiegandPulseInterval);
        pkt.writeByte(cardNumStart);
        pkt.writeByte(cardNumAddr);
        pkt.writeByte(notifyInterval);
        pkt.writeByte(notifyCondition);
        pkt.writeByte(notifyNop);
        pkt.writeByte(antenna);
        // +16
        pkt.writeByte(triggerMode);
        pkt.write(hostNetaddr);
        pkt.writeWord(hostPort);
        pkt.write(reserved);
        pkt.writeByte(checkAlarm ? 1 : 0);
        pkt.writeByte(reserved2);
        pkt.writeByte(controlRelay ? 1 : 0);
    }

    @Override
    public String toString() {
        BCharOut out = new BCharOut();
        out.println("read-tag-mode: " + readTagMode);
        out.println("tag-keep: " + tagKeepTime + "ms / " + tagKeepCount + " tags");
        out.println("tag-read-interval: " + tagReadInterval + " ms");
        out.println("output-format: " + outputFormat);
        out.println("output-interface: " + outputInterface);
        out.println("wiegand-pulse: width " + wiegandPulseWidth + ", interval " + wiegandPulseInterval);
        out.println("card-num: start " + cardNumStart + ", addr " + cardNumAddr);
        out.println("notify: every " + notifyInterval + " ms when condition " + notifyCondition);
        out.println("notify-nop: " + notifyNop);
        out.println("antenna: " + antenna);
        out.println("trigger-mode: " + triggerMode);
        out.println("host: " + netCodec.encode(hostNetaddr) + ":" + hostPort);
        out.println("check-alarm: " + checkAlarm);
        out.println("control-relay: " + controlRelay);
        return out.toString();
    }

}

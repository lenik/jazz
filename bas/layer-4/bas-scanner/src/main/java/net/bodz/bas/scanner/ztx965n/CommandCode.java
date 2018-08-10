package net.bodz.bas.scanner.ztx965n;

import java.util.HashMap;
import java.util.Map;

public enum CommandCode {

    // ISO18000-6C commands

    /**
     * 根据掩码条件识别天线辐射场范围存在的可识别标签ID
     */
    Scan(0xEE),

    /**
     * 从读写器内存中取得已通过rfs_ListTagID命令列出的电子标签ID
     */
    ScanBuf(0xED),

    /**
     * 读取指定标签上指定数据区指定地址开始处的一块数据
     */
    ReadMemory(0xEC),

    /**
     * 向指定标签上指定数据区指定地址单元写入数据
     */
    WriteMemory(0xEB),

    /**
     * 把指定标签上指定数据区设置为写保护
     */
    LockMemory(0xEA),

    /**
     * 永久休眠标签
     */
    KillTag(0xE8),

    /**
     * 向标签EPC单元写入EPC数据
     */
    WriteEpc(0xE7),

    /**
     * 读取标签的EPC号以及标签上指定数据区指定地址开始处的一块数据。
     *
     * 数据块长度以字(16bits)为单位
     */
    ReadEpcAndData(0xE0),

    // Other commands.

    /**
     * 设定RS232口工作波特率
     *
     * <pre>
     * 04H 9600bps
     * 05H 19200bps
     * 06H 38400bps
     * 07H 57600bps
     * 08H 115200bps
     * </pre>
     */
    SetRs232BaudRate(0x01),

    /**
     * 取读写器的硬件、软件版本号
     */
    GetReaderInfo(0x02),

    /**
     * 设置读写器继电器状态
     */
    SetRelayState(0x03),

    /**
     * 设定读写器发射功率系数
     */
    SetEmitPower(0x04),

    /**
     * 设定读写器向外发射微波信号的频率通道号
     */
    SetFreqChannel(0x05),

    /**
     * 读取读写器基本工作参数
     */
    GetBaseWorkParam(0x06),

    /**
     * 设置读写器的串口波特率、发射频率、输出功率等基本工作参数
     */
    SetBaseWorkParam(0x09),

    /**
     * 选择从哪个天线收发信号
     */
    SelectAntenna(0x0A),

    /**
     * 获取读写器继电器状态
     */
    GetRelayState(0x0B),

    /**
     * 恢复读写器的出厂参数
     */
    FactoryReset(0x0D),

    /**
     * 复位读写器
     */
    ResetReader(0x0E),

    /**
     * 启动/停止读写器的自动工作模式
     */
    SetAutoMode(0x0F),

    /**
     * 清除内存
     */
    ClearMemory(0x10),

    /**
     * 设置读写器时间
     */
    SetReaderTime(0x11),

    /**
     * 获得读写器时间
     */
    GetReaderTime(0x12),

    /**
     * 获得与主动工作有关的参数
     */
    SetActiveWorkParam(0x13),

    /**
     * 设置与主动工作有关的参数
     */
    GetActiveWorkParam(0x14),

    /**
     * 设置过滤器参数
     */
    SetSelectFilter(0x15),

    /**
     * 获得过滤器参数
     */
    GetSelectFilter(0x16),

    /**
     * 设置读写器网络地址
     */
    SetNetConf(0x30),

    /**
     * 获得读写器网络地址
     */
    GetNetConf(0x31),

    /**
     * 设置读写器网络MAC
     */
    SetMAC(0x32),

    /**
     * 获得读写器网络MAC
     */
    GetMAC(0x33),

    /**
     * 获得标签纪录
     */
    GetTagRecords(0x57),

    ;

    int code;

    private CommandCode(int code) {
        this.code = code;
        Index.map.put(code, this);
    }

    public int getCode() {
        return code;
    }

    static class Index {
        static Map<Integer, CommandCode> map = new HashMap<>();
    }

    public static CommandCode forCode(int code) {
        return Index.map.get(code);
    }

}

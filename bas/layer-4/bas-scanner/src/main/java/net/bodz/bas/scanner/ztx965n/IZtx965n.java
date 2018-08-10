package net.bodz.bas.scanner.ztx965n;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public interface IZtx965n
        extends IDataTypes {

    /**
     * 根据掩码条件识别天线辐射场范围存在的可识别标签ID
     *
     * @param memBank
     *            0:password, 1:EPC, 2:TID, 3:user
     * @param start
     *            in bit
     * @param len
     *            in bit
     * @param mask
     *            mask bits. length = ceil(len(bit) / 8)
     */
    List<Words> scan(int memBank, int start, int len, byte[] mask)
            throws IOException, Ztx965nException;

    /**
     * 从读写器内存中取得已通过rfs_ListTagID命令列出的电子标签ID
     *
     * 第1个字节为开始序号no;第2个字节为标签数目m(<=8)
     */
    List<Words> scanBuf(int no, int count)
            throws IOException, Ztx965nException;

    /**
     * 读取指定标签上指定数据区指定地址开始处的一块数据
     *
     * @param start
     *            in bytes.
     * @param len
     *            in words.
     */
    byte[] readMemory(Words epc, int memBank, int start, int len, Password passwd)
            throws IOException, Ztx965nException;

    /**
     * 向指定标签上指定数据区指定地址单元写入数据
     *
     * @param start
     *            in bytes.
     * @param data
     *            length must be even.
     */
    void writeMemory(Words epc, int memBank, int start, byte[] data, Password passwd)
            throws IOException, Ztx965nException;

    /**
     * 把指定标签上指定数据区设置为写保护
     */
    void lockMemory(Words epc, int memBank, int lock, Password passwd)
            throws IOException, Ztx965nException;

    /**
     * 永久休眠标签
     */
    void killTag(Words epc, Password passwd)
            throws IOException, Ztx965nException;

    /**
     * 向标签EPC单元写入EPC数据
     */
    void writeEpc(Integer address, Words epc, Password passwd)
            throws IOException, Ztx965nException;

    /**
     * 读取标签的EPC号以及标签上指定数据区指定地址开始处的一块 数据。
     *
     * @param start
     *            in bytes.
     * @param len
     *            length must be even.
     */
    EpcAndData readEpcAndData(Integer address, int memBank, int start, int len)
            throws IOException, Ztx965nException;

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
    void setRs232BaudRate(int baudRate)
            throws IOException, Ztx965nException;

    /**
     * 取读写器的硬件、软件版本号
     */
    ReaderInfo getReaderInfo()
            throws IOException, Ztx965nException;

    /**
     * 设置读写器继电器状态
     */
    void setRelayState(boolean relay1, boolean relay2)
            throws IOException, Ztx965nException;

    /**
     * 设定读写器发射功率系数
     *
     * @param emitPower
     *            in 20-30 dBm. Default is 20 dBm.
     */
    void setEmitPower(int emitPower)
            throws IOException, Ztx965nException;

    /**
     * 设定读写器向外发射微波信号的频率通道号
     */
    void setFreqChannel(int fmin, int fmax)
            throws IOException, Ztx965nException;

    /**
     * 读取读写器基本工作参数
     */
    BaseConfig getBaseConfig()
            throws IOException, Ztx965nException;

    /**
     * 设置读写器基本工作参数
     *
     * 设置读写器的串口波特率、发射频率、输出功率等基本工作参数.
     */
    void setBaseConfig(BaseConfig param)
            throws IOException, Ztx965nException;

    /**
     * 选择从哪个天线收发信号
     */
    void selectAntenna(int antenna)
            throws IOException, Ztx965nException;

    /**
     * 获取读写器继电器状态
     */
    boolean[] getRelayState()
            throws IOException, Ztx965nException;

    /**
     * 恢复读写器的出厂参数
     */
    void factoryReset()
            throws IOException, Ztx965nException;

    /**
     * 复位读写器
     */
    void resetReader()
            throws IOException, Ztx965nException;

    /**
     * 启动/停止读写器的自动工作模式
     */
    void setAutoMode(boolean autoMode)
            throws IOException, Ztx965nException;

    /**
     * 清除内存
     */
    void clearMemory()
            throws IOException, Ztx965nException;

    /**
     * 获得读写器时间
     */
    Calendar getReaderTime()
            throws IOException, Ztx965nException;

    /**
     * 设置读写器时间
     */
    void setReaderTime(Calendar time)
            throws IOException, Ztx965nException;

    /**
     * 设置与主动工作有关的参数
     */
    ActiveConfig getActiveConfig()
            throws IOException, Ztx965nException;

    /**
     * 获得与主动工作有关的参数
     */
    void setActiveConfig(ActiveConfig config)
            throws IOException, Ztx965nException;

    /**
     * 获得过滤器参数
     */
    SelectFilter getSelectFilter()
            throws IOException, Ztx965nException;

    /**
     * 设置过滤器参数
     */
    void setSelectFilter(SelectFilter filter)
            throws IOException, Ztx965nException;

    /**
     * 获得读写器网络地址
     */
    NetConf getNetConf()
            throws IOException, Ztx965nException;

    /**
     * 设置读写器网络地址
     */
    void setNetConf(NetConf conf)
            throws IOException, Ztx965nException;

    /**
     * 获得读写器网络MAC
     */
    String getMAC()
            throws IOException, Ztx965nException;

    /**
     * 设置读写器网络MAC
     */
    void setMAC(String mac)
            throws IOException, Ztx965nException;

    /**
     * 获得标签纪录
     *
     * @param len
     *            equals or less than 5.
     */
    List<TagRecord> getTagRecords(int addr, int len)
            throws IOException, Ztx965nException;

}

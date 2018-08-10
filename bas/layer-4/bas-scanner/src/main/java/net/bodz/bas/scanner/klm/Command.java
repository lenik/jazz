package net.bodz.bas.scanner.klm;

public enum Command {

    /**
     * 获取读写器模块信息
     */
    GetReaderInfo(0x03),

    /**
     * 单次轮询指令
     */
    Scan(0x22),

    /**
     * 多次轮询指令
     */
    RepeatScan(0x27),

    /**
     * 停止多次轮询指令
     */
    RepeatCancel(0x28),

    /**
     * 设置 Select 参数指令
     */
    SetSelectFilter(0x0C),

    /**
     * 设置发送 Select 指令
     */
    SendSelect(0x12),

    /**
     * 读标签数据存储区
     */
    ReadMemory(0x39),

    /**
     * 写标签数据存储区
     */
    WriteMemory(0x49),

    /**
     * 锁定 Lock 标签数据存储区
     */
    LockMemory(0x82),

    /**
     * 灭活 Kill 标签
     */
    KillTag(0x65),

    /**
     * 获取 Query 参数
     */
    GetQueryParam(0x0D),

    /**
     * 设置 Query 参数
     */
    SetQueryParam(0x0E),

    /**
     * 设置工作地区
     */
    SetCountry(0x07),

    /**
     * 获取工作信道
     */
    GetChannel(0xAA),

    /**
     * 设置工作信道
     */
    SetChannel(0xAB),

    /**
     * 设置自动跳频
     */
    SetAutoFreqHop(0xAD),

    /**
     * 获取发射功率
     */
    GetEmitPower(0xB7),

    /**
     * 设置发射功率
     */
    SetEmitPower(0xB6),

    /**
     * 设置发射连续载波
     */
    SetContCarrierWave(0xB0),

    /**
     * 设置接收解调器参数
     */
    SetDemodParam(0xF0),

    /**
     * 获取接收解调器参数
     */
    GetDemodParam(0xF1),

    /**
     * 测试射频输入端阻塞信号
     */
    TestRFInputBlockness(0xF2),

    /**
     * 测试信道 RSSI
     */
    TestChannelRSSI(0xF3),

    /**
     * 控制 IO 端口
     */
    SetIOPort(0x1A),

    /**
     * NXP ReadProtec/Reset ReadProtect 指令
     */
    NXP_ReadProtect(0xE1),

    /**
     * NXP Change EAS 指令
     */
    NXP_ChangeEAS(0xE3),

    /**
     * NXP EAS-Alarm 指令
     */
    NXP_EASAlarm(0xE4), ;

    private Command(int code) {
        this.code = code;
    }

    int code;
}

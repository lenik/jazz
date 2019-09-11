package net.bodz.bas.scanner.ep6000;

import java.util.HashMap;
import java.util.Map;

public enum NumParam {

    // ISO18000-6C commands

    /**
     * 设备启动提示音是否启用
     */
    PowerOnSoundEnabled(0x0010),

    /**
     * 成功解码提示音是否启用
     */
    DecodeSoundEnabled(0x0011),

    /**
     * 蜂鸣器音量大小设置
     * <ul>
     * <li>0: 音量低
     * <li>3: 音量高
     * </ul>
     */
    SoundVolume(0x0012),

    /**
     * 成功解码音频率设置
     * <ul>
     * <li>1: 频率低
     * <li>2: 频率中
     * <li>3: 频率高
     * </ul>
     */
    DecodeSoundFreq(0x0013),

    /**
     * 错误警告音频率设置
     * <ul>
     * <li>1: 频率低
     * <li>2: 频率中
     * <li>3: 频率高
     * </ul>
     */
    ErrorWarnSoundFreq(0x0014),

    /**
     * 成功解码音时长设置
     * <ul>
     * <li>0 : 成功解码提示音长
     * <li>1: 成功解码提示音短
     * </ul>
     */
    DecodeSoundDuration(0x0015),

    /**
     * 反色条码选项
     * <ul>
     * <li>0: 仅解正常条码
     * <li>1: 仅解反色条码
     * <li>2: 正常条码和反色条码都解
     * </ul>
     */
    Reflection(0x0026),

    /**
     * Codabar 是否启用
     */
    CodabarEnabled(0x0027),

    /**
     * Codabar 起始/结束字符
     * <ul>
     * <li>0: 不发送 Codabar 起始/结束字符
     * <li>1: 发送 Codabar 起始/结束字符
     * </ul>
     */
    CodabarStartStopChar(0x0028),

    /**
     * Code39 是否启用
     */
    Code39Enabled(0x0029),

    /**
     * Code39 校验位设置
     * <ul>
     * <li>0: 校验关闭
     * <li>1: 校验打开不发送校验位
     * <li>2: 校验打开发送校验
     * </ul>
     */
    Code39Verified(0x002A),

    /**
     * Interleaved 2 of 5 是否启用
     */
    Itf25Enabled(0x002B),

    /**
     * Interleaved 2 of 5 校验位设置
     * <ul>
     * <li>0: 校验关闭
     * <li>1: 校验打开不发送校验位
     * <li>2: 校验打开发送校验
     * </ul>
     */
    Itf25Verified(0x002C),

    /**
     * Interleaved 2 of 5 解码长度设置
     * <ul>
     * <li>0: 任意长度(4-24 位)
     * <li>1: 固定 6 位长度
     * <li>2: 固定 8 位长度
     * <li>3: 固定 10 位长度
     * <li>4: 固定 12 位长度
     * <li>5: 固定 14 位长度
     * <li>6: 固定 16 位长度
     * <li>7: 固定 18 位长度
     * <li>8: 固定 20 位长度
     * <li>9: 固定 22 位长度
     * <li>A: 固定 24 位长度
     * </ul>
     */
    Itf25Length(0x002D),

    /**
     * Code93 是否启用
     */
    Code93Enabled(0x002E),

    /**
     * Code128 是否启用
     */
    Code128Enabled(0x002F),

    /**
     * ISBT128 是否启用
     */
    Isbt128Enabled(0x0030),

    /**
     * GS1-128 是否启用
     */
    Gs1_128Enabled(0x0031),

    /**
     * UPC-A 是否启用
     */
    UpcAEnabled(0x0032),

    /**
     * UPC-A 校验位设置
     * <ul>
     * <li>0: 不发送 UPC-A 校验位
     * <li>1: 发送 UPC-A 校验位
     * </ul>
     */
    UpcAVerified(0x0033),

    /**
     * UPC-E 是否启用
     */
    UpcEEnabled(0x0034),

    /**
     * UPC-E 校验位设置
     * <ul>
     * <li>0: 不发送 UPC-E 校验位
     * <li>1: 发送 UPC-E 校验位
     * </ul>
     */
    UpcEVerified(0x0035),

    /**
     * UPC/EAN/JAN 附加码设置
     * <ul>
     * <li>0: 忽略 UPC/EAN/JAN 附加码
     * <li>1: 解码 UPC/EAN/JAN 附加码
     * <li>2: 自适应 UPC/EAN/JAN 附加码
     * </ul>
     */
    UpcEanJanPlus(0x0038),

    /**
     * EAN/JAN-13 是否启用
     */
    Ean13Enabled(0x0039),

    /**
     * EAN/JAN-8 是否启用
     */
    Ean8Enabled(0x003A),

    /**
     * GS1 DataBar(RSS14)是否启用
     */
    Gs1DatabarRss14(0x003B),

    /**
     * GS1 DataBar Limited 是否启用
     */
    Gs1DatabarLimited(0x003C),

    /**
     * GS1 DataBar Expanded 是否启用
     */
    Gs1DatabarExpanded(0x003D),

    /**
     * PDF417 是否启用
     */
    Pdf417Enabled(0x003E),

    /**
     * QR 码是否启用
     */
    QrCordeEnabled(0x0040),

    /**
     * Micro QR 码是否启用
     */
    MicroQrCodeEnabled(0x0041),

    /**
     * DataMatrix 是否启用
     */
    DataMatrixEnabled(0x0043),

    /**
     * Aztec 是否启用
     */
    AztezEnabled(0x0044),

    /**
     * Code 32 是否启用
     */
    Code32Enabled(0x0046),

    /**
     * EAN13 转 ISBN
     */
    ConvertEan13ToIsbn(0x0047),

    /**
     * EAN13 转 ISSN
     */
    ConvertEan13ToIssn(0x0048),

    /**
     * 工业 25 码是否启用
     */
    Industry25Enabled(0x0049),

    /**
     * 矩阵 25 码是否启用
     */
    Matrix25Enabled(0x004A),

    /**
     * 照明灯控制
     * <ul>
     * <li>0: 标准
     * <li>1: 常灭
     * <li>2: 常亮
     * </ul>
     */
    SetLightness(0x0052),

    /**
     * 韦根协议选择
     * <ul>
     * <li>0: 26bit
     * <li>1: 34bit
     * </ul>
     */
    SetWiegandMode(0x0053),

    /**
     * Rs232 物理串口波特率配置
     * <ul>
     * <li>0: 4800
     * <li>1: 9600
     * <li>2: 19200
     * <li>3: 38400
     * <li>4: 51200
     * <li>6: 115200
     * </ul>
     */
    SetRs232BaudRate(0x0054),

    /**
     * Rs232 物理串口数据位停止位校验位配置
     *
     * @see IDataTypes.data#encodeRs232Mode(boolean, int, ParityCheckMode)
     */
    SetRs232Protocol(0x0056),

    ;

    public final int code;

    private NumParam(int code) {
        this.code = code;
        Index.map.put(code, this);
    }

    public int getCode() {
        return code;
    }

    static class Index {
        static Map<Integer, NumParam> map = new HashMap<Integer, NumParam>();
    }

    public static NumParam forCode(int code) {
        return Index.map.get(code);
    }

}

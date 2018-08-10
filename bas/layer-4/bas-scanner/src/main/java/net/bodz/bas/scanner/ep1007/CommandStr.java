package net.bodz.bas.scanner.ep1007;

import java.util.HashMap;
import java.util.Map;

/**
 * 是否支持经条形码指令设置;
 */
@interface BarcodeControllable {
}

/**
 * 是否支持经条形码指令设置;
 */
@interface SerialControllable {
}

/**
 * 当允许应答时,串口发送的指令是否需要扫描器作应答;
 */
@interface RequireAck {
}

public enum CommandStr {

    /**
     * 进入设置模式
     */
    @BarcodeControllable
    YSET("YSET"),

    /**
     * 退出并保存
     */
    @BarcodeControllable
    @SerialControllable
    @RequireAck
    YEND("YEND"),

    /**
     * 触发扫描
     *
     * 当触发扫描识读成功时,除了应答,还会返回条码字符的 ASCII 码;
     */
    @SerialControllable
    @RequireAck
    TRIGGER_SCAN("YLTK"),

    /**
     * 停止扫描
     */
    @BarcodeControllable
    @SerialControllable
    @RequireAck
    STOP_SCAN("YLSK"),

    /**
     * 恢复出厂默认值
     */
    @BarcodeControllable
    @SerialControllable
    @RequireAck
    FACTORY_DEFAULTS("YDFK"),

    /**
     * 恢复用户默认值
     */
    @BarcodeControllable
    @SerialControllable
    @RequireAck
    CUSTOM_DEFAULTS("YDCK"),

    /**
     * 写入用户默认值
     */
    @BarcodeControllable
    @SerialControllable
    @RequireAck
    WR_CUSTOM_DEFAULTS("YWCK"),

    /**
     * 读取产品批次版本
     *
     * 当允许应答时,扫描器会先应答再返回版本号;
     */
    @BarcodeControllable
    @SerialControllable
    @RequireAck
    READ_REVISION("YRVK"),

    /**
     * 单次触发扫描模式(F0000)
     */
    @BarcodeControllable
    @SerialControllable
    @RequireAck
    SetDecodeOnce("F0000"),

    /**
     * 常亮连续触发扫描模式(F0001)
     */
    @BarcodeControllable
    @SerialControllable
    @RequireAck
    SetDecodeContinuous("F0001"),

    /**
     * 不允许重复识读(F0100)
     */
    @BarcodeControllable
    @SerialControllable
    @RequireAck
    DisableRepeatDecode("F0100"),

    /**
     * 允许重复识读(F0101)
     */
    @BarcodeControllable
    @SerialControllable
    @RequireAck
    EnableRepeatDecode("F0101"),

    /**
     * 离开 进入 允许重复识读(F0102)
     */
    @BarcodeControllable
    @SerialControllable
    @RequireAck
    SwitchRepeatDecode("F0102"),

    /**
     * TTL/RS232 输出(A0000)
     */
    @BarcodeControllable
    @SerialControllable
    @RequireAck
    SerialOut("A0000"),

    /**
     * USB HID Keyboard 输出(A0001)
     */
    @BarcodeControllable
    @SerialControllable
    @RequireAck
    KeyboardOut("A0001"),

    /**
     * 不需要应答(E0000)
     */
    @SerialControllable
    NoAck("E0000"),

    /**
     * 需要应答(E0001)
     */
    @SerialControllable
    RequireAck("E0001"),

    DisableAllBarcode("I1000"),

    EnableAllBarcode("I1001"),

    ;

    String str;

    private CommandStr(String cmd) {
        this.str = cmd;
        Index.map.put(cmd, this);
    }

    public String getStr() {
        return str;
    }

    static class Index {
        static Map<String, CommandStr> map = new HashMap<>();
    }

    public static CommandStr forStr(String str) {
        return Index.map.get(str);
    }

}

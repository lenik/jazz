package net.bodz.bas.scanner.ztx965n;

import java.util.HashMap;
import java.util.Map;

public enum RxErrorCode {

    /**
     * 操作成功
     */
    OK(0),

    // Returned by the reader

    /**
     * 天线连接失败
     */
    NoAntenna(0x01),

    /**
     * 未检测到标签
     */
    NoLabel(0x02),

    /**
     * 非法标签
     */
    InvalidLabel(0x03),

    /**
     * 读写功率不够
     */
    LessPower(0x04),

    /**
     * 该区写保护
     */
    WriteProtError(0x05),

    /**
     * 校验和错误
     */
    ChecksumError(0x06),

    /**
     * 参数错误
     */
    ParameterError(0x07),

    /**
     * 数据区不存在
     */
    MemoryError(0x08),

    /**
     * 密码不正确
     */
    PasswordError(0x09),

    /**
     * G2标签毁灭密码为全零
     */
    KillPasswordError(0x0A),

    /**
     * 非法命令
     *
     * 当读写器处于主动工作时,该命令为非法命令
     */
    NonlicetCommand(0x0B),

    /**
     * 密码不匹配的非法用户
     */
    NonlicetUser(0x0C),

    /**
     * 表示无效指令,如参数不正确的指令
     */
    InvalidCommand(0x1E),

    /**
     * 未知指令
     */
    OtherError(0x1F),

    // //函数输入错误
    /**
     * 其它错误
     */
    IllegalCall(0x20),
    // NoCardIdInput(0x20),

    // 通信方面出错信息

    /**
     * 通信口初始化失败
     */
    InitRs232Err(0x81),

    /**
     * 找不到读写器
     */
    NoScanner(0x82),

    /**
     * 收发通信数据出错
     */
    CommError(0x83),

    /**
     * 设置波特率出错
     */
    BaudrateError(0x84),

    /**
     * 串口初始化失败
     */
    InitRs232Err_2(0x85),

    /**
     * 串口收发通信数据出错
     */
    CommError_2(0x86),

    ;

    int code;

    private RxErrorCode(int code) {
        this.code = code;
        Index.map.put(code, this);
    }

    static class Index {
        static Map<Integer, RxErrorCode> map = new HashMap<>();
    }

    public static RxErrorCode forCode(int code) {
        return Index.map.get(code);
    }

}

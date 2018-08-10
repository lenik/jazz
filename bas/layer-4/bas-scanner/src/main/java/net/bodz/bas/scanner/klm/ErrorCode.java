package net.bodz.bas.scanner.klm;

public enum ErrorCode {

    /**
     * 命令帧中指令代码错误。
     */
    CommandError(0x17),

    /**
     * 跳频搜索信道超时。所有信道在这段时间内都被占用。
     */
    FhssFail(0x20),

    /**
     * 轮询操作失败。没有标签返回或者返回数据 CRC 校验错误。
     */
    InventoryFail(0x15),

    /**
     * 访问标签失败,有可能是访问密码 password 不对。
     */
    AccessFail(0x16),

    /**
     * 读标签数据存数区失败。标签没有返回或者返回数据 CRC 校验错误
     */
    ReadFail(0x09),

    /**
     * 读标签数据存储区错误。
     *
     * 返回的代码由 0xA0 位或 Error Code 得 到。Error code 信息详见下表。
     */
    ReadError_(0xA0),

    /**
     * 写标签数据存数区失败。标签没有返回或者返回数据 CRC 校验错 误。
     */
    WriteFail(0x10),

    /**
     * 写标签数据存储区错误。
     *
     * 返回的代码由 0xB0 位或 Error Code 得 到。Error code 信息详见下表。
     */
    WriteError_(0xB0),

    /**
     * 锁定标签数据存数区失败。标签没有返回或者返回数据 CRC 校验错 误。
     */
    LockFail(0x13),

    /**
     * 锁定标签数据存储区错误。
     *
     * 返回的代码由 0xC0 位或 Error Code 得到。Error code 信息详见下表。
     */
    LockError_(0xC0),

    /**
     * 灭活标签失败。标签没有返回或者返回数据 CRC 校验错误。
     */
    KillFail(0x12),

    /**
     * 灭活标签错误。
     *
     * 返回的代码由 0xC0 位或 Error Code 得到。Error code 信息详见下表。
     */
    KillError_(0xD0),

    /**
     * ReadProtect 指令失败,标签没有返回数据或者返回数据 CRC 校验错误。
     */
    NXP_ReadProtectFail(0x2A),

    /**
     * Reset ReadProtect 指令失败,标签没有返回数据或者返 回数据 CRC 校验错误。
     */
    NXP_ResetReadProtectFail(0x2B),

    /**
     * Change EAS 指令失败,标签没有返回数据或者返回数据 CRC 校验错误。
     */
    NXP_ChangeEASFail(0x1B),

    /**
     * NXP 特有指令标签返回的错误代码,错误代码由 0xE0 或上标签返回的 Error Code 得到。
     */
    NXP_Special_(0xE0),

    ;

    int code;

    private ErrorCode(int code) {
        this.code = code;
    }

}

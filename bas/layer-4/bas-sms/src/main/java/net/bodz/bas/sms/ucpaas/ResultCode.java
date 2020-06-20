package net.bodz.bas.sms.ucpaas;

public interface ResultCode {

    int OK = 0;

    /**
     * 对同个号码发送短信超过限定频率
     */
    int ExceedsMaxFreq = 105147;

    /**
     * 请求频率过快
     */
    int TooFast = 105166;

}

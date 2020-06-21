package net.bodz.bas.sms;

public interface ISmsListener {

    void sent(SmsRecord record);

    void failed(SmsRecord record);

    void ignored(SmsRecord record);

    void completed(SmsRecord record);

}

package net.bodz.bas.sms;

import net.bodz.bas.fmt.json.IJsonSerializable;

public interface ISmsSendResponse
        extends IJsonSerializable {

    SmsSendState getSendState();

    String getErrorMessage();

}

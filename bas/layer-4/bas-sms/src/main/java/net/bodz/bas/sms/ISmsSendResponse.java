package net.bodz.bas.sms;

import net.bodz.bas.fmt.json.IJsonForm;

public interface ISmsSendResponse
        extends IJsonForm {

    SmsSendState getSendState();

    String getErrorMessage();

}

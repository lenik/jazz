package net.bodz.bas.sms;

import java.io.IOException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.order.IPriority;

public interface IShortMessageService
        extends IPriority {

    void setAutoCommit(boolean autoCommit);

    /**
     * execute batch.
     */
    void commit()
            throws IOException, ParseException;

    /**
     * @return <code>true</code> if function supported.
     */
    boolean sendText(String recipient, String text)
            throws IOException;

    /**
     * @return <code>true</code> if <code>templateName</code> is defined.
     */
    boolean sendPrepared(String recipient, String templateName, Object... params)
            throws IOException, ParseException;

    void addSmsListener(ISmsListener listener);

    void removeSmsListener(ISmsListener listener);

}

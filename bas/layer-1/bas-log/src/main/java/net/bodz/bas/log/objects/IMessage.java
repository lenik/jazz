package net.bodz.bas.log.objects;

import java.io.Serializable;

public interface IMessage
        extends Serializable {

    /**
     * @param messagePiece
     *            Can be <code>null</code>, which means {@link String#valueOf(Object)
     *            String.valueOf}<code>(null)</code>.
     * @return <code>this</code> or re-constructed buffer.
     */
    IMessage add(Object messagePiece);

}

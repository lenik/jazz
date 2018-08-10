package net.bodz.bas.scanner.ep6000;

import java.io.IOException;

public interface IEp6000
        extends IDataTypes {

    int getParameter(NumParam parameter)
            throws IOException, Ep6000Exception;

    byte[] getParameter(DataParam parameter)
            throws IOException, Ep6000Exception;

    /**
     * @param storage
     *            Permanent if <code>true</code>, or write to the RAM if <code>false</code>.
     */
    void setParameter(NumParam parameter, int value, boolean storage)
            throws IOException, Ep6000Exception;

    void startDecode()
            throws IOException, Ep6000Exception;

    void stopDecode()
            throws IOException, Ep6000Exception;

}

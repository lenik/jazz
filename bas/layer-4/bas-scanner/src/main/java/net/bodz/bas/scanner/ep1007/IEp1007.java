package net.bodz.bas.scanner.ep1007;

import java.io.IOException;

public interface IEp1007
        extends IDataTypes {

    void enterSetMode()
            throws IOException, Ep1007Exception;

    void saveAndExit()
            throws IOException, Ep1007Exception;

    void startDecode()
            throws IOException, Ep1007Exception;

    void stopDecode()
            throws IOException, Ep1007Exception;

    void factoryReset()
            throws IOException, Ep1007Exception;

    void loadCustomDefaults()
            throws IOException, Ep1007Exception;

    void saveCustomDefaults()
            throws IOException, Ep1007Exception;

    void readRevision()
            throws IOException, Ep1007Exception;

    void setContinuousMode(boolean enabled)
            throws IOException, Ep1007Exception;

    void setRepeatMode(boolean enabled)
            throws IOException, Ep1007Exception;

    void switchRepeatMode()
            throws IOException, Ep1007Exception;

    /**
     * @param Output
     *            to TTL/RS232 if <code>false</code>, or USB HID Keyboard if <code>true</code>.
     */
    void setKeyboardMode(boolean enabled)
            throws IOException, Ep1007Exception;

    void setRequireAck(boolean required)
            throws IOException, Ep1007Exception;

    void enableAllBarcodes(boolean state)
            throws IOException, Ep1007Exception;

}

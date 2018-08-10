package net.bodz.bas.scanner.ztx965n;

import java.io.IOException;

public class ReaderInfo
        implements IRxParser {

    int firmwareMajor;
    int firmwareMinor;
    int softwareMajor;
    int softwareMinor;

    @Override
    public ReaderInfo parse(RxPacket in)
            throws IOException {
        firmwareMajor = in.readByte();
        firmwareMinor = in.readByte();
        softwareMajor = in.readByte();
        softwareMinor = in.readByte();
        return this;
    }

    public String getFirmwareVersion() {
        return firmwareMajor + "." + firmwareMinor;
    }

    public String getSoftwareVersion() {
        return softwareMajor + "." + softwareMinor;
    }

    @Override
    public String toString() {
        return "firmware(" + getFirmwareVersion() + "), software(" + getSoftwareVersion() + ")";
    }

}

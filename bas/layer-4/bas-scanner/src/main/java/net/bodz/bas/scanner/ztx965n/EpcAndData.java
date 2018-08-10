package net.bodz.bas.scanner.ztx965n;

public class EpcAndData {

    Words epc;
    byte[] data;

    public EpcAndData(Words epc, byte[] data) {
        this.epc = epc;
        this.data = data;
    }

}

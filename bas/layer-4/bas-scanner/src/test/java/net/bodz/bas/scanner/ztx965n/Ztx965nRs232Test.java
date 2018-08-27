package net.bodz.bas.scanner.ztx965n;

import java.util.List;

import org.junit.Assert;

public class Ztx965nRs232Test
        extends Assert
        implements IDataTypes {

    SerialZtx965n z;
    Password pass = new Password(0, 0, 0, 0);

    public Ztx965nRs232Test()
            throws Exception {
        z = new SerialZtx965n();
    }

    public void testActive()
            throws Exception {
        z.setAutoMode(false);
        z.setOutputFormat(0);
        // z.setBeep(true);
        z.setAntenna(1);
        z.setAutoMode(true);

        while (true) {
            System.out.println("Begin");
            int off = 0;
            while (off < 200) {
                List<TagRecord> tagRecords = z.getTagRecords(off, 5);
                System.out.println("    got " + tagRecords.size());
                for (TagRecord r : tagRecords)
                    System.out.println("    >> " + r);
                off += tagRecords.size();
            }
        }
    }

    public void testList()
            throws Exception {
        byte mask[] = {};
        while (true) {
            try {
                List<Words> list = z.scan(MemBank.EPC, 0, 0, mask);
                System.out.println("Scanned: " + list.size());
                for (Words epc : list)
                    dumpTag(epc);
            } catch (Ztx965nException e2) {
                System.out.println("Error: " + e2.errorCode);
            }
        }
    }

    public void test1()
            throws Exception {
        z.setAutoMode(true);

        Words epc1 = new Words("e2000017190f021822103459", 12);
        dumpTag(epc1);

// byte[] data = new byte[64];
// byte[] data = "小馒头巧克力味 ".getBytes("utf-8");
// z.writeMemory(epc1, MemBank.User, 0, data, pass);
    }

    void dumpTag(Words epc)
            throws Exception {
        System.out.println("    " + epc);

        try {
            byte[] mpass = z.readMemory(epc, MemBank.Password, 0, 8, pass);
            System.out.println("        Password: " + memCodec.encode(mpass));
        } catch (Ztx965nException e) {
            System.out.println("        Error: " + e.errorCode);
        }
        try {
            byte[] mepc = z.readMemory(epc, MemBank.EPC, 4, 12, pass);
            System.out.println("        EPC: " + memCodec.encode(mepc));
        } catch (Ztx965nException e) {
            System.out.println("        Error: " + e.errorCode);
        }
        try {
            byte[] mtid = z.readMemory(epc, MemBank.TID, 0, 6, pass);
            System.out.println("        TID: " + memCodec.encode(mtid));
        } catch (Ztx965nException e) {
            System.out.println("        Error: " + e.errorCode);
        }
        try {
            byte[] muser = z.readMemory(epc, MemBank.User, 0, 64, pass);
            System.out.println("        User: " + memCodec.encode(muser));
            if (muser[0] != 0)
                System.out.println("        User: " + new String(muser, "utf-8").trim());
        } catch (Ztx965nException e) {
            System.out.println("        Error: " + e.errorCode);
        }
    }

    public static void main(String[] args)
            throws Exception {
        Ztx965nRs232Test a = new Ztx965nRs232Test();
        try {
            a.test1();
        } finally {
            a.z.close();
        }
    }

}

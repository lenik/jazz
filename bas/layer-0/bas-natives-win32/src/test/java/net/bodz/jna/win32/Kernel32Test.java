package net.bodz.jna.win32;

import java.nio.ByteBuffer;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

public class Kernel32Test
        extends Assert
        implements IWin32 {

    @Before
    public void checkWin32() {
        Assume.assumeTrue(Win32Config.isWin32());
    }

    @Test
    public void testGetVolumeInformation() {
        ByteBuffer buf = ByteBuffer.allocate(256);
        ByteBuffer fsbuf = ByteBuffer.allocate(256);
        IntByReference pSerial = new IntByReference();
        IntByReference pMaxLen = new IntByReference();
        IntByReference pFlags = new IntByReference();
        kernel32.GetVolumeInformationA("C:/", buf, buf.capacity(), pSerial, pMaxLen, pFlags, fsbuf, fsbuf.capacity());
        String volName = Kernel32Utils.getAsciz(buf);
        String fsName = Kernel32Utils.getAsciz(fsbuf);
        output("Vol Name = " + volName);
        output("FS Name = " + fsName);
        System.out.printf("Serial = %x\n", pSerial.getValue());
        // C80A-170D => c80a170d
        output("Max complen = " + pMaxLen.getValue());
        output("Flags = " + Integer.toBinaryString(pFlags.getValue()));
    }

    @Test
    public void testPerformanceVars()
            throws InterruptedException {
        LongByReference pFrequence = new LongByReference();
        LongByReference pCount = new LongByReference();
        kernel32.QueryPerformanceFrequency(pFrequence);
        kernel32.QueryPerformanceCounter(pCount);
        long freq = pFrequence.getValue();
        long count = pCount.getValue();
        output("Frequence: " + freq);
        output("Counter: " + count);
        for (int i = 0; i < 10; i++) {
            kernel32.QueryPerformanceCounter(pCount);
            count = pCount.getValue();
            double systime = (double) count / freq;
            output("System up time: " + systime);
            Thread.sleep(1);
        }
    }

    void output(String s) {
        // System.out.println(s);
    }

}

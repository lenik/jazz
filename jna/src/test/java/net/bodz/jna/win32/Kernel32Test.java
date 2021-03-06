package net.bodz.jna.win32;

import static net.bodz.jna.win32.Win32.kernel32;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import org.junit.Test;

import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

public class Kernel32Test {

    public static String getAsciz(ByteBuffer buffer) {
        return getAsciz(buffer, Charset.defaultCharset());
    }

    public static String getAsciz(ByteBuffer buffer, String encoding) {
        return getAsciz(buffer, Charset.forName(encoding));
    }

    public static String getAsciz(ByteBuffer buffer, Charset charset) {
        int limit = buffer.limit();
        byte[] array = new byte[limit];
        int len = 0;
        for (len = 0; len < limit; len++) {
            byte b = buffer.get();
            if (b == 0)
                break;
            array[len++] = b;
        }
        String s = new String(array, 0, len, charset);
        return s;
    }

    @Test
    public void testGetVolumeInformation() {
        ByteBuffer buf = ByteBuffer.allocate(256);
        ByteBuffer fsbuf = ByteBuffer.allocate(256);
        IntByReference pSerial = new IntByReference();
        IntByReference pMaxLen = new IntByReference();
        IntByReference pFlags = new IntByReference();
        kernel32.GetVolumeInformationA("C:/", buf, buf.capacity(), pSerial,
                pMaxLen, pFlags, fsbuf, fsbuf.capacity());
        String volName = getAsciz(buf);
        String fsName = getAsciz(fsbuf);
        System.out.println("Vol Name = " + volName);
        System.out.println("FS Name = " + fsName);
        System.out.printf("Serial = %x\n", pSerial.getValue());
        // C80A-170D => c80a170d
        System.out.println("Max complen = " + pMaxLen.getValue());
        System.out.println("Flags = "
                + Integer.toBinaryString(pFlags.getValue()));

    }

    @Test
    public void testPerformanceVars() throws InterruptedException {
        LongByReference pFrequence = new LongByReference();
        LongByReference pCount = new LongByReference();
        kernel32.QueryPerformanceFrequency(pFrequence);
        kernel32.QueryPerformanceCounter(pCount);
        long freq = pFrequence.getValue();
        long count = pCount.getValue();
        System.out.println("Frequence: " + freq);
        System.out.println("Counter: " + count);
        for (int i = 0; i < 10; i++) {
            kernel32.QueryPerformanceCounter(pCount);
            count = pCount.getValue();
            double systime = (double) count / freq;
            System.out.println("System up time: " + systime);
            Thread.sleep(100);
        }
    }

}

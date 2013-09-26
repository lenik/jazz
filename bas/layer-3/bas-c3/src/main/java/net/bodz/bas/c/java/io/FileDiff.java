package net.bodz.bas.c.java.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.IStreamInputSourceWrapper;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.io.res.tools.StreamReading;
import net.bodz.bas.text.diff.DiffEntry;
import net.bodz.bas.text.diff.IDiffComparator;

public class FileDiff {

    static final int blockSize = 4096;

    /**
     * @return first position of difference, or -1 if the two are same.
     */
    public static long findFirstDifferentByte(IStreamInputSource src1, IStreamInputSource src2)
            throws IOException {
        if (src1 == src2)
            return -1;

        if (src1 == null || src2 == null)
            return 0;

        File srcf = null;
        File dstf = null;
        if (src1 instanceof File)
            if (!(srcf = ((File) src1)).canRead())
                return 0;
        if (src2 instanceof File)
            if (!(dstf = ((File) src2)).canRead())
                return 0;

        if (srcf != null && dstf != null) {
            if (srcf.length() != dstf.length())
                return 0;
        }

        InputStream s = src1.newInputStream();
        InputStream d = null;
        try {
            d = src2.newInputStream();
            byte[] buf1 = new byte[blockSize];
            byte[] buf2 = new byte[blockSize];
            long offset = 0;
            int len1;

            while ((len1 = s.read(buf1)) != -1) {

                int len2 = d.read(buf2, 0, len1);
                if (len2 == -1)
                    return offset;

                while (len2 != len1) {
                    int more2 = d.read(buf2, len2, len1 - len2);
                    if (more2 == -1)
                        return offset + len2;
                    len2 += more2;
                }

                assert len1 == len2;

                if (len1 == buf1.length)
                    if (Arrays.equals(buf1, buf2)) {
                        offset += len1;
                        continue;
                    }

                for (int i = 0; i < len1; i++)
                    if (buf1[i] != buf2[i])
                        return offset + i;
                offset += len1;
            }
        } finally {
            s.close();
            if (d != null)
                d.close();
        }
        return -1;
    }

    public static boolean equals(IStreamInputSource src1, IStreamInputSource src2)
            throws IOException {
        return findFirstDifferentByte(src1, src2) == -1;
    }

    public static boolean equals(IStreamInputSourceWrapper src1, IStreamInputSourceWrapper src2)
            throws IOException {
        return equals(src1.getInputSource(), src2.getInputSource());
    }

    public static boolean equals(File file1, File file2)
            throws IOException {
        return equals(new FileResource(file1), new FileResource(file2));
    }

    public static List<DiffEntry> compareDiff(IStreamInputSource src1, IStreamInputSource src2,
            IDiffComparator diffComparator)
            throws IOException {
        if (src1 == null)
            throw new NullPointerException("src1");
        if (src2 == null)
            throw new NullPointerException("src2");
        if (diffComparator == null)
            throw new NullPointerException("diffComparator");

        List<String> lines1 = src1.to(StreamReading.class).readLines();
        List<String> lines2 = src2.to(StreamReading.class).readLines();
        List<DiffEntry> diffs = diffComparator.compareDiff(lines1, lines2);
        return diffs;
    }

    public static List<DiffEntry> compareDiff(IStreamInputSourceWrapper src1, IStreamInputSourceWrapper src2,
            IDiffComparator diffComparator)
            throws IOException {
        return compareDiff(src1.getInputSource(), src2.getInputSource(), diffComparator);
    }

    public static List<DiffEntry> compareDiff(File file1, File file2, IDiffComparator diffComparator)
            throws IOException {
        return compareDiff(new FileResource(file1), new FileResource(file2), diffComparator);
    }

}

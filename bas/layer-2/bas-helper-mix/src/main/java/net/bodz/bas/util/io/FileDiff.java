package net.bodz.bas.util.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.IStreamResource;
import net.bodz.bas.io.resource.builtin.LocalFileResource;
import net.bodz.bas.text.diff.DiffComparator;
import net.bodz.bas.text.diff.DiffInfo;

public class FileDiff {

    static final int blockSize = 4096;

    /**
     * @return first position of difference, or -1 if the two are same.
     */
    public static long findFirstDifferentByte(IStreamInputSource src, IStreamInputSource dst)
            throws IOException {
        if (src == dst)
            return -1;
        if (src == null || dst == null)
            return 0;
        File srcf = null;
        File dstf = null;
        if (src instanceof File)
            if (!(srcf = ((File) src)).canRead())
                return 0;
        if (dst instanceof File)
            if (!(dstf = ((File) dst)).canRead())
                return 0;
        if (srcf != null && dstf != null) {
            if (srcf.length() != dstf.length())
                return 0;
        }
        InputStream s = src.newInputStream();
        InputStream d = null;
        try {
            d = dst.newInputStream();
            byte[] sbuf = new byte[blockSize];
            byte[] dbuf = new byte[blockSize];
            long offset = 0;
            int slen;
            while ((slen = s.read(sbuf)) != -1) {
                int dlen = d.read(dbuf, 0, slen);
                if (dlen == -1)
                    return offset;
                while (dlen != slen) {
                    int dmore = d.read(dbuf, dlen, slen - dlen);
                    if (dmore == -1)
                        return offset + dlen;
                    dlen += dmore;
                }
                assert slen == dlen;
                if (slen == sbuf.length)
                    if (Arrays.equals(sbuf, dbuf)) {
                        offset += slen;
                        continue;
                    }
                for (int i = 0; i < slen; i++)
                    if (sbuf[i] != dbuf[i])
                        return offset + i;
                offset += slen;
            }
        } finally {
            s.close();
            if (d != null)
                d.close();
        }
        return -1;
    }

    public static boolean equals(File src, File dst)
            throws IOException {
        return equals(new LocalFileResource(src), new LocalFileResource(dst));
    }

    public static boolean equals(IStreamInputSource src, IStreamInputSource dst)
            throws IOException {
        return findFirstDifferentByte(src, dst) == -1;
    }

    /**
     * If difference info is available, then return the first difference. otherwise return
     * <code>false</code> if any different exists.
     * 
     * @return <code>null</code> if the same
     */
    public static Object copyDiff(IStreamInputSource src, IStreamResource dst, DiffComparator diff)
            throws IOException {
        Object ret;
        if (diff != null) {
            List<String> al = src.forRead().listLines();
            List<String> bl = dst.forRead().listLines();
            List<DiffInfo> diffs = diff.diffCompare(al, bl);
            if (diffs.size() == 0)
                return null;
            ret = diffs;
        } else {
            if (equals(src, dst))
                return null;
            ret = false;
        }
        // Writer out = dst.forWrite().newWriter();
        dst.forWrite().writeChars(src);
        return ret;
    }

    /**
     * return <code>true</code> if the two are diff and actually copied.
     */
    public static boolean copyDiff(IStreamInputSource src, IStreamResource dst)
            throws IOException {
        return copyDiff(src, dst, null) != null;
    }

}

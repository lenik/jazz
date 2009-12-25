package net.bodz.bas.commons.buffer;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public interface IPagedBuffer {

    MemPage add(byte x);

    MemPage add(byte[] buf, int off, int len);

    MemPage add(byte[] buf);

    MemPage add(ByteBuffer buf);

    MemPage insert(PagePos pos, byte x);

    MemPage insert(PagePos pos, int len, byte fill);

    MemPage insert(PagePos pos, byte[] buf, int off, int len);

    MemPage insert(PagePos pos, byte[] buf);

    MemPage insert(PagePos pos, ByteBuffer buf);

    void delete(PagePos begin, PagePos end);

    void compact(int pageSize);

    byte[] getBytes(PagePos begin, PagePos end);

    String getString(PagePos begin, PagePos end, Charset charset);

    String getString(PagePos begin, PagePos end, String charset);

    String getString(PagePos begin, PagePos end);

}

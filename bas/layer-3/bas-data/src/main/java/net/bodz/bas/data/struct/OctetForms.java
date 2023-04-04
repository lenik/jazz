package net.bodz.bas.data.struct;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.BByteIn;
import net.bodz.bas.io.BByteOut;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;
import net.bodz.bas.io.data.DataInImplLE;
import net.bodz.bas.io.data.DataOutImplLE;

public class OctetForms {

    public static byte[] pack(IOctetStreamForm octetsForm) {
        return pack(octetsForm, (Charset) null);
    }

    public static byte[] pack(IOctetStreamForm octetsForm, String encoding) {
        Charset charset = Charset.forName(encoding);
        return pack(octetsForm, charset);
    }

    public static byte[] pack(IOctetStreamForm octetsForm, Charset charset) {
        BByteOut buf = new BByteOut();
        IDataOut out = DataOutImplLE.from(buf);
        if (charset != null)
            out.setCharset(charset);
        try {
            octetsForm.writeObject(out);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
        byte[] array = buf.toByteArray();
        return array;
    }

    public static <T extends IOctetStreamForm> T unpack(byte[] octets, T obj)
            throws ParseException {
        return unpack(octets, obj, (Charset) null);
    }

    public static <T extends IOctetStreamForm> T unpack(byte[] octets, T obj, String encoding)
            throws ParseException {
        Charset charset = Charset.forName(encoding);
        return unpack(octets, obj, charset);
    }

    public static <T extends IOctetStreamForm> T unpack(byte[] octets, T obj, Charset charset)
            throws ParseException {
        BByteIn in = new BByteIn(octets);
        try {
            return unpack(in, obj, charset);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

    public static <T extends IOctetStreamForm> T unpack(IByteIn octets, T obj)
            throws IOException, ParseException {
        return unpack(octets, obj, (Charset) null);
    }

    public static <T extends IOctetStreamForm> T unpack(IByteIn octets, T obj, String encoding)
            throws IOException, ParseException {
        Charset charset = Charset.forName(encoding);
        return unpack(octets, obj, charset);
    }

    public static <T extends IOctetStreamForm> T unpack(IByteIn octets, T obj, Charset charset)
            throws IOException, ParseException {
        IDataIn in = DataInImplLE.from(octets);
        in.setCharset(charset);
        obj.readObject(in);
        return obj;
    }

}

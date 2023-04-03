package net.bodz.bas.data.struct;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.BByteIn;
import net.bodz.bas.io.BByteOut;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;
import net.bodz.bas.io.data.DataInImplLE;
import net.bodz.bas.io.data.DataOutImplLE;

public class OctetForms {

    public static byte[] pack(IOctetStreamForm octetsForm)
            throws IOException {
        return pack(octetsForm, (Charset) null);
    }

    public static byte[] pack(IOctetStreamForm octetsForm, String encoding)
            throws IOException {
        Charset charset = Charset.forName(encoding);
        return pack(octetsForm, charset);
    }

    public static byte[] pack(IOctetStreamForm octetsForm, Charset charset)
            throws IOException {
        BByteOut buf = new BByteOut();
        IDataOut out = DataOutImplLE.from(buf);
        if (charset != null)
            out.setCharset(charset);
        octetsForm.writeObject(out);
        byte[] array = buf.toByteArray();
        return array;
    }

    public static <T extends IOctetStreamForm> T unpack(byte[] octets, T obj)
            throws IOException, ParseException {
        return unpack(octets, obj, (Charset) null);
    }

    public static <T extends IOctetStreamForm> T unpack(byte[] octets, T obj, String encoding)
            throws IOException, ParseException {
        Charset charset = Charset.forName(encoding);
        return unpack(octets, obj, charset);
    }

    public static <T extends IOctetStreamForm> T unpack(byte[] octets, T obj, Charset charset)
            throws IOException, ParseException {
        BByteIn in = new BByteIn(octets);
        return unpack(in, obj, charset);
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

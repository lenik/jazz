package net.bodz.bas.fmt.rst;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.BTreeOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.adapter.WriterPrintOut;
import net.bodz.bas.meta.source.FnHelper;

@FnHelper
public class RstFn {

    public static String toString(IRstSerializable obj) {
        BCharOut buf = new BCharOut(1024);
        IRstOutput out = RstOutputImpl.from(buf);
        try {
            obj.writeObject(out);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
        String rst = buf.toString();
        return rst;
    }

    public static String toString(IRstSerializable obj, String indent) {
        BTreeOut buf = new BTreeOut();
        buf.getTextIndention().setCurrentLinePrefix(indent);
        try {
            obj.writeObject(RstOutputImpl.from(buf));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return buf.toString();
    }

    public static void saveToRst(IRstSerializable obj, File file)
            throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
        IPrintOut out = new WriterPrintOut(osw);
        IRstOutput rstOutput = RstOutputImpl.from(out);
        obj.writeObject(rstOutput);
        out.close();
    }

    public static void loadFromRst(IRstSerializable ctx, File file)
            throws IOException, ElementHandlerException, ParseException {
        if (!file.exists())
            return;

        RstLoader rstLoader = new RstLoader();

        FileInputStream in = new FileInputStream(file);
        try {
            InputStreamReader reader = new InputStreamReader(in, "utf-8");
            RstInput rstInput = new RstInput(reader);

            rstLoader.load(rstInput, ctx.getElementHandler());
        } finally {
            in.close();
        }
    }

}

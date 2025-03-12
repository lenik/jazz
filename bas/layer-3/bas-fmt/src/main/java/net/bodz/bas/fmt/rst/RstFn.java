package net.bodz.bas.fmt.rst;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.fmt.api.ElementHandlerException;
import net.bodz.bas.fmt.rst.obj.BeanRstDumper;
import net.bodz.bas.fmt.rst.obj.BeanRstHandler;
import net.bodz.bas.fmt.rst.obj.ReflectRstDumper;
import net.bodz.bas.fmt.rst.obj.ReflectRstHandler;
import net.bodz.bas.fmt.rst.obj.RstSource;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.BTreeOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.adapter.WriterPrintOut;
import net.bodz.bas.meta.source.FnHelper;

@FnHelper
public class RstFn {

    public static IRstHandler getDefaultHandler(IRstForm obj) {
        RstSource aRstSource = obj.getClass().getAnnotation(RstSource.class);
        if (aRstSource != null)
            if (aRstSource.bean() == true)
                return new BeanRstHandler(obj);
        return new ReflectRstHandler(obj);
    }

    public static void defaultDump(IRstForm obj, IRstOutput out)
            throws IOException, FormatException {
        RstSource aRstSource = obj.getClass().getAnnotation(RstSource.class);
        if (aRstSource != null)
            if (aRstSource.bean() == true)
                new BeanRstDumper(out).dump(obj);
        new ReflectRstDumper(out).dump(obj);
    }

    public static String toString(IRstForm obj) {
        BCharOut buf = new BCharOut(1024);
        IRstOutput out = RstOutputImpl.from(buf);
        try {
            obj.writeObject(out);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        } catch (FormatException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        String rst = buf.toString();
        return rst;
    }

    public static String toString(IRstForm obj, String indent) {
        BTreeOut buf = new BTreeOut();
        buf.getTextIndention().setCurrentLinePrefix(indent);
        try {
            obj.writeObject(RstOutputImpl.from(buf));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (FormatException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return buf.toString();
    }

    public static void saveToRst(IRstForm obj, File file)
            throws IOException, FormatException {
        saveToRst(obj, file.toPath());
    }

    public static void saveToRst(IRstForm obj, Path file)
            throws IOException, FormatException {
        try (OutputStream fos = Files.newOutputStream(file)) {
            OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
            IPrintOut out = new WriterPrintOut(osw);
            IRstOutput rstOutput = RstOutputImpl.from(out);
            obj.writeObject(rstOutput);
            out.flush();
        }
    }

    public static void loadFromRst(IRstForm ctx, File file)
            throws IOException, ElementHandlerException, ParseException {
        loadFromRst(ctx, file.toPath());
    }

    public static void loadFromRst(IRstForm ctx, Path file)
            throws IOException, ElementHandlerException, ParseException {
        if (Files.notExists(file))
            return;

        RstLoader rstLoader = new RstLoader();

        try (InputStream in = Files.newInputStream(file)) {
            InputStreamReader reader = new InputStreamReader(in, "utf-8");
            RstInput rstInput = new RstInput(reader);
            rstLoader.load(rstInput, ctx.getElementHandler());
        }
    }

    public static void loadFromRst(IRstForm ctx, String rst)
            throws IOException, ElementHandlerException, ParseException {
        RstLoader rstLoader = new RstLoader();
        StringReader reader = new StringReader(rst);
        RstInput rstInput = new RstInput(reader);
        rstLoader.load(rstInput, ctx.getElementHandler());
    }

}

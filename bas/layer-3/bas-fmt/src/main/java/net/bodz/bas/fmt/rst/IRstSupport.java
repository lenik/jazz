package net.bodz.bas.fmt.rst;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.rst.bean.BeanRstDumper;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.adapter.WriterPrintOut;

public interface IRstSupport
        extends IRstSerializable {

    class rst {

        private static IRstDumper dumper = BeanRstDumper.getInstance();

        public static void dump(IRstOutput out, Object obj)
                throws IOException {
            dumper.dump(out, obj);
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

}

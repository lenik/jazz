package net.bodz.bas.shell.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.fmt.textmap.TextMapFormat;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.meta.build.ProgramName;
import net.bodz.bas.program.skel.BatchEditCLI;
import net.bodz.bas.program.skel.FileHandler;
import net.bodz.mda.xjdoc.FlatfDocLoader;
import net.bodz.mda.xjdoc.model.IElementDoc;

@ProgramName("ff2tm")
public class Xjdoc2TextMap
        extends BatchEditCLI {

    FlatfDocLoader flatfDocLoader = new FlatfDocLoader();
    TextMapFormat textMapFormat = new TextMapFormat();

    public Xjdoc2TextMap() {
        textMapFormat.keyStartPattern = "@";
        textMapFormat.keyEndPattern = "";
        textMapFormat.indented = true;
    }

    @Override
    public void processFile(FileHandler handler)
            throws Exception {
        Map<String, iString> map = new LinkedHashMap<String, iString>();

        IElementDoc doc = flatfDocLoader.load(handler.getInputSource());
        for (Entry<String, Object> entry : doc.getTagMap().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            map.put(key, iString.fn.val(value));
        }

        iString text = doc.getText();
        map.put("text", text);

        String keyStart = textMapFormat.keyStartPattern;
        String keyEnd = textMapFormat.keyEndPattern;

        IPrintOut out = handler.getOutputTarget().newPrintOut();
        for (Entry<String, iString> entry : map.entrySet()) {
            out.println(keyStart + entry.getKey() + keyEnd);
            iString istr = entry.getValue();
            String plstr = istr.toParaLangString("\n    ");
            out.println("    " + plstr);
        }
        out.close();

        handler.save();
    }

    public static void main(String[] args)
            throws Exception {
        new Xjdoc2TextMap().execute(args);
    }

}

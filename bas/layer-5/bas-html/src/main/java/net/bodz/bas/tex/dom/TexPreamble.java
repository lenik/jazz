package net.bodz.bas.tex.dom;

import java.util.List;
import java.util.Map;

import net.bodz.bas.c.string.AbstractTextBlock;
import net.bodz.bas.io.ITreeOut;

public class TexPreamble
        extends AbstractTextBlock {

    DocumentClass documentClass;
    Map<String, UsePackage> usePackages;
    List<String> codes;

    public void usePackage(String name, String... parameters) {
        UsePackage usePackage = new UsePackage(name);
        for (String param : parameters) {
            Map<String, String> map = usePackage.getParameters();
            int eq = param.indexOf('=');
            String val = "";
            if (eq != -1) {
                val = param.substring(eq + 1);
                param = param.substring(0, eq);
            }
            map.put(param, val);
        }
    }

    @Override
    public void format(ITreeOut out) {
        out.println(documentClass);
        out.println();
        for (UsePackage usePackage : usePackages.values())
            out.println(usePackage);
        out.println();
        for (String code : codes)
            out.println(code);
    }

}

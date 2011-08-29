package net.bodz.bas.text.diff.gnudiff;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.sio.Stdio;
import net.bodz.bas.text.diff.DiffInfo;
import net.bodz.bas.text.diff.gnudiff.DiffPrint.Base;
import net.bodz.bas.text.diff.gnudiff.DiffPrint.ContextPrint;
import net.bodz.bas.text.diff.gnudiff.DiffPrint.EdPrint;
import net.bodz.bas.text.diff.gnudiff.DiffPrint.NormalPrint;
import net.bodz.bas.text.diff.gnudiff.DiffPrint.UnifiedPrint;

public class DiffPrintTest {

    static List<String> listLines(String filename)
            throws IOException {
        FileInputStream in = new FileInputStream(filename);
        InputStreamReader reader = new InputStreamReader(in, "utf-8");
        BufferedReader r = new BufferedReader(reader);
        List<String> lines = new ArrayList<String>();
        String line;
        while ((line = r.readLine()) != null)
            lines.add(line);
        return lines;
    }

    public static void main(String[] argv)
            throws IOException {
        String filea = argv[argv.length - 2];
        String fileb = argv[argv.length - 1];
        List<String> a = listLines(filea);
        List<String> b = listLines(fileb);
        GNUDiff d = new GNUDiff(a, b);
        char style = 'n';
        for (int i = 0; i < argv.length - 2; ++i) {
            String f = argv[i];
            if (f.startsWith("-")) {
                for (int j = 1; j < f.length(); ++j) {
                    switch (f.charAt(j)) {
                    case 'e': // Ed style
                        style = 'e';
                        break;
                    case 'c': // Context diff
                        style = 'c';
                        break;
                    case 'u':
                        style = 'u';
                        break;
                    }
                }
            }
        }
        boolean reverse = style == 'e';
        d.heuristic = true;
        List<DiffInfo> script = d.diff_2(reverse);
        if (script == null)
            System.err.println("No differences");
        else {
            Base p;
            switch (style) {
            case 'e':
                p = new EdPrint(a, b, Stdio.cout);
                break;
            case 'c':
                p = new ContextPrint(a, b, Stdio.cout);
                break;
            case 'u':
                p = new UnifiedPrint(a, b, Stdio.cout);
                break;
            default:
                p = new NormalPrint(a, b, Stdio.cout);
            }
            p.print_header(filea, fileb);
            p.print_script(script);
        }
    }

}

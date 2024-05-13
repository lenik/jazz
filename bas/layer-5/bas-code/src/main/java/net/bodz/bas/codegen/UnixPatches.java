package net.bodz.bas.codegen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import net.bodz.bas.err.UnexpectedException;

public class UnixPatches {

    public static boolean samePatchBody(String patch1, String patch2) {
        if (patch1 == patch2)
            return true;
        if (patch1 == null || patch2 == null)
            return false;
        BufferedReader in1 = new BufferedReader(new StringReader(patch1));
        BufferedReader in2 = new BufferedReader(new StringReader(patch2));
        while (true) {
            try {
                String line1 = in1.readLine();
                String line2 = in2.readLine();
                if (line1 == null || line2 == null)
                    return line1 == line2;

                if (line1.length() > 4 && line2.length() > 4) {
                    String mode1 = line1.substring(0, 4);
                    String mode2 = line2.substring(0, 4);
                    if (mode1.equals(mode2))
                        switch (mode1) {
                        case "--- ":
                        case "+++ ":
                            // don't compare these header lines.
                            continue;
                        }
                }

                if (! line1.equals(line2))
                    return false;
            } catch (IOException e) {
                throw new UnexpectedException(e.getMessage(), e);
            }
        }
    }

    public static String modifyDiffPath(String diff, String prefix1, String prefix2) {
        BufferedReader in = new BufferedReader(new StringReader(diff));
        String line;
        StringBuilder buf = new StringBuilder(diff.length() + 100);
        String path1 = null;
        try {
            while ((line = in.readLine()) != null) {
                if (line.length() > 4) {
                    switch (line.substring(0, 4)) {
                    case "--- ":
                        path1 = line.substring(4).trim();
                        line = "--- " + prefix1 + "/" + path1;
                        break;
                    case "+++ ":
                        if (path1 == null)
                            throw new NullPointerException("path1");
                        line = "+++ " + prefix2 + "/" + path1;
                        path1 = null;
                        break;
                    }
                }
                buf.append(line);
                buf.append('\n');
            }
        } catch (IOException e) {
            throw new UnexpectedException();
        }
        return buf.toString();
    }

}

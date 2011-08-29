package net.bodz.bas.l10n.zh;

import java.io.Flushable;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.sio.ICharIn;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.sio.ISimpleCharIn;
import net.bodz.bas.sio.ISimpleCharOut;
import net.bodz.bas.sio.StringCharIn;

/**
 * @see <a href="http://www.unicode.org/charts/PDF/UFF00.pdf">Halfwidth and Fullwidth Forms</a>
 */
public class ConvertCharacterWidth {

    static final Set<Character> f2hSpaceBeforeSet;
    static final Set<Character> f2hSpaceAfterSet;
    static {
        f2hSpaceBeforeSet = new HashSet<Character>();
        f2hSpaceAfterSet = new HashSet<Character>();

        String both = "＆＊＋－／＜＝＞＾｜～";
        String before = "“‘（［｛";
        String after = "　”’）］｝！，．：；？";
        for (int i = 0; i < both.length(); i++) {
            char ch = both.charAt(i);
            f2hSpaceBeforeSet.add(ch);
            f2hSpaceAfterSet.add(ch);
        }
        for (int i = 0; i < before.length(); i++)
            f2hSpaceBeforeSet.add(before.charAt(i));
        for (int i = 0; i < after.length(); i++)
            f2hSpaceAfterSet.add(after.charAt(i));
    }

    public static void toHalfWidth(ISimpleCharIn in, ISimpleCharOut out)
            throws IOException {
        // 0xFF00~0xFF5E: full width ASCII chars
        int ch;
        while ((ch = in.read()) != -1) {
            String convstr = null;
            boolean spaceBefore = f2hSpaceBeforeSet.contains(ch);
            boolean spaceAfter = f2hSpaceAfterSet.contains(ch);

            if (ch >= 0xFF00 && ch <= 0xFF5E)
                ch -= 0xFEE0;
            else
                switch (ch) {
                case '　':
                    ch = ' ';
                    break;
                case 0xFF61: // '。'
                    ch = '.';
                    break;
                case '…':
                    convstr = "...";
                    break;
                }

            if (spaceBefore)
                out.write(' ');
            if (convstr != null)
                for (int i = 0; i < convstr.length(); i++)
                    out.write(convstr.charAt(i));
            else
                out.write(ch);
            if (spaceAfter)
                out.write(' ');
        }
    }

    public static String toHalfWidth(String s) {
        BCharOut buf = new BCharOut(s.length());
        try {
            toHalfWidth(new StringCharIn(s), buf);
        } catch (IOException e) {
            throw new UnexpectedException(e);
        }
        return buf.toString();
    }

    static class UndoableCharOut
            implements ISimpleCharOut, Flushable {

        private final ISimpleCharOut out;
        private int preWrite = -1;

        public UndoableCharOut(ISimpleCharOut out) {
            if (out == null)
                throw new NullPointerException("out");
            this.out = out;
        }

        @Override
        public void write(int ch)
                throws IOException {
            if (preWrite != -1)
                out.write(preWrite);
            preWrite = ch;
        }

        public int lookback() {
            return preWrite;
        }

        public int lookback(int depth) {
            if (depth != 1)
                throw new UnsupportedOperationException("only 1 char to look back is supported");
            return preWrite;
        }

        /**
         * @return <code>false</code> If nothing to undo.
         */
        public boolean undo() {
            if (preWrite == -1)
                return false;
            preWrite = -1;
            return true;
        }

        public void flush()
                throws IOException {
            if (preWrite != -1)
                out.write(preWrite);
        }

    }

    public static void toFullWidth(ISimpleCharIn in, ISimpleCharOut out)
            throws IOException {
        // 0x21~0x7e: half width ASCII chars
        UndoableCharOut _out = new UndoableCharOut(out);

        int lastCh = -1;
        int ch;
        boolean removeNextSpace = false;
        while ((ch = in.read()) != -1) {
            if (removeNextSpace) {
                removeNextSpace = false;
                if (ch == ' ')
                    continue;
            }

            boolean isConverted;
            if (ch >= 0x21 && ch <= 0x7E) {
                ch += 0xFEE0;
                isConverted = true;
            } else {
                isConverted = true;
                switch (ch) {
                case ' ':
                    ch = '　';
                    break;
                default:
                    isConverted = false;
                }
            }

            if (isConverted) {
                if (f2hSpaceBeforeSet.contains((char) ch)) {
                    if (lastCh == ' ')
                        _out.undo();
                }
                removeNextSpace = f2hSpaceAfterSet.contains((char) ch);
            }
            _out.write(lastCh = ch);
        }
        _out.flush();
    }

    public static String toFullWidth(String s) {
        BCharOut buf = new BCharOut(s.length());
        try {
            toFullWidth(new StringCharIn(s), buf);
        } catch (IOException e) {
            throw new UnexpectedException(e);
        }
        return buf.toString();
    }

    public static void switchWidth(ICharIn in, ICharOut out)
            throws IOException {
        throw new NotImplementedException();
    }

    public static String switchWidth(String s) {
        BCharOut buf = new BCharOut(s.length());
        try {
            switchWidth(new StringCharIn(s), buf);
        } catch (IOException e) {
            throw new UnexpectedException(e);
        }
        return buf.toString();
    }

    // public static void listWidthStop()

}

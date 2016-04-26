package net.bodz.bas.l10n.zh;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.ISimpleCharIn;
import net.bodz.bas.io.ISimpleCharOut;
import net.bodz.bas.io.StringCharIn;

/**
 * Half width characters:
 * !"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\]^_`abcdefghijklmnopqrstuvwxyz{|}~
 * 
 * Full width characters:
 * ！＂＃＄％＆＇（）＊＋，－．／０１２３４５６７８９：；＜＝＞？＠ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ［＼］＾＿｀ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ｛｜｝～
 * 
 * @see <a href="http://www.unicode.org/charts/PDF/UFF00.pdf">Halfwidth and Fullwidth Forms</a>
 */
public class CharWidthConverter {

    static final Set<Integer> f2hSpaceBeforeSet;
    static final Set<Integer> f2hSpaceAfterSet;
    static {
        f2hSpaceBeforeSet = new HashSet<>();
        f2hSpaceAfterSet = new HashSet<>();

        String both = "＆＊＋－／＜＝＞＾｜～";
        String before = "“‘（［｛";
        String after = "　”’）］｝！，．：；？";
        for (int i = 0; i < both.length(); i++) {
            int ch = both.charAt(i);
            f2hSpaceBeforeSet.add(ch);
            f2hSpaceAfterSet.add(ch);
        }
        for (int i = 0; i < before.length(); i++)
            f2hSpaceBeforeSet.add((int) before.charAt(i));
        for (int i = 0; i < after.length(); i++)
            f2hSpaceAfterSet.add((int) after.charAt(i));
    }

    boolean insertPadSpaces;
    boolean removePadSpaces;

    public String toHalfWidth(String s) {
        BCharOut buf = new BCharOut(s.length());
        try {
            toHalfWidth(new StringCharIn(s), buf);
        } catch (IOException e) {
            throw new UnexpectedException(e);
        }
        return buf.toString();
    }

    public void toHalfWidth(ISimpleCharIn in, ICharOut out)
            throws IOException {
        // 0xFF00~0xFF5E: full width ASCII chars
        int ch;
        while ((ch = in.read()) != -1) {
            String convstr = null;
            boolean spaceBefore = insertPadSpaces && f2hSpaceBeforeSet.contains(ch);
            boolean spaceAfter = insertPadSpaces && f2hSpaceAfterSet.contains(ch);

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
                out.write(convstr);
            else
                out.write(ch);

            if (spaceAfter)
                out.write(' ');
        }
    }

    public String toFullWidth(String s) {
        BCharOut buf = new BCharOut(s.length());
        try {
            toFullWidth(new StringCharIn(s), buf);
        } catch (IOException e) {
            throw new UnexpectedException(e);
        }
        return buf.toString();
    }

    public void toFullWidth(ISimpleCharIn in, ISimpleCharOut out)
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

            if (removePadSpaces && isConverted) {
                if (f2hSpaceBeforeSet.contains(ch)) {
                    if (lastCh == ' ')
                        _out.undo();
                }
                removeNextSpace = f2hSpaceAfterSet.contains(ch);
            }
            _out.write(lastCh = ch);
        }
        _out.flush();
    }

    public String switchWidth(String s) {
        BCharOut buf = new BCharOut(s.length());
        try {
            switchWidth(new StringCharIn(s), buf);
        } catch (IOException e) {
            throw new UnexpectedException(e);
        }
        return buf.toString();
    }

    public void switchWidth(ICharIn in, ICharOut out)
            throws IOException {
        throw new NotImplementedException();
    }

    // public static void listWidthStop()

    static final CharWidthConverter NORMAL;
    static final CharWidthConverter COMPACT;
    static {
        NORMAL = new CharWidthConverter();
        NORMAL.insertPadSpaces = true;
        NORMAL.removePadSpaces = true;
        COMPACT = new CharWidthConverter();
        COMPACT.insertPadSpaces = false;
        COMPACT.removePadSpaces = true;
    }

}

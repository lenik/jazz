package net.bodz.bas.l10n.zh.conv;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;

import net.bodz.bas.fmt.util.FourCC;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.res.builtin.FileResource;

public class OpenCCDatrie
        extends Assert {

    private static int M_OPEN = FourCC.littleEndian("OPEN");
    private static int M_CCDA = FourCC.littleEndian("CCDA");
    private static int M_TRIE = FourCC.littleEndian("TRIE");

    private String words[];
    private int index[];

    private int dat[];
    private int datItemCount;

    public String word(int index) {
        return words[index];
    }

    /**
     * @param mapbuf
     *            array of [match-length, word-index]
     * @return mapsize ( == match-count * 2 )
     */
    int convert(String str, int[] mapbuf) {
        int mapsize = 0;
        int len = str.length();
        int pos = 0;
        int prev = 0;
        while (pos < len) {
            int table = dat[prev * 3 + 0];
            int ch = str.charAt(pos);
            int index = table + ch;
            if (index < 0 || index >= datItemCount)
                break;
            int parent = dat[index * 3 + 1];
            if (parent != prev)
                break;
            prev = index;
            int word = dat[prev * 3 + 2];
            if (word != -1) {
                mapbuf[mapsize++] = pos + 1;
                mapbuf[mapsize++] = word;
            }
            pos++;
        }
        return mapsize;
    }

    public void parseOcd(File file)
            throws IOException {
        IDataIn in = new FileResource(file).newDataInLE();

        assertEquals(M_OPEN, in.readDword());
        assertEquals(M_CCDA, in.readDword());
        assertEquals(M_TRIE, in.readDword());

        int lexiconLength = in.readDword();

        int[] lexicon = new int[lexiconLength];
        in.readDwords(lexicon);

        int indexLength = in.readDword();
        index = new int[indexLength];
        in.readDwords(index);

        int lexiconCount = in.readDword();
        words = new String[lexiconCount];

        datItemCount = in.readDword();
        dat = new int[datItemCount * 3];
        in.readDwords(dat);

        int last = 0;
        for (int i = 0; i < lexiconCount; i++) {
            int j;
            for (j = last; j < indexLength; j++)
                if (index[j] == -1)
                    break;

            int count = j - last;
            StringBuilder sb = new StringBuilder(count /* * 2 */);
            for (j = 0; j < count; j++) {
                int off = index[last + j];
                while (lexicon[off] != 0) {
                    int ch = lexicon[off];
                    sb.append((char) ch);
                    off++;
                }
            }
            words[i] = sb.toString();

            last += j + 1;
        }

        in.close();
    }

    static char[] toUcs16(int[] ucs32) {
        char[] ucs16 = new char[ucs32.length];
        for (int i = 0; i < ucs32.length; i++)
            ucs16[i] = (char) ucs32[i];
        return ucs16;
    }

}

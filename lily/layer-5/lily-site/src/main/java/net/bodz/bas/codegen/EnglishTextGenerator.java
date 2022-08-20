package net.bodz.bas.codegen;

import java.util.Random;

import net.bodz.bas.err.UnexpectedException;

public class EnglishTextGenerator {

    static char[] textTab = "aaaaeeeeiiiioooouuuuubcdfghjklmnpqrstvwxyz".toCharArray();
    static char[] breakTab = "               ,,,,,;;..-_\'@#&*?!".toCharArray();
    static char[] symTab = "......;?!".toCharArray();

    static Random random = new Random();

    public static String makeText(int maxLen, int maxWordLen) {
        boolean endWithSym = random.nextBoolean();
        int len = random.nextInt(maxLen + 1);
        if (len <= 1)
            endWithSym = false;
        if (endWithSym)
            len--;

        StringBuilder sb = new StringBuilder();
        int pos = 0;
        while (pos < len) {
            int wordLen; // = random.nextInt(maxWordLen) + 1;
            do {
                double p = random.nextGaussian() * 2 + 3.5;
                wordLen = (int) p;
            } while (wordLen < 1 || wordLen > maxLen);

            if (pos + wordLen > len)
                wordLen = len - pos;
            else if (pos + wordLen + 3 > len)
                // len - (pos + wordLen) <= 2: will cause extra space problem.
                wordLen = len - pos;

            for (int j = 0; j < wordLen; j++) {
                int ord = random.nextInt(textTab.length);
                char ch = textTab[ord];
                if (j == 0) {
                    if (random.nextInt(pos == 0 ? 2 : 10) == 0)
                        ch = Character.toUpperCase(ch);
                }
                sb.append(ch);
            }

            pos += wordLen;

            if (pos < len - 1) {
                int breakOrd = random.nextInt(breakTab.length);

                char breakCh = breakTab[breakOrd];
                sb.append(breakCh);
                pos++;
                switch (breakCh) {
                case '&':
                case '*':
                case '-':
                case '_':
                case '@':
                case '#':
                case '\'':
                case ' ':
                    break;
                default:
                    if (pos < len - 1) {
                        sb.append(' ');
                        pos++;
                    }
                }
            }
        }
        if (endWithSym) {
            int symOrd = random.nextInt(symTab.length);
            char symCh = symTab[symOrd];
            sb.append(symCh);
            pos++;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int max = 80;
        for (int i = 0; i < 10000; i++) {
            String s = EnglishTextGenerator.makeText(max, 12);
            if (s.length() > max)
                throw new UnexpectedException();
            System.out.println(s);
        }
    }

}

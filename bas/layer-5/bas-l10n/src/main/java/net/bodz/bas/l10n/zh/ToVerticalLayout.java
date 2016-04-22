package net.bodz.bas.l10n.zh;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import net.bodz.bas.c.java.nio.Charsets;

public class ToVerticalLayout {

    static final String verticalLine = "┆";
    static final Charset charset = Charsets.UTF8;

    public static void main(String[] args)
            throws IOException, UnsupportedFlavorException {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        String src;
        if (args.length == 0) {

            Object data = clipboard.getData(DataFlavor.stringFlavor);
            src = (String) data;
            System.out.println("Source: ");
            System.out.println(src);
            // throw new IllegalArgumentException("Syntax: ToVerticalLayout FILE/STR/CLIPBOARD");
        } else {
            src = args[0];
            if (src.length() < 1024) {
                File srcfile = new File(src);
                if (srcfile.exists()) {
                    Reader in = new InputStreamReader(new FileInputStream(srcfile), charset);
                    int approxChars = (int) (charset.newDecoder().averageCharsPerByte() * (int) srcfile.length());
                    StringBuilder buf = new StringBuilder(approxChars);
                    char[] block = new char[1024];
                    int cc;
                    while ((cc = in.read(block)) != -1)
                        buf.append(block, 0, cc);
                    in.close();
                    src = buf.toString();
                }
            }
        }

        int width = 20;
        String widthProperty = System.getProperty("width");
        if (widthProperty != null)
            width = Integer.parseInt(widthProperty);

        StringBuilder buf = new StringBuilder();

        for (String paragraph : src.split("\n+")) {
            if (paragraph.trim().isEmpty()) {
                buf.append("\n");
                continue;
            }
            paragraph = CharWidthConverter.NORMAL.toFullWidth(paragraph);
            // src = src.replace("\n", "　　");
            // src = "　　" + src;

            int len = paragraph.length();
            int height = (len + width - 1) / width;

            // re-adjust width to avoid much of space
            // width = (len + height - 1) / height;

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int index = (width - 1 - x) * height + y;
                    char ch = index < paragraph.length() ? paragraph.charAt(index) : '　';
                    buf.append(verticalLine + ch);
                }
                buf.append(verticalLine + "\n");
            }
            buf.append('\n');
        }

        String result = buf.toString();
        System.out.println("Result: ");
        System.out.println(result);
        clipboard.setContents(new StringSelection(result), null);
    }

}

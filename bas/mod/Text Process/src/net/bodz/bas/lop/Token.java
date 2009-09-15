package net.bodz.bas.lop;

import net.bodz.bas.lop.util.XYTellable;

public interface Token extends XYTellable {

    int getId();

    String getName();

    String getText();

    int length();

    char charAt(int index);

    Object getValue();

    /**
     * 在文本中，除了被识别为 Token 的文字外，还有其它可能被忽略的字符，如空格、换行等。通常 Token 所占据的文字块比词法分析中单次扫描过的文字要少。
     * {@link #getBoundary()}返回本次扫描的停止为止。通过记录每个 Token 的边界位置，即可得出一个 Token 所占据的扫描块范围。
     */
    XYTellable getBoundary();

}

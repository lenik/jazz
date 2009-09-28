package net.bodz.mda.tmpl.xtx;

import java.io.IOException;
import java.io.Reader;

import net.bodz.bas.io.CharOuts.BCharOut;
import net.bodz.bas.types.sg.InvalidStateException;

public class XtxParser {

    Reader reader;

    public XtxParser(Reader reader) {
        this.reader = reader;
    }

    public static final int CODE_GATE = '\u00AC'; // ¬
    public static final int ESCAPE    = '\\';

    static final int        S_TEXT    = 0;
    static final int        S_CODE    = 1;
    static final int        S_EXPR    = 2;

    void parse() throws IOException {
        int state = S_TEXT;
        int braceLevel = 0;
        CodeEmitter emitter = null;
        BCharOut buffer = new BCharOut();
        while (true) {
            int c = reader.read();
            if (c == ESCAPE) {
                c = reader.read();
                switch (c) {
                case CODE_GATE:
                case '{':
                case '}':
                    buffer._write(c);
                    continue;
                }
                buffer._write(ESCAPE);
            }
            switch (state) {
            case S_TEXT:
                switch (c) {
                case CODE_GATE:
                    state = S_CODE;
                    break;
                case '{':
                    state = S_EXPR;
                    braceLevel++;
                    break;
                default:
                    buffer._write(c);
                    continue;
                }
                String text = buffer.flip();
                emitter.emitText(text);
                break;
            case S_CODE:
                switch (c) {
                case '\n':
                    buffer._write('\n');
                case CODE_GATE:
                    state = S_TEXT;
                    break;
                default:
                    buffer._write(c);
                    continue;
                }
                String code = buffer.flip();
                emitter.emitCode(code);
                break;
            case S_EXPR:
                switch (c) {
                case '{':
                    braceLevel++;
                    break;
                case '}':
                    braceLevel--;
                    break;
                }
                buffer._write(c);
                if (braceLevel == 0) {
                    String exprCode = buffer.flip();
                    emitter.emitExpr(exprCode);
                    state = S_TEXT;
                }
                break;
            default:
                throw new InvalidStateException();
            }
        }

    }
}

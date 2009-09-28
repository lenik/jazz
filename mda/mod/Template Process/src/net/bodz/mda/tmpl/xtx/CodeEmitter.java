package net.bodz.mda.tmpl.xtx;

import java.io.IOException;

public interface CodeEmitter {

    void setSourceLocation(String source);

    void setSourcePosition(int line, int column);

    void start();

    void emitCode(String code) throws IOException;

    void emitExpr(String exprCode) throws IOException;

    void emitText(String text) throws IOException;

    void end();

}

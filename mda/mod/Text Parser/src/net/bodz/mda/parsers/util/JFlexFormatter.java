package net.bodz.mda.parsers.util;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.CharOuts.Buffer;
import net.bodz.bas.lang.err.OutOfDomainException;
import net.bodz.bas.types.util.Empty;
import net.bodz.bas.types.util.Strings;
import net.bodz.mda.java.initial.util.Imports;
import net.bodz.mda.parsers.LexAnnotations;
import net.bodz.mda.parsers.LexMatchAcceptor;
import net.bodz.mda.parsers._LexMatch;
import net.bodz.mda.parsers.LexAnnotations.Alignment;

public class JFlexFormatter extends LexMatchAcceptor {

    private Imports       imports;
    private Buffer        header;
    private Buffer        decl;
    private Buffer        body;
    private Alignment     alignment;
    private int           ruleAlignInState;
    private int           indent;
    private Set<Class<?>> rulesExceptions;

    public JFlexFormatter(Class<?> lexerModel, String className) {
        LexAnnotations la = new LexAnnotations(lexerModel);
        alignment = la.getAlignment();
        imports = new Imports(lexerModel);
        header = new Buffer();
        decl = new Buffer();
        body = new Buffer();
        rulesExceptions = new HashSet<Class<?>>();

        la.visit(this);

        Package package_ = lexerModel.getPackage();
        if (package_ != null)
            header.println("package " + package_.getName() + ";");
        if (!imports.isEmpty()) {
            header.println();
            imports.dump(header);
        }
    }

    void indent(CharOut out) {
        out.println(Strings.repeat(indent * 4, ' '));
    }

    @Override
    protected void symbol(String name, String value) {
        indent(decl);
        int symAlign = alignment.getSymbolAlignment();
        decl.printf("%s = %s\n", //
                Strings.padRight(name, symAlign), value);
    }

    @Override
    protected void enter(String state) {
        boolean exclusive = state.startsWith("-");
        if (exclusive) {
            state = state.substring(1);
            indent(decl);
            decl.println("%x " + state);
        }
        indent(body);
        body.println("<" + state + "> {");
        indent++;
        ruleAlignInState = alignment.getRuleAlignment(state);
    }

    @Override
    protected void leave(String state) {
        indent--;
        body.println("}");
    }

    @Override
    protected void rule(_LexMatch match, Method action, int mode) {
        // ControlBreak
        indent(body);
        String _action = null;
        if (action != null)
            switch (mode) {
            case VOID:
                _action = action + "();";
                break;
            case YYTEXT:
                _action = action + "(yytext);"; // getText()
                break;
            default:
                throw new OutOfDomainException("mode", mode);
            }
        if (_action == null)
            _action = "";
        else {
            for (Class<?> et : action.getExceptionTypes()) {
                imports.add(et);
                rulesExceptions.add(et);
            }
            Class<?> retType = action.getReturnType();
            if (retType == void.class) {
                if (match.isMayReturn()) {
                    String LEXVT = "";
                    _action = "try {" //
                            + _action //
                            + "} catch (ControlReturn ret) {" //
                            + "return " + LEXVT + "ret.getReturnValue(); " //
                            + "}";
                }
            } else {
                _action = "return " + _action;
            }
            _action = "{ " + _action + " }";
        }
        body.printf("%s %s", //
                Strings.padRight(match.getValue(), ruleAlignInState), _action);
    }

    public String getHeader() {
        return header.toString();
    }

    public String getDecl() {
        return decl.toString();
    }

    public String getBody() {
        return body.toString();
    }

    public Class<?>[] getRulesExceptions() {
        return rulesExceptions.toArray(Empty.Classes);
    }

}

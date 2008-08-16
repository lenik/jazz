package net.bodz.mda.parsers.util;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.CharOuts.Buffer;
import net.bodz.bas.lang.err.OutOfDomainException;
import net.bodz.bas.types.util.Empty;
import net.bodz.bas.types.util.Strings;
import net.bodz.mda.java.codesnippets.CopyConstructor;
import net.bodz.mda.java.initial.util.Imports;
import net.bodz.mda.parsers.LexAnnotations;
import net.bodz.mda.parsers.LexMatchAcceptor;
import net.bodz.mda.parsers._LexMatch;
import net.bodz.mda.parsers.LexAnnotations.Alignment;

public class JFlexFormatter extends LexMatchAcceptor {

    private String        simpleName;
    private String        simpleSuperName;
    private Imports       imports;
    private Buffer        header;
    private Buffer        decl;
    private Buffer        body;
    private Set<Class<?>> rulesExceptions;
    private Alignment     alignment;
    private int           currentRuleAlignment;
    private int           indent;

    public JFlexFormatter(Class<?> lexerModel, String className) {
        simpleName = Strings.afterLast(className, '.');
        simpleSuperName = lexerModel.getSimpleName();

        imports = new Imports(lexerModel);
        header = new Buffer();
        decl = new Buffer();
        body = new Buffer();
        rulesExceptions = new HashSet<Class<?>>();
        LexAnnotations la = new LexAnnotations(lexerModel);
        alignment = la.getAlignment();

        imports.add(lexerModel);

        CopyConstructor cc = new CopyConstructor();
        cc.setImports(imports);
        cc.setIndent(indent + 1);
        String ctors = cc.generate(lexerModel, className);
        decl.println("%{");
        decl.print(ctors);
        decl.println("%}");

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
        if (state == null || state.isEmpty())
            return;
        boolean exclusive = state.startsWith("-");
        if (exclusive) {
            state = state.substring(1);
            indent(decl);
            decl.println("%x " + state);
        }
        indent(body);
        body.println("<" + state + "> {");
        indent++;
        currentRuleAlignment = alignment.getRuleAlignment(state);
    }

    @Override
    protected void leave(String state) {
        if (state == null || state.isEmpty())
            return;
        indent--;
        body.println("}");
    }

    @Override
    protected void rule(_LexMatch match, Method actionMethod, int mode) {
        // ControlBreak
        indent(body);
        String _action = null;
        if (actionMethod != null)
            switch (mode) {
            case VOID:
                _action = actionMethod.getName() + "();";
                break;
            case YYTEXT:
                _action = actionMethod.getName() + "(yytext());"; // getText()
                break;
            default:
                throw new OutOfDomainException("mode", mode);
            }
        if (_action == null)
            _action = "";
        else {
            for (Class<?> et : actionMethod.getExceptionTypes()) {
                imports.add(et);
                rulesExceptions.add(et);
            }
            Class<?> retType = actionMethod.getReturnType();
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
        body.printf(
                "%s %s", //
                Strings.padRight(match.getValue(), currentRuleAlignment),
                _action);
    }

    public String getSimpleName() {
        return simpleName;
    }

    public String getSimpleSuperName() {
        return simpleSuperName;
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

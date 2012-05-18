package net.bodz.bas.text.lop;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class LexAnnotations {

    private TreeMap<AbstractLexMatch, Field> syms;
    private TreeMap<AbstractLexMatch, Method> rules;

    public LexAnnotations(Class<?> clazz, boolean inherits) {
        syms = new TreeMap<AbstractLexMatch, Field>();
        rules = new TreeMap<AbstractLexMatch, Method>();
        load(clazz, inherits);
    }

    public LexAnnotations(Class<?> clazz) {
        this(clazz, true);
    }

    void load(Class<?> clazz, boolean inherits) {
        if (inherits) {
            Class<?> parent = clazz.getSuperclass();
            if (parent != null)
                load(parent, inherits);
        }

        // const
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            int mod = field.getModifiers();
            if (!Modifier.isStatic(mod))
                continue;
            LexMatch a = field.getAnnotation(LexMatch.class);
            if (a != null)
                loadSymbol(new AbstractLexMatch(a, i), field);
        }

        // rules
        Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            LexMatch a = method.getAnnotation(LexMatch.class);
            if (a != null)
                loadRule(new AbstractLexMatch(a, i), method);
        }
    }

    void loadSymbol(AbstractLexMatch symval, Field symdecl) {
        syms.put(symval, symdecl);
    }

    void loadRule(AbstractLexMatch match, Method action) {
        rules.put(match, action);
    }

    public synchronized void visit(LexMatchAcceptor acceptor) {
        for (Entry<AbstractLexMatch, Field> ent : syms.entrySet()) {
            AbstractLexMatch symval = ent.getKey();
            Field symdecl = ent.getValue();
            acceptor.symbol(symdecl.getName(), symval.getValue());
        }
        String lastState = null;
        for (Entry<AbstractLexMatch, Method> ent : rules.entrySet()) {
            AbstractLexMatch match = ent.getKey();
            String state = match.getState();
            if (lastState == null) {
                acceptor.enter(state);
            } else if (state != lastState) {
                acceptor.leave(lastState);
                acceptor.enter(state);
            }
            Method action = ent.getValue();
            Class<?>[] params = action.getParameterTypes();
            switch (params.length) {
            case 0: // action()
                acceptor.rule(match, action, LexMatchAcceptor.VOID);
                break;
            case 1: // action(yytext)
                if (params[0] != String.class)
                    throw new RuntimeException("illegal action type: " + action);
                acceptor.rule(match, action, LexMatchAcceptor.YYTEXT);
                break;
            default:
                throw new RuntimeException("illegal action type: " + action);
            }
        }
        if (lastState != null)
            acceptor.leave(lastState);
    }

    public static class Alignment
            extends LexMatchAcceptor {

        private int symSize;
        private Map<String, Integer> ruleSizeInState;
        private int ruleSizeInDefaultState;
        private int ruleSize;

        @Override
        protected void symbol(String name, String value) {
            if (name.length() > symSize)
                symSize = name.length();
        }

        @Override
        protected void enter(String state) {
            ruleSize = 0;
        }

        @Override
        protected void leave(String state) {
            if (state == null || state.isEmpty())
                ruleSizeInDefaultState = ruleSize;
            else {
                if (ruleSizeInState == null)
                    ruleSizeInState = new HashMap<String, Integer>();
                ruleSizeInState.put(state, ruleSize);
            }
        }

        @Override
        protected void rule(AbstractLexMatch match, Method action, int mode) {
            int len = match.getValue().length();
            if (len > ruleSize)
                ruleSize = len;
        }

        public int getSymbolAlignment() {
            return symSize;
        }

        public int getRuleAlignment(String state) {
            if (state == null || state.isEmpty())
                return ruleSizeInDefaultState;
            Integer val = null;
            if (ruleSizeInState != null)
                val = ruleSizeInState.get(state);
            if (val == null) {
                // return 0;
                throw new IllegalArgumentException("no such state: " + state);
            }
            return val;
        }

    }

    public Alignment getAlignment() {
        Alignment alignment = new Alignment();
        visit(alignment);
        return alignment;
    }

}

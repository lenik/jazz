package net.bodz.bas.lop;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class LexAnnotations {

    private TreeMap<_LexMatch, Field>  syms;
    private TreeMap<_LexMatch, Method> rules;

    public LexAnnotations(Class<?> clazz, boolean inherits) {
        syms = new TreeMap<_LexMatch, Field>();
        rules = new TreeMap<_LexMatch, Method>();
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
                loadSymbol(new _LexMatch(a, i), field);
        }

        // rules
        Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            LexMatch a = method.getAnnotation(LexMatch.class);
            if (a != null)
                loadRule(new _LexMatch(a, i), method);
        }
    }

    void loadSymbol(_LexMatch symval, Field symdecl) {
        syms.put(symval, symdecl);
    }

    void loadRule(_LexMatch match, Method action) {
        rules.put(match, action);
    }

    public synchronized void visit(LexMatchAcceptor acceptor) {
        for (Entry<_LexMatch, Field> ent : syms.entrySet()) {
            _LexMatch symval = ent.getKey();
            Field symdecl = ent.getValue();
            acceptor.symbol(symdecl.getName(), symval.getValue());
        }
        String lastState = null;
        for (Entry<_LexMatch, Method> ent : rules.entrySet()) {
            _LexMatch match = ent.getKey();
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

    public static class Alignment extends LexMatchAcceptor {

        private int                  symSize;
        private Map<String, Integer> ruleSizeInState;
        private int                  ruleSizeInDefaultState;
        private int                  ruleSize;

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
        protected void rule(_LexMatch match, Method action, int mode) {
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

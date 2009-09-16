package net.bodz.xfo.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import net.bodz.bas.io.CharOuts.BCharOut;
import net.bodz.bas.lang.a.Surrogate;
import net.bodz.bas.types.util.Strings;

/**
 * @test
 */
public class FSM {

    class TransitionMap {

        private Map<Integer, Integer> char2next;
        private int                   other;

        public TransitionMap(int other) {
            this.char2next = new TreeMap<Integer, Integer>();
            this.other = other;
        }

        public void setNext(int ch, int state) {
            char2next.put(ch, state);
        }

        public Integer _getNext(int ch) {
            return char2next.get(ch);
        }

        public int getNext(int ch) {
            Integer state = char2next.get(ch);
            if (state == null)
                return other;
            return state;
        }

        public int getOther() {
            return other;
        }

        public void setOther(int other) {
            this.other = other;
        }

        @Override
        public String toString() {
            BCharOut out = new BCharOut();
            out.println("TransitionMap: ");
            for (Entry<Integer, Integer> e : char2next.entrySet()) {
                int ch = e.getKey();
                int state = e.getValue();
                out.printf("    '%s' => %s\n", Strings.escape(ch), getStateName(state));
            }
            return out.toString();
        }

    }

    public static final int      START = 0;
    public static final int      ERROR = 1;

    private List<TransitionMap>  states;
    private Map<Integer, String> stateNames;

    public FSM() {
        states = new ArrayList<TransitionMap>();
        stateNames = new TreeMap<Integer, String>();
        states.add(new TransitionMap(START));
        states.add(new TransitionMap(ERROR));
        stateNames.put(START, "START");
        stateNames.put(ERROR, "ERROR");
    }

    public int prepare(String stateName, int start, String s) {
        int state = prepare(start, s, null);
        stateNames.put(state, stateName);
        return state;
    }

    /**
     * @return end if end isn't null, or new created state.
     */
    @Surrogate(false)
    public int prepare(int start, String s, Integer end) {
        if (s == null)
            throw new NullPointerException("s");
        if (s.isEmpty())
            throw new IllegalArgumentException("s is empty ");
        int state = start;
        int max = s.length() - 1;
        for (int i = 0; i <= max; i++) {
            TransitionMap stateMap = states.get(state);
            char ch = s.charAt(i);
            if (i == max && end != null) {
                stateMap.setNext(ch, end);
                state = end;
            } else {
                Integer _next = stateMap._getNext(ch);
                if (_next != null)
                    state = _next;
                else {
                    states.add(new TransitionMap(ERROR));
                    state = states.size() - 1;
                    stateMap.setNext(ch, state);
                }
            }
        }
        return state;
    }

    public String getStateName(int state) {
        String name = stateNames.get(state);
        return name == null ? String.valueOf(state) : name;
    }

    @Override
    public String toString() {
        BCharOut out = new BCharOut(states.size() * 100);
        for (int i = 0; i < states.size(); i++) {
            TransitionMap tm = states.get(i);
            out.println("State " + getStateName(i) + ": " + tm);
        }
        return out.toString();
    }

}

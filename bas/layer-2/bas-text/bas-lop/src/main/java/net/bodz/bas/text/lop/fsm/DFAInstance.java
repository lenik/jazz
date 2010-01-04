package net.bodz.bas.text.lop.fsm;

public class DFAInstance {

    private final DFA dfa;

    private DFAState state;

    public DFAInstance(DFA dfa, int startStateId) {
        if (dfa == null)
            throw new NullPointerException("dfa");
        this.dfa = dfa;
        // this.stateId = startStateId;
    }

    public DFAState getState() {
        return state;
    }

    public void setState(int stateId) {
    }

    public void enter(int ch) {
        state = state.getNext(ch);
    }

    public void enter(String s) {
        int len = s.length();
        for (int i = 0; i < len; i++)
            enter(s.charAt(i));
    }

    @Override
    public String toString() {
        return dfa.getName() + "#" + state.getName();
    }

}

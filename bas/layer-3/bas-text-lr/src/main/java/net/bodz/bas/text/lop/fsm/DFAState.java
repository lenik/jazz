package net.bodz.bas.text.lop.fsm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.sio.IPrintOut;

/**
 * DFA State: For any two of transitions in a DFA State, their CharSet are not intersected.
 */
public class DFAState
        implements Comparable<DFAState> {

    private final DFA dfa;
    private final int id;
    private final String name;
    private final int flags;

    /**
     * TODO: 为了改善访问性能，避免new Char(ch)的额外开销，应该使用一种新型的双列TreeMap.
     */
    private TreeMap<CharSet, DFAState> transitionMap;

    private DFAState defaultTransition;

    DFAState(DFA dfa, int id, String name) {
        this(dfa, id, name, 0);
    }

    DFAState(DFA dfa, int id, String name, int flags) {
        if (dfa == null)
            throw new NullPointerException("dfa");
        this.dfa = dfa;
        this.id = id;
        if (name == null)
            name = String.valueOf(id);
        this.name = name;
        this.flags = flags;
        this.transitionMap = new TreeMap<CharSet, DFAState>();
    }

    public DFA getDFA() {
        return dfa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFlags() {
        return flags;
    }

    public DFAState getDefaultTransition() {
        return defaultTransition;
    }

    public void setDefaultTransition(DFAState defaultTransition) {
        this.defaultTransition = defaultTransition;
    }

    public Entry<CharSet, DFAState> getNextEntry(int ch) {
        Char _ch = new Char(ch);
        Entry<CharSet, DFAState> entry = transitionMap.floorEntry(_ch);
        while (entry != null) {
            CharSet key = entry.getKey();
            if (key.contains(ch))
                return entry;
            entry = transitionMap.higherEntry(key);
        }
        return null;
    }

    public DFAState getNext(int ch) {
        Entry<CharSet, DFAState> entry = getNextEntry(ch);
        if (entry != null)
            return entry.getValue();
        return defaultTransition;
    }

    /**
     * @return existing or new created next state.
     */
    public PrepareResult prepare(CharSet chars, String nameHint) {
        Entry<CharSet, DFAState> entry = transitionMap.floorEntry(chars);
        CharSet ceiling = transitionMap.ceilingKey(chars);
        List<SplitResult> splits = new ArrayList<SplitResult>();
        while (entry != null) {
            CharSet key = entry.getKey();
            DFAState state = entry.getValue();
            if (key == ceiling)
                break;
            switch (key.intersects(chars)) {
            case CharSet.NONE:
                DFAState newState = dfa.addState(nameHint);
                transitionMap.put(chars, newState);
                break;
            case CharSet.UNKNOWN:
            case CharSet.PARTIAL:
            case CharSet.ALL_THIS: // key < chars
            case CharSet.ALL_THAT: // key > chars
            case CharSet.EQUALS: // key = chars
                SplitResult split = key.split(chars);
                transitionMap.remove(key);
                DFAState ns = null;
                CharSet[] thisOnly = split.getThis();
                CharSet[] thatOnly = split.getThat();
                if (thatOnly.length != 0)
                    ns = dfa.addState(nameHint);
                for (CharSet cs : split.getThis())
                    transitionMap.put(cs, state);
                for (CharSet cs : thatOnly)
                    transitionMap.put(cs, ns);
                for (CharSet common : split.getCommon()) {
                    ; // transitionMap.put(common, stateMerge);
                }
                break;

            }
            entry = transitionMap.higherEntry(key);
        }
        // return splits.toArray(new SplitResult[0]);
        throw new NotImplementedException();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(DFAState o) {
        if (o == null)
            return 1;
        return id - o.id;
    }

    public void dumpTransitionMap(IPrintOut out, String prefix) {
        if (defaultTransition != null)
            out.println(prefix + "default => " + defaultTransition.getName());
        for (Entry<CharSet, DFAState> e : transitionMap.entrySet()) {
            CharSet chars = e.getKey();
            DFAState state = e.getValue();
            out.println(prefix + chars + " => " + state.getName());
        }
    }

}

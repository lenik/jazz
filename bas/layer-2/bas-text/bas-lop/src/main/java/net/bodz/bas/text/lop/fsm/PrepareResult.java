package net.bodz.bas.text.lop.fsm;

import java.util.ArrayList;
import java.util.List;

public class PrepareResult {

    public static class Split extends SplitResult {

        private final CharSet origThis;
        private final DFAState origNext;
        private final CharSet _thisNext;
        private final CharSet _thatNext;
        private final CharSet _commonNext;

        public Split(Object _this, Object that, Object common, CharSet origThis, DFAState origNext, CharSet thisNext,
                CharSet thatNext, CharSet commonNext) {
            super(_this, that, common);
            this.origThis = origThis;
            this.origNext = origNext;
            _thisNext = thisNext;
            _thatNext = thatNext;
            _commonNext = commonNext;
        }

    }

    private List<Split> splits;

    public PrepareResult() {
        this.splits = new ArrayList<Split>();
    }

    public void logSplit(CharSet origThis, DFAState origNext, SplitResult result) {

    }

}

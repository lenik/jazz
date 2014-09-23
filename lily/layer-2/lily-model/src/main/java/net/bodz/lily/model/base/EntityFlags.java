package net.bodz.lily.model.base;

import net.bodz.bas.c.primitive.Flags32;

public class EntityFlags
        extends Flags32 {

    private static final long serialVersionUID = 1L;

    // Basic Area [0..7]
    public static final int MARKED = 1 << 0; // LTS
    public static final int DELETED = 1 << 1; // LTS
    public static final int HIDDEN = 1 << 2; // LTS
    public static final int LOCKED = 1 << 3; // LTS
    public static final int UNLOCK_REQ = 1 << 4; // LTS
    public static final int USER_LOCK = 1 << 5; // LTS

    // Private Area [8..15]
    public static final int EXTRA = 1 << 8;
    public static final int EXTRA2 = 1 << 9;
    public static final int USER_SEL1 = 1 << 10;
    public static final int USER_SEL2 = 1 << 11;
    public static final int USER_SEL3 = 1 << 12;
    public static final int OVERRIDED = 1 << 13; // LTS: Mutable builtin/test data.
    public static final int TEST_DATA = 1 << 14; // LTS
    public static final int BUILTIN_DATA = 1 << 15; // LTS

    // Style Area [16..23]
    public static final int BOLD = 1 << 16; // LTS
    public static final int ITALIC = 1 << 17; // LTS
    public static final int UNDERLINE = 1 << 18;
    public static final int STRIKELINE = 1 << 19; // LTS
    public static final int GRAY = 1 << 20;
    public static final int BLINK = 1 << 21;

    // Severity Area [24..31]
    public static final int WARN = 1 << 30; // LTS
    public static final int ERROR = 1 << 31; // LTS
    public static final int FATAL = WARN | ERROR; // LTS

    public EntityFlags() {
        super();
    }

    public EntityFlags(int flags) {
        super(flags);
    }

    public boolean isMarked() {
        return test(MARKED);
    }

    public void setMarked(boolean marked) {
        setOrClear(MARKED, marked);
    }

    public boolean isDeleted() {
        return test(DELETED);
    }

    public void setDeleted(boolean deleted) {
        setOrClear(DELETED, deleted);
    }

    public boolean isHidden() {
        return test(HIDDEN);
    }

    public void setHidden(boolean hidden) {
        setOrClear(HIDDEN, hidden);
    }

    public boolean isLocked() {
        return test(LOCKED);
    }

    public void setLocked(boolean locked) {
        setOrClear(LOCKED, locked);
    }

    public boolean isUnlockReq() {
        return test(UNLOCK_REQ);
    }

    public void setUnlockReq(boolean unlockReq) {
        setOrClear(UNLOCK_REQ, unlockReq);
    }

    public boolean isUserLock() {
        return test(USER_LOCK);
    }

    public void setUserLock(boolean userLock) {
        setOrClear(USER_LOCK, userLock);
    }

    public boolean isExtra() {
        return test(EXTRA);
    }

    public void setExtra(boolean extra) {
        setOrClear(EXTRA, extra);
    }

    public boolean isExtra2() {
        return test(EXTRA2);
    }

    public void setExtra2(boolean extra2) {
        setOrClear(EXTRA2, extra2);
    }

    public boolean isUserSel1() {
        return test(USER_SEL1);
    }

    public void setUserSel1(boolean userSel1) {
        setOrClear(USER_SEL1, userSel1);
    }

    public boolean isUserSel2() {
        return test(USER_SEL2);
    }

    public void setUserSel2(boolean userSel2) {
        setOrClear(USER_SEL2, userSel2);
    }

    public boolean isUserSel3() {
        return test(USER_SEL3);
    }

    public void setUserSel3(boolean userSel3) {
        setOrClear(USER_SEL3, userSel3);
    }

    public boolean isOverrided() {
        return test(OVERRIDED);
    }

    public void setOverrided(boolean overrided) {
        setOrClear(OVERRIDED, overrided);
    }

    public boolean isBuitlinData() {
        return test(BUILTIN_DATA);
    }

    public void setBuiltinData(boolean builtinData) {
        setOrClear(BUILTIN_DATA, builtinData);
    }

    public boolean isTestData() {
        return test(TEST_DATA);
    }

    public void setTestData(boolean testData) {
        setOrClear(TEST_DATA, testData);
    }

    public boolean isBold() {
        return test(BOLD);
    }

    public void setBold(boolean bold) {
        setOrClear(BOLD, bold);
    }

    public boolean isItalic() {
        return test(ITALIC);
    }

    public void setItalic(boolean italic) {
        setOrClear(ITALIC, italic);
    }

    public boolean isUnderline() {
        return test(UNDERLINE);
    }

    public void setUnderline(boolean underline) {
        setOrClear(UNDERLINE, underline);
    }

    public boolean isStrikeline() {
        return test(STRIKELINE);
    }

    public void setStrikeline(boolean strikeline) {
        setOrClear(STRIKELINE, strikeline);
    }

    public boolean isGray() {
        return test(GRAY);
    }

    public void setGray(boolean gray) {
        setOrClear(GRAY, gray);
    }

    public boolean isBlink() {
        return test(BLINK);
    }

    public void setBlink(boolean blink) {
        setOrClear(BLINK, blink);
    }

    public boolean isWarn() {
        return test(WARN);
    }

    public void setWarn(boolean warn) {
        setOrClear(WARN, warn);
    }

    public boolean isError() {
        return test(ERROR);
    }

    public void setError(boolean error) {
        setOrClear(ERROR, error);
    }

}
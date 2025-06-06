package net.bodz.bas.make;

import net.bodz.bas.make.fn.*;
import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPattern;
import net.bodz.bas.make.pattern.dtkey.IDataTypedParameterizedKey;
import net.bodz.bas.make.pattern.dtkey.SimpleDataTypedKeyPatternMakeRule0;
import net.bodz.bas.make.pattern.dtkey.SimpleDataTypedKeyPatternMakeRule1;
import net.bodz.bas.make.pattern.dtkey.SimpleDataTypedKeyPatternMakeRule2;
import net.bodz.bas.make.pattern.dtkey.SimpleDataTypedKeyPatternMakeRule3;
import net.bodz.bas.make.pattern.dtkey.SimpleDataTypedKeyPatternMakeRule4;
import net.bodz.bas.make.pattern.dtkey.SimpleDataTypedKeyPatternMakeRule5;
import net.bodz.bas.make.pattern.dtkey.SimpleDataTypedKeyPatternMakeRule6;
import net.bodz.bas.make.pattern.dtkey.SimpleDataTypedKeyPatternMakeRule7;
import net.bodz.bas.make.pattern.key.IKeyPattern;
import net.bodz.bas.make.pattern.key.ITargetTypedKeyPattern;
import net.bodz.bas.make.pattern.key.SimpleKeyPatternMakeRule0;
import net.bodz.bas.make.pattern.key.SimpleKeyPatternMakeRule1;
import net.bodz.bas.make.pattern.key.SimpleKeyPatternMakeRule2;
import net.bodz.bas.make.pattern.key.SimpleKeyPatternMakeRule3;
import net.bodz.bas.make.pattern.key.SimpleKeyPatternMakeRule4;
import net.bodz.bas.make.pattern.key.SimpleKeyPatternMakeRule5;
import net.bodz.bas.make.pattern.key.SimpleKeyPatternMakeRule6;
import net.bodz.bas.make.pattern.key.SimpleKeyPatternMakeRule7;
import net.bodz.bas.make.pattern.key.SimpleTargetTypedKeyPatternMakeRule0;
import net.bodz.bas.make.pattern.key.SimpleTargetTypedKeyPatternMakeRule1;
import net.bodz.bas.make.pattern.key.SimpleTargetTypedKeyPatternMakeRule2;
import net.bodz.bas.make.pattern.key.SimpleTargetTypedKeyPatternMakeRule3;
import net.bodz.bas.make.pattern.key.SimpleTargetTypedKeyPatternMakeRule4;
import net.bodz.bas.make.pattern.key.SimpleTargetTypedKeyPatternMakeRule5;
import net.bodz.bas.make.pattern.key.SimpleTargetTypedKeyPatternMakeRule6;
import net.bodz.bas.make.pattern.key.SimpleTargetTypedKeyPatternMakeRule7;
import net.bodz.bas.make.pattern.target.ITargetPattern;
import net.bodz.bas.make.pattern.target.SimpleTargetPatternMakeRule0;
import net.bodz.bas.make.pattern.target.SimpleTargetPatternMakeRule1;
import net.bodz.bas.make.pattern.target.SimpleTargetPatternMakeRule2;
import net.bodz.bas.make.pattern.target.SimpleTargetPatternMakeRule3;
import net.bodz.bas.make.pattern.target.SimpleTargetPatternMakeRule4;
import net.bodz.bas.make.pattern.target.SimpleTargetPatternMakeRule5;
import net.bodz.bas.make.pattern.target.SimpleTargetPatternMakeRule6;
import net.bodz.bas.make.pattern.target.SimpleTargetPatternMakeRule7;
import net.bodz.bas.meta.decl.NotNull;

public interface IMakeRules
        extends IMakeRulesBase {

    // rules: exact match

    default <T extends IKeyData<TK, TT>, TK, TT> //
    void addRule(@NotNull T target, @NotNull IMakeable0<TT> fn) {
        addRule(target, //
                SimpleMakeRule0.<T, T, TK, TT>builder() //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, U extends IKeyData<UK, UT>, UK, UT> //
    void addRule(@NotNull T target, U input1, @NotNull IMakeable1<TT, UT> fn) {
        addRule(target, //
                SimpleMakeRule1.<T, T, TK, TT, U, UK, UT>builder() //
                        .input(input1) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, U extends IKeyData<UK, UT>, UK, UT, V extends IKeyData<VK, VT>, VK, VT> //
    void addRule(@NotNull T target, U input1, V input2, @NotNull IMakeable2<TT, UT, VT> fn) {
        addRule(target, //
                SimpleMakeRule2.<T, T, TK, TT, U, UK, UT, V, VK, VT>builder() //
                        .input(input1, input2) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, U extends IKeyData<UK, UT>, UK, UT, V extends IKeyData<VK, VT>, VK, VT, W extends IKeyData<WK, WT>, WK, WT> //
    void addRule(@NotNull T target, U input1, V input2, W input3, @NotNull IMakeable3<TT, UT, VT, WT> fn) {
        addRule(target, //
                SimpleMakeRule3.<T, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT>builder() //
                        .input(input1, input2, input3) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, U extends IKeyData<UK, UT>, UK, UT, V extends IKeyData<VK, VT>, VK, VT, W extends IKeyData<WK, WT>, WK, WT, X extends IKeyData<XK, XT>, XK, XT> //
    void addRule(@NotNull T target, U input1, V input2, W input3, X input4, @NotNull IMakeable4<TT, UT, VT, WT, XT> fn) {
        addRule(target, //
                SimpleMakeRule4.<T, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT>builder() //
                        .input(input1, input2, input3, input4) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, U extends IKeyData<UK, UT>, UK, UT, V extends IKeyData<VK, VT>, VK, VT, W extends IKeyData<WK, WT>, WK, WT, X extends IKeyData<XK, XT>, XK, XT, Y extends IKeyData<YK, YT>, YK, YT> //
    void addRule(@NotNull T target, U input1, V input2, W input3, X input4, Y input5, @NotNull IMakeable5<TT, UT, VT, WT, XT, YT> fn) {
        addRule(target, //
                SimpleMakeRule5.<T, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT>builder() //
                        .input(input1, input2, input3, input4, input5) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, U extends IKeyData<UK, UT>, UK, UT, V extends IKeyData<VK, VT>, VK, VT, W extends IKeyData<WK, WT>, WK, WT, X extends IKeyData<XK, XT>, XK, XT, Y extends IKeyData<YK, YT>, YK, YT, Z extends IKeyData<ZK, ZT>, ZK, ZT> //
    void addRule(@NotNull T target, U input1, V input2, W input3, X input4, Y input5, Z input6, @NotNull IMakeable6<TT, UT, VT, WT, XT, YT, ZT> fn) {
        addRule(target, //
                SimpleMakeRule6.<T, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT>builder() //
                        .input(input1, input2, input3, input4, input5, input6) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, U1 extends IKeyData<U1K, U1T>, U1K, U1T, U2 extends IKeyData<U2K, U2T>, U2K, U2T, U3 extends IKeyData<U3K, U3T>, U3K, U3T, U4 extends IKeyData<U4K, U4T>, U4K, U4T, U5 extends IKeyData<U5K, U5T>, U5K, U5T, U6 extends IKeyData<U6K, U6T>, U6K, U6T, U7 extends IKeyData<U7K, U7T>, U7K, U7T> //
    void addRule(@NotNull T target, U1 input1, U2 input2, U3 input3, U4 input4, U5 input5, U6 input6, U7 input7, @NotNull IMakeable7<TT, U1T, U2T, U3T, U4T, U5T, U6T, U7T> fn) {
        addRule(target, //
                SimpleMakeRule7.<T, T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T>builder() //
                        .input(input1, input2, input3, input4, input5, input6, input7) //
                        .fn(fn).build());
    }

    // rules: key match

    default <T extends IKeyData<TK, TT>, TK, TT> //
    void addKeyRule(@NotNull TK key, @NotNull IMakeable0<TT> fn) {
        addKeyRule(key, //
                SimpleMakeRule0.<TK, T, TK, TT>builder() //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT> //
    void addKeyRule(@NotNull TK key, @NotNull U input1, @NotNull IMakeable1<TT, UT> fn) {
        addKeyRule(key, //
                SimpleMakeRule1.<TK, T, TK, TT, U, UK, UT>builder() //
                        .input(input1) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT> //
    void addKeyRule(@NotNull TK key, @NotNull U input1, @NotNull V input2, @NotNull IMakeable2<TT, UT, VT> fn) {
        addKeyRule(key, //
                SimpleMakeRule2.<TK, T, TK, TT, U, UK, UT, V, VK, VT>builder() //
                        .input(input1, input2) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT> //
    void addKeyRule(@NotNull TK key, @NotNull U input1, @NotNull V input2, @NotNull W input3, @NotNull IMakeable3<TT, UT, VT, WT> fn) {
        addKeyRule(key, //
                SimpleMakeRule3.<TK, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT>builder() //
                        .input(input1, input2, input3) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT> //
    void addKeyRule(@NotNull TK key, @NotNull U input1, @NotNull V input2, @NotNull W input3, @NotNull X input4, @NotNull IMakeable4<TT, UT, VT, WT, XT> fn) {
        addKeyRule(key, //
                SimpleMakeRule4.<TK, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT>builder() //
                        .input(input1, input2, input3, input4) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT, //
            Y extends IKeyData<YK, YT>, YK, YT> //
    void addKeyRule(@NotNull TK key, @NotNull U input1, @NotNull V input2, @NotNull W input3, @NotNull X input4, @NotNull Y input5, @NotNull IMakeable5<TT, UT, VT, WT, XT, YT> fn) {
        addKeyRule(key, //
                SimpleMakeRule5.<TK, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT>builder() //
                        .input(input1, input2, input3, input4, input5) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT, //
            Y extends IKeyData<YK, YT>, YK, YT, //
            Z extends IKeyData<ZK, ZT>, ZK, ZT> //
    void addKeyRule(@NotNull TK key, @NotNull U input1, @NotNull V input2, @NotNull W input3, @NotNull X input4, @NotNull Y input5, @NotNull Z input6, @NotNull IMakeable6<TT, UT, VT, WT, XT, YT, ZT> fn) {
        addKeyRule(key, //
                SimpleMakeRule6.<TK, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT>builder() //
                        .input(input1, input2, input3, input4, input5, input6) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, //
            U1 extends IKeyData<U1K, U1T>, U1K, U1T, //
            U2 extends IKeyData<U2K, U2T>, U2K, U2T, //
            U3 extends IKeyData<U3K, U3T>, U3K, U3T, //
            U4 extends IKeyData<U4K, U4T>, U4K, U4T, //
            U5 extends IKeyData<U5K, U5T>, U5K, U5T, //
            U6 extends IKeyData<U6K, U6T>, U6K, U6T, //
            U7 extends IKeyData<U7K, U7T>, U7K, U7T> //
    void addKeyRule(@NotNull TK key, @NotNull U1 input1, @NotNull U2 input2, @NotNull U3 input3, @NotNull U4 input4, @NotNull U5 input5, @NotNull U6 input6, @NotNull U7 input7, @NotNull IMakeable7<TT, U1T, U2T, U3T, U4T, U5T, U6T, U7T> fn) {
        addKeyRule(key, //
                SimpleMakeRule7.<TK, T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T>builder() //
                        .input(input1, input2, input3, input4, input5, input6, input7) //
                        .fn(fn).build());
    }

    // rules: key type match

    default <T extends IKeyData<TK, TT>, TK, TT> //
    void addKeyTypeRule(@NotNull Class<TK> keyType, @NotNull IMakeable0<TT> fn) {
        addKeyTypeRule(keyType, //
                SimpleMakeRule0.<Class<TK>, T, TK, TT>builder() //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT> //
    void addKeyTypeRule(@NotNull Class<TK> keyType, @NotNull U input1, @NotNull IMakeable1<TT, UT> fn) {
        addKeyTypeRule(keyType, //
                SimpleMakeRule1.<Class<TK>, T, TK, TT, U, UK, UT>builder() //
                        .input(input1) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT> //
    void addKeyTypeRule(@NotNull Class<TK> keyType, @NotNull U input1, @NotNull V input2, @NotNull IMakeable2<TT, UT, VT> fn) {
        addKeyTypeRule(keyType, //
                SimpleMakeRule2.<Class<TK>, T, TK, TT, U, UK, UT, V, VK, VT>builder() //
                        .input(input1, input2) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT> //
    void addKeyTypeRule(@NotNull Class<TK> keyType, @NotNull U input1, @NotNull V input2, @NotNull W input3, @NotNull IMakeable3<TT, UT, VT, WT> fn) {
        addKeyTypeRule(keyType, //
                SimpleMakeRule3.<Class<TK>, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT>builder() //
                        .input(input1, input2, input3) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT> //
    void addKeyTypeRule(@NotNull Class<TK> keyType, @NotNull U input1, @NotNull V input2, @NotNull W input3, @NotNull X input4, @NotNull IMakeable4<TT, UT, VT, WT, XT> fn) {
        addKeyTypeRule(keyType, //
                SimpleMakeRule4.<Class<TK>, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT>builder() //
                        .input(input1, input2, input3, input4) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT, //
            Y extends IKeyData<YK, YT>, YK, YT> //
    void addKeyTypeRule(@NotNull Class<TK> keyType, @NotNull U input1, @NotNull V input2, @NotNull W input3, @NotNull X input4, @NotNull Y input5, @NotNull IMakeable5<TT, UT, VT, WT, XT, YT> fn) {
        addKeyTypeRule(keyType, //
                SimpleMakeRule5.<Class<TK>, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT>builder() //
                        .input(input1, input2, input3, input4, input5) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT, //
            Y extends IKeyData<YK, YT>, YK, YT, //
            Z extends IKeyData<ZK, ZT>, ZK, ZT> //
    void addKeyTypeRule(@NotNull Class<TK> keyType, @NotNull U input1, @NotNull V input2, @NotNull W input3, @NotNull X input4, @NotNull Y input5, @NotNull Z input6, @NotNull IMakeable6<TT, UT, VT, WT, XT, YT, ZT> fn) {
        addKeyTypeRule(keyType, //
                SimpleMakeRule6.<Class<TK>, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT>builder() //
                        .input(input1, input2, input3, input4, input5, input6) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, //
            U1 extends IKeyData<U1K, U1T>, U1K, U1T, //
            U2 extends IKeyData<U2K, U2T>, U2K, U2T, //
            U3 extends IKeyData<U3K, U3T>, U3K, U3T, //
            U4 extends IKeyData<U4K, U4T>, U4K, U4T, //
            U5 extends IKeyData<U5K, U5T>, U5K, U5T, //
            U6 extends IKeyData<U6K, U6T>, U6K, U6T, //
            U7 extends IKeyData<U7K, U7T>, U7K, U7T> //
    void addKeyTypeRule(@NotNull Class<TK> keyType, @NotNull U1 input1, @NotNull U2 input2, @NotNull U3 input3, @NotNull U4 input4, @NotNull U5 input5, @NotNull U6 input6, @NotNull U7 input7, @NotNull IMakeable7<TT, U1T, U2T, U3T, U4T, U5T, U6T, U7T> fn) {
        addKeyTypeRule(keyType, //
                SimpleMakeRule7.<Class<TK>, T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T>builder() //
                        .input(input1, input2, input3, input4, input5, input6, input7) //
                        .fn(fn).build());
    }

    // rules: data type match

    default <T extends IKeyData<TK, TT>, TK, TT> //
    void addRule(@NotNull Class<TT> dataType, @NotNull IMakeable0<TT> fn) {
        addRule(dataType, //
                SimpleMakeRule0.<Class<TT>, T, TK, TT>builder() //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT> //
    void addRule(@NotNull Class<TT> dataType, @NotNull U input1, @NotNull IMakeable1<TT, UT> fn) {
        addRule(dataType, //
                SimpleMakeRule1.<Class<TT>, T, TK, TT, U, UK, UT>builder() //
                        .input(input1) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT> //
    void addRule(@NotNull Class<TT> dataType, @NotNull U input1, @NotNull V input2, @NotNull IMakeable2<TT, UT, VT> fn) {
        addRule(dataType, //
                SimpleMakeRule2.<Class<TT>, T, TK, TT, U, UK, UT, V, VK, VT>builder() //
                        .input(input1, input2) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT> //
    void addRule(@NotNull Class<TT> dataType, @NotNull U input1, @NotNull V input2, @NotNull W input3, @NotNull IMakeable3<TT, UT, VT, WT> fn) {
        addRule(dataType, //
                SimpleMakeRule3.<Class<TT>, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT>builder() //
                        .input(input1, input2, input3) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT> //
    void addRule(@NotNull Class<TT> dataType, @NotNull U input1, @NotNull V input2, @NotNull W input3, @NotNull X input4, @NotNull IMakeable4<TT, UT, VT, WT, XT> fn) {
        addRule(dataType, //
                SimpleMakeRule4.<Class<TT>, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT>builder() //
                        .input(input1, input2, input3, input4) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT, //
            Y extends IKeyData<YK, YT>, YK, YT> //
    void addRule(@NotNull Class<TT> dataType, @NotNull U input1, @NotNull V input2, @NotNull W input3, @NotNull X input4, @NotNull Y input5, @NotNull IMakeable5<TT, UT, VT, WT, XT, YT> fn) {
        addRule(dataType, //
                SimpleMakeRule5.<Class<TT>, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT>builder() //
                        .input(input1, input2, input3, input4, input5) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, //
            U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT, //
            Y extends IKeyData<YK, YT>, YK, YT, //
            Z extends IKeyData<ZK, ZT>, ZK, ZT> //
    void addRule(@NotNull Class<TT> dataType, @NotNull U input1, @NotNull V input2, @NotNull W input3, @NotNull X input4, @NotNull Y input5, @NotNull Z input6, @NotNull IMakeable6<TT, UT, VT, WT, XT, YT, ZT> fn) {
        addRule(dataType, //
                SimpleMakeRule6.<Class<TT>, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT>builder() //
                        .input(input1, input2, input3, input4, input5, input6) //
                        .fn(fn).build());
    }

    default <T extends IKeyData<TK, TT>, TK, TT, //
            U1 extends IKeyData<U1K, U1T>, U1K, U1T, //
            U2 extends IKeyData<U2K, U2T>, U2K, U2T, //
            U3 extends IKeyData<U3K, U3T>, U3K, U3T, //
            U4 extends IKeyData<U4K, U4T>, U4K, U4T, //
            U5 extends IKeyData<U5K, U5T>, U5K, U5T, //
            U6 extends IKeyData<U6K, U6T>, U6K, U6T, //
            U7 extends IKeyData<U7K, U7T>, U7K, U7T> //
    void addRule(@NotNull Class<TT> dataType, @NotNull U1 input1, @NotNull U2 input2, @NotNull U3 input3, @NotNull U4 input4, @NotNull U5 input5, @NotNull U6 input6, @NotNull U7 input7, @NotNull IMakeable7<TT, U1T, U2T, U3T, U4T, U5T, U6T, U7T> fn) {
        addRule(dataType, //
                SimpleMakeRule7.<Class<TT>, T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T>builder() //
                        .input(input1, input2, input3, input4, input5, input6, input7) //
                        .fn(fn).build());
    }

    // rules: key pattern

    default <Tp extends IKeyPattern<Param, K>, Param, K, //
            T extends IKeyData<K, TT>, TT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull CompileFunction0<T, K, TT> fn) {
        addPatternRule(pattern, //
                SimpleKeyPatternMakeRule0.<Tp, Tp, Param, K, T, TT>builder()//
                        .pattern(pattern) //
                        .fn(fn).build());
    }

    default <Tp extends IKeyPattern<Param, K>, Param, K, //
            Us extends IParameterizedKey<Param, UK>, UK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull CompileFunction1<T, K, TT, U, UK, UT> fn) {
        addPatternRule(pattern, //
                SimpleKeyPatternMakeRule1.<Tp, Tp, Param, K, Us, UK, T, TT, U, UT>builder()//
                        .pattern(pattern) //
                        .input(input1s)//
                        .fn(fn).build());
    }

    default <Tp extends IKeyPattern<Param, K>, Param, K, //
            Us extends IParameterizedKey<Param, UK>, UK, //
            Vs extends IParameterizedKey<Param, VK>, VK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull CompileFunction2<T, K, TT, U, UK, UT, V, VK, VT> fn) {
        addPatternRule(pattern, //
                SimpleKeyPatternMakeRule2.<Tp, Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s)//
                        .fn(fn).build());
    }

    default <Tp extends IKeyPattern<Param, K>, Param, K, //
            Us extends IParameterizedKey<Param, UK>, UK, //
            Vs extends IParameterizedKey<Param, VK>, VK, //
            Ws extends IParameterizedKey<Param, WK>, WK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull CompileFunction3<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT> fn) {
        addPatternRule(pattern, //
                SimpleKeyPatternMakeRule3.<Tp, Tp, Param, K, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s, input3s)//
                        .fn(fn).build());
    }

    default <Tp extends IKeyPattern<Param, K>, Param, K, //
            Us extends IParameterizedKey<Param, UK>, UK, //
            Vs extends IParameterizedKey<Param, VK>, VK, //
            Ws extends IParameterizedKey<Param, WK>, WK, //
            Xs extends IParameterizedKey<Param, XK>, XK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull Xs input4s, @NotNull CompileFunction4<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> fn) {
        addPatternRule(pattern, //
                SimpleKeyPatternMakeRule4.<Tp, Tp, Param, K, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s, input3s, input4s)//
                        .fn(fn).build());
    }

    default <Tp extends IKeyPattern<Param, K>, Param, K, //
            Us extends IParameterizedKey<Param, UK>, UK, //
            Vs extends IParameterizedKey<Param, VK>, VK, //
            Ws extends IParameterizedKey<Param, WK>, WK, //
            Xs extends IParameterizedKey<Param, XK>, XK, //
            Ys extends IParameterizedKey<Param, YK>, YK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT, //
            Y extends IKeyData<YK, YT>, YT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull Xs input4s, @NotNull Ys input5s, @NotNull CompileFunction5<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> fn) {
        addPatternRule(pattern, //
                SimpleKeyPatternMakeRule5.<Tp, Tp, Param, K, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s, input3s, input4s, input5s)//
                        .fn(fn).build());
    }

    default <Tp extends IKeyPattern<Param, K>, Param, K, //
            Us extends IParameterizedKey<Param, UK>, UK, //
            Vs extends IParameterizedKey<Param, VK>, VK, //
            Ws extends IParameterizedKey<Param, WK>, WK, //
            Xs extends IParameterizedKey<Param, XK>, XK, //
            Ys extends IParameterizedKey<Param, YK>, YK, //
            Zs extends IParameterizedKey<Param, ZK>, ZK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT, //
            Y extends IKeyData<YK, YT>, YT, //
            Z extends IKeyData<ZK, ZT>, ZT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull Xs input4s, @NotNull Ys input5s, @NotNull Zs input6s, @NotNull CompileFunction6<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> fn) {
        addPatternRule(pattern, //
                SimpleKeyPatternMakeRule6.<Tp, Tp, Param, K, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s, input3s, input4s, input5s, input6s)//
                        .fn(fn).build());
    }

    default <Tp extends IKeyPattern<Param, K>, Param, K, //
            U1s extends IParameterizedKey<Param, U1K>, U1K, //
            U2s extends IParameterizedKey<Param, U2K>, U2K, //
            U3s extends IParameterizedKey<Param, U3K>, U3K, //
            U4s extends IParameterizedKey<Param, U4K>, U4K, //
            U5s extends IParameterizedKey<Param, U5K>, U5K, //
            U6s extends IParameterizedKey<Param, U6K>, U6K, //
            U7s extends IParameterizedKey<Param, U7K>, U7K, //
            T extends IKeyData<K, TT>, TT, //
            U1 extends IKeyData<U1K, U1T>, U1T, //
            U2 extends IKeyData<U2K, U2T>, U2T, //
            U3 extends IKeyData<U3K, U3T>, U3T, //
            U4 extends IKeyData<U4K, U4T>, U4T, //
            U5 extends IKeyData<U5K, U5T>, U5T, //
            U6 extends IKeyData<U6K, U6T>, U6T, //
            U7 extends IKeyData<U7K, U7T>, U7T> //
    void addPatternRule(@NotNull Tp pattern, @NotNull U1s input1s, @NotNull U2s input2s, @NotNull U3s input3s, @NotNull U4s input4s, @NotNull U5s input5s, @NotNull U6s input6s, @NotNull U7s input7s, @NotNull CompileFunction7<T, K, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T> fn) {
        addPatternRule(pattern, //
                SimpleKeyPatternMakeRule7.<Tp, Tp, Param, K, U1s, U1K, U2s, U2K, U3s, U3K, U4s, U4K, U5s, U5K, U6s, U6K, U7s, U7K, T, TT, U1, U1T, U2, U2T, U3, U3T, U4, U4T, U5, U5T, U6, U6T, U7, U7T>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s, input3s, input4s, input5s, input6s, input7s)//
                        .fn(fn).build());
    }

    // rules: data typed key pattern

    default <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            T extends IKeyData<K, TT>, TT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull CompileFunction0<T, K, TT> fn) {
        addPatternRule(pattern, //
                SimpleDataTypedKeyPatternMakeRule0.<Tp, Tp, Param, K, T, TT>builder()//
                        .pattern(pattern) //
                        .fn(fn).build());
    }

    default <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull CompileFunction1<T, K, TT, U, UK, UT> fn) {
        addPatternRule(pattern, //
                SimpleDataTypedKeyPatternMakeRule1.<Tp, Tp, Param, K, Us, UK, T, TT, U, UT>builder()//
                        .pattern(pattern) //
                        .input(input1s)//
                        .fn(fn).build());
    }

    default <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, VK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull CompileFunction2<T, K, TT, U, UK, UT, V, VK, VT> fn) {
        addPatternRule(pattern, //
                SimpleDataTypedKeyPatternMakeRule2.<Tp, Tp, Param, K, Us, UK, Vs, VK, T, TT, U, UT, V, VT>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s)//
                        .fn(fn).build());
    }

    default <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, VK, //
            Ws extends IDataTypedParameterizedKey<Param, WK, WT>, WK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull CompileFunction3<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT> fn) {
        addPatternRule(pattern, //
                SimpleDataTypedKeyPatternMakeRule3.<Tp, Tp, Param, K, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s, input3s)//
                        .fn(fn).build());
    }

    default <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, VK, //
            Ws extends IDataTypedParameterizedKey<Param, WK, WT>, WK, //
            Xs extends IDataTypedParameterizedKey<Param, XK, XT>, XK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull Xs input4s, @NotNull CompileFunction4<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> fn) {
        addPatternRule(pattern, //
                SimpleDataTypedKeyPatternMakeRule4.<Tp, Tp, Param, K, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s, input3s, input4s)//
                        .fn(fn).build());
    }

    default <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, VK, //
            Ws extends IDataTypedParameterizedKey<Param, WK, WT>, WK, //
            Xs extends IDataTypedParameterizedKey<Param, XK, XT>, XK, //
            Ys extends IDataTypedParameterizedKey<Param, YK, YT>, YK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT, //
            Y extends IKeyData<YK, YT>, YT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull Xs input4s, @NotNull Ys input5s, @NotNull CompileFunction5<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> fn) {
        addPatternRule(pattern, //
                SimpleDataTypedKeyPatternMakeRule5.<Tp, Tp, Param, K, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s, input3s, input4s, input5s)//
                        .fn(fn).build());
    }

    default <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
            Vs extends IDataTypedParameterizedKey<Param, VK, VT>, VK, //
            Ws extends IDataTypedParameterizedKey<Param, WK, WT>, WK, //
            Xs extends IDataTypedParameterizedKey<Param, XK, XT>, XK, //
            Ys extends IDataTypedParameterizedKey<Param, YK, YT>, YK, //
            Zs extends IDataTypedParameterizedKey<Param, ZK, ZT>, ZK, //
            T extends IKeyData<K, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT, //
            Y extends IKeyData<YK, YT>, YT, //
            Z extends IKeyData<ZK, ZT>, ZT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull Xs input4s, @NotNull Ys input5s, @NotNull Zs input6s, @NotNull CompileFunction6<T, K, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> fn) {
        addPatternRule(pattern, //
                SimpleDataTypedKeyPatternMakeRule6.<Tp, Tp, Param, K, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s, input3s, input4s, input5s, input6s)//
                        .fn(fn).build());
    }

    default <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
            U1s extends IDataTypedParameterizedKey<Param, U1K, U1T>, U1K, //
            U2s extends IDataTypedParameterizedKey<Param, U2K, U2T>, U2K, //
            U3s extends IDataTypedParameterizedKey<Param, U3K, U3T>, U3K, //
            U4s extends IDataTypedParameterizedKey<Param, U4K, U4T>, U4K, //
            U5s extends IDataTypedParameterizedKey<Param, U5K, U5T>, U5K, //
            U6s extends IDataTypedParameterizedKey<Param, U6K, U6T>, U6K, //
            U7s extends IDataTypedParameterizedKey<Param, U7K, U7T>, U7K, //
            T extends IKeyData<K, TT>, TT, //
            U1 extends IKeyData<U1K, U1T>, U1T, //
            U2 extends IKeyData<U2K, U2T>, U2T, //
            U3 extends IKeyData<U3K, U3T>, U3T, //
            U4 extends IKeyData<U4K, U4T>, U4T, //
            U5 extends IKeyData<U5K, U5T>, U5T, //
            U6 extends IKeyData<U6K, U6T>, U6T, //
            U7 extends IKeyData<U7K, U7T>, U7T> //
    void addPatternRule(@NotNull Tp pattern, @NotNull U1s input1s, @NotNull U2s input2s, @NotNull U3s input3s, @NotNull U4s input4s, @NotNull U5s input5s, @NotNull U6s input6s, @NotNull U7s input7s, @NotNull CompileFunction7<T, K, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T> fn) {
        addPatternRule(pattern, //
                SimpleDataTypedKeyPatternMakeRule7.<Tp, Tp, Param, K, U1s, U1K, U2s, U2K, U3s, U3K, U4s, U4K, U5s, U5K, U6s, U6K, U7s, U7K, T, TT, U1, U1T, U2, U2T, U3, U3T, U4, U4T, U5, U5T, U6, U6T, U7, U7T>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s, input3s, input4s, input5s, input6s, input7s)//
                        .fn(fn).build());
    }

    // rules: target typed key pattern

    default <Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, //
            T extends IKeyData<TK, TT>, TT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull CompileFunction0<T, TK, TT> fn) {
        addPatternRule(pattern, //
                SimpleTargetTypedKeyPatternMakeRule0.<Tp, Tp, Param, TK, T, TT>builder()//
                        .pattern(pattern) //
                        .fn(fn).build());
    }

    default <Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedKey<Param, UK>, UK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull CompileFunction1<T, TK, TT, U, UK, UT> fn) {
        addPatternRule(pattern, //
                SimpleTargetTypedKeyPatternMakeRule1.<Tp, Tp, Param, TK, Us, UK, T, TT, U, UT>builder()//
                        .pattern(pattern) //
                        .input(input1s)//
                        .fn(fn).build());
    }

    default <Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedKey<Param, UK>, UK, //
            Vs extends IParameterizedKey<Param, VK>, VK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull CompileFunction2<T, TK, TT, U, UK, UT, V, VK, VT> fn) {
        addPatternRule(pattern, //
                SimpleTargetTypedKeyPatternMakeRule2.<Tp, Tp, Param, TK, Us, UK, Vs, VK, T, TT, U, UT, V, VT>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s)//
                        .fn(fn).build());
    }

    default <Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedKey<Param, UK>, UK, //
            Vs extends IParameterizedKey<Param, VK>, VK, //
            Ws extends IParameterizedKey<Param, WK>, WK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull CompileFunction3<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> fn) {
        addPatternRule(pattern, //
                SimpleTargetTypedKeyPatternMakeRule3.<Tp, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s, input3s)//
                        .fn(fn).build());
    }

    default <Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedKey<Param, UK>, UK, //
            Vs extends IParameterizedKey<Param, VK>, VK, //
            Ws extends IParameterizedKey<Param, WK>, WK, //
            Xs extends IParameterizedKey<Param, XK>, XK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull Xs input4s, @NotNull CompileFunction4<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> fn) {
        addPatternRule(pattern, //
                SimpleTargetTypedKeyPatternMakeRule4.<Tp, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s, input3s, input4s)//
                        .fn(fn).build());
    }

    default <Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedKey<Param, UK>, UK, //
            Vs extends IParameterizedKey<Param, VK>, VK, //
            Ws extends IParameterizedKey<Param, WK>, WK, //
            Xs extends IParameterizedKey<Param, XK>, XK, //
            Ys extends IParameterizedKey<Param, YK>, YK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT, //
            Y extends IKeyData<YK, YT>, YT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull Xs input4s, @NotNull Ys input5s, @NotNull CompileFunction5<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> fn) {
        addPatternRule(pattern, //
                SimpleTargetTypedKeyPatternMakeRule5.<Tp, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s, input3s, input4s, input5s)//
                        .fn(fn).build());
    }

    default <Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedKey<Param, UK>, UK, //
            Vs extends IParameterizedKey<Param, VK>, VK, //
            Ws extends IParameterizedKey<Param, WK>, WK, //
            Xs extends IParameterizedKey<Param, XK>, XK, //
            Ys extends IParameterizedKey<Param, YK>, YK, //
            Zs extends IParameterizedKey<Param, ZK>, ZK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT, //
            Y extends IKeyData<YK, YT>, YT, //
            Z extends IKeyData<ZK, ZT>, ZT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull Xs input4s, @NotNull Ys input5s, @NotNull Zs input6s, @NotNull CompileFunction6<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> fn) {
        addPatternRule(pattern, //
                SimpleTargetTypedKeyPatternMakeRule6.<Tp, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s, input3s, input4s, input5s, input6s)//
                        .fn(fn).build());
    }

    default <Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, //
            U1s extends IParameterizedKey<Param, U1K>, U1K, //
            U2s extends IParameterizedKey<Param, U2K>, U2K, //
            U3s extends IParameterizedKey<Param, U3K>, U3K, //
            U4s extends IParameterizedKey<Param, U4K>, U4K, //
            U5s extends IParameterizedKey<Param, U5K>, U5K, //
            U6s extends IParameterizedKey<Param, U6K>, U6K, //
            U7s extends IParameterizedKey<Param, U7K>, U7K, //
            T extends IKeyData<TK, TT>, TT, //
            U1 extends IKeyData<U1K, U1T>, U1T, //
            U2 extends IKeyData<U2K, U2T>, U2T, //
            U3 extends IKeyData<U3K, U3T>, U3T, //
            U4 extends IKeyData<U4K, U4T>, U4T, //
            U5 extends IKeyData<U5K, U5T>, U5T, //
            U6 extends IKeyData<U6K, U6T>, U6T, //
            U7 extends IKeyData<U7K, U7T>, U7T> //
    void addPatternRule(@NotNull Tp pattern, @NotNull U1s input1s, @NotNull U2s input2s, @NotNull U3s input3s, @NotNull U4s input4s, @NotNull U5s input5s, @NotNull U6s input6s, @NotNull U7s input7s, @NotNull CompileFunction7<T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T> fn) {
        addPatternRule(pattern, //
                SimpleTargetTypedKeyPatternMakeRule7.<Tp, Tp, Param, TK, U1s, U1K, U2s, U2K, U3s, U3K, U4s, U4K, U5s, U5K, U6s, U6K, U7s, U7K, T, TT, U1, U1T, U2, U2T, U3, U3T, U4, U4T, U5, U5T, U6, U6T, U7, U7T>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s, input3s, input4s, input5s, input6s, input7s)//
                        .fn(fn).build());
    }

    // rules: target pattern

    default <Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
            T extends IKeyData<TK, TT>, TT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull CompileFunction0<T, TK, TT> fn) {
        addPatternRule(pattern, //
                SimpleTargetPatternMakeRule0.<Tp, Tp, Param, TK, T, TT>builder()//
                        .pattern(pattern) //
                        .fn(fn).build());
    }

    default <Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull CompileFunction1<T, TK, TT, U, UK, UT> fn) {
        addPatternRule(pattern, //
                SimpleTargetPatternMakeRule1.<Tp, Tp, Param, TK, Us, UK, T, TT, U, UT>builder()//
                        .pattern(pattern) //
                        .input(input1s)//
                        .fn(fn).build());
    }

    default <Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
            Vs extends IParameterizedTarget<Param, V, VK, VT>, VK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull CompileFunction2<T, TK, TT, U, UK, UT, V, VK, VT> fn) {
        addPatternRule(pattern, //
                SimpleTargetPatternMakeRule2.<Tp, Tp, Param, TK, Us, UK, Vs, VK, T, TT, U, UT, V, VT>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s)//
                        .fn(fn).build());
    }

    default <Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
            Vs extends IParameterizedTarget<Param, V, VK, VT>, VK, //
            Ws extends IParameterizedTarget<Param, W, WK, WT>, WK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull CompileFunction3<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> fn) {
        addPatternRule(pattern, //
                SimpleTargetPatternMakeRule3.<Tp, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, T, TT, U, UT, V, VT, W, WT>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s, input3s)//
                        .fn(fn).build());
    }

    default <Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
            Vs extends IParameterizedTarget<Param, V, VK, VT>, VK, //
            Ws extends IParameterizedTarget<Param, W, WK, WT>, WK, //
            Xs extends IParameterizedTarget<Param, X, XK, XT>, XK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull Xs input4s, @NotNull CompileFunction4<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> fn) {
        addPatternRule(pattern, //
                SimpleTargetPatternMakeRule4.<Tp, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s, input3s, input4s)//
                        .fn(fn).build());
    }

    default <Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
            Vs extends IParameterizedTarget<Param, V, VK, VT>, VK, //
            Ws extends IParameterizedTarget<Param, W, WK, WT>, WK, //
            Xs extends IParameterizedTarget<Param, X, XK, XT>, XK, //
            Ys extends IParameterizedTarget<Param, Y, YK, YT>, YK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT, //
            Y extends IKeyData<YK, YT>, YT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull Xs input4s, @NotNull Ys input5s, @NotNull CompileFunction5<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> fn) {
        addPatternRule(pattern, //
                SimpleTargetPatternMakeRule5.<Tp, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s, input3s, input4s, input5s)//
                        .fn(fn).build());
    }

    default <Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
            Us extends IParameterizedTarget<Param, U, UK, UT>, UK, //
            Vs extends IParameterizedTarget<Param, V, VK, VT>, VK, //
            Ws extends IParameterizedTarget<Param, W, WK, WT>, WK, //
            Xs extends IParameterizedTarget<Param, X, XK, XT>, XK, //
            Ys extends IParameterizedTarget<Param, Y, YK, YT>, YK, //
            Zs extends IParameterizedTarget<Param, Z, ZK, ZT>, ZK, //
            T extends IKeyData<TK, TT>, TT, //
            U extends IKeyData<UK, UT>, UT, //
            V extends IKeyData<VK, VT>, VT, //
            W extends IKeyData<WK, WT>, WT, //
            X extends IKeyData<XK, XT>, XT, //
            Y extends IKeyData<YK, YT>, YT, //
            Z extends IKeyData<ZK, ZT>, ZT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull Vs input2s, @NotNull Ws input3s, @NotNull Xs input4s, @NotNull Ys input5s, @NotNull Zs input6s, @NotNull CompileFunction6<T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> fn) {
        addPatternRule(pattern, //
                SimpleTargetPatternMakeRule6.<Tp, Tp, Param, TK, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, Zs, ZK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT, Z, ZT>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s, input3s, input4s, input5s, input6s)//
                        .fn(fn).build());
    }

    default <Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
            U1s extends IParameterizedTarget<Param, U1, U1K, U1T>, U1K, //
            U2s extends IParameterizedTarget<Param, U2, U2K, U2T>, U2K, //
            U3s extends IParameterizedTarget<Param, U3, U3K, U3T>, U3K, //
            U4s extends IParameterizedTarget<Param, U4, U4K, U4T>, U4K, //
            U5s extends IParameterizedTarget<Param, U5, U5K, U5T>, U5K, //
            U6s extends IParameterizedTarget<Param, U6, U6K, U6T>, U6K, //
            U7s extends IParameterizedTarget<Param, U7, U7K, U7T>, U7K, //
            T extends IKeyData<TK, TT>, TT, //
            U1 extends IKeyData<U1K, U1T>, U1T, //
            U2 extends IKeyData<U2K, U2T>, U2T, //
            U3 extends IKeyData<U3K, U3T>, U3T, //
            U4 extends IKeyData<U4K, U4T>, U4T, //
            U5 extends IKeyData<U5K, U5T>, U5T, //
            U6 extends IKeyData<U6K, U6T>, U6T, //
            U7 extends IKeyData<U7K, U7T>, U7T> //
    void addPatternRule(@NotNull Tp pattern, @NotNull U1s input1s, @NotNull U2s input2s, @NotNull U3s input3s, @NotNull U4s input4s, @NotNull U5s input5s, @NotNull U6s input6s, @NotNull U7s input7s, @NotNull CompileFunction7<T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T> fn) {
        addPatternRule(pattern, //
                SimpleTargetPatternMakeRule7.<Tp, Tp, Param, TK, U1s, U1K, U2s, U2K, U3s, U3K, U4s, U4K, U5s, U5K, U6s, U6K, U7s, U7K, T, TT, U1, U1T, U2, U2T, U3, U3T, U4, U4T, U5, U5T, U6, U6T, U7, U7T>builder()//
                        .pattern(pattern) //
                        .input(input1s, input2s, input3s, input4s, input5s, input6s, input7s)//
                        .fn(fn).build());
    }

}

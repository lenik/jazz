package net.bodz.bas.make.fn;

import java.util.function.BiConsumer;

import net.bodz.bas.make.IDataTypedKey;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IMakeRule;

public abstract class SimpleMakeRuleBuilders<S, T extends IKeyData<TK, TT>, TK, TT> {

    public abstract BiConsumer<S, IMakeRule<T, TK, TT>> getApply();

    public abstract S getSubject();

    public SimpleMakeRule0.Builder<S, T, TK, TT> input() {
        return SimpleMakeRule0.<S, T, TK, TT>builder()//
                .apply(getApply()).subject(getSubject())//
                .input();
    }

    public <U extends IKeyData<UK, UT>, UK, UT>SimpleMakeRule1.Builder<S, T, TK, TT, U, UK, UT> input(IDataTypedKey<UK, UT> input1) {
        return SimpleMakeRule1.<S, T, TK, TT, U, UK, UT>builder()//
                .apply(getApply()).subject(getSubject())//
                .input(input1);
    }

    public <U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT>SimpleMakeRule2.Builder<S, T, TK, TT, U, UK, UT, V, VK, VT> input(IDataTypedKey<UK, UT> input1, IDataTypedKey<VK, VT> input2) {
        return SimpleMakeRule2.<S, T, TK, TT, U, UK, UT, V, VK, VT>builder()//
                .apply(getApply()).subject(getSubject())//
                .input(input1, input2);
    }

    public <U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT>SimpleMakeRule3.Builder<S, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT> input(IDataTypedKey<UK, UT> input1, IDataTypedKey<VK, VT> input2, IDataTypedKey<WK, WT> input3) {
        return SimpleMakeRule3.<S, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT>builder()//
                .apply(getApply()).subject(getSubject())//
                .input(input1, input2, input3);
    }

    public <U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT>SimpleMakeRule4.Builder<S, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT> input(IDataTypedKey<UK, UT> input1, IDataTypedKey<VK, VT> input2, IDataTypedKey<WK, WT> input3, IDataTypedKey<XK, XT> input4) {
        return SimpleMakeRule4.<S, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT>builder()//
                .apply(getApply()).subject(getSubject())//
                .input(input1, input2, input3, input4);
    }

    public <U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT, //
            Y extends IKeyData<YK, YT>, YK, YT>SimpleMakeRule5.Builder<S, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT> input(IDataTypedKey<UK, UT> input1, IDataTypedKey<VK, VT> input2, IDataTypedKey<WK, WT> input3, IDataTypedKey<XK, XT> input4, IDataTypedKey<YK, YT> input5) {
        return SimpleMakeRule5.<S, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT>builder()//
                .apply(getApply()).subject(getSubject())//
                .input(input1, input2, input3, input4, input5);
    }

    public <U extends IKeyData<UK, UT>, UK, UT, //
            V extends IKeyData<VK, VT>, VK, VT, //
            W extends IKeyData<WK, WT>, WK, WT, //
            X extends IKeyData<XK, XT>, XK, XT, //
            Y extends IKeyData<YK, YT>, YK, YT, //
            Z extends IKeyData<ZK, ZT>, ZK, ZT>SimpleMakeRule6.Builder<S, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT> input(IDataTypedKey<UK, UT> input1, IDataTypedKey<VK, VT> input2, IDataTypedKey<WK, WT> input3, IDataTypedKey<XK, XT> input4, IDataTypedKey<YK, YT> input5, IDataTypedKey<ZK, ZT> input6) {
        return SimpleMakeRule6.<S, T, TK, TT, U, UK, UT, V, VK, VT, W, WK, WT, X, XK, XT, Y, YK, YT, Z, ZK, ZT>builder()//
                .apply(getApply()).subject(getSubject())//
                .input(input1, input2, input3, input4, input5, input6);
    }

    public <U1 extends IKeyData<U1K, U1T>, U1K, U1T, //
            U2 extends IKeyData<U2K, U2T>, U2K, U2T, //
            U3 extends IKeyData<U3K, U3T>, U3K, U3T, //
            U4 extends IKeyData<U4K, U4T>, U4K, U4T, //
            U5 extends IKeyData<U5K, U5T>, U5K, U5T, //
            U6 extends IKeyData<U6K, U6T>, U6K, U6T, //
            U7 extends IKeyData<U7K, U7T>, U7K, U7T>SimpleMakeRule7.Builder<S, T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T> input(IDataTypedKey<U1K, U1T> input1, IDataTypedKey<U2K, U2T> input2, IDataTypedKey<U3K, U3T> input3, IDataTypedKey<U4K, U4T> input4, IDataTypedKey<U5K, U5T> input5, IDataTypedKey<U6K, U6T> input6, IDataTypedKey<U7K, U7T> input7) {
        return SimpleMakeRule7.<S, T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T>builder()//
                .apply(getApply()).subject(getSubject())//
                .input(input1, input2, input3, input4, input5, input6, input7);
    }

}

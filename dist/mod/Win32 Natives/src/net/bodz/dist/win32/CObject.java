package net.bodz.dist.win32;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class CObject {

    public final Dispatch dispatchTarget;

    public CObject(String progid) {
        if (progid == null)
            throw new NullPointerException("progid");
        this.dispatchTarget = new Dispatch(progid);
    }

    public CObject(Dispatch dispatchTarget) {
        if (dispatchTarget == null)
            throw new NullPointerException("dispatchTarget");
        this.dispatchTarget = dispatchTarget;
    }

    public final Variant call(int dispid, Object a1, Object a2, Object a3, Object a4, Object a5,
            Object a6, Object a7, Object a8) {
        return Dispatch.call(dispatchTarget, dispid, a1, a2, a3, a4, a5, a6, a7, a8);
    }

    public final Variant call(int dispid, Object a1, Object a2, Object a3, Object a4, Object a5,
            Object a6, Object a7) {
        return Dispatch.call(dispatchTarget, dispid, a1, a2, a3, a4, a5, a6, a7);
    }

    public final Variant call(int dispid, Object a1, Object a2, Object a3, Object a4, Object a5,
            Object a6) {
        return Dispatch.call(dispatchTarget, dispid, a1, a2, a3, a4, a5, a6);
    }

    public final Variant call(int dispid, Object a1, Object a2, Object a3, Object a4, Object a5) {
        return Dispatch.call(dispatchTarget, dispid, a1, a2, a3, a4, a5);
    }

    public final Variant call(int dispid, Object a1, Object a2, Object a3, Object a4) {
        return Dispatch.call(dispatchTarget, dispid, a1, a2, a3, a4);
    }

    public final Variant call(int dispid, Object a1, Object a2, Object a3) {
        return Dispatch.call(dispatchTarget, dispid, a1, a2, a3);
    }

    public final Variant call(int dispid, Object a1, Object a2) {
        return Dispatch.call(dispatchTarget, dispid, a1, a2);
    }

    public final Variant call(int dispid, Object a1) {
        return Dispatch.call(dispatchTarget, dispid, a1);
    }

    public final Variant call(int dispid) {
        return Dispatch.call(dispatchTarget, dispid);
    }

    public final Variant call(String name, Object a1, Object a2, Object a3, Object a4, Object a5,
            Object a6, Object a7, Object a8) {
        return Dispatch.call(dispatchTarget, name, a1, a2, a3, a4, a5, a6, a7, a8);
    }

    public final Variant call(String name, Object a1, Object a2, Object a3, Object a4, Object a5,
            Object a6, Object a7) {
        return Dispatch.call(dispatchTarget, name, a1, a2, a3, a4, a5, a6, a7);
    }

    public final Variant call(String name, Object a1, Object a2, Object a3, Object a4, Object a5,
            Object a6) {
        return Dispatch.call(dispatchTarget, name, a1, a2, a3, a4, a5, a6);
    }

    public final Variant call(String name, Object a1, Object a2, Object a3, Object a4, Object a5) {
        return Dispatch.call(dispatchTarget, name, a1, a2, a3, a4, a5);
    }

    public final Variant call(String name, Object a1, Object a2, Object a3, Object a4) {
        return Dispatch.call(dispatchTarget, name, a1, a2, a3, a4);
    }

    public final Variant call(String name, Object a1, Object a2, Object a3) {
        return Dispatch.call(dispatchTarget, name, a1, a2, a3);
    }

    public final Variant call(String name, Object a1, Object a2) {
        return Dispatch.call(dispatchTarget, name, a1, a2);
    }

    public final Variant call(String name, Object a1) {
        return Dispatch.call(dispatchTarget, name, a1);
    }

    public final Variant call(String name) {
        return Dispatch.call(dispatchTarget, name);
    }

    public final Variant callN_CaseSensitive(String name, Object[] values) {
        return Dispatch.callN_CaseSensitive(dispatchTarget, name, values);
    }

    public final Variant callN(int dispId, Object[] args) {
        return Dispatch.callN(dispatchTarget, dispId, args);
    }

    public final Variant callN(String name, Object[] args) {
        return Dispatch.callN(dispatchTarget, name, args);
    }

    public final void callSub(int dispid, Object a1, Object a2, Object a3, Object a4, Object a5,
            Object a6, Object a7, Object a8) {
        Dispatch.callSub(dispatchTarget, dispid, a1, a2, a3, a4, a5, a6, a7, a8);
    }

    public final void callSub(int dispid, Object a1, Object a2, Object a3, Object a4, Object a5,
            Object a6, Object a7) {
        Dispatch.callSub(dispatchTarget, dispid, a1, a2, a3, a4, a5, a6, a7);
    }

    public final void callSub(int dispid, Object a1, Object a2, Object a3, Object a4, Object a5,
            Object a6) {
        Dispatch.callSub(dispatchTarget, dispid, a1, a2, a3, a4, a5, a6);
    }

    public final void callSub(int dispid, Object a1, Object a2, Object a3, Object a4, Object a5) {
        Dispatch.callSub(dispatchTarget, dispid, a1, a2, a3, a4, a5);
    }

    public final void callSub(int dispid, Object a1, Object a2, Object a3, Object a4) {
        Dispatch.callSub(dispatchTarget, dispid, a1, a2, a3, a4);
    }

    public final void callSub(int dispid, Object a1, Object a2, Object a3) {
        Dispatch.callSub(dispatchTarget, dispid, a1, a2, a3);
    }

    public final void callSub(int dispid, Object a1, Object a2) {
        Dispatch.callSub(dispatchTarget, dispid, a1, a2);
    }

    public final void callSub(int dispid, Object a1) {
        Dispatch.callSub(dispatchTarget, dispid, a1);
    }

    public final void callSub(int dispid) {
        Dispatch.callSub(dispatchTarget, dispid);
    }

    public final void callSub(String name, Object a1, Object a2, Object a3, Object a4, Object a5,
            Object a6, Object a7, Object a8) {
        Dispatch.callSub(dispatchTarget, name, a1, a2, a3, a4, a5, a6, a7, a8);
    }

    public final void callSub(String name, Object a1, Object a2, Object a3, Object a4, Object a5,
            Object a6, Object a7) {
        Dispatch.callSub(dispatchTarget, name, a1, a2, a3, a4, a5, a6, a7);
    }

    public final void callSub(String name, Object a1, Object a2, Object a3, Object a4, Object a5,
            Object a6) {
        Dispatch.callSub(dispatchTarget, name, a1, a2, a3, a4, a5, a6);
    }

    public final void callSub(String name, Object a1, Object a2, Object a3, Object a4, Object a5) {
        Dispatch.callSub(dispatchTarget, name, a1, a2, a3, a4, a5);
    }

    public final void callSub(String name, Object a1, Object a2, Object a3, Object a4) {
        Dispatch.callSub(dispatchTarget, name, a1, a2, a3, a4);
    }

    public final void callSub(String name, Object a1, Object a2, Object a3) {
        Dispatch.callSub(dispatchTarget, name, a1, a2, a3);
    }

    public final void callSub(String name, Object a1, Object a2) {
        Dispatch.callSub(dispatchTarget, name, a1, a2);
    }

    public final void callSub(String name, Object a1) {
        Dispatch.callSub(dispatchTarget, name, a1);
    }

    public final void callSub(String name) {
        Dispatch.callSub(dispatchTarget, name);
    }

    public final void callSubN(int dispId, Object[] args) {
        Dispatch.callSubN(dispatchTarget, dispId, args);
    }

    public final void callSubN(String name, Object[] args) {
        Dispatch.callSubN(dispatchTarget, name, args);
    }

    public final Variant get_CaseSensitive(String name) {
        return Dispatch.get_CaseSensitive(dispatchTarget, name);
    }

    public final Variant get(int dispid) {
        return Dispatch.get(dispatchTarget, dispid);
    }

    public final Variant get(String name) {
        return Dispatch.get(dispatchTarget, name);
    }

    public final int getIDOfName(String name) {
        return Dispatch.getIDOfName(dispatchTarget, name);
    }

    public final int[] getIDsOfNames(int lcid, String[] names) {
        return Dispatch.getIDsOfNames(dispatchTarget, lcid, names);
    }

    public final int[] getIDsOfNames(String[] names) {
        return Dispatch.getIDsOfNames(dispatchTarget, names);
    }

    public final String getProgramId() {
        return dispatchTarget.getProgramId();
    }

    public final Variant invoke(int dispId, int wFlags, Object[] oArg, int[] uArgErr) {
        return Dispatch.invoke(dispatchTarget, dispId, wFlags, oArg, uArgErr);
    }

    public final Variant invoke(String name, int dispId, int lcid, int wFlags, Object[] oArg,
            int[] uArgErr) {
        return Dispatch.invoke(dispatchTarget, name, dispId, lcid, wFlags, oArg, uArgErr);
    }

    public final Variant invoke(String name, int wFlags, Object[] oArg, int[] uArgErr) {
        return Dispatch.invoke(dispatchTarget, name, wFlags, oArg, uArgErr);
    }

    public final void invokeSub(int dispid, int wFlags, Object[] oArg, int[] uArgErr) {
        Dispatch.invokeSub(dispatchTarget, dispid, wFlags, oArg, uArgErr);
    }

    public final void invokeSub(String name, int dispid, int lcid, int wFlags, Object[] oArg,
            int[] uArgErr) {
        Dispatch.invokeSub(dispatchTarget, name, dispid, lcid, wFlags, oArg, uArgErr);
    }

    public final void invokeSub(String name, int wFlags, Object[] oArg, int[] uArgErr) {
        Dispatch.invokeSub(dispatchTarget, name, wFlags, oArg, uArgErr);
    }

    public final void invokeSubv(int dispId, int wFlags, Variant[] vArg, int[] uArgErr) {
        Dispatch.invokeSubv(dispatchTarget, dispId, wFlags, vArg, uArgErr);
    }

    public final void invokeSubv(String name, int dispId, int lcid, int wFlags, Variant[] vArg,
            int[] uArgErr) {
        Dispatch.invokeSubv(dispatchTarget, name, dispId, lcid, wFlags, vArg, uArgErr);
    }

    public final void invokeSubv(String name, int wFlags, Variant[] vArg, int[] uArgErr) {
        Dispatch.invokeSubv(dispatchTarget, name, wFlags, vArg, uArgErr);
    }

    public final Variant invokev(int dispId, int wFlags, Variant[] vArg, int[] uArgErr) {
        return Dispatch.invokev(dispatchTarget, dispId, wFlags, vArg, uArgErr);
    }

    public final Variant invokev(String name, int dispId, int lcid, int wFlags, Variant[] vArg,
            int[] uArgErr) {
        return Dispatch.invokev(dispatchTarget, name, dispId, lcid, wFlags, vArg, uArgErr);
    }

    public final Variant invokev(String name, int wFlags, Variant[] vArg, int[] uArgErr,
            int wFlagsEx) {
        return Dispatch.invokev(dispatchTarget, name, wFlags, vArg, uArgErr, wFlagsEx);
    }

    public final Variant invokev(String name, int wFlags, Variant[] vArg, int[] uArgErr) {
        return Dispatch.invokev(dispatchTarget, name, wFlags, vArg, uArgErr);
    }

    public final void put_Casesensitive(String name, Object val) {
        Dispatch.put_Casesensitive(dispatchTarget, name, val);
    }

    public final void put(int dispid, Object val) {
        Dispatch.put(dispatchTarget, dispid, val);
    }

    public final void put(String name, Object val) {
        Dispatch.put(dispatchTarget, name, val);
    }

    public final void putRef(int dispid, Object val) {
        Dispatch.putRef(dispatchTarget, dispid, val);
    }

    public final void putRef(String name, Object val) {
        Dispatch.putRef(dispatchTarget, name, val);
    }

    public final Dispatch QueryInterface(String iid) {
        return dispatchTarget.QueryInterface(iid);
    }

    public final void safeRelease() {
        dispatchTarget.safeRelease();
    }

}

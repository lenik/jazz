package net.bodz.bas.compare;

public interface IContentComparator<view_t, mutable_t> {

    ICompareResult<view_t, mutable_t> compare(view_t source, view_t target);

}

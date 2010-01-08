package net.bodz.bas.fs;

public interface IEntryNameFilter {

    boolean accept(IFolder folder, String name);

}

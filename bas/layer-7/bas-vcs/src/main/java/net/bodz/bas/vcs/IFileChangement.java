package net.bodz.bas.vcs;

public interface IFileChangement {

    IVcsWorkingCopy getWorkingCopy();

    String getPath();

    FileChangeStatus getStatus();

    String getRenamedFrom();

    int getSimilarity();

    int getAddedLines();

    int getRemovedLines();

}
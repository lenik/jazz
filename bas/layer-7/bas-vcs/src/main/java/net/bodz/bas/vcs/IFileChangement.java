package net.bodz.bas.vcs;

public interface IFileChangement {

    String getPath();

    FileChangeStatus getStatus();

    String getRenamedFrom();

    int getSimilarity();

    int getAddedLines();

    int getRemovedLines();

}
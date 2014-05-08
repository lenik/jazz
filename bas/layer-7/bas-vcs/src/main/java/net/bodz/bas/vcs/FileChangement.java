package net.bodz.bas.vcs;

public class FileChangement
        implements IFileChangement {

    IVcsWorkingCopy workingCopy;
    String path;
    FileChangeStatus status = FileChangeStatus.MODIFY;
    String renamedFrom;
    int similarity;

    int addedLines;
    int removedLines;

    public FileChangement(IVcsWorkingCopy workingCopy) {
        this.workingCopy = workingCopy;
    }

    @Override
    public IVcsWorkingCopy getWorkingCopy() {
        return workingCopy;
    }

    @Override
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public FileChangeStatus getStatus() {
        return status;
    }

    public void setStatus(FileChangeStatus status) {
        this.status = status;
    }

    @Override
    public String getRenamedFrom() {
        return renamedFrom;
    }

    public void setRenamedFrom(String renamedFrom) {
        this.renamedFrom = renamedFrom;
    }

    @Override
    public int getSimilarity() {
        return similarity;
    }

    public void setSimilarity(int similarity) {
        this.similarity = similarity;
    }

    @Override
    public int getAddedLines() {
        return addedLines;
    }

    public void setAddedLines(int addedLines) {
        this.addedLines = addedLines;
    }

    @Override
    public int getRemovedLines() {
        return removedLines;
    }

    public void setRemovedLines(int removedLines) {
        this.removedLines = removedLines;
    }

}

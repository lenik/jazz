package net.bodz.bas.reflect.members;

import net.bodz.bas.exceptions.ReadOnlyException;

public class FindMemberOptions {

    private boolean findAllDeclaredMembers;
    private Class<?> stopClass;

    private int maxDistance;

    private boolean setAccessibleAlways;
    private boolean ignoreException;

    private boolean isSortResults;

    public boolean isSetAccessibleAlways() {
        return setAccessibleAlways;
    }

    public void setSetAccessibleAlways(boolean setAccessibleAlways) {
        this.setAccessibleAlways = setAccessibleAlways;
    }

    public boolean isIgnoreException() {
        return ignoreException;
    }

    public void setIgnoreException(boolean ignoreException) {
        this.ignoreException = ignoreException;
    }

    public boolean isGetDeclaredMembers() {
        return findAllMembers;
    }

    public void setGetDeclaredMembers(boolean getDeclaredMembers) {
        this.findAllMembers = getDeclaredMembers;
    }

    public Class<?> getStopClass() {
        return stopClass;
    }

    public void setStopClass(Class<?> stopClass) {
        this.stopClass = stopClass;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    public boolean isSortResults() {
        return isSortResults;
    }

    public void setSortResults(boolean isSortResults) {
        this.isSortResults = isSortResults;
    }

    private static final FindMemberOptions defaultOptions;
    static {
        defaultOptions = new FindMemberOptions() {

            @Override
            public void setGetDeclaredMembers(boolean getDeclaredMembers) {
                throw new ReadOnlyException();
            }

            @Override
            public void setIgnoreException(boolean ignoreException) {
                throw new ReadOnlyException();
            }

            @Override
            public void setSetAccessibleAlways(boolean setAccessibleAlways) {
                throw new ReadOnlyException();
            }

            @Override
            public void setSortResults(boolean isSortResults) {
                throw new ReadOnlyException();
            }

            @Override
            public void setStopClass(Class<?> stopClass) {
                throw new ReadOnlyException();
            }

        };
    }

    public static FindMemberOptions getDefaultOptions() {
        return defaultOptions;
    }

}

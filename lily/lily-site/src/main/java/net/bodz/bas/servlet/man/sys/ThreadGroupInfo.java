package net.bodz.bas.servlet.man.sys;

public class ThreadGroupInfo {

    ThreadGroup group;

    public ThreadGroupInfo(ThreadGroup group) {
        this.group = group;
    }

    public String getName() {
        return group.getName();
    }

    public String getParentName() {
        ThreadGroup parent = group.getParent();
        return parent == null ? null : parent.getName();
    }

    public int getMaxPriority() {
        return group.getMaxPriority();
    }

    @Deprecated
    public boolean isDaemon() {
        return group.isDaemon();
    }

    @Deprecated
    public boolean isDestroyed() {
        return group.isDestroyed();
    }

    public int getActiveCount() {
        return group.activeCount();
    }

    public int getActiveGroupCount() {
        return group.activeGroupCount();
    }

    public String getString() {
        return group.toString();
    }

}

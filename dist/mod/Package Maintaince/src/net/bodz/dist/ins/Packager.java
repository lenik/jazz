package net.bodz.dist.ins;

public class Packager {

    IProject project;
    ISession session;

    public void pack() throws InstallException {
        session.pack(project, 0);
    }

}

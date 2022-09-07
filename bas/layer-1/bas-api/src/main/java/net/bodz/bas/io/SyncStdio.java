package net.bodz.bas.io;

public class SyncStdio
        extends SyncIndentedOuts {

    private static final long serialVersionUID = 1L;

    public static final int STDOUT = 0;
    public static final int STDERR = 1;

    public final ICharIn in;
    public final ITreeOut out;
    public final ITreeOut err;

    public SyncStdio() {
        super(Stdio.cout.indented(), Stdio.cerr.indented());
        in = Stdio.cin;
        out = get(STDOUT);
        err = get(STDERR);
    }

}

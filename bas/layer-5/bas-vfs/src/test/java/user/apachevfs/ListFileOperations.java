package user.apachevfs;

import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.VFS;
import org.apache.commons.vfs.operations.FileOperation;

import net.bodz.bas.bean.BeanDump;
import net.bodz.bas.sio.PrintOutImpl;
import net.bodz.bas.sio.Stdio;

public class ListFileOperations {

    static PrintOutImpl out = new PrintOutImpl(Stdio.cout);

    public static void main(String[] args)
            throws Exception {
        FileObject file = VFS.getManager().resolveFile(//
                // self.toString()
                "http://www.google.com/abcd/d%20ef?a=b&c=d"

        );

        out.println("FileObject: ");
        BeanDump.dumpProperties(file, out);

        out.println();
        out.println("file.name:");
        BeanDump.dumpProperties(file.getName(), out);

        Class<?>[] operations = file.getFileOperations().getOperations();
        if (operations != null)
            for (Class<?> opt : operations) {
                FileOperation op = file.getFileOperations().getOperation(opt);
                out.println("File operation of " + opt);
                BeanDump.dumpProperties(op, out);
                out.println();
            }
    }

}

package net.bodz.bas.fs;

import java.net.URL;

import net.bodz.bas.bean.BeanDump;
import net.bodz.bas.sio.LineCharOutImpl;
import net.bodz.bas.sio.Stdio;

import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.VFS;
import org.apache.commons.vfs.operations.FileOperation;

public class VfsTest {

    static LineCharOutImpl out = new LineCharOutImpl(Stdio.cout);

    static URL getClassFile(Class<?> clazz) {
        return clazz.getResource(clazz.getSimpleName() + ".class");
    }

    public static void main(String[] args)
            throws Exception {
        URL self = getClassFile(String.class);
        FileObject file = VFS.getManager().resolveFile(//
                // self.toString()
                "http://www.google.com/abcd/d%20ef?a=b&c=d"

        );

        System.out.println("FileObject: ");
        BeanDump.dumpProperties(out, file);

        System.out.println();
        System.out.println("file.name:");
        BeanDump.dumpProperties(out, file.getName());

        Class<?>[] operations = file.getFileOperations().getOperations();
        if (operations != null)
            for (Class<?> opt : operations) {
                FileOperation op = file.getFileOperations().getOperation(opt);
                System.out.println("File operation of " + opt);
                BeanDump.dumpProperties(out, op);
                System.out.println();
            }
    }

}

package net.bodz.bas.cli.skel;

import java.io.IOException;

import net.bodz.bas.lang.negotiation.Negotiation;
import net.bodz.bas.potato.mda.tagbook.PotatoBook;
import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.text.flatf.FlatfOutput;
import net.bodz.bas.vfs.IFile;
import net.bodz.mda.xjdoc.conv.ClassDocs;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.tags.ITagBook;

public class BatchCLITest
        extends BatchCLI {

    @Override
    protected void processImpl(FileHandler handler)
            throws Exception {
        String name = handler.getName();
        IFile file = handler.getFile();
        IFile outDir = handler.getOutDir();
        IFile outFile = handler.getOutFile();
        IFile destFile = handler.getDestFile();

        System.out.println("Name: " + name);
        System.out.println("File: " + file);
        System.out.println("Out-Dir: " + outDir);
        System.out.println("Out-File: " + outFile);
        System.out.println("Dest-File: " + destFile);
    }

    static void printBaseDoc()
            throws IOException {
        ClassDoc doc = ClassDocs.loadFromResource(BatchCLI.class);
        BCharOut buf = new BCharOut();
        FlatfOutput out = new FlatfOutput(buf);
        doc.writeObject(out, Negotiation.list(//
                Negotiation.parameter(ITagBook.class, new PotatoBook())));
        System.out.println(buf);
    }

    static void runSkel()
            throws Exception {
        new BatchCLITest().execute(//
                "/tmp" //
                );
    }

    public static void main(String[] args)
            throws Exception {
        // printBaseDoc();
        runSkel();
    }

}

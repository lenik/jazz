package net.bodz.bas.cli.skel;

import java.io.IOException;
import java.util.Map.Entry;

import net.bodz.bas.cli.model.IOption;
import net.bodz.bas.cli.model.IOptionGroup;
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

        System.out.println("ProcessImp:");
        System.out.println("    Name: " + name);
        System.out.println("    File: " + file);
        System.out.println("    Out-Dir: " + outDir);
        System.out.println("    Out-File: " + outFile);
        System.out.println("    Dest-File: " + destFile);
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
                "-h" //
                );
    }

    static void dumpOptions(IOptionGroup group) {
        IOptionGroup parent = group.getParent();
        if (parent != null) {
            dumpOptions(parent);
            System.out.println();
        }

        System.out.printf("Group [%s] %s: %s\n", //
                group.getName(), //
                group.getDisplayName(), //
                group.getDescription());

        for (Entry<String, IOption> entry : group.getLocalOptionMap().entrySet()) {
            IOption opt = entry.getValue();
            System.out.printf("    Option [%s] %s: %s\n", //
                    entry.getKey(), //
                    opt.getDisplayName(), //
                    opt.getDescription());
            System.out.println("        Aliases: " + opt.getAliases());
            System.out.println("        Type: " + opt.getType());
            System.out.println("        Value-Type: " + opt.getValueType());
        }
    }

    public static void main(String[] args)
            throws Exception {
        // printBaseDoc();
        // runSkel();
        dumpOptions(new BatchCLITest());
    }

}

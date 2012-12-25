package net.bodz.bas.cli.skel;

import java.io.IOException;
import java.util.Map.Entry;

import net.bodz.bas.cli.model.IOption;
import net.bodz.bas.cli.model.IOptionGroup;
import net.bodz.bas.potato.mda.tagbook.PotatoBook;
import net.bodz.bas.rtx.Negotiation;
import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.text.flatf.FlatfOutput;
import net.bodz.bas.vfs.IFile;
import net.bodz.mda.xjdoc.conv.ClassDocs;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.tags.ITagBook;

public class BatchCLITest
        extends BatchCLI {

    @Override
    public void processFile(FileHandler handler)
            throws Exception {
        String pathSpec = handler.getPathSpec();
        IFile file = handler.getInputFile();
        IFile outFile = handler.getOutputFile();

        System.out.println("ProcessFile: ");
        System.out.println("    Path-Spec: " + pathSpec);
        System.out.println("    File: " + file);
        System.out.println("    Out-File: " + outFile);
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

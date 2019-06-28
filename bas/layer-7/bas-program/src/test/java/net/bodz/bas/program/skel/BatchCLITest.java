package net.bodz.bas.program.skel;

import java.io.IOException;
import java.util.Map.Entry;

import net.bodz.bas.fmt.flatf.FlatfOutput;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.meta.build.MainVersion;
import net.bodz.bas.meta.build.ProgramName;
import net.bodz.bas.program.model.IOption;
import net.bodz.bas.program.model.IOptionGroup;
import net.bodz.bas.rtx.Options;
import net.bodz.bas.vfs.IFile;
import net.bodz.mda.xjdoc.IXjdocProvider;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.artifact.ArtifactTagLibrary;
import net.bodz.mda.xjdoc.taglib.ITagLibrary;

@MainVersion({ 0, 0 })
@ProgramName("battest")
public class BatchCLITest
        extends BatchCLI {

    static IXjdocProvider xjdocs = Xjdocs.getDefaultProvider();

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
        ClassDoc doc = xjdocs.getClassDoc(BatchCLI.class);
        BCharOut buf = new BCharOut();
        FlatfOutput out = new FlatfOutput(buf);
        doc.writeObject(out, new Options() //
                .addOption(ITagLibrary.class, new ArtifactTagLibrary()));
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
                group.getLabel(), //
                group.getDescription());

        for (Entry<String, IOption> entry : group.getLocalOptionMap().entrySet()) {
            IOption opt = entry.getValue();
            System.out.printf("    Option [%s] %s: %s\n", //
                    entry.getKey(), //
                    opt.getLabel(), //
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

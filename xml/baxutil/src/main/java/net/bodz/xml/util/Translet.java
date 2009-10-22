package net.bodz.xml.util;

import java.io.File;
import java.nio.charset.Charset;

import net.bodz.bas.a.BootInfo;
import net.bodz.bas.cli.BasicCLI;
import net.bodz.bas.cli.a.Option;

@BootInfo(syslibs = "bodz_xml")
public abstract class Translet extends BasicCLI {

    @Option(alias = ".E", vnam = "ENCODING", doc = "default encoding of output files")
    protected Charset outputEncoding = Charset.defaultCharset();

    @Option(alias = "O", vnam = "DIR", doc = "put output files under this directory")
    protected File    outputDirectory;

    @Option(vnam = ".EXT", optional = ".bak", doc = "backup modified files with given extension")
    protected String  backupExtension;

    @Option(alias = "P", doc = "protected mode, don't modify any files")
    protected boolean dryRun;

    @Option(alias = "f", doc = "force overwrite existing files, this includes --error-continue")
    protected boolean force;

}

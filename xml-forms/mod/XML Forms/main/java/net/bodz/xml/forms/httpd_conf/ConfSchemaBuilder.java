package net.bodz.xml.forms.httpd_conf;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;

import net.bodz.bas.a.Doc;
import net.bodz.bas.a.RcsKeywords;
import net.bodz.bas.a.Version;
import net.bodz.bas.cli.a.Option;
import net.bodz.bas.io.util.GlobFilter;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TreeTextMap;
import net.bodz.xml.forms.apache.jaxb.Context;
import net.bodz.xml.forms.apache.jaxb.Directivesynopsis;
import net.bodz.xml.forms.apache.jaxb.Modulesynopsis;
import net.bodz.xml.forms.xs.jaxb.Schema;
import net.bodz.xml.util.SAXSourceWithModifiedEntities;
import net.bodz.xml.util.Translet;

@Doc("Build httpd-conf.xsd from apache manual docs")
@RcsKeywords(id = "$Id$")
@Version( { 0, 1 })
public class ConfSchemaBuilder extends Translet {

    @Option(alias = "m", vnam = "DIR", doc = "get conf elements from manual docs", required = true)
    File manualDirectory;

    @Override
    protected void _boot() throws Exception {
        if (!manualDirectory.isDirectory())
            throw new IllegalArgumentException("manualDirectory isn't a directory: "
                    + manualDirectory);
        if (outputDirectory == null)
            outputDirectory = new File(manualDirectory, "mod");
    }

    @Override
    protected void doMain(String[] args) throws Exception {
        JAXBContext context = JAXBContext.newInstance(APACHE_PACKAGE);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        File[] docs = new File(manualDirectory, "mod").listFiles(new GlobFilter("*.xml"));
        TextMap<Set<String>> contextDirectives = new TreeTextMap<Set<String>>();

        for (File doc : docs) {
            if (skipnames.contains(doc.getName()))
                continue;
            L.info("Parse ", doc);
            try {
                FileInputStream in = new FileInputStream(doc);
                Source source = new SAXSourceWithModifiedEntities(in);
                Object obj = unmarshaller.unmarshal(source);
                if (obj instanceof Modulesynopsis) {
                    Modulesynopsis syn = (Modulesynopsis) obj;
                    Schema schema = SchemaFragments.build(syn);
                    File xsdFile = new File(outputDirectory, syn.getName() + ".xsd");
                    L.info("  save schema to ", xsdFile);
                    JAXB.marshal(schema, xsdFile);

                    // collect contexts
                    for (Directivesynopsis d : syn.getDirectivesynopsis()) {
                        for (Context c : d.getContextlist().getContext()) {
                            String ctx = c.getvalue();
                            Set<String> dset = contextDirectives.get(ctx);
                            if (dset == null)
                                contextDirectives.put(ctx, dset = new TreeSet<String>());
                            dset.add(d.getName());

                        }
                    }
                    continue;
                }
            } catch (UnmarshalException e) {
            }
            L.warn("  skipped: ", doc);
        }
    }

    public static void main(String[] args) throws Exception {
        new ConfSchemaBuilder().run(args);
    }

    public static String NS             = "http://xml.bodz.net/f/forms/httpd-conf";

    static String        APACHE_PACKAGE = Modulesynopsis.class.getPackage().getName();
    static Set<String>   skipnames;
    static {
        skipnames = new HashSet<String>();
        skipnames.add("allmodules.xml");
        skipnames.add("directives.xml");
        skipnames.add("allmodules.xml");
    }

}

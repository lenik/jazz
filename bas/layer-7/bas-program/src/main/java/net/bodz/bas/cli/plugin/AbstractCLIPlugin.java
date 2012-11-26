package net.bodz.bas.cli.plugin;

import java.util.Map;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.cli.model.IOption;
import net.bodz.bas.cli.model.IOptionGroup;
import net.bodz.bas.cli.model.OptionGroupFactory;
import net.bodz.bas.cli.skel.CLISyntaxException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.mda.xjdoc.model1.ArtifactObject;

public class AbstractCLIPlugin
        extends ArtifactObject
        implements ICLIPlugin {

    protected IOptionGroup getOptions() {
        return OptionGroupFactory.getClassOptions(getClass());
    }

    @Override
    public void setParameters(Map<String, Object> parameters)
            throws CLISyntaxException, ParseException {
        if (parameters.isEmpty())
            return;
        IOptionGroup group = getOptions();
        group.load(this, parameters);
    }

    @Deprecated
    @SuppressWarnings("unchecked")
    @Override
    public void initialize() {
        super.initialize();
        IOptionGroup pptionGroup = getOptions();
        Map<?, ?> properties = System.getProperties();
        try {
            pptionGroup.load(this, (Map<String, ?>) properties);
        } catch (CLISyntaxException e) {
            throw new Error(e.getMessage(), e);
        } catch (ParseException e) {
            throw new Error(e.getMessage(), e);
        }
    }

    @Override
    public void formatHelpText(IPrintOut out, String prefix) {
        IOptionGroup opts = getOptions();
        Map<String, IOption> map = opts.getLocalOptionMap();
        int maxlen = 0;
        for (String name : map.keySet())
            if (name.length() > maxlen)
                maxlen = name.length();
        for (Map.Entry<String, IOption> e : map.entrySet()) {
            String name = e.getKey();
            IOption opt = e.getValue();
            // String vnam = opt.o.vnam();
            String description = opt.getDescription().toString();

            out.print(prefix);
            out.print(name);
            if (!description.isEmpty()) {
                out.print(": ");
                if (name.length() < maxlen)
                    out.print(Strings.repeat(maxlen - name.length(), ' '));
                out.print(description);
            }
            out.println();
        }
    }

}

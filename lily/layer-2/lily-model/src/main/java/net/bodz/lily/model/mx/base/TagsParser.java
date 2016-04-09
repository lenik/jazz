package net.bodz.lily.model.mx.base;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractParser;
import net.bodz.lily.model.base.schema.TagDef;

public class TagsParser
        extends AbstractParser<List<TagDef>> {

    @Override
    public List<TagDef> parse(String text, IOptions options)
            throws ParseException {
        Set<String> labels = new LinkedHashSet<>();
        StringTokenizer tokens = new StringTokenizer(text, "\n");
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            labels.add(token);
        }

        List<TagDef> tags = new ArrayList<>();
        for (String label : labels) {
            // if (StringPred.isDecimal(token))
            TagDef tag = new TagDef();
            tag.setLabel(label);
            tags.add(tag);
        }
        return tags;
    }

}

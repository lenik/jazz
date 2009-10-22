package net.bodz.xml.forms.httpd_conf;

import java.util.List;

import javax.xml.namespace.QName;

import net.bodz.xml.forms.apache.jaxb.Directivesynopsis;
import net.bodz.xml.forms.apache.jaxb.Modulesynopsis;
import net.bodz.xml.forms.xs.jaxb.Annotated;
import net.bodz.xml.forms.xs.jaxb.Attribute;
import net.bodz.xml.forms.xs.jaxb.ExplicitGroup;
import net.bodz.xml.forms.xs.jaxb.FormChoice;
import net.bodz.xml.forms.xs.jaxb.LocalComplexType;
import net.bodz.xml.forms.xs.jaxb.LocalElement;
import net.bodz.xml.forms.xs.jaxb.LocalSimpleType;
import net.bodz.xml.forms.xs.jaxb.NamedGroup;
import net.bodz.xml.forms.xs.jaxb.Restriction;
import net.bodz.xml.forms.xs.jaxb.Schema;
import net.bodz.xml.forms.xs.jaxb.XsHelper;

public class SchemaFragments {

    public static Schema build(Modulesynopsis syn) {
        Schema schema = new Schema();
        schema.setTargetNamespace(ConfSchemaBuilder.NS);
        schema.setElementFormDefault(FormChoice.QUALIFIED);
        schema.setAttributeFormDefault(FormChoice.UNQUALIFIED);

        NamedGroup group = new NamedGroup();
        group.setName(syn.getName());
        group.setAnnotation(XsHelper.getAnnotation(syn.getDescription()));
        schema.getIncludeOrImportOrRedefine().add(group);
        ExplicitGroup choiceGroup = new ExplicitGroup();
        group.getParticle().add(XsHelper.FACTORY.createChoice(choiceGroup));
        List<Object> choiceList = choiceGroup.getParticle();

        // for (Section s : syn.getSection())

        for (Directivesynopsis d : syn.getDirectivesynopsis()) {
            LocalElement element = new LocalElement();
            LocalComplexType type = new LocalComplexType();
            element.setName(d.getName());
            element.setAnnotation(XsHelper.getAnnotation(d.getDescription()));
            element.setComplexType(type);
            List<Annotated> attributes = type.getAttributeOrAttributeGroup();

            String syntax = d.getSyntax();
            if (syntax == null)
                continue;
            String[] terms = syntax.split("\\s+");
            for (int i = 1; i < terms.length; i++) {
                String term = terms[i];
                if (term.contains("|")) {
                    String[] enums = term.split("\\|");
                    String aname = "value";
                    Attribute attr = new Attribute();
                    attr.setName(aname);
                    attr.setUse("required");
                    LocalSimpleType simtype = new LocalSimpleType();
                    {
                        Restriction restriction = new Restriction();
                        restriction.setBase(new QName(XsHelper.NS, "string"));
                        List<Object> facets = restriction.getFacets();
                        for (String e : enums)
                            facets.add(XsHelper.newEnum(e));
                        simtype.setRestriction(restriction);
                        attr.setSimpleType(simtype);
                    }
                    attributes.add(attr);
                } else if (term.matches("\\w+")) {
                    String aname = term.toLowerCase();
                    Attribute attr = new Attribute();
                    attr.setName(aname);
                    attr.setUse("required");
                    attr.setType(new QName(XsHelper.NS, "string"));
                    attributes.add(attr);
                }
            }
            choiceList.add(XsHelper.FACTORY.createGroupElement(element));
        }
        return schema;
    }

}

package net.bodz.lily.codegen.doc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.bodz.bas.c.org.json.JsonWriter;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.lily.model.base.CoObject;
import net.bodz.lily.util.mapper.DataListFields;
import net.bodz.mda.xjdoc.model.ClassDoc;

public class PropertyExporter {

    ModuleIndexer indexer;
    boolean usePathKey = true;

    public PropertyExporter(ModuleIndexer indexer) {
        if (indexer == null)
            throw new NullPointerException("indexer");
        this.indexer = indexer;
    }

    public void export(JsonWriter out, AbstractTypeInfo start) {
        export(out, start, null, "");
    }

    public void export(JsonWriter out, AbstractTypeInfo start, Set<String> includes, String pathPrefix) {
        AbstractTypeInfo node = start;
        while (node != null) {
            List<IProperty> properties = new ArrayList<>();
            for (IProperty property : node.getPropertyMap().values()) {
                String name = property.getName();
                if (includes != null && !includes.contains(name))
                    continue;
                properties.add(property);
            }

            if (properties.isEmpty()) {
                node = node.parent;
                continue;
            }

            out.object(); // property-group
            // out.entry("declaredClass", node.getDeclaredClass().getName());
            out.entry("name", node.declaredClass.getName());
            out.entry("doc", node.doc.toString());
            out.key("properties");
            out.object(); // properties

            for (IProperty property : properties) {
                String name = property.getName();
                Class<?> type = property.getPropertyType();
                if (usePathKey)
                    out.key(pathPrefix + name);
                else
                    out.key(name);
                out.object();
                out.entry("name", name);
                out.entry("namePath", pathPrefix + name);
                out.entry("type", type.getName());
                out.entry("label", property.getLabel());
                out.entry("description", property.getDescription());

                if (CoObject.class.isAssignableFrom(type)) {
                    EntityInfo nested = indexer.getEntity(type);
                    ClassDoc classDoc = nested.getDoc();
                    if (classDoc != null)
                        out.entry("doc", classDoc.getText().toString());

                    // generally, only id and label is available in nested references.
                    DataListFields aDataListFields = property.getAnnotation(DataListFields.class);
                    String[] dataListFields = { "id", "label" };
                    if (aDataListFields != null)
                        dataListFields = aDataListFields.value();
                    Set<String> fieldSet = new HashSet<>(Arrays.asList(dataListFields));

                    out.key("propertyGroups");
                    out.array();
                    export(out, nested, fieldSet, //
                            usePathKey ? (pathPrefix + name + ".") : name);
                    out.endArray();
                }
                out.endObject(); // property
            }
            out.endObject(); // properties
            node = node.parent;
            out.endObject(); // property-group
        }
    }

}

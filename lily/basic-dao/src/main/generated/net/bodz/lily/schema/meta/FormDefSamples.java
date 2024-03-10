package net.bodz.lily.schema.meta;

import net.bodz.lily.schema.meta.dao.SchemaDefMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class FormDefSamples
        extends TestSampleBuilder {

    public SchemaDef schema;

    @Override
    public FormDef build()
            throws Exception {
        FormDef a = new FormDef();
        a.setSchema(schema);
        a.setSubject("kum'rko x hzfgj. ubi_max, dgoau ae ii.");
        a.setRawText("Uja, cylp uieou'ueqo*wie i! oev; osoi-x, Yspwu m? exn e, hne_ixar*T'usuce, oeuno h! yme, pywup");
        return a;
    }

    @Override
    public FormDefSamples wireAny(IRandomPicker picker) {
        this.schema = picker.pickAny(SchemaDefMapper.class, "_schema");
        return this;
    }

    @Override
    public FormDef buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}

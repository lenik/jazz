package net.bodz.lily.model.base.schema;

import java.util.List;

import net.bodz.bas.db.meta.TableName;

/**
 * @label Schema
 * @label.zh.cn 方案
 */
@TableName("schema")
public class SchemaDef
        extends AbstractDefinition<SchemaDef> {

    private static final long serialVersionUID = 1L;

    List<CategoryDef> categories;
    List<PhaseDef> phases;
    List<PriorityDef> priorities;
    List<TagSetDef> tagSets;

}

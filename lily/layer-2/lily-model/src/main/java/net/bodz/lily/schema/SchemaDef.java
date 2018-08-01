package net.bodz.lily.schema;

import java.util.List;

import javax.persistence.Table;

/**
 * @label Schema
 * @label.zh.cn 方案
 */
@Table(name = "schema")
public class SchemaDef
        extends AbstractDefinition<SchemaDef> {

    private static final long serialVersionUID = 1L;

    List<CategoryDef> categories;
    List<PhaseDef> phases;
    List<PriorityDef> priorities;
    List<TagGroupDef> tagSets;
    List<ParameterDef> parameters;

    public List<CategoryDef> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDef> categories) {
        this.categories = categories;
    }

    public List<PhaseDef> getPhases() {
        return phases;
    }

    public void setPhases(List<PhaseDef> phases) {
        this.phases = phases;
    }

    public List<PriorityDef> getPriorities() {
        return priorities;
    }

    public void setPriorities(List<PriorityDef> priorities) {
        this.priorities = priorities;
    }

    public List<TagGroupDef> getTagGroups() {
        return tagSets;
    }

    public void setTagGroups(List<TagGroupDef> tagSets) {
        this.tagSets = tagSets;
    }

    public List<ParameterDef> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParameterDef> parameters) {
        this.parameters = parameters;
    }

}

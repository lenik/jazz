package net.bodz.violet.issue;

import java.util.Set;

import javax.persistence.Table;

import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.t.base.CoMessage;

@IdType(Long.class)
@Table(name = "issue")
public class Issue
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    private IssueCategory category;
    private IssuePhase phase;
    private Set<IssueTag> tags;

    /**
     * @label Category
     * @label.zh 分类
     */
    @OfGroup(StdGroup.Classification.class)
    public IssueCategory getCategory() {
        return category;
    }

    public void setCategory(IssueCategory category) {
        this.category = category;
    }

    @OfGroup(StdGroup.Status.class)
    public IssuePhase getPhase() {
        return phase;
    }

    public void setPhase(IssuePhase phase) {
        this.phase = phase;
    }

    @OfGroup(StdGroup.Classification.class)
    public Set<IssueTag> getTags() {
        return tags;
    }

    public void setTags(Set<IssueTag> tags) {
        this.tags = tags;
    }

}

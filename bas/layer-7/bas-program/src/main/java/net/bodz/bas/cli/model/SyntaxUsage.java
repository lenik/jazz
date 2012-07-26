package net.bodz.bas.cli.model;

import net.bodz.bas.i18n.dom.DomainString;

/**
 * Examples:
 * 
 * @usage.clean $0 clean -df
 *              <p>
 *              Remove all untracked files, and directories.
 *              <p lang="zh-cn">
 *              删除所有未跟踪的文件和目录。
 */
public class SyntaxUsage {

    String id;
    String syntax;
    DomainString description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSyntax() {
        return syntax;
    }

    public void setSyntax(String syntax) {
        this.syntax = syntax;
    }

    public DomainString getDescription() {
        return description;
    }

    public void setDescription(DomainString description) {
        this.description = description;
    }

}

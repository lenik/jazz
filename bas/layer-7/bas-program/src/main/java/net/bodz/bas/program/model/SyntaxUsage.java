package net.bodz.bas.program.model;

import net.bodz.bas.i18n.dom.iString;

/**
 * Examples:
 *
 * <pre>
 * &#64;usage.clean $0 clean -df
 *      Remove all untracked files, and directories.
 *      &lt;p lang="zh-cn"&gt;
 *      删除所有未跟踪的文件和目录。
 * </pre>
 */
public class SyntaxUsage {

    String id;
    String syntax;
    iString description;

    SyntaxUsage() {
    }

    public SyntaxUsage(String id, String script) {
        if (script == null)
            throw new NullPointerException("script");

        this.id = id;

        int lineSep = script.indexOf('\n');
        String _description;
        if (lineSep == -1) {
            syntax = script;
            _description = "";
        } else {
            syntax = script.substring(0, lineSep).trim();
            _description = script.substring(lineSep + 1).trim();
        }

        description = iString.fn.parseParaLangString(_description);
    }

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

    public iString getDescription() {
        return description;
    }

    public void setDescription(iString description) {
        this.description = description;
    }

}

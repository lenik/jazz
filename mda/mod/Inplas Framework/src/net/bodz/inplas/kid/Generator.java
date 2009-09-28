package net.bodz.inplas.kid;


/**
 * Features
 * <ol>
 * <li> using freejava dialects as abstract dbms layer
 * <li>
 * </ol>
 */
public class Generator {

    Project project;

    public Generator(Project project) {
        this.project = project;
    }

    public void generate() {
        for (Table table : project.tables) {
            generate(table);
        }
    }

    public void generate(Table table) {
        table.getName();
    }

    protected String jspFileOfTable(String name) {
        return String.format("%s.jsp", name);
    }

    protected String jspVarOfField(String field) {
        return String.format("%s", field);
    }

    protected String jsVarOfField(String field) {
        return String.format("%s", field);
    }

}

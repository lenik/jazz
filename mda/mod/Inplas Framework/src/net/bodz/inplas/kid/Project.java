package net.bodz.inplas.kid;

import java.util.ArrayList;
import java.util.List;

public class Project {

    public Config         config = new Config();

    protected List<Table> tables = new ArrayList<Table>();

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public void addTable(Table table) {
        table.project = this;
        tables.add(table);
    }

}

package net.bodz.violet.fab;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@Table(name = "fabstdtester")
@IdType(Integer.class)
public class FabStdTester
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    FabStdTesterType type;
    String commandLine;

    public FabStdTester() {
    }

    public FabStdTesterType getType() {
        return type;
    }

    public void setType(FabStdTesterType type) {
        this.type = type;
    }

    public String getCommandLine() {
        return commandLine;
    }

    public void setCommandLine(String commandLine) {
        this.commandLine = commandLine;
    }

}

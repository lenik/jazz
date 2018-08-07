package net.bodz.violet.manu;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@Table(name = "manustdtester")
@IdType(Integer.class)
public class ManuStdTester
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    ManuStdTesterType type;
    String commandLine;

    public ManuStdTester() {
    }

    public ManuStdTesterType getType() {
        return type;
    }

    public void setType(ManuStdTesterType type) {
        this.type = type;
    }

    public String getCommandLine() {
        return commandLine;
    }

    public void setCommandLine(String commandLine) {
        this.commandLine = commandLine;
    }

}

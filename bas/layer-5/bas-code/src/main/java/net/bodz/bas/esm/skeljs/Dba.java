package net.bodz.bas.esm.skeljs;

import net.bodz.bas.esm.EsmModule;
import net.bodz.bas.esm.EsmName;
import net.bodz.bas.esm.SourcePath;

public class Dba
        extends EsmModule {

    public Dba(int priority) {
        super("@skeljs/dba", priority);
    }

    @SourcePath("src/lily/conrete")
    public class LilyConcrete
            extends Source {

        public final EsmName CoObjectType = name("CoObjectType");
        public final EsmName CoObject = name("CoObject");
        public final EsmName CoMessageType = name("CoMessageType");
        public final EsmName CoMessage = name("CoMessage");
        public final EsmName IdEntityType = name("IdEntityType");
        public final EsmName IdEntity = name("IdEntity");

    }

    @SourcePath("src/lily/entity")
    public class LilyEntity
            extends Source {

        public final EsmName integer = typeName("integer");
        public final EsmName _long = typeName("long");

        public final EsmName IEntityType = name("IEntityType");
        public final EsmName EntityType = name("EntityType");
        public final EsmName EntityPropertyMap = name("EntityPropertyMap");
        public final EsmName IEntityProperty = name("IEntityProperty");
        public final EsmName EntityProperty = name("EntityProperty");

        public final EsmName primaryKey = name("primaryKey");
        public final EsmName property = name("property");

    }

    @SourcePath("src/ui/table/types")
    public class UiTableTypes
            extends Source {

        public final EsmName SetupDataFunc = typeName("SetupDataFunc");
        public final EsmName OnAppliedFunc = typeName("OnAppliedFunc");
        public final EsmName ConfigFn = typeName("ConfigFn");
        public final EsmName TextMap = name("TextMap");
        public final EsmName ColumnType = name("ColumnType");
        public final EsmName Selection = name("Selection");

    }

    @SourcePath("src/ui/lily/defaults")
    public class UiLilyDefaults
            extends Source {

        public final EsmName getDefaultCommands = name("getDefaultCommands");
        public final EsmName getDefaultCommand = name("getDefaultCommand");
        public final EsmName getDefaultStatuses = name("getDefaultStatuses");
        public final EsmName getDefaultStatus = name("getDefaultStatus");
        public final EsmName getDefaultFieldRowProps = name("getDefaultFieldRowProps");

    }

    public final LilyConcrete concrete = new LilyConcrete();
    public final LilyEntity entity = new LilyEntity();
    public final UiTableTypes tableTypes = new UiTableTypes();
    public final UiLilyDefaults defaults = new UiLilyDefaults();

}

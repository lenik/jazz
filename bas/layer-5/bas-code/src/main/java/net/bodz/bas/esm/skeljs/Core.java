package net.bodz.bas.esm.skeljs;

import net.bodz.bas.esm.EsmModule;
import net.bodz.bas.esm.EsmName;
import net.bodz.bas.esm.SourcePath;

public class Core
        extends EsmModule {

    public Core(int priority) {
        super("@skeljs/core", priority);
    }

    @SourcePath("src/logging/api")
    public class LoggingApi
            extends Source {

        public final EsmName LogLevel = typeName("LogLevel");
        public final EsmName Exception = name("Exception");
        public final EsmName StackTrackElement = name("StackTrackElement");
        public final EsmName LogEntry = name("LogEntry");
        public final EsmName levelIcon = name("levelIcon");
        public final EsmName simpleName = name("simpleName");
        public final EsmName typeLabel = name("typeLabel");
        public final EsmName causes = name("causes");
        public final EsmName parseException = name("parseException");
        public final EsmName Logger = name("Logger");

    }

    @SourcePath("src/ui/types")
    public class UiTypes
            extends Source {

        public final EsmName bool = name("bool");
        public final EsmName UiGroupItem = name("UiGroupItem");
        public final EsmName UiComponent = name("UiComponent");
        public final EsmName NameSet = typeName("NameSet");
        public final EsmName Selector = name("Selector");
        public final EsmName select = name("select");
        public final EsmName UiGroup = name("UiGroup");
        public final EsmName UiGroupMap = name("UiGroupMap");
        public final EsmName group = name("group");
        public final EsmName DialogAction = typeName("DialogAction");
        public final EsmName EventHandler = typeName("EventHandler");
        public final EsmName Href = typeName("Href");
        public final EsmName Command = name("Command");
        public final EsmName DialogSelectCallback = name("DialogSelectCallback");
        public final EsmName AsyncDialogSelectCallback = name("AsyncDialogSelectCallback");
        public final EsmName CommandBehavior = name("CommandBehavior");
        public final EsmName getDialogCmds = name("getDialogCmds");
        public final EsmName IValidateResult = name("IValidateResult");
        public final EsmName ValidateResult = name("ValidateResult");
        public final EsmName Validator = typeName("Validator");
        public final EsmName MessageFunc = typeName("MessageFunc");
        public final EsmName Status = name("Status");

    }

    @SourcePath("src/lang/VarMap")
    public class VarMap
            extends Source {

        public final EsmName INameMap = name("INameMap");
        public final EsmName INamedStrings = name("INamedStrings");
        public final EsmName VarMap = name("VarMap");

    }

    public final LoggingApi loggingApi = new LoggingApi();
    public final UiTypes uiTypes = new UiTypes();
    public final VarMap VarMap = new VarMap();

}

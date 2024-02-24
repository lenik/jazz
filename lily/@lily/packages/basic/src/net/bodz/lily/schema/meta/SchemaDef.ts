import CategoryDef from "./CategoryDef";
import ParameterDef from "./ParameterDef";
import PhaseDef from "./PhaseDef";
import PriorityDef from "./PriorityDef";
import SchemaDefTypeInfo from "./SchemaDefTypeInfo";
import TagGroupDef from "./TagGroupDef";
import _SchemaDef_stuff from "./_SchemaDef_stuff";

export class SchemaDef extends _SchemaDef_stuff {
    static TYPE = new SchemaDefTypeInfo();

    categories?: CategoryDef[]
    parameters?: ParameterDef[]
    phases?: PhaseDef[]
    priorities?: PriorityDef[]
    tagGroups?: TagGroupDef[]

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default SchemaDef;

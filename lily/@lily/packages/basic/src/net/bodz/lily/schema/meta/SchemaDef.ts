import type { List } from "skel01-core/src/lang/basetype";

import CategoryDef from "./CategoryDef";
import ParameterDef from "./ParameterDef";
import PhaseDef from "./PhaseDef";
import PriorityDef from "./PriorityDef";
import SchemaDefTypeInfo from "./SchemaDefTypeInfo";
import TagGroupDef from "./TagGroupDef";
import _SchemaDef_stuff from "./_SchemaDef_stuff";

export class SchemaDef extends _SchemaDef_stuff<SchemaDef> {

    static _typeInfo: SchemaDefTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = SchemaDefTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    categories?: List<CategoryDef>
    parameters?: List<ParameterDef>
    phases?: List<PhaseDef>
    priorities?: List<PriorityDef>
    tagGroups?: List<TagGroupDef>

    constructor(o?: any) {
        super(o);
    }
}

export default SchemaDef;

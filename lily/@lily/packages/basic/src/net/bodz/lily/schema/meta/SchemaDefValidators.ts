import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type List from "../../../../../java/util/List";
import type CategoryDef from "./CategoryDef";
import type ParameterDef from "./ParameterDef";
import type PhaseDef from "./PhaseDef";
import type PriorityDef from "./PriorityDef";
import type SchemaDefTypeInfo from "./SchemaDefTypeInfo";
import type TagGroupDef from "./TagGroupDef";
import _SchemaDef_stuff_Validators from "./_SchemaDef_stuff_Validators";

export class SchemaDefValidators extends _SchemaDef_stuff_Validators {

    constructor(type: SchemaDefTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as SchemaDefTypeInfo;
    }

    validateCategories(val: List<CategoryDef>) {
    }

    validateParameters(val: List<ParameterDef>) {
    }

    validatePhases(val: List<PhaseDef>) {
    }

    validatePriorities(val: List<PriorityDef>) {
    }

    validateTagGroups(val: List<TagGroupDef>) {
    }

}

export default SchemaDefValidators;

import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

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

    validateCategories(val: CategoryDef[]) {
    }

    validateParameters(val: ParameterDef[]) {
    }

    validatePhases(val: PhaseDef[]) {
    }

    validatePriorities(val: PriorityDef[]) {
    }

    validateTagGroups(val: TagGroupDef[]) {
    }

}

export default SchemaDefValidators;

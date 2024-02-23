import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import { CategoryDef } from "./CategoryDef";
import { ParameterDef } from "./ParameterDef";
import { PhaseDef } from "./PhaseDef";
import { PriorityDef } from "./PriorityDef";
import { TagGroupDef } from "./TagGroupDef";
import _SchemaDef_stuffValidators from "./_SchemaDef_stuffValidators";

export class SchemaDefValidators extends _SchemaDef_stuffValidators {
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

import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import { TagDef } from "./TagDef";
import _TagGroupDef_stuffValidators from "./_TagGroupDef_stuffValidators";

export class TagGroupDefValidators extends _TagGroupDef_stuffValidators {
    validateOrtho(val: boolean) {
    }
    validateTags(val: TagDef[]) {
    }

}

export default TagGroupDefValidators;

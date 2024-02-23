import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import { TagDef } from "./TagDef";
import _TagGroupDef_stuff_Validators from "./_TagGroupDef_stuff_Validators";

export class TagGroupDefValidators extends _TagGroupDef_stuff_Validators {
    validateOrtho(val: boolean) {
    }
    validateTags(val: TagDef[]) {
    }

}

export default TagGroupDefValidators;

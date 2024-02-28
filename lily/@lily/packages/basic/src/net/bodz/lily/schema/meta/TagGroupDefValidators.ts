import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type List from "../../../../../java/util/List";
import type TagDef from "./TagDef";
import type TagGroupDefTypeInfo from "./TagGroupDefTypeInfo";
import _TagGroupDef_stuff_Validators from "./_TagGroupDef_stuff_Validators";

export class TagGroupDefValidators extends _TagGroupDef_stuff_Validators {

    constructor(type: TagGroupDefTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as TagGroupDefTypeInfo;
    }

    validateOrtho(val: boolean) {
    }

    validateTags(val: List<TagDef>) {
    }

}

export default TagGroupDefValidators;

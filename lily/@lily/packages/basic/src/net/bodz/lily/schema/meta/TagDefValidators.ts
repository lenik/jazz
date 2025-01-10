import { ValidateResult } from "skel01-core/src/ui/types";

import type TagDefTypeInfo from "./TagDefTypeInfo";
import _TagDef_stuff_Validators from "./_TagDef_stuff_Validators";

export class TagDefValidators extends _TagDef_stuff_Validators {

    constructor(type: TagDefTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as TagDefTypeInfo;
    }

}

export default TagDefValidators;

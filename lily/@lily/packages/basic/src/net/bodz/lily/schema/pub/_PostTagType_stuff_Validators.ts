import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoTagValidators from "../../concrete/CoTagValidators";
import type _PostTagType_stuff_TypeInfo from "./_PostTagType_stuff_TypeInfo";

export class _PostTagType_stuff_Validators extends CoTagValidators {

    constructor(type: _PostTagType_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PostTagType_stuff_TypeInfo;
    }

}

export default _PostTagType_stuff_Validators;

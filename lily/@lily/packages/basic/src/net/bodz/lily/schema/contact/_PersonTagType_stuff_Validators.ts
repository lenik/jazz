import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoTagValidators from "../../concrete/CoTagValidators";
import type _PersonTagType_stuff_TypeInfo from "./_PersonTagType_stuff_TypeInfo";

export class _PersonTagType_stuff_Validators extends CoTagValidators {

    constructor(type: _PersonTagType_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PersonTagType_stuff_TypeInfo;
    }

}

export default _PersonTagType_stuff_Validators;

import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import CoImagedValidators from "../../concrete/CoImagedValidators";
import type _Storage_stuff_TypeInfo from "./_Storage_stuff_TypeInfo";

export class _Storage_stuff_Validators extends CoImagedValidators {

    constructor(type: _Storage_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _Storage_stuff_TypeInfo;
    }

    validateName(val: string) {
    }

}

export default _Storage_stuff_Validators;

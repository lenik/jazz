import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type PolicyTypeInfo from "./PolicyTypeInfo";
import _Policy_stuff_Validators from "./_Policy_stuff_Validators";

export class PolicyValidators extends _Policy_stuff_Validators {

    constructor(type: PolicyTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PolicyTypeInfo;
    }

}

export default PolicyValidators;

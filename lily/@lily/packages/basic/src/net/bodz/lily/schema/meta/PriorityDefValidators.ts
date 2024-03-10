import { ValidateResult } from "@skeljs/core/src/ui/types";

import type PriorityDefTypeInfo from "./PriorityDefTypeInfo";
import _PriorityDef_stuff_Validators from "./_PriorityDef_stuff_Validators";

export class PriorityDefValidators extends _PriorityDef_stuff_Validators {

    constructor(type: PriorityDefTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PriorityDefTypeInfo;
    }

}

export default PriorityDefValidators;

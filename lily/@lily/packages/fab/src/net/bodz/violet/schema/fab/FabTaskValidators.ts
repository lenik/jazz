import type { long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type FabTaskTypeInfo from "./FabTaskTypeInfo";
import _FabTask_stuff_Validators from "./_FabTask_stuff_Validators";

export class FabTaskValidators extends _FabTask_stuff_Validators {

    constructor(type: FabTaskTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as FabTaskTypeInfo;
    }

}

export default FabTaskValidators;
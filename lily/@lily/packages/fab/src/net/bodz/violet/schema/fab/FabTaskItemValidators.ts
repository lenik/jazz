import type { long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type FabTaskItemTypeInfo from "./FabTaskItemTypeInfo";
import _FabTaskItem_stuff_Validators from "./_FabTaskItem_stuff_Validators";

export class FabTaskItemValidators extends _FabTaskItem_stuff_Validators {

    constructor(type: FabTaskItemTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as FabTaskItemTypeInfo;
    }

}

export default FabTaskItemValidators;

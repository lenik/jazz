import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type FabFnTypeInfo from "./FabFnTypeInfo";
import _FabFn_stuff_Validators from "./_FabFn_stuff_Validators";

export class FabFnValidators extends _FabFn_stuff_Validators {

    constructor(type: FabFnTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as FabFnTypeInfo;
    }

}

export default FabFnValidators;
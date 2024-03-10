import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type FabStdProcessTypeInfo from "./FabStdProcessTypeInfo";
import _FabStdProcess_stuff_Validators from "./_FabStdProcess_stuff_Validators";

export class FabStdProcessValidators extends _FabStdProcess_stuff_Validators {

    constructor(type: FabStdProcessTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as FabStdProcessTypeInfo;
    }

}

export default FabStdProcessValidators;

import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type FabStdProcessInputTypeInfo from "./FabStdProcessInputTypeInfo";
import _FabStdProcessInput_stuff_Validators from "./_FabStdProcessInput_stuff_Validators";

export class FabStdProcessInputValidators extends _FabStdProcessInput_stuff_Validators {

    constructor(type: FabStdProcessInputTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as FabStdProcessInputTypeInfo;
    }

}

export default FabStdProcessInputValidators;

import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type FabStdTestParameterTypeInfo from "./FabStdTestParameterTypeInfo";
import _FabStdTestParameter_stuff_Validators from "./_FabStdTestParameter_stuff_Validators";

export class FabStdTestParameterValidators extends _FabStdTestParameter_stuff_Validators {

    constructor(type: FabStdTestParameterTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as FabStdTestParameterTypeInfo;
    }

}

export default FabStdTestParameterValidators;

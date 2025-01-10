import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type FabStdTestTypeInfo from "./FabStdTestTypeInfo";
import _FabStdTest_stuff_Validators from "./_FabStdTest_stuff_Validators";

export class FabStdTestValidators extends _FabStdTest_stuff_Validators {

    constructor(type: FabStdTestTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as FabStdTestTypeInfo;
    }

}

export default FabStdTestValidators;

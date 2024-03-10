import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type FabStdTesterTypeInfo from "./FabStdTesterTypeInfo";
import _FabStdTester_stuff_Validators from "./_FabStdTester_stuff_Validators";

export class FabStdTesterValidators extends _FabStdTester_stuff_Validators {

    constructor(type: FabStdTesterTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as FabStdTesterTypeInfo;
    }

}

export default FabStdTesterValidators;

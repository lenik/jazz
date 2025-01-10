import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

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

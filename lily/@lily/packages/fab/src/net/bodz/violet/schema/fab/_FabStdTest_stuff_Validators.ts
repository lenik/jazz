import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoImagedValidators from "lily-basic/src/net/bodz/lily/concrete/CoImagedValidators";

import type FabStdTest from "./FabStdTest";
import type FabStdTestCategory from "./FabStdTestCategory";
import type _FabStdTest_stuff_TypeInfo from "./_FabStdTest_stuff_TypeInfo";

export class _FabStdTest_stuff_Validators extends CoImagedValidators {

    constructor(type: _FabStdTest_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _FabStdTest_stuff_TypeInfo;
    }

    validateCode(val: string) {
    }

    validateDepth(val: int) {
    }

    validateRefCount(val: int) {
    }

    validateCategory(val: FabStdTestCategory) {
    }

    validateParent(val: FabStdTest) {
    }

}

export default _FabStdTest_stuff_Validators;

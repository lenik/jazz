import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@lily/basic/src/net/bodz/lily/concrete/CoEntityValidators";

import type _FabStdTester_stuff_TypeInfo from "./_FabStdTester_stuff_TypeInfo";

export class _FabStdTester_stuff_Validators extends CoEntityValidators {

    constructor(type: _FabStdTester_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _FabStdTester_stuff_TypeInfo;
    }

    validateId(val: int) {
    }

    validateCmdline(val: string) {
    }

}

export default _FabStdTester_stuff_Validators;

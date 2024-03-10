import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@lily/basic/src/net/bodz/lily/concrete/CoEntityValidators";

import type FabStdTest from "./FabStdTest";
import type _FabStdTestParameter_stuff_TypeInfo from "./_FabStdTestParameter_stuff_TypeInfo";

export class _FabStdTestParameter_stuff_Validators extends CoEntityValidators {

    constructor(type: _FabStdTestParameter_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _FabStdTestParameter_stuff_TypeInfo;
    }

    validateId(val: int) {
    }

    validateRequired(val: boolean) {
    }

    validateProperties(val: JsonVariant) {
    }

    validateExpected(val: string) {
    }

    validateTest(val: FabStdTest) {
    }

}

export default _FabStdTestParameter_stuff_Validators;

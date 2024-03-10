import type { long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@lily/basic/src/net/bodz/lily/concrete/CoEntityValidators";

import type FabStdTestParameter from "./FabStdTestParameter";
import type FabStdTester from "./FabStdTester";
import type FabTrackTest from "./FabTrackTest";
import type _FabTrackTestParameter_stuff_TypeInfo from "./_FabTrackTestParameter_stuff_TypeInfo";

export class _FabTrackTestParameter_stuff_Validators extends CoEntityValidators {

    constructor(type: _FabTrackTestParameter_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _FabTrackTestParameter_stuff_TypeInfo;
    }

    validateId(val: long) {
    }

    validateActual(val: string) {
    }

    validateValid(val: boolean) {
    }

    validateParameter(val: FabStdTestParameter) {
    }

    validateTester(val: FabStdTester) {
    }

    validateTest(val: FabTrackTest) {
    }

}

export default _FabTrackTestParameter_stuff_Validators;

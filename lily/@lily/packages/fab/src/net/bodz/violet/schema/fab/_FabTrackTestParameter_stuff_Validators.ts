import type { long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import IdEntityValidators from "@lily/basic/src/net/bodz/lily/concrete/IdEntityValidators";

import type FabStdTestParameter from "./FabStdTestParameter";
import type FabStdTester from "./FabStdTester";
import type FabTrackTest from "./FabTrackTest";
import type _FabTrackTestParameter_stuff_TypeInfo from "./_FabTrackTestParameter_stuff_TypeInfo";

export class _FabTrackTestParameter_stuff_Validators extends IdEntityValidators {

    constructor(type: _FabTrackTestParameter_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _FabTrackTestParameter_stuff_TypeInfo;
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

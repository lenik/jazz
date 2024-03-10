import type { long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@lily/basic/src/net/bodz/lily/concrete/CoEntityValidators";

import type FabStdTest from "./FabStdTest";
import type FabTrack from "./FabTrack";
import type _FabTrackTest_stuff_TypeInfo from "./_FabTrackTest_stuff_TypeInfo";

export class _FabTrackTest_stuff_Validators extends CoEntityValidators {

    constructor(type: _FabTrackTest_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _FabTrackTest_stuff_TypeInfo;
    }

    validateId(val: long) {
    }

    validateValid(val: boolean) {
    }

    validateTrack(val: FabTrack) {
    }

    validateStandard(val: FabStdTest) {
    }

}

export default _FabTrackTest_stuff_Validators;

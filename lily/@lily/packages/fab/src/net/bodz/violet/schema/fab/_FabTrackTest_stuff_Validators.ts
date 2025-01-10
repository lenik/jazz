import type { long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import IdEntityValidators from "@lily/basic/src/net/bodz/lily/concrete/IdEntityValidators";

import type FabStdTest from "./FabStdTest";
import type FabTrack from "./FabTrack";
import type _FabTrackTest_stuff_TypeInfo from "./_FabTrackTest_stuff_TypeInfo";

export class _FabTrackTest_stuff_Validators extends IdEntityValidators {

    constructor(type: _FabTrackTest_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _FabTrackTest_stuff_TypeInfo;
    }

    validateValid(val: boolean) {
    }

    validateTrack(val: FabTrack) {
    }

    validateStandard(val: FabStdTest) {
    }

}

export default _FabTrackTest_stuff_Validators;

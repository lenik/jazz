import type { long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type FabTrackTestParameterTypeInfo from "./FabTrackTestParameterTypeInfo";
import _FabTrackTestParameter_stuff_Validators from "./_FabTrackTestParameter_stuff_Validators";

export class FabTrackTestParameterValidators extends _FabTrackTestParameter_stuff_Validators {

    constructor(type: FabTrackTestParameterTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as FabTrackTestParameterTypeInfo;
    }

}

export default FabTrackTestParameterValidators;

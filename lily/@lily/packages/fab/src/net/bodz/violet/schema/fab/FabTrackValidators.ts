import type { long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type FabTrackTypeInfo from "./FabTrackTypeInfo";
import _FabTrack_stuff_Validators from "./_FabTrack_stuff_Validators";

export class FabTrackValidators extends _FabTrack_stuff_Validators {

    constructor(type: FabTrackTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as FabTrackTypeInfo;
    }

}

export default FabTrackValidators;
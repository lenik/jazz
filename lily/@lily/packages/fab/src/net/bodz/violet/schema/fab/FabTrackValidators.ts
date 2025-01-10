import type { long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

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

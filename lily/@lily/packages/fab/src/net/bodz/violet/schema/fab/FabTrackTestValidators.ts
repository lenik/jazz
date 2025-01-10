import type { long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type FabTrackTestTypeInfo from "./FabTrackTestTypeInfo";
import _FabTrackTest_stuff_Validators from "./_FabTrackTest_stuff_Validators";

export class FabTrackTestValidators extends _FabTrackTest_stuff_Validators {

    constructor(type: FabTrackTestTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as FabTrackTestTypeInfo;
    }

}

export default FabTrackTestValidators;

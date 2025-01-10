import type { long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type FabTrackPartyTypeInfo from "./FabTrackPartyTypeInfo";
import _FabTrackParty_stuff_Validators from "./_FabTrackParty_stuff_Validators";

export class FabTrackPartyValidators extends _FabTrackParty_stuff_Validators {

    constructor(type: FabTrackPartyTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as FabTrackPartyTypeInfo;
    }

}

export default FabTrackPartyValidators;

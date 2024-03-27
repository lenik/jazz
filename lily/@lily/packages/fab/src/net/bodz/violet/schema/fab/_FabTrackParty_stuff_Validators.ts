import type { long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import IdEntityValidators from "@lily/basic/src/net/bodz/lily/concrete/IdEntityValidators";
import type Person from "@lily/basic/src/net/bodz/lily/schema/contact/Person";

import type FabTrack from "./FabTrack";
import type _FabTrackParty_stuff_TypeInfo from "./_FabTrackParty_stuff_TypeInfo";

export class _FabTrackParty_stuff_Validators extends IdEntityValidators {

    constructor(type: _FabTrackParty_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _FabTrackParty_stuff_TypeInfo;
    }

    validateRole(val: string) {
    }

    validatePerson(val: Person) {
    }

    validateTrack(val: FabTrack) {
    }

}

export default _FabTrackParty_stuff_Validators;

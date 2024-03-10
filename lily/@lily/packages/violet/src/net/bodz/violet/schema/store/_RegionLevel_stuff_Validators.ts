import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoCodeValidators from "@lily/basic/src/net/bodz/lily/concrete/CoCodeValidators";

import type _RegionLevel_stuff_TypeInfo from "./_RegionLevel_stuff_TypeInfo";

export class _RegionLevel_stuff_Validators extends CoCodeValidators {

    constructor(type: _RegionLevel_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _RegionLevel_stuff_TypeInfo;
    }

    validateDummy(val: int) {
    }

}

export default _RegionLevel_stuff_Validators;

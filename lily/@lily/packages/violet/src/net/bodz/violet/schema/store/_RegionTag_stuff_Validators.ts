import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoTagValidators from "@lily/basic/src/net/bodz/lily/concrete/CoTagValidators";

import type _RegionTag_stuff_TypeInfo from "./_RegionTag_stuff_TypeInfo";

export class _RegionTag_stuff_Validators extends CoTagValidators {

    constructor(type: _RegionTag_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _RegionTag_stuff_TypeInfo;
    }

}

export default _RegionTag_stuff_Validators;

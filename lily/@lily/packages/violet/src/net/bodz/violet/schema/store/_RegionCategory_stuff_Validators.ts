import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoCategoryValidators from "lily-basic/src/net/bodz/lily/concrete/CoCategoryValidators";

import type _RegionCategory_stuff_TypeInfo from "./_RegionCategory_stuff_TypeInfo";

export class _RegionCategory_stuff_Validators extends CoCategoryValidators {

    constructor(type: _RegionCategory_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _RegionCategory_stuff_TypeInfo;
    }

    validateName(val: string) {
    }

}

export default _RegionCategory_stuff_Validators;

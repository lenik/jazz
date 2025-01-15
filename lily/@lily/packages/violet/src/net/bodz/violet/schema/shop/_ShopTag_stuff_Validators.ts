import { ValidateResult } from "skel01-core/src/ui/types";
import CoTagValidators from "lily-basic/src/net/bodz/lily/concrete/CoTagValidators";

import type _ShopTag_stuff_TypeInfo from "./_ShopTag_stuff_TypeInfo";

export class _ShopTag_stuff_Validators extends CoTagValidators {

    constructor(type: _ShopTag_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ShopTag_stuff_TypeInfo;
    }

}

export default _ShopTag_stuff_Validators;

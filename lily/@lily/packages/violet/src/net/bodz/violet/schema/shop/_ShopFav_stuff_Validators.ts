import { ValidateResult } from "@skeljs/core/src/ui/types";
import FavRecordValidators from "@lily/basic/src/net/bodz/lily/concrete/FavRecordValidators";

import type Shop from "./Shop";
import type _ShopFav_stuff_TypeInfo from "./_ShopFav_stuff_TypeInfo";

export class _ShopFav_stuff_Validators extends FavRecordValidators {

    constructor(type: _ShopFav_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ShopFav_stuff_TypeInfo;
    }

    validateShop(val: Shop) {
    }

}

export default _ShopFav_stuff_Validators;

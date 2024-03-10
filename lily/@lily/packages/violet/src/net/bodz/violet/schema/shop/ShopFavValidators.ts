import { ValidateResult } from "@skeljs/core/src/ui/types";

import type ShopFavTypeInfo from "./ShopFavTypeInfo";
import _ShopFav_stuff_Validators from "./_ShopFav_stuff_Validators";

export class ShopFavValidators extends _ShopFav_stuff_Validators {

    constructor(type: ShopFavTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ShopFavTypeInfo;
    }

}

export default ShopFavValidators;

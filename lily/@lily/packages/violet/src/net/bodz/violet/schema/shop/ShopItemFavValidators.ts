import { ValidateResult } from "skel01-core/src/ui/types";

import type ShopItemFavTypeInfo from "./ShopItemFavTypeInfo";
import _ShopItemFav_stuff_Validators from "./_ShopItemFav_stuff_Validators";

export class ShopItemFavValidators extends _ShopItemFav_stuff_Validators {

    constructor(type: ShopItemFavTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ShopItemFavTypeInfo;
    }

}

export default ShopItemFavValidators;

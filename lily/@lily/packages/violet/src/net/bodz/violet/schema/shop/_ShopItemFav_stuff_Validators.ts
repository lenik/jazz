import { ValidateResult } from "skel01-core/src/ui/types";
import FavRecordValidators from "lily-basic/src/net/bodz/lily/concrete/FavRecordValidators";

import type ShopItem from "./ShopItem";
import type _ShopItemFav_stuff_TypeInfo from "./_ShopItemFav_stuff_TypeInfo";

export class _ShopItemFav_stuff_Validators extends FavRecordValidators {

    constructor(type: _ShopItemFav_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ShopItemFav_stuff_TypeInfo;
    }

    validateShopItem(val: ShopItem) {
    }

}

export default _ShopItemFav_stuff_Validators;

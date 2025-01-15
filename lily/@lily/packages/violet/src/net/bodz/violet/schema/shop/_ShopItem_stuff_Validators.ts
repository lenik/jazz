import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { BigDecimal, long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoImagedEventValidators from "lily-basic/src/net/bodz/lily/concrete/CoImagedEventValidators";

import type Artifact from "../art/Artifact";
import type Shop from "./Shop";
import type ShopItemCategory from "./ShopItemCategory";
import type _ShopItem_stuff_TypeInfo from "./_ShopItem_stuff_TypeInfo";

export class _ShopItem_stuff_Validators extends CoImagedEventValidators {

    constructor(type: _ShopItem_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ShopItem_stuff_TypeInfo;
    }

    validateBatch(val: JsonVariant) {
    }

    validatePrice(val: BigDecimal) {
    }

    validateQuantity(val: BigDecimal) {
    }

    validateCategory(val: ShopItemCategory) {
    }

    validateShop(val: Shop) {
    }

    validateArtifact(val: Artifact) {
    }

}

export default _ShopItem_stuff_Validators;

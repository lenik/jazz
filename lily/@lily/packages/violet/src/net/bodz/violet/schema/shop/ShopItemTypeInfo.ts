import { BIG_DECIMAL } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import ShopItem from "./ShopItem";
import ShopItemValidators from "./ShopItemValidators";
import _ShopItem_stuff_TypeInfo from "./_ShopItem_stuff_TypeInfo";

export class ShopItemTypeInfo extends _ShopItem_stuff_TypeInfo {

    readonly validators = new ShopItemValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.shop.ShopItem"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new ShopItem();
    }

    override preamble() {
        super.preamble();
        this.declare({
            total: property({ type: BIG_DECIMAL, validator: this.validators.validateTotal }),
        });
    }

    static readonly INSTANCE = new ShopItemTypeInfo();

}

export default ShopItemTypeInfo;

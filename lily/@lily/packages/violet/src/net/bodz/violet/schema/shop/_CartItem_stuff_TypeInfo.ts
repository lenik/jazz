import { BIG_DECIMAL, LONG } from "@skeljs/core/src/lang/baseinfo";
import type { long } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoEntityTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoEntityTypeInfo";

import ShopItem from "./ShopItem";
import _CartItem_stuff_Validators from "./_CartItem_stuff_Validators";

export class _CartItem_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "cartitem";

    static readonly FIELD_ID = "id";
    static readonly FIELD_SHOP_ITEM_ID = "shopitem";
    static readonly FIELD_PRICE = "price";
    static readonly FIELD_QUANTITY = "qty";

    static readonly N_ID = 19;
    static readonly N_SHOP_ITEM_ID = 19;
    static readonly N_PRICE = 20;
    static readonly N_QUANTITY = 20;

    readonly validators = new _CartItem_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.shop.CartItem"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: LONG, nullable: false, precision: 19, validator: this.validators.validateId }),
            price: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validatePrice }),
            quantity: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateQuantity }),

            shopItem: property({ type: ShopItem.TYPE, nullable: false, validator: this.validators.validateShopItem }),
            shopItemId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _CartItem_stuff_TypeInfo();

}

export default _CartItem_stuff_TypeInfo;

import { LONG } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import FavRecordTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/FavRecordTypeInfo";

import ShopItem from "./ShopItem";
import _ShopItemFav_stuff_Validators from "./_ShopItemFav_stuff_Validators";

export class _ShopItemFav_stuff_TypeInfo extends FavRecordTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "shopitem_fav";

    static readonly FIELD_SHOP_ITEM_ID = "shopitem";

    static readonly N_SHOP_ITEM_ID = 19;

    readonly validators = new _ShopItemFav_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.shop.ShopItemFav"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({

            shopItem: property({ type: ShopItem.TYPE, nullable: false, validator: this.validators.validateShopItem }),
            shopItemId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _ShopItemFav_stuff_TypeInfo();

}

export default _ShopItemFav_stuff_TypeInfo;
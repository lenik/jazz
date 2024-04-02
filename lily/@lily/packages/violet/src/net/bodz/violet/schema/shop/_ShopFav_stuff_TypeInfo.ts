import { INT } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import FavRecordTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/FavRecordTypeInfo";

import { Shop_TYPE } from "./ShopTypeInfo";
import _ShopFav_stuff_Validators from "./_ShopFav_stuff_Validators";

export class _ShopFav_stuff_TypeInfo extends FavRecordTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "shop_fav";

    static readonly FIELD_SHOP_ID = "shop";

    static readonly N_SHOP_ID = 10;

    readonly validators = new _ShopFav_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.shop.ShopFav"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({

            shop: property({ type: Shop_TYPE, nullable: false, validator: this.validators.validateShop }),
            shopId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _ShopFav_stuff_TypeInfo();

}

export default _ShopFav_stuff_TypeInfo;

export const _ShopFav_stuff_TYPE = _ShopFav_stuff_TypeInfo.INSTANCE;

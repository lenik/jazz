import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { BIG_DECIMAL, INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import type { long } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoImagedEventTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoImagedEventTypeInfo";

import { Artifact_TYPE } from "../art/ArtifactTypeInfo";
import { SalesOrder_TYPE } from "./SalesOrderTypeInfo";
import { ShopItem_TYPE } from "./ShopItemTypeInfo";
import _SalesOrderItem_stuff_Validators from "./_SalesOrderItem_stuff_Validators";

export class _SalesOrderItem_stuff_TypeInfo extends CoImagedEventTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "saleodrl";

    static readonly FIELD_ORDER_ID = "odr";
    static readonly FIELD_SHOP_ITEM_ID = "shopitem";
    static readonly FIELD_ARTIFACT_ID = "art";
    static readonly FIELD_BATCH = "batch";
    static readonly FIELD_QUANTITY = "qty";
    static readonly FIELD_PRICE = "price";
    static readonly FIELD_AMOUNT = "amount";
    static readonly FIELD_N1 = "n1";

    static readonly N_ORDER_ID = 19;
    static readonly N_SHOP_ITEM_ID = 19;
    static readonly N_ARTIFACT_ID = 10;
    static readonly N_BATCH = 2147483647;
    static readonly N_QUANTITY = 20;
    static readonly N_PRICE = 20;
    static readonly N_AMOUNT = 20;
    static readonly N_N1 = 20;

    readonly validators = new _SalesOrderItem_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.shop.SalesOrderItem"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            batch: property({ type: JSON_VARIANT, validator: this.validators.validateBatch }),
            quantity: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateQuantity }),
            price: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validatePrice }),
            amount: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateAmount }),
            n1: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateN1 }),

            order: property({ type: SalesOrder_TYPE, nullable: false, validator: this.validators.validateOrder }),
            orderId: property({ type: LONG, nullable: false, precision: 19 }),

            shopItem: property({ type: ShopItem_TYPE, validator: this.validators.validateShopItem }),
            shopItemId: property({ type: LONG, precision: 19 }),

            artifact: property({ type: Artifact_TYPE, nullable: false, validator: this.validators.validateArtifact }),
            artifactId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _SalesOrderItem_stuff_TypeInfo();

}

export default _SalesOrderItem_stuff_TypeInfo;

export const _SalesOrderItem_stuff_TYPE = _SalesOrderItem_stuff_TypeInfo.INSTANCE;

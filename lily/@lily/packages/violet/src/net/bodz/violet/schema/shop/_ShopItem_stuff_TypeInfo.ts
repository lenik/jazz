import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { BIG_DECIMAL, INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import type { long } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoImagedEventTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoImagedEventTypeInfo";

import Artifact from "../art/Artifact";
import Shop from "./Shop";
import ShopItemCategory from "./ShopItemCategory";
import _ShopItem_stuff_Validators from "./_ShopItem_stuff_Validators";

export class _ShopItem_stuff_TypeInfo extends CoImagedEventTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "shopitem";

    static readonly FIELD_SHOP_ID = "shop";
    static readonly FIELD_CATEGORY_ID = "cat";
    static readonly FIELD_ARTIFACT_ID = "art";
    static readonly FIELD_BATCH = "batch";
    static readonly FIELD_PRICE = "price";
    static readonly FIELD_QUANTITY = "qty";

    static readonly N_SHOP_ID = 10;
    static readonly N_CATEGORY_ID = 10;
    static readonly N_ARTIFACT_ID = 10;
    static readonly N_BATCH = 2147483647;
    static readonly N_PRICE = 20;
    static readonly N_QUANTITY = 20;

    readonly validators = new _ShopItem_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.shop.ShopItem"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            batch: property({ type: JSON_VARIANT, validator: this.validators.validateBatch }),
            price: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validatePrice }),
            quantity: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateQuantity }),

            category: property({ type: ShopItemCategory.TYPE, validator: this.validators.validateCategory }),
            categoryId: property({ type: INT, precision: 10 }),

            shop: property({ type: Shop.TYPE, validator: this.validators.validateShop }),
            shopId: property({ type: INT, precision: 10 }),

            artifact: property({ type: Artifact.TYPE, nullable: false, validator: this.validators.validateArtifact }),
            artifactId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _ShopItem_stuff_TypeInfo();

}

export default _ShopItem_stuff_TypeInfo;

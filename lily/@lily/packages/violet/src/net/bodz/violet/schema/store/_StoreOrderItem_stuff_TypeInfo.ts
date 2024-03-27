import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { BIG_DECIMAL, INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { long } from "@skeljs/core/src/lang/basetype";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoImagedEventTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoImagedEventTypeInfo";

import Artifact from "../art/Artifact";
import Region from "./Region";
import StoreOrder from "./StoreOrder";
import _StoreOrderItem_stuff_Validators from "./_StoreOrderItem_stuff_Validators";

export class _StoreOrderItem_stuff_TypeInfo extends CoImagedEventTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "storeodrl";

    static readonly FIELD_ORDER_ID = "odr";
    static readonly FIELD_ARTIFACT_ID = "art";
    static readonly FIELD_REGION_ID = "region";
    static readonly FIELD_BATCH = "batch";
    static readonly FIELD_SERIAL = "serial";
    static readonly FIELD_EXPIRE = "expire";
    static readonly FIELD_QUANTITY = "qty";
    static readonly FIELD_PRICE = "price";
    static readonly FIELD_AMOUNT = "amount";
    static readonly FIELD_NOTES = "notes";

    static readonly N_ORDER_ID = 19;
    static readonly N_ARTIFACT_ID = 10;
    static readonly N_REGION_ID = 10;
    static readonly N_BATCH = 2147483647;
    static readonly N_SERIAL = 19;
    static readonly N_EXPIRE = 35;
    static readonly N_QUANTITY = 20;
    static readonly N_PRICE = 20;
    static readonly N_AMOUNT = 20;
    static readonly N_NOTES = 200;

    readonly validators = new _StoreOrderItem_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.store.StoreOrderItem"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            batch: property({ type: JSON_VARIANT, validator: this.validators.validateBatch }),
            serial: property({ type: LONG, precision: 19, validator: this.validators.validateSerial }),
            expire: property({ type: OffsetDateTime.TYPE, precision: 35, scale: 6, validator: this.validators.validateExpire }),
            quantity: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateQuantity }),
            price: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validatePrice }),
            amount: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateAmount }),
            notes: property({ type: STRING, precision: 200, validator: this.validators.validateNotes }),

            artifact: property({ type: Artifact.TYPE, nullable: false, validator: this.validators.validateArtifact }),
            artifactId: property({ type: INT, nullable: false, precision: 10 }),

            order: property({ type: StoreOrder.TYPE, nullable: false, validator: this.validators.validateOrder }),
            orderId: property({ type: LONG, nullable: false, precision: 19 }),

            region: property({ type: Region.TYPE, nullable: false, validator: this.validators.validateRegion }),
            regionId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _StoreOrderItem_stuff_TypeInfo();

}

export default _StoreOrderItem_stuff_TypeInfo;

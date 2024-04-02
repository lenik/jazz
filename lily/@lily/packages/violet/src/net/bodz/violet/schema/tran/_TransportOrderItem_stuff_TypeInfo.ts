import { BIG_DECIMAL, INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import type { long } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoEventTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoEventTypeInfo";

import { Artifact_TYPE } from "../art/ArtifactTypeInfo";
import { TransportOrder_TYPE } from "./TransportOrderTypeInfo";
import _TransportOrderItem_stuff_Validators from "./_TransportOrderItem_stuff_Validators";

export class _TransportOrderItem_stuff_TypeInfo extends CoEventTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "tranodrl";

    static readonly FIELD_ORDER_ID = "odr";
    static readonly FIELD_ARTIFACT_ID = "art";
    static readonly FIELD_QUANTITY = "qty";
    static readonly FIELD_PRICE = "price";
    static readonly FIELD_AMOUNT = "amount";

    static readonly N_ORDER_ID = 19;
    static readonly N_ARTIFACT_ID = 10;
    static readonly N_QUANTITY = 20;
    static readonly N_PRICE = 20;
    static readonly N_AMOUNT = 20;

    readonly validators = new _TransportOrderItem_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.tran.TransportOrderItem"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            quantity: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateQuantity }),
            price: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validatePrice }),
            amount: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateAmount }),

            artifact: property({ type: Artifact_TYPE, validator: this.validators.validateArtifact }),
            artifactId: property({ type: INT, precision: 10 }),

            order: property({ type: TransportOrder_TYPE, nullable: false, validator: this.validators.validateOrder }),
            orderId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _TransportOrderItem_stuff_TypeInfo();

}

export default _TransportOrderItem_stuff_TypeInfo;

export const _TransportOrderItem_stuff_TYPE = _TransportOrderItem_stuff_TypeInfo.INSTANCE;

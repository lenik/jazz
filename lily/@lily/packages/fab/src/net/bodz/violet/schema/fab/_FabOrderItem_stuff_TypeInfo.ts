import { BIG_DECIMAL, BOOLEAN, INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { long } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoImagedEventTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoImagedEventTypeInfo";
import Artifact from "@lily/violet/src/net/bodz/violet/schema/art/Artifact";

import FabOrder from "./FabOrder";
import _FabOrderItem_stuff_Validators from "./_FabOrderItem_stuff_Validators";

export class _FabOrderItem_stuff_TypeInfo extends CoImagedEventTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "fabodrl";

    static readonly FIELD_ORDER_ID = "odr";
    static readonly FIELD_ARTIFACT_ID = "art";
    static readonly FIELD_RESALE = "resale";
    static readonly FIELD_ALT_LABEL = "o_label";
    static readonly FIELD_ALT_SPEC = "o_spec";
    static readonly FIELD_ALT_UOM = "o_uom";
    static readonly FIELD_QUANTITY = "qty";
    static readonly FIELD_PRICE = "price";
    static readonly FIELD_AMOUNT = "amount";
    static readonly FIELD_NOTES = "notes";

    static readonly N_ORDER_ID = 19;
    static readonly N_ARTIFACT_ID = 10;
    static readonly N_RESALE = 1;
    static readonly N_ALT_LABEL = 30;
    static readonly N_ALT_SPEC = 80;
    static readonly N_ALT_UOM = 30;
    static readonly N_QUANTITY = 20;
    static readonly N_PRICE = 20;
    static readonly N_AMOUNT = 20;
    static readonly N_NOTES = 200;

    readonly validators = new _FabOrderItem_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.fab.FabOrderItem"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            resale: property({ type: BOOLEAN, nullable: false, precision: 1, validator: this.validators.validateResale }),
            altLabel: property({ type: STRING, precision: 30, validator: this.validators.validateAltLabel }),
            altSpec: property({ type: STRING, precision: 80, validator: this.validators.validateAltSpec }),
            altUom: property({ type: STRING, precision: 30, validator: this.validators.validateAltUom }),
            quantity: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateQuantity }),
            price: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validatePrice }),
            amount: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateAmount }),
            notes: property({ type: STRING, precision: 200, validator: this.validators.validateNotes }),

            artifact: property({ type: Artifact.TYPE, nullable: false, validator: this.validators.validateArtifact }),
            artifactId: property({ type: INT, nullable: false, precision: 10 }),

            order: property({ type: FabOrder.TYPE, nullable: false, validator: this.validators.validateOrder }),
            orderId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _FabOrderItem_stuff_TypeInfo();

}

export default _FabOrderItem_stuff_TypeInfo;

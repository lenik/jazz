import { BIG_DECIMAL, INT, SHORT, STRING } from "skel01-core/src/lang/baseinfo";
import type { int } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import CoImagedTypeInfo from "lily-basic/src/net/bodz/lily/concrete/CoImagedTypeInfo";
import { UomRow_TYPE } from "lily-basic/src/net/bodz/lily/schema/util/UomRowTypeInfo";

import { ArtifactCategory_TYPE } from "./ArtifactCategoryTypeInfo";
import { ArtifactPhase_TYPE } from "./ArtifactPhaseTypeInfo";
import { Artifact_TYPE } from "./ArtifactTypeInfo";
import { ArtifactType_TYPE } from "./ArtifactTypeTypeInfo";
import _Artifact_stuff_Validators from "./_Artifact_stuff_Validators";

export class _Artifact_stuff_TypeInfo extends CoImagedTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "art";

    static readonly FIELD_SKU_CODE = "sku";
    static readonly FIELD_BAR_CODE = "barcode";
    static readonly FIELD_RFID_CODE = "rfid";
    static readonly FIELD_MODEL_NAME = "model";
    static readonly FIELD_PROTO_ID = "proto";
    static readonly FIELD_TYPE_ID = "type";
    static readonly FIELD_CATEGORY_ID = "cat";
    static readonly FIELD_PHASE_ID = "phase";
    static readonly FIELD_UOM_ID = "uom";
    static readonly FIELD_FINISH = "finish";
    static readonly FIELD_PRICE = "price";

    static readonly N_SKU_CODE = 30;
    static readonly N_BAR_CODE = 30;
    static readonly N_RFID_CODE = 30;
    static readonly N_MODEL_NAME = 80;
    static readonly N_PROTO_ID = 10;
    static readonly N_TYPE_ID = 10;
    static readonly N_CATEGORY_ID = 10;
    static readonly N_PHASE_ID = 10;
    static readonly N_UOM_ID = 10;
    static readonly N_FINISH = 5;
    static readonly N_PRICE = 12;

    readonly validators = new _Artifact_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.violet.schema.art.Artifact"; }
    get icon() { return "fa-tag"; }
    get description() { return "物品"; }

    override preamble() {
        super.preamble();
        this.declare({
            skuCode: property({ type: STRING, precision: 30, validator: this.validators.validateSkuCode }),
            barCode: property({ type: STRING, precision: 30, validator: this.validators.validateBarCode }),
            rfidCode: property({ type: STRING, precision: 30, validator: this.validators.validateRfidCode }),
            modelName: property({ type: STRING, precision: 80, validator: this.validators.validateModelName }),
            finish: property({ type: SHORT, nullable: false, precision: 5, validator: this.validators.validateFinish }),
            price: property({ type: BIG_DECIMAL, precision: 12, scale: 2, validator: this.validators.validatePrice }),

            type: property({ type: ArtifactType_TYPE, validator: this.validators.validateType }),
            typeId: property({ type: INT, precision: 10 }),

            proto: property({ type: this, validator: this.validators.validateProto }),
            protoId: property({ type: INT, precision: 10 }),

            phase: property({ type: ArtifactPhase_TYPE, validator: this.validators.validatePhase }),
            phaseId: property({ type: INT, precision: 10 }),

            uom: property({ type: UomRow_TYPE, validator: this.validators.validateUom }),
            uomId: property({ type: INT, precision: 10 }),

            category: property({ type: ArtifactCategory_TYPE, validator: this.validators.validateCategory }),
            categoryId: property({ type: INT, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _Artifact_stuff_TypeInfo();

}

export default _Artifact_stuff_TypeInfo;

export const _Artifact_stuff_TYPE = _Artifact_stuff_TypeInfo.INSTANCE;

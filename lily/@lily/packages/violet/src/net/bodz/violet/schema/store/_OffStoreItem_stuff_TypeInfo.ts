import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { BIG_DECIMAL, INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import type { long } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import IdEntityTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import Artifact from "../art/Artifact";
import _OffStoreItem_stuff_Validators from "./_OffStoreItem_stuff_Validators";

export class _OffStoreItem_stuff_TypeInfo extends IdEntityTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "offstorel";

    static readonly FIELD_ARTIFACT_ID = "art";
    static readonly FIELD_BATCH = "batch";
    static readonly FIELD_QUANTITY = "qty";

    static readonly N_ARTIFACT_ID = 10;
    static readonly N_BATCH = 2147483647;
    static readonly N_QUANTITY = 20;

    readonly validators = new _OffStoreItem_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.store.OffStoreItem"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            batch: property({ type: JSON_VARIANT, validator: this.validators.validateBatch }),
            quantity: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateQuantity }),

            artifact: property({ type: Artifact.TYPE, nullable: false, validator: this.validators.validateArtifact }),
            artifactId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _OffStoreItem_stuff_TypeInfo();

}

export default _OffStoreItem_stuff_TypeInfo;

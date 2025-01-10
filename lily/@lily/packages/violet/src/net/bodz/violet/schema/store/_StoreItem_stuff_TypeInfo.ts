import { JSON_VARIANT } from "skel01-core/src/lang/bas-info";
import { BIG_DECIMAL, INT, LONG } from "skel01-core/src/lang/baseinfo";
import type { long } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import IdEntityTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import { Artifact_TYPE } from "../art/ArtifactTypeInfo";
import { Region_TYPE } from "./RegionTypeInfo";
import _StoreItem_stuff_Validators from "./_StoreItem_stuff_Validators";

export class _StoreItem_stuff_TypeInfo extends IdEntityTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "storel";

    static readonly FIELD_ARTIFACT_ID = "art";
    static readonly FIELD_REGION_ID = "region";
    static readonly FIELD_BATCH = "batch";
    static readonly FIELD_QUANTITY = "qty";

    static readonly N_ARTIFACT_ID = 10;
    static readonly N_REGION_ID = 10;
    static readonly N_BATCH = 2147483647;
    static readonly N_QUANTITY = 20;

    readonly validators = new _StoreItem_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.store.StoreItem"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            batch: property({ type: JSON_VARIANT, validator: this.validators.validateBatch }),
            quantity: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateQuantity }),

            region: property({ type: Region_TYPE, nullable: false, validator: this.validators.validateRegion }),
            regionId: property({ type: INT, nullable: false, precision: 10 }),

            artifact: property({ type: Artifact_TYPE, nullable: false, validator: this.validators.validateArtifact }),
            artifactId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _StoreItem_stuff_TypeInfo();

}

export default _StoreItem_stuff_TypeInfo;

export const _StoreItem_stuff_TYPE = _StoreItem_stuff_TypeInfo.INSTANCE;

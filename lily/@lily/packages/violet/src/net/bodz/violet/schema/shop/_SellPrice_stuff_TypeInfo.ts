import { BIG_DECIMAL, INT, STRING } from "skel01-core/src/lang/baseinfo";
import type { int } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import IdEntityTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import { Artifact_TYPE } from "../art/ArtifactTypeInfo";
import _SellPrice_stuff_Validators from "./_SellPrice_stuff_Validators";

export class _SellPrice_stuff_TypeInfo extends IdEntityTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "art_price";

    static readonly FIELD_CODE = "code";
    static readonly FIELD_ARTIFACT_ID = "art";
    static readonly FIELD_PRICE = "price";

    static readonly N_CODE = 20;
    static readonly N_ARTIFACT_ID = 10;
    static readonly N_PRICE = 12;

    readonly validators = new _SellPrice_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.violet.schema.shop.SellPrice"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            code: property({ type: STRING, precision: 20, validator: this.validators.validateCode }),
            price: property({ type: BIG_DECIMAL, nullable: false, precision: 12, scale: 2, validator: this.validators.validatePrice }),

            artifact: property({ type: Artifact_TYPE, nullable: false, validator: this.validators.validateArtifact }),
            artifactId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _SellPrice_stuff_TypeInfo();

}

export default _SellPrice_stuff_TypeInfo;

export const _SellPrice_stuff_TYPE = _SellPrice_stuff_TypeInfo.INSTANCE;

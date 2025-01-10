import { JSON_VARIANT } from "skel01-core/src/lang/bas-info";
import { INT } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import { OrgUnit_TYPE } from "@lily/basic/src/net/bodz/lily/schema/contact/OrgUnitTypeInfo";

import AbstractAssetTypeInfo from "./AbstractAssetTypeInfo";
import _OrgUnitAsset_stuff_Validators from "./_OrgUnitAsset_stuff_Validators";

export class _OrgUnitAsset_stuff_TypeInfo extends AbstractAssetTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "asset_ou";

    static readonly FIELD_OWNER_ID = "owner";
    static readonly FIELD_BATCH = "batch";

    static readonly N_OWNER_ID = 10;
    static readonly N_BATCH = 2147483647;

    readonly validators = new _OrgUnitAsset_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.asset.OrgUnitAsset"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            batch: property({ type: JSON_VARIANT, validator: this.validators.validateBatch }),

            owner: property({ type: OrgUnit_TYPE, nullable: false, validator: this.validators.validateOwner }),
            ownerId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _OrgUnitAsset_stuff_TypeInfo();

}

export default _OrgUnitAsset_stuff_TypeInfo;

export const _OrgUnitAsset_stuff_TYPE = _OrgUnitAsset_stuff_TypeInfo.INSTANCE;

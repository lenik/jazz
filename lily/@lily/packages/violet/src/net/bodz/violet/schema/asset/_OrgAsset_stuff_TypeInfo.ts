import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { INT } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import { Organization_TYPE } from "@lily/basic/src/net/bodz/lily/schema/contact/OrganizationTypeInfo";

import AbstractAssetTypeInfo from "./AbstractAssetTypeInfo";
import _OrgAsset_stuff_Validators from "./_OrgAsset_stuff_Validators";

export class _OrgAsset_stuff_TypeInfo extends AbstractAssetTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "asset_org";

    static readonly FIELD_OWNER_ID = "owner";
    static readonly FIELD_BATCH = "batch";

    static readonly N_OWNER_ID = 10;
    static readonly N_BATCH = 2147483647;

    readonly validators = new _OrgAsset_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.asset.OrgAsset"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            batch: property({ type: JSON_VARIANT, validator: this.validators.validateBatch }),

            owner: property({ type: Organization_TYPE, nullable: false, validator: this.validators.validateOwner }),
            ownerId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _OrgAsset_stuff_TypeInfo();

}

export default _OrgAsset_stuff_TypeInfo;

export const _OrgAsset_stuff_TYPE = _OrgAsset_stuff_TypeInfo.INSTANCE;

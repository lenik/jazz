import { JSON_VARIANT } from "skel01-core/src/lang/bas-info";
import { INT } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import { User_TYPE } from "lily-basic/src/net/bodz/lily/schema/account/UserTypeInfo";

import AbstractAssetTypeInfo from "./AbstractAssetTypeInfo";
import _UserAsset_stuff_Validators from "./_UserAsset_stuff_Validators";

export class _UserAsset_stuff_TypeInfo extends AbstractAssetTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "asset_user";

    static readonly FIELD_OWNER_ID = "owner";
    static readonly FIELD_BATCH = "batch";

    static readonly N_OWNER_ID = 10;
    static readonly N_BATCH = 2147483647;

    readonly validators = new _UserAsset_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.asset.UserAsset"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            batch: property({ type: JSON_VARIANT, validator: this.validators.validateBatch }),

            owner: property({ type: User_TYPE, nullable: false, inheritsDoc: true, 
                description: "User Account", 
                validator: this.validators.validateOwner }),
            ownerId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _UserAsset_stuff_TypeInfo();

}

export default _UserAsset_stuff_TypeInfo;

export const _UserAsset_stuff_TYPE = _UserAsset_stuff_TypeInfo.INSTANCE;

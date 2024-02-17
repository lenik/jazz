
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Long } from "../../../../../java/lang/Long";
import { * as validators } from "./PersonValidators";

// Type Info

export class _VAppRequestApi_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "vappreq_api";

    name = "net.bodz.lily.schema.vapp.VAppRequestApi"
    icon = "fa-tag"

    static const FIELD_ID = "id";
    static const FIELD_PARENT_ID = "parent";
    static const FIELD_API_ID = "api";

    static const N_ID = 19;
    static const N_PARENT_ID = 10;
    static const N_API_ID = 10;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "long", nullable: false, precision: 19, validator: validators.validate_id }),

        parent: property({ type: "net.bodz.lily.schema.vapp.VAppRequest", nullable: false, validator: validators.validate_parent }),
        parentId: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_parentId }),

        api: property({ type: "net.bodz.lily.schema.vapp.ApiType", nullable: false, validator: validators.validate_api }),
        apiId: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_apiId }),
    }

    constructor() {
        super();
        this.declare(_VAppRequestApi_stuff_Type.declaredProperty);
    }

}

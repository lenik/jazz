import type { integer, long } from "@skeljs/core/src/lang/type";
import CoEntityType from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { ApiType } from "./ApiType";
import { VAppRequest } from "./VAppRequest";
import VAppRequestApiValidators from "./VAppRequestApiValidators";

export class _VAppRequestApi_stuff_Type extends CoEntityType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "vappreq_api";

    name = "net.bodz.lily.schema.vapp.VAppRequestApi"
    icon = "fa-tag"

    static FIELD_ID = "id";
    static FIELD_PARENT_ID = "parent";
    static FIELD_API_ID = "api";

    static N_ID = 19;
    static N_PARENT_ID = 10;
    static N_API_ID = 10;

    static validators = new VAppRequestApiValidators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "long", nullable: false, precision: 19, validator: this.validators.validateId }),

        parent: property({ type: VAppRequest.TYPE, nullable: false, validator: this.validators.validateParent }),
        parentId: property({ type: "integer", nullable: false, precision: 10 }),

        api: property({ type: ApiType.TYPE, nullable: false, validator: this.validators.validateApi }),
        apiId: property({ type: "integer", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_VAppRequestApi_stuff_Type.declaredProperty);
    }

}

export default _VAppRequestApi_stuff_Type;

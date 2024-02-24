import type { integer, long } from "@skeljs/core/src/lang/type";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import ApiTypeTypeInfo from "./ApiTypeTypeInfo";
import VAppRequestTypeInfo from "./VAppRequestTypeInfo";
import _VAppRequestApi_stuff_Validators from "./_VAppRequestApi_stuff_Validators";

export class _VAppRequestApi_stuff_TypeInfo extends CoEntityTypeInfo {

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

    validators = new _VAppRequestApi_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "long", nullable: false, precision: 19, validator: this.validators.validateId }),

        parent: property({ type: VAppRequestTypeInfo, nullable: false, validator: this.validators.validateParent }),
        parentId: property({ type: "integer", nullable: false, precision: 10 }),

        api: property({ type: ApiTypeTypeInfo, nullable: false, validator: this.validators.validateApi }),
        apiId: property({ type: "integer", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _VAppRequestApi_stuff_TypeInfo;

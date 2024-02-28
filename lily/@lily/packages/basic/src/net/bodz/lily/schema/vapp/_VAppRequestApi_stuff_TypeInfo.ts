import { INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import type { long } from "@skeljs/core/src/lang/basetype";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import ApiType from "./ApiType";
import VAppRequest from "./VAppRequest";
import _VAppRequestApi_stuff_Validators from "./_VAppRequestApi_stuff_Validators";

export class _VAppRequestApi_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "vappreq_api";

    get name() { return "net.bodz.lily.schema.vapp.VAppRequestApi"; }
    get icon() { return "fa-tag"; }

    static FIELD_ID = "id";
    static FIELD_PARENT_ID = "parent";
    static FIELD_API_ID = "api";

    static N_ID = 19;
    static N_PARENT_ID = 10;
    static N_API_ID = 10;

    validators = new _VAppRequestApi_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: LONG, nullable: false, precision: 19, validator: this.validators.validateId }),

        parent: property({ type: VAppRequest.TYPE, nullable: false, validator: this.validators.validateParent }),
        parentId: property({ type: INT, nullable: false, precision: 10 }),

        api: property({ type: ApiType.TYPE, nullable: false, validator: this.validators.validateApi }),
        apiId: property({ type: INT, nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _VAppRequestApi_stuff_TypeInfo;

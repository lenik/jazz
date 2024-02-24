import type { integer } from "@skeljs/core/src/lang/type";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import _UserOtherIdType_stuff_Validators from "./_UserOtherIdType_stuff_Validators";

export class _UserOtherIdType_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "useroidtype";

    name = "net.bodz.lily.schema.account.UserOtherIdType"
    icon = "fa-tag"
    description = "Type of Open ID"

    static FIELD_ID = "id";
    static FIELD_DUMMY = "dummy";

    static N_ID = 10;
    static N_DUMMY = 10;

    validators = new _UserOtherIdType_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        dummy: property({ type: "integer", precision: 10, validator: this.validators.validateDummy }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _UserOtherIdType_stuff_TypeInfo;

import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityTypeInfo from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityTypeInfo";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import _GroupType_stuff_Validators from "./_GroupType_stuff_Validators";

export class _GroupType_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "grouptype";

    name = "net.bodz.lily.schema.account.GroupType"
    icon = "fa-tag"
    description = "Group Type"

    static FIELD_ID = "id";
    static FIELD_NAME = "name";
    static FIELD_DUMMY = "dummy";

    static N_ID = 10;
    static N_NAME = 20;
    static N_DUMMY = 10;

    static validators = new _GroupType_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        name: property({ type: "string", precision: 20, 
            description: "Group type name (unique)", 
            validator: this.validators.validateName }),
        dummy: property({ type: "integer", precision: 10, validator: this.validators.validateDummy }),
    }

    constructor() {
        super();
        this.declare(_GroupType_stuff_TypeInfo.declaredProperty);
    }

}

export default _GroupType_stuff_TypeInfo;

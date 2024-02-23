import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityType from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import UserTypeValidators from "./UserTypeValidators";

export class _UserType_stuff_Type extends CoEntityType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "usertype";

    name = "net.bodz.lily.schema.account.UserType"
    icon = "fa-tag"
    description = "User Type"

    static FIELD_ID = "id";
    static FIELD_NAME = "name";
    static FIELD_DUMMY = "dummy";

    static N_ID = 10;
    static N_NAME = 20;
    static N_DUMMY = 10;

    static validators = new UserTypeValidators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        name: property({ type: "string", precision: 20, 
            description: "The user type name", 
            validator: this.validators.validateName }),
        dummy: property({ type: "integer", precision: 10, validator: this.validators.validateDummy }),
    }

    constructor() {
        super();
        this.declare(_UserType_stuff_Type.declaredProperty);
    }

}

export default _UserType_stuff_Type;

import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import _UserType_stuff_Validators from "./_UserType_stuff_Validators";

export class _UserType_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "usertype";

    get name() { return "net.bodz.lily.schema.account.UserType"; }
    get icon() { return "fa-tag"; }
    get description() { return "User Type"; }

    static FIELD_ID = "id";
    static FIELD_NAME = "name";
    static FIELD_DUMMY = "dummy";

    static N_ID = 10;
    static N_NAME = 20;
    static N_DUMMY = 10;

    validators = new _UserType_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
        name: property({ type: STRING, precision: 20, 
            description: "The user type name", 
            validator: this.validators.validateName }),
        dummy: property({ type: INT, precision: 10, validator: this.validators.validateDummy }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _UserType_stuff_TypeInfo;

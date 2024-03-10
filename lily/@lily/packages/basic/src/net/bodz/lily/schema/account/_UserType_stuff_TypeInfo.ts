import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import _UserType_stuff_Validators from "./_UserType_stuff_Validators";

export class _UserType_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "usertype";

    static readonly FIELD_ID = "id";
    static readonly FIELD_NAME = "name";
    static readonly FIELD_PROPERTIES = "props";
    static readonly FIELD_DUMMY = "dummy";

    static readonly N_ID = 10;
    static readonly N_NAME = 20;
    static readonly N_PROPERTIES = 2147483647;
    static readonly N_DUMMY = 10;

    readonly validators = new _UserType_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.account.UserType"; }
    get icon() { return "fa-tag"; }
    get description() { return "User Type"; }

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
            name: property({ type: STRING, precision: 20, 
                description: "The user type name", 
                validator: this.validators.validateName }),
            properties: property({ type: JSON_VARIANT, validator: this.validators.validateProperties }),
            dummy: property({ type: INT, precision: 10, validator: this.validators.validateDummy }),
        });
    }

    static readonly INSTANCE = new _UserType_stuff_TypeInfo();

}

export default _UserType_stuff_TypeInfo;

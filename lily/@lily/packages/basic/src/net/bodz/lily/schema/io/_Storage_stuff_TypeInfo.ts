import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import _Storage_stuff_Validators from "./_Storage_stuff_Validators";

export class _Storage_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "storage";

    get name() { return "net.bodz.lily.schema.io.Storage"; }
    get icon() { return "fa-tag"; }

    static FIELD_ID = "id";
    static FIELD_NAME = "name";

    static N_ID = 10;
    static N_NAME = 30;

    validators = new _Storage_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
        name: property({ type: STRING, nullable: false, precision: 30, validator: this.validators.validateName }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _Storage_stuff_TypeInfo;

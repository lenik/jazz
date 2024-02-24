import type { integer } from "@skeljs/core/src/lang/type";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import _Storage_stuff_Validators from "./_Storage_stuff_Validators";

export class _Storage_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "storage";

    name = "net.bodz.lily.schema.io.Storage"
    icon = "fa-tag"

    static FIELD_ID = "id";
    static FIELD_NAME = "name";

    static N_ID = 10;
    static N_NAME = 30;

    validators = new _Storage_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        name: property({ type: "string", nullable: false, precision: 30, validator: this.validators.validateName }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _Storage_stuff_TypeInfo;

import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityType from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import StorageValidators from "./StorageValidators";

export class _Storage_stuff_Type extends CoEntityType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "storage";

    name = "net.bodz.lily.schema.io.Storage"
    icon = "fa-tag"

    static FIELD_ID = "id";
    static FIELD_NAME = "name";

    static N_ID = 10;
    static N_NAME = 30;

    static validators = new StorageValidators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        name: property({ type: "string", nullable: false, precision: 30, validator: this.validators.validateName }),
    }

    constructor() {
        super();
        this.declare(_Storage_stuff_Type.declaredProperty);
    }

}

export default _Storage_stuff_Type;

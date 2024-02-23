import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import StorageValidators from "./StorageValidators";
import _Storage_stuff_TypeInfo from "./_Storage_stuff_TypeInfo";

// Type Info

export class StorageTypeInfo extends _Storage_stuff_TypeInfo {

    name = "net.bodz.lily.schema.io.Storage"
    icon = "fa-tag"

    static validators = new StorageValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(StorageTypeInfo.declaredProperty);
    }

}

export default Storage;

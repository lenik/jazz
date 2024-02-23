import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import StorageValidators from "./StorageValidators";
import _Storage_stuff_Type from "./_Storage_stuff_Type";

// Type Info

export class StorageType extends _Storage_stuff_Type {

    name = "net.bodz.lily.schema.io.Storage"
    icon = "fa-tag"

    static validators = new StorageValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(StorageType.declaredProperty);
    }

}

export default Storage;

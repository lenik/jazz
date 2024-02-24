import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import StorageValidators from "./StorageValidators";
import _Storage_stuff_TypeInfo from "./_Storage_stuff_TypeInfo";

export class StorageTypeInfo extends _Storage_stuff_TypeInfo {

    name = "net.bodz.lily.schema.io.Storage"
    icon = "fa-tag"

    validators = new StorageValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default StorageTypeInfo;

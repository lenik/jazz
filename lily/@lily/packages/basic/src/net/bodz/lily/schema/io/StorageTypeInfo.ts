import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import StorageValidators from "./StorageValidators";
import _Storage_stuff_TypeInfo from "./_Storage_stuff_TypeInfo";

export class StorageTypeInfo extends _Storage_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.io.Storage"; }
    get icon() { return "fa-tag"; }

    validators = new StorageValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default StorageTypeInfo;

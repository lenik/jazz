import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import VApiValidators from "./VApiValidators";
import _VApi_stuff_TypeInfo from "./_VApi_stuff_TypeInfo";

// Type Info

export class VApiTypeInfo extends _VApi_stuff_TypeInfo {

    name = "net.bodz.lily.schema.vapp.VApi"
    icon = "fa-tag"

    static validators = new VApiValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(VApiTypeInfo.declaredProperty);
    }

}

export default VApi;

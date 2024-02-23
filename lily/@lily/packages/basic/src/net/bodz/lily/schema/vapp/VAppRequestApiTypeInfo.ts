import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import VAppRequestApiValidators from "./VAppRequestApiValidators";
import _VAppRequestApi_stuff_TypeInfo from "./_VAppRequestApi_stuff_TypeInfo";

// Type Info

export class VAppRequestApiTypeInfo extends _VAppRequestApi_stuff_TypeInfo {

    name = "net.bodz.lily.schema.vapp.VAppRequestApi"
    icon = "fa-tag"

    static validators = new VAppRequestApiValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(VAppRequestApiTypeInfo.declaredProperty);
    }

}

export default VAppRequestApi;

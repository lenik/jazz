import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import VAppRequestValidators from "./VAppRequestValidators";
import _VAppRequest_stuff_TypeInfo from "./_VAppRequest_stuff_TypeInfo";

// Type Info

export class VAppRequestTypeInfo extends _VAppRequest_stuff_TypeInfo {

    name = "net.bodz.lily.schema.vapp.VAppRequest"
    icon = "fa-tag"

    static validators = new VAppRequestValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(VAppRequestTypeInfo.declaredProperty);
    }

}

export default VAppRequest;

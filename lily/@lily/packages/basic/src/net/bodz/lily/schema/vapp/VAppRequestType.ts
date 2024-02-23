import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import VAppRequestValidators from "./VAppRequestValidators";
import _VAppRequest_stuff_Type from "./_VAppRequest_stuff_Type";

// Type Info

export class VAppRequestType extends _VAppRequest_stuff_Type {

    name = "net.bodz.lily.schema.vapp.VAppRequest"
    icon = "fa-tag"

    static validators = new VAppRequestValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(VAppRequestType.declaredProperty);
    }

}

export default VAppRequest;

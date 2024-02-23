import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import VAppRequestApiValidators from "./VAppRequestApiValidators";
import _VAppRequestApi_stuff_Type from "./_VAppRequestApi_stuff_Type";

// Type Info

export class VAppRequestApiType extends _VAppRequestApi_stuff_Type {

    name = "net.bodz.lily.schema.vapp.VAppRequestApi"
    icon = "fa-tag"

    static validators = new VAppRequestApiValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(VAppRequestApiType.declaredProperty);
    }

}

export default VAppRequestApi;

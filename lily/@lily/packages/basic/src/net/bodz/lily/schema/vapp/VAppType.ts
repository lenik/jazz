import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import VAppValidators from "./VAppValidators";
import _VApp_stuff_Type from "./_VApp_stuff_Type";

// Type Info

export class VAppType extends _VApp_stuff_Type {

    name = "net.bodz.lily.schema.vapp.VApp"
    icon = "fa-tag"

    static validators = new VAppValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(VAppType.declaredProperty);
    }

}

export default VApp;

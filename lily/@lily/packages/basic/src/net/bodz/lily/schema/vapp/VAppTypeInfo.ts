import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import VAppValidators from "./VAppValidators";
import _VApp_stuff_TypeInfo from "./_VApp_stuff_TypeInfo";

// Type Info

export class VAppTypeInfo extends _VApp_stuff_TypeInfo {

    name = "net.bodz.lily.schema.vapp.VApp"
    icon = "fa-tag"

    static validators = new VAppValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(VAppTypeInfo.declaredProperty);
    }

}

export default VApp;

import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import UomValidators from "./UomValidators";
import _Uom_stuff_TypeInfo from "./_Uom_stuff_TypeInfo";

// Type Info

export class UomTypeInfo extends _Uom_stuff_TypeInfo {

    name = "net.bodz.lily.schema.util.Uom"
    icon = "fa-tag"

    static validators = new UomValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(UomTypeInfo.declaredProperty);
    }

}

export default Uom;

import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import UomValidators from "./UomValidators";
import _Uom_stuff_Type from "./_Uom_stuff_Type";

// Type Info

export class UomType extends _Uom_stuff_Type {

    name = "net.bodz.lily.schema.util.Uom"
    icon = "fa-tag"

    static validators = new UomValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(UomType.declaredProperty);
    }

}

export default Uom;

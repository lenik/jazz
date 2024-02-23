import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import VAppCatValidators from "./VAppCatValidators";
import _VAppCat_stuff_Type from "./_VAppCat_stuff_Type";

// Type Info

export class VAppCatType extends _VAppCat_stuff_Type {

    name = "net.bodz.lily.schema.vapp.VAppCat"
    icon = "fa-tag"

    static validators = new VAppCatValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(VAppCatType.declaredProperty);
    }

}

export default VAppCat;

import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import VAppCatValidators from "./VAppCatValidators";
import _VAppCat_stuff_TypeInfo from "./_VAppCat_stuff_TypeInfo";

export class VAppCatTypeInfo extends _VAppCat_stuff_TypeInfo {

    name = "net.bodz.lily.schema.vapp.VAppCat"
    icon = "fa-tag"

    validators = new VAppCatValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default VAppCatTypeInfo;

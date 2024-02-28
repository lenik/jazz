import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import UomValidators from "./UomValidators";
import _Uom_stuff_TypeInfo from "./_Uom_stuff_TypeInfo";

export class UomTypeInfo extends _Uom_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.util.Uom"; }
    get icon() { return "fa-tag"; }

    validators = new UomValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default UomTypeInfo;

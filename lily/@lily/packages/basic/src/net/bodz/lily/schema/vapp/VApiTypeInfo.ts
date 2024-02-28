import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import VApiValidators from "./VApiValidators";
import _VApi_stuff_TypeInfo from "./_VApi_stuff_TypeInfo";

export class VApiTypeInfo extends _VApi_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.vapp.VApi"; }
    get icon() { return "fa-tag"; }

    validators = new VApiValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default VApiTypeInfo;

import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import VAppRequestApiValidators from "./VAppRequestApiValidators";
import _VAppRequestApi_stuff_TypeInfo from "./_VAppRequestApi_stuff_TypeInfo";

export class VAppRequestApiTypeInfo extends _VAppRequestApi_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.vapp.VAppRequestApi"; }
    get icon() { return "fa-tag"; }

    validators = new VAppRequestApiValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default VAppRequestApiTypeInfo;

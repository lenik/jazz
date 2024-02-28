import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import VApiLogValidators from "./VApiLogValidators";
import _VApiLog_stuff_TypeInfo from "./_VApiLog_stuff_TypeInfo";

export class VApiLogTypeInfo extends _VApiLog_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.vapp.VApiLog"; }
    get icon() { return "fa-tag"; }

    validators = new VApiLogValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default VApiLogTypeInfo;

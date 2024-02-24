import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import VApiLogValidators from "./VApiLogValidators";
import _VApiLog_stuff_TypeInfo from "./_VApiLog_stuff_TypeInfo";

export class VApiLogTypeInfo extends _VApiLog_stuff_TypeInfo {

    name = "net.bodz.lily.schema.vapp.VApiLog"
    icon = "fa-tag"

    validators = new VApiLogValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default VApiLogTypeInfo;

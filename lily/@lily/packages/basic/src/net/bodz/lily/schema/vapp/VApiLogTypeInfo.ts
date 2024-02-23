import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import VApiLogValidators from "./VApiLogValidators";
import _VApiLog_stuff_TypeInfo from "./_VApiLog_stuff_TypeInfo";

// Type Info

export class VApiLogTypeInfo extends _VApiLog_stuff_TypeInfo {

    name = "net.bodz.lily.schema.vapp.VApiLog"
    icon = "fa-tag"

    static validators = new VApiLogValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(VApiLogTypeInfo.declaredProperty);
    }

}

export default VApiLog;

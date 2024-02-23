import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ApiTypeValidators from "./ApiTypeValidators";
import _ApiType_stuff_TypeInfo from "./_ApiType_stuff_TypeInfo";

// Type Info

export class ApiTypeTypeInfo extends _ApiType_stuff_TypeInfo {

    name = "net.bodz.lily.schema.vapp.ApiType"
    icon = "fa-tag"

    static validators = new ApiTypeValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ApiTypeTypeInfo.declaredProperty);
    }

}

export default ApiType;

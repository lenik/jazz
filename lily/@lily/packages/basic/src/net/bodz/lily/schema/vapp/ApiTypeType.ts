import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ApiTypeValidators from "./ApiTypeValidators";
import _ApiType_stuff_Type from "./_ApiType_stuff_Type";

// Type Info

export class ApiTypeType extends _ApiType_stuff_Type {

    name = "net.bodz.lily.schema.vapp.ApiType"
    icon = "fa-tag"

    static validators = new ApiTypeValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ApiTypeType.declaredProperty);
    }

}

export default ApiType;

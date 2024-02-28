import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import ApiTypeValidators from "./ApiTypeValidators";
import _ApiType_stuff_TypeInfo from "./_ApiType_stuff_TypeInfo";

export class ApiTypeTypeInfo extends _ApiType_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.vapp.ApiType"; }
    get icon() { return "fa-tag"; }

    validators = new ApiTypeValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ApiTypeTypeInfo;

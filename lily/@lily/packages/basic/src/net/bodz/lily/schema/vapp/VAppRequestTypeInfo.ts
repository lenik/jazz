import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import VAppRequestValidators from "./VAppRequestValidators";
import _VAppRequest_stuff_TypeInfo from "./_VAppRequest_stuff_TypeInfo";

export class VAppRequestTypeInfo extends _VAppRequest_stuff_TypeInfo {

    name = "net.bodz.lily.schema.vapp.VAppRequest"
    icon = "fa-tag"

    validators = new VAppRequestValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default VAppRequestTypeInfo;

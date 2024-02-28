import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import VAppValidators from "./VAppValidators";
import _VApp_stuff_TypeInfo from "./_VApp_stuff_TypeInfo";

export class VAppTypeInfo extends _VApp_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.vapp.VApp"; }
    get icon() { return "fa-tag"; }

    validators = new VAppValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default VAppTypeInfo;

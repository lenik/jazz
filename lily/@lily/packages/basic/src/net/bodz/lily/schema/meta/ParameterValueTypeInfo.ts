import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import ParameterValueValidators from "./ParameterValueValidators";
import _ParameterValue_stuff_TypeInfo from "./_ParameterValue_stuff_TypeInfo";

export class ParameterValueTypeInfo extends _ParameterValue_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.meta.ParameterValue"; }
    get icon() { return "fa-tag"; }

    validators = new ParameterValueValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ParameterValueTypeInfo;

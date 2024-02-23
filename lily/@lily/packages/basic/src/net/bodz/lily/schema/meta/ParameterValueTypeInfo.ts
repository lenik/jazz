import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ParameterValueValidators from "./ParameterValueValidators";
import _ParameterValue_stuff_TypeInfo from "./_ParameterValue_stuff_TypeInfo";

// Type Info

export class ParameterValueTypeInfo extends _ParameterValue_stuff_TypeInfo {

    name = "net.bodz.lily.schema.meta.ParameterValue"
    icon = "fa-tag"

    static validators = new ParameterValueValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ParameterValueTypeInfo.declaredProperty);
    }

}

export default ParameterValue;

import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ParameterValueValidators from "./ParameterValueValidators";
import _ParameterValue_stuff_Type from "./_ParameterValue_stuff_Type";

// Type Info

export class ParameterValueType extends _ParameterValue_stuff_Type {

    name = "net.bodz.lily.schema.meta.ParameterValue"
    icon = "fa-tag"

    static validators = new ParameterValueValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ParameterValueType.declaredProperty);
    }

}

export default ParameterValue;

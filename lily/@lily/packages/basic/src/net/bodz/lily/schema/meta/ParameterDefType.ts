import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ParameterDefValidators from "./ParameterDefValidators";
import _ParameterDef_stuff_Type from "./_ParameterDef_stuff_Type";

// Type Info

export class ParameterDefType extends _ParameterDef_stuff_Type {

    name = "net.bodz.lily.schema.meta.ParameterDef"
    icon = "fa-tag"
    label = "Parameter"

    static validators = new ParameterDefValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ParameterDefType.declaredProperty);
    }

}

export default ParameterDef;

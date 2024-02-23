import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import FormDefValidators from "./FormDefValidators";
import _FormDef_stuff_Type from "./_FormDef_stuff_Type";

// Type Info

export class FormDefType extends _FormDef_stuff_Type {

    name = "net.bodz.lily.schema.meta.FormDef"
    icon = "fa-tag"

    static validators = new FormDefValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(FormDefType.declaredProperty);
    }

}

export default FormDef;

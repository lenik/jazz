import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import FormDefValidators from "./FormDefValidators";
import _FormDef_stuff_TypeInfo from "./_FormDef_stuff_TypeInfo";

export class FormDefTypeInfo extends _FormDef_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.meta.FormDef"; }
    get icon() { return "fa-tag"; }

    validators = new FormDefValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default FormDefTypeInfo;

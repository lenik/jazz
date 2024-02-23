import type { integer } from "@skeljs/core/src/lang/type";
import CoCategoryTypeInfo from "@skeljs/dba/src/net/bodz/lily/concrete/CoCategoryTypeInfo";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { TypeParamType } from "../../meta/TypeParamType";
import _PartyCategory_stuff_Validators from "./_PartyCategory_stuff_Validators";

export class _PartyCategory_stuff_TypeInfo extends CoCategoryTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "partycat";

    name = "net.bodz.lily.schema.contact.PartyCategory"
    icon = "fa-tag"

    static FIELD_NAME = "name";

    static N_NAME = 30;

    static validators = new _PartyCategory_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {
        name: property({ type: "string", precision: 30, validator: this.validators.validateName }),
    }

    constructor() {
        super();
        this.declare(_PartyCategory_stuff_TypeInfo.declaredProperty);
    }

}

export default _PartyCategory_stuff_TypeInfo;

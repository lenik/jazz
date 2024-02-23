import type { integer } from "@skeljs/core/src/lang/type";
import CoCategoryType from "@skeljs/dba/src/net/bodz/lily/concrete/CoCategoryType";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { TypeParamType } from "../../meta/TypeParamType";
import PartyCategoryValidators from "./PartyCategoryValidators";

export class _PartyCategory_stuff_Type extends CoCategoryType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "partycat";

    name = "net.bodz.lily.schema.contact.PartyCategory"
    icon = "fa-tag"

    static FIELD_NAME = "name";

    static N_NAME = 30;

    static validators = new PartyCategoryValidators();

    static declaredProperty: EntityPropertyMap = {
        name: property({ type: "string", precision: 30, validator: this.validators.validateName }),
    }

    constructor() {
        super();
        this.declare(_PartyCategory_stuff_Type.declaredProperty);
    }

}

export default _PartyCategory_stuff_Type;

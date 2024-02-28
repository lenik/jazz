import { STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoCategoryTypeInfo from "../../concrete/CoCategoryTypeInfo";
import _PartyCategory_stuff_Validators from "./_PartyCategory_stuff_Validators";

export class _PartyCategory_stuff_TypeInfo extends CoCategoryTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "partycat";

    get name() { return "net.bodz.lily.schema.contact.PartyCategory"; }
    get icon() { return "fa-tag"; }

    static FIELD_NAME = "name";

    static N_NAME = 30;

    validators = new _PartyCategory_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        name: property({ type: STRING, precision: 30, validator: this.validators.validateName }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _PartyCategory_stuff_TypeInfo;

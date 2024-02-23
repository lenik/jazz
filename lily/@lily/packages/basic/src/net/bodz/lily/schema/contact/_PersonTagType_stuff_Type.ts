import CoTagType from "@skeljs/dba/src/net/bodz/lily/concrete/CoTagType";
import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { TypeParamType } from "../../meta/TypeParamType";
import PersonTagTypeValidators from "./PersonTagTypeValidators";

export class _PersonTagType_stuff_Type extends CoTagType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "persontag";

    name = "net.bodz.lily.schema.contact.PersonTagType"
    icon = "fa-tag"

    static validators = new PersonTagTypeValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(_PersonTagType_stuff_Type.declaredProperty);
    }

}

export default _PersonTagType_stuff_Type;

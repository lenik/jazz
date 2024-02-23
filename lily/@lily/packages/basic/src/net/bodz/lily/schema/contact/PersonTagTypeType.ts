import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PersonTagTypeValidators from "./PersonTagTypeValidators";
import _PersonTagType_stuff_Type from "./_PersonTagType_stuff_Type";

// Type Info

export class PersonTagTypeType extends _PersonTagType_stuff_Type {

    name = "net.bodz.lily.schema.contact.PersonTagType"
    icon = "fa-tag"

    static validators = new PersonTagTypeValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PersonTagTypeType.declaredProperty);
    }

}

export default PersonTagType;

import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PersonTagTypeValidators from "./PersonTagTypeValidators";
import _PersonTagType_stuff_TypeInfo from "./_PersonTagType_stuff_TypeInfo";

// Type Info

export class PersonTagTypeTypeInfo extends _PersonTagType_stuff_TypeInfo {

    name = "net.bodz.lily.schema.contact.PersonTagType"
    icon = "fa-tag"

    static validators = new PersonTagTypeValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PersonTagTypeTypeInfo.declaredProperty);
    }

}

export default PersonTagType;

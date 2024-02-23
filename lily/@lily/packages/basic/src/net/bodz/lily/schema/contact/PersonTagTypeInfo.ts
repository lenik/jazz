import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PersonTagValidators from "./PersonTagValidators";
import _PersonTag_stuff_TypeInfo from "./_PersonTag_stuff_TypeInfo";

// Type Info

export class PersonTagTypeInfo extends _PersonTag_stuff_TypeInfo {

    name = "net.bodz.lily.schema.contact.PersonTag"
    icon = "fa-tag"

    static validators = new PersonTagValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PersonTagTypeInfo.declaredProperty);
    }

}

export default PersonTag;

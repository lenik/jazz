import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PersonRoleValidators from "./PersonRoleValidators";
import _PersonRole_stuff_TypeInfo from "./_PersonRole_stuff_TypeInfo";

// Type Info

export class PersonRoleTypeInfo extends _PersonRole_stuff_TypeInfo {

    name = "net.bodz.lily.schema.contact.PersonRole"
    icon = "fa-tag"

    static validators = new PersonRoleValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PersonRoleTypeInfo.declaredProperty);
    }

}

export default PersonRole;

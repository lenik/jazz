import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PersonRoleValidators from "./PersonRoleValidators";
import _PersonRole_stuff_TypeInfo from "./_PersonRole_stuff_TypeInfo";

export class PersonRoleTypeInfo extends _PersonRole_stuff_TypeInfo {

    name = "net.bodz.lily.schema.contact.PersonRole"
    icon = "fa-tag"

    validators = new PersonRoleValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PersonRoleTypeInfo;

import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import PersonRoleValidators from "./PersonRoleValidators";
import _PersonRole_stuff_TypeInfo from "./_PersonRole_stuff_TypeInfo";

export class PersonRoleTypeInfo extends _PersonRole_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.contact.PersonRole"; }
    get icon() { return "fa-tag"; }

    validators = new PersonRoleValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PersonRoleTypeInfo;

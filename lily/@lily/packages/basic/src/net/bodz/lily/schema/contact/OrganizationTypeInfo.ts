import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import OrganizationValidators from "./OrganizationValidators";
import _Organization_stuff_TypeInfo from "./_Organization_stuff_TypeInfo";

// Type Info

export class OrganizationTypeInfo extends _Organization_stuff_TypeInfo {

    name = "net.bodz.lily.schema.contact.Organization"
    icon = "fa-tag"

    static validators = new OrganizationValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(OrganizationTypeInfo.declaredProperty);
    }

}

export default Organization;

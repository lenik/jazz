import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import OrganizationValidators from "./OrganizationValidators";
import _Organization_stuff_Type from "./_Organization_stuff_Type";

// Type Info

export class OrganizationType extends _Organization_stuff_Type {

    name = "net.bodz.lily.schema.contact.Organization"
    icon = "fa-tag"

    static validators = new OrganizationValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(OrganizationType.declaredProperty);
    }

}

export default Organization;

import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import OrganizationValidators from "./OrganizationValidators";
import _Organization_stuff_TypeInfo from "./_Organization_stuff_TypeInfo";

export class OrganizationTypeInfo extends _Organization_stuff_TypeInfo {

    name = "net.bodz.lily.schema.contact.Organization"
    icon = "fa-tag"

    validators = new OrganizationValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default OrganizationTypeInfo;

import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import OrganizationValidators from "./OrganizationValidators";
import _Organization_stuff_TypeInfo from "./_Organization_stuff_TypeInfo";

export class OrganizationTypeInfo extends _Organization_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.contact.Organization"; }
    get icon() { return "fa-tag"; }

    validators = new OrganizationValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default OrganizationTypeInfo;

import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import Contact from "./Contact";
import OrgUnitValidators from "./OrgUnitValidators";
import PersonRole from "./PersonRole";
import _OrgUnit_stuff_TypeInfo from "./_OrgUnit_stuff_TypeInfo";

export class OrgUnitTypeInfo extends _OrgUnit_stuff_TypeInfo {

    name = "net.bodz.lily.schema.contact.OrgUnit"
    icon = "fa-tag"

    validators = new OrgUnitValidators(this);

    declaredProperty: EntityPropertyMap = {
        contact: property({ type: "Contact", validator: this.validators.validateContact }),
        staff: property({ type: "PersonRole[]", validator: this.validators.validateStaff }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default OrgUnitTypeInfo;

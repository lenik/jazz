import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { List } from "../../../../../java/util/List";
import { Contact } from "./Contact";
import OrgUnitValidators from "./OrgUnitValidators";
import _OrgUnit_stuff_TypeInfo from "./_OrgUnit_stuff_TypeInfo";

// Type Info

export class OrgUnitTypeInfo extends _OrgUnit_stuff_TypeInfo {

    name = "net.bodz.lily.schema.contact.OrgUnit"
    icon = "fa-tag"

    static validators = new OrgUnitValidators();

    static declaredProperty: EntityPropertyMap = {
        contact: property({ type: "Contact", validator: this.validators.validateContact }),
        staff: property({ type: "List", validator: this.validators.validateStaff }),
    }

    constructor() {
        super();
        this.declare(OrgUnitTypeInfo.declaredProperty);
    }

}

export default OrgUnit;

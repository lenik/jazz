import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { List } from "../../../../../java/util/List";
import { Contact } from "./Contact";
import OrgUnitValidators from "./OrgUnitValidators";
import _OrgUnit_stuff_Type from "./_OrgUnit_stuff_Type";

// Type Info

export class OrgUnitType extends _OrgUnit_stuff_Type {

    name = "net.bodz.lily.schema.contact.OrgUnit"
    icon = "fa-tag"

    static validators = new OrgUnitValidators();

    static declaredProperty: EntityPropertyMap = {
        contact: property({ type: "Contact", validator: this.validators.validateContact }),
        staff: property({ type: "List", validator: this.validators.validateStaff }),
    }

    constructor() {
        super();
        this.declare(OrgUnitType.declaredProperty);
    }

}

export default OrgUnit;

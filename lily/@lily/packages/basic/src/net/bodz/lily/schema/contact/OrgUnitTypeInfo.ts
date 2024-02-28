import { LIST } from "@skeljs/core/src/lang/baseinfo";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import Contact from "./Contact";
import OrgUnitValidators from "./OrgUnitValidators";
import PersonRole from "./PersonRole";
import _OrgUnit_stuff_TypeInfo from "./_OrgUnit_stuff_TypeInfo";

export class OrgUnitTypeInfo extends _OrgUnit_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.contact.OrgUnit"; }
    get icon() { return "fa-tag"; }

    validators = new OrgUnitValidators(this);

    declaredProperty: EntityPropertyMap = {
        contact: property({ type: Contact.TYPE, validator: this.validators.validateContact }),
        staff: property({ type: LIST(PersonRole.TYPE), validator: this.validators.validateStaff }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default OrgUnitTypeInfo;

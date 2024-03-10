import { LIST } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import Contact from "./Contact";
import OrgUnitValidators from "./OrgUnitValidators";
import PersonRole from "./PersonRole";
import _OrgUnit_stuff_TypeInfo from "./_OrgUnit_stuff_TypeInfo";

export class OrgUnitTypeInfo extends _OrgUnit_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.contact.OrgUnit"; }
    get icon() { return "fa-tag"; }

    validators = new OrgUnitValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
            contact: property({ type: Contact.TYPE, validator: this.validators.validateContact }),
            staff: property({ type: LIST(PersonRole.TYPE), validator: this.validators.validateStaff }),
        });
    }

    constructor() {
        super();
    }

}

export default OrgUnitTypeInfo;
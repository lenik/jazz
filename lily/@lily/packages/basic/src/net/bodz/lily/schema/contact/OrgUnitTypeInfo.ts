import { LIST } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import { Contact_TYPE } from "./ContactTypeInfo";
import OrgUnit from "./OrgUnit";
import OrgUnitValidators from "./OrgUnitValidators";
import { PersonRole_TYPE } from "./PersonRoleTypeInfo";
import _OrgUnit_stuff_TypeInfo from "./_OrgUnit_stuff_TypeInfo";

export class OrgUnitTypeInfo extends _OrgUnit_stuff_TypeInfo {

    readonly validators = new OrgUnitValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.contact.OrgUnit"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new OrgUnit();
    }

    override preamble() {
        super.preamble();
        this.declare({
            contact: property({ type: Contact_TYPE, validator: this.validators.validateContact }),
            staff: property({ type: LIST(PersonRole_TYPE), validator: this.validators.validateStaff }),
        });
    }

    static readonly INSTANCE = new OrgUnitTypeInfo();

}

export default OrgUnitTypeInfo;

export const OrgUnit_TYPE = OrgUnitTypeInfo.INSTANCE;

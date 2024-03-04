import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import IdEntityTypeInfo from '../../concrete/IdEntityTypeInfo';
import Organization from "./Organization";
import OrgUnit from "./OrgUnit";
import Person from "./Person";
import Zone from "../geo/Zone";

export class ContactTypeInfo extends IdEntityTypeInfo {

    name = "net.bodz.lily.schema.contact.Contact"
    icon = "fa-address-card"
    label = "Contact Information"
    description = "A contact record."

    declaredProperty: EntityPropertyMap = {
        org: property({ type: Organization.TYPE, icon: "fa-build" }),
        orgUnit: property({ type: OrgUnit.TYPE, icon: "fa-build" }),
        person: property({ type: Person.TYPE, icon: "fa-male" }),

        rename: property({ type: 'string', icon: "fa-tag" }),
        usage: property({ type: 'string', icon: "fas-lightbulb" }),

        zone: property({ type: Zone.TYPE, icon: "far-map" }),
        country: property({ type: 'string', icon: "far-globe" }),
        r1: property({ type: 'string', icon: "fab-buromobelexperte" }),
        r2: property({ type: 'string', icon: "fab-buromobelexperte" }),
        r3: property({ type: 'string', icon: "fab-buromobelexperte" }),
        r4: property({ type: 'string', icon: "fab-buromobelexperte" }),

        address1: property({ type: 'string', icon: "far-location-dot" }),
        address2: property({ type: 'string', icon: "far-location-dot" }),
        postalCode: property({ type: 'string', icon: "fas-map" }),

        tel: property({ type: 'string', icon: "fa-tel" }),
        mobile: property({ type: 'string', icon: "fa-mobile" }),
        fax: property({ type: 'string', icon: "fa-fax" }),
        email: property({ type: 'string', icon: "fa-email" }),
        web: property({ type: 'string', icon: "fa-web" }),
        qq: property({ type: 'string', icon: "fa-qq" }),
        wechat: property({ type: 'string', icon: "fa-wechat" }),

    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }
}

export default ContactTypeInfo;

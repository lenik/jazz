import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import IdEntityTypeInfo from "../../concrete/IdEntityTypeInfo";
import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import Organization from "./Organization";
import OrgUnit from "./OrgUnit";
import Person from "./Person";
import Zone from "../geo/Zone";
import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";

export class ContactTypeInfo extends IdEntityTypeInfo {

    get name() { return "net.bodz.lily.schema.contact.Contact"; }
    get icon() { return "fa-address-card"; }
    get label() { return "Contact Information"; }
    get description() { return "A contact record."; }

    override preamble() {
        super.preamble();
        this.declare({
            org: property({ type: Organization.TYPE, icon: "fa-build" }),
            orgUnit: property({ type: OrgUnit.TYPE, icon: "fa-build" }),
            person: property({ type: Person.TYPE, icon: "fa-male" }),

            rename: property({ type: STRING, icon: "fa-tag" }),
            usage: property({ type: STRING, icon: "fas-lightbulb" }),

            zone: property({ type: Zone.TYPE, icon: "far-map" }),
            country: property({ type: STRING, icon: "far-globe" }),
            r1: property({ type: STRING, icon: "fab-buromobelexperte" }),
            r2: property({ type: STRING, icon: "fab-buromobelexperte" }),
            r3: property({ type: STRING, icon: "fab-buromobelexperte" }),
            r4: property({ type: STRING, icon: "fab-buromobelexperte" }),

            address1: property({ type: STRING, icon: "far-location-dot" }),
            address2: property({ type: STRING, icon: "far-location-dot" }),
            postalCode: property({ type: STRING, icon: "fas-map" }),

            tel: property({ type: STRING, icon: "fa-tel" }),
            mobile: property({ type: STRING, icon: "fa-mobile" }),
            fax: property({ type: STRING, icon: "fa-fax" }),
            email: property({ type: STRING, icon: "fa-email" }),
            web: property({ type: STRING, icon: "fa-web" }),
            qq: property({ type: STRING, icon: "fa-qq" }),
            wechat: property({ type: STRING, icon: "fa-wechat" }),
        });
    }

    constructor() {
        super(INT);
    }
}

export default ContactTypeInfo;

import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import IdEntityTypeInfo from "../../concrete/IdEntityTypeInfo";
import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import ContactValidators from "./ContactValidators";
import Contact from "./Contact";
import Organization from "./Organization";
import OrgUnit from "./OrgUnit";
import Person from "./Person";
import Zone from "../geo/Zone";
import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";

export class ContactTypeInfo extends IdEntityTypeInfo {

    readonly validators = new ContactValidators(this);

    constructor() {
        super(INT);

        import('./Organization').then((a) => this.property.org.type = a.Organization.TYPE);
        import('./OrgUnit').then((a) => this.property.orgUnit.type = a.OrgUnit.TYPE);
        import('./Person').then((a) => this.property.person.type = a.Person.TYPE);
    }

    get name() { return "net.bodz.lily.schema.contact.Contact"; }
    get icon() { return "fa-address-card"; }
    get label() { return "Contact Information"; }
    get description() { return "A contact record."; }

    override create() {
        return new Contact();
    }

    override preamble() {
        super.preamble();
        this.declare({
            org: property({
                type: JSON_VARIANT, icon: "fa-build",
                validator: this.validators.validateOrg
            }),
            orgUnit: property({
                type: JSON_VARIANT, icon: "fa-build",
                validator: this.validators.validateOrgUnit
            }),
            person: property({
                type: JSON_VARIANT, icon: "fa-male",
                validator: this.validators.validatePerson
            }),
            rename: property({
                type: STRING, icon: "fa-tag",
                validator: this.validators.validateRename
            }),
            usage: property({
                type: STRING, icon: "fas-lightbulb",
                validator: this.validators.validateUsage
            }),
            zone: property({
                type: Zone.TYPE, icon: "far-map",
                validator: this.validators.validateZone
            }),
            country: property({
                type: STRING, icon: "far-globe",
                validator: this.validators.validateCountry
            }),
            r1: property({
                type: STRING, icon: "fab-buromobelexperte",
                validator: this.validators.validateR1
            }),
            r2: property({
                type: STRING, icon: "fab-buromobelexperte",
                validator: this.validators.validateR2
            }),
            r3: property({
                type: STRING, icon: "fab-buromobelexperte",
                validator: this.validators.validateR3
            }),
            r4: property({
                type: STRING, icon: "fab-buromobelexperte",
                validator: this.validators.validateR4
            }),
            address1: property({
                type: STRING, icon: "far-location-dot",
                validator: this.validators.validateAddress1
            }),
            address2: property({
                type: STRING, icon: "far-location-dot",
                validator: this.validators.validateAddress2
            }),
            postalCode: property({
                type: STRING, icon: "fas-map",
                validator: this.validators.validatePostalCode
            }),
            tel: property({
                type: STRING, icon: "fa-tel",
                validator: this.validators.validateTel
            }),
            mobile: property({
                type: STRING, icon: "fa-mobile",
                validator: this.validators.validateMobile
            }),
            fax: property({
                type: STRING, icon: "fa-fax",
                validator: this.validators.validateFax
            }),
            email: property({
                type: STRING, icon: "fa-email",
                validator: this.validators.validateEmail
            }),
            web: property({
                type: STRING, icon: "fa-web",
                validator: this.validators.validateWeb
            }),
            qq: property({
                type: STRING, icon: "fa-qq",
                validator: this.validators.validateQq
            }),
            wechat: property({
                type: STRING, icon: "fa-wechat",
                validator: this.validators.validateWechat
            }),
        });
    }

    static readonly INSTANCE = new ContactTypeInfo();

}

export default ContactTypeInfo;
export const Contact_TYPE = ContactTypeInfo.INSTANCE;

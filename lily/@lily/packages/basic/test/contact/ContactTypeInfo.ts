import { EntityPropertyMap, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import { LONG, STRING } from '@skeljs/core/src/lang/baseinfo';
import { JSON_VARIANT } from '@skeljs/core/src/lang/bas-info';
import IdEntityTypeInfo from '../../src/net/bodz/lily/concrete/IdEntityTypeInfo';
import Person from '../../src/net/bodz/lily/schema/contact/Person';

export class ContactTypeInfo extends IdEntityTypeInfo {

    get name() { return "net.bodz.lily.schema.contact.Contact"; }
    get icon() { return "fa-address-card"; }
    get label() { return "Contact Information"; }
    get description() { return "A contact record."; }

    override preamble() {
        super.preamble();
        this.declare({
            org: property({ type: JSON_VARIANT, icon: "fa-build" }),
            orgUnit: property({ type: JSON_VARIANT, icon: "fa-build" }),
            person: property({ type: Person.TYPE, icon: "fa-male" }),

            rename: property({ type: STRING, icon: "fa-tag" }),
            usage: property({ type: STRING, icon: "fa-tag" }),

            zone: property({ type: STRING, icon: "fa-tag" }),
            country: property({ type: STRING, icon: "fa-tag" }),
            r1: property({ type: STRING, icon: "fa-tag" }),
            r2: property({ type: STRING, icon: "fa-tag" }),
            r3: property({ type: STRING, icon: "fa-tag" }),
            r4: property({ type: STRING, icon: "fa-tag" }),

            address1: property({ type: STRING, icon: "fa-tag" }),
            address2: property({ type: STRING, icon: "fa-tag" }),
            postalCode: property({ type: STRING, icon: "fa-tag" }),

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
        super(LONG);
    }

}

export default ContactTypeInfo;

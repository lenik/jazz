import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import IdEntityTypeInfo from '@skeljs/dba/src/net/bodz/lily/concrete/IdEntityTypeInfo';

export class ContactTypeInfo extends IdEntityTypeInfo {

    name = "net.bodz.lily.schema.contact.Contact"
    icon = "fa-address-card"
    label = "Contact Information"
    description = "A contact record."

    static declaredProperty: EntityPropertyMap = {
        org: property({ type: 'any', icon: "fa-build" }),
        orgUnit: property({ type: 'any', icon: "fa-build" }),
        person: property({ type: 'any', icon: "fa-male" }),

        rename: property({ type: 'string', icon: "fa-tag" }),
        usage: property({ type: 'string', icon: "fa-tag" }),

        zone: property({ type: 'string', icon: "fa-tag" }),
        country: property({ type: 'string', icon: "fa-tag" }),
        r1: property({ type: 'string', icon: "fa-tag" }),
        r2: property({ type: 'string', icon: "fa-tag" }),
        r3: property({ type: 'string', icon: "fa-tag" }),
        r4: property({ type: 'string', icon: "fa-tag" }),

        address1: property({ type: 'string', icon: "fa-tag" }),
        address2: property({ type: 'string', icon: "fa-tag" }),
        postalCode: property({ type: 'string', icon: "fa-tag" }),

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
        this.declare(ContactTypeInfo.declaredProperty);
    }
}

export default ContactTypeInfo;

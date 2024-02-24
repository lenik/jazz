import { EntityPropertyMap, property } from '@skeljs/dba/src/net/bodz/lily/entity';
import IdEntityTypeInfo from '@skeljs/dba/src/net/bodz/lily/concrete/IdEntityTypeInfo';

import PartyValidators from './PartyValidators';
import PersonTypeInfo from './PersonTypeInfo';

export class PartyTypeInfo extends IdEntityTypeInfo {

    validators = new PartyValidators();

    declaredProperty: EntityPropertyMap = {
        category: property({ type: 'any', icon: "far-crow" }),
        birthday: property({ type: 'Moment', icon: "fa-baby" }),

        locale: property({ type: 'string', icon: "far-globe" }),
        timeZoneId: property({ type: 'string', icon: "fa-map" }),

        peer: property({ type: 'boolean', nullable: false, icon: "fa-user-group" }),
        customer: property({ type: 'boolean', nullable: false, icon: "fa-hand-holding-dollar" }),
        supplier: property({ type: 'boolean', nullable: false, icon: "far-industry" }),

        tags: property({ type: 'string[]', icon: "fa-tags" }),

        subject: property({ type: 'string', icon: "fa-quote-left" }),
        contacts: property({ type: 'Contact[]', icon: "far-address-book" }),

        bank: property({ type: 'string', icon: "far-building-columns" }),
        account: property({ type: 'string', icon: "far-id-card" }),

        bankCount: property({ type: 'number', icon: "far-hashtag" }),
        roleCount: property({ type: 'number', icon: "far-hashtag" }),

        // person

        employee: property({ type: "boolean", icon: "far-user-tie" }),
        gender: property({ type: "char", icon: "far-transgender" }),
        pronoun: property({ type: "string", icon: "far-genderless" }),
        sexualOrientation: property({ type: "string", icon: "far-venus-mars" }),
        homeland: property({ type: "string", icon: "far-home" }),
        passport: property({ type: "string", icon: "far-passport" }),
        ssn: property({ type: "string", icon: "far-id-card" }),
        dln: property({ type: "string", icon: "far-id-card" }),

        mother: property({ type: PersonTypeInfo, icon: "far-female" }),
        father: property({ type: PersonTypeInfo, icon: "far-male" }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }
}

export default PartyTypeInfo;

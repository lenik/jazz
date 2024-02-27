import { EntityPropertyMap, property } from '@skeljs/dba/src/net/bodz/lily/entity';
import IdEntityTypeInfo from '../../concrete/IdEntityTypeInfo';
import PartyValidators from './PartyValidators';
import Person from './Person';
import PartyCategory from './PartyCategory';

export class PartyTypeInfo extends IdEntityTypeInfo {

    validators = new PartyValidators(this);

    declaredProperty: EntityPropertyMap = {
        category: property({ type: PartyCategory.TYPE, icon: "far-crow" }),
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

        mother: property({ type: "Person.TYPE", icon: "far-female" }),
        father: property({ type: "Person.TYPE", icon: "far-male" }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);

        import('./Person').then((a) => {
            this.property.mother.type = a.Person.TYPE
            this.property.father.type = a.Person.TYPE
        });
    }
}

export default PartyTypeInfo;

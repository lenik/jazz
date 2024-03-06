import { property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import EntityPropertyMap from '@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap';
import IdEntityTypeInfo from '../../concrete/IdEntityTypeInfo';
import PartyValidators from './PartyValidators';
import { ARRAY, BOOLEAN, CHAR, INT, STRING } from '@skeljs/core/src/lang/baseinfo';
import LocalDate from '@skeljs/core/src/lang/time/LocalDate';
import PartyCategory from './PartyCategory';
import Person from './Person';
import Contact from './Contact';
import { JSON_VARIANT } from '@skeljs/core/src/lang/bas-info';

export class PartyTypeInfo extends IdEntityTypeInfo {

    validators = new PartyValidators(this);

    declaredProperty: EntityPropertyMap = {
        category: property({ type: PartyCategory.TYPE, icon: "far-crow" }),
        birthday: property({ type: LocalDate.TYPE, icon: "fa-baby" }),

        locale: property({ type: STRING, icon: "far-globe" }),
        timeZoneId: property({ type: STRING, icon: "fa-map" }),

        peer: property({ type: BOOLEAN, nullable: false, icon: "fa-user-group" }),
        customer: property({ type: BOOLEAN, nullable: false, icon: "fa-hand-holding-dollar" }),
        supplier: property({ type: BOOLEAN, nullable: false, icon: "far-industry" }),

        tags: property({ type: ARRAY(STRING), icon: "fa-tags" }),

        subject: property({ type: STRING, icon: "fa-quote-left" }),
        contacts: property({ type: ARRAY(Contact.TYPE), icon: "far-address-book" }),

        bank: property({ type: STRING, icon: "far-building-columns" }),
        account: property({ type: STRING, icon: "far-id-card" }),

        bankCount: property({ type: INT, icon: "far-hashtag" }),
        roleCount: property({ type: INT, icon: "far-hashtag" }),

        // person

        employee: property({ type: BOOLEAN, icon: "far-user-tie" }),
        gender: property({ type: CHAR, icon: "far-transgender" }),
        pronoun: property({ type: STRING, icon: "far-genderless" }),
        sexualOrientation: property({ type: STRING, icon: "far-venus-mars" }),
        homeland: property({ type: STRING, icon: "far-home" }),
        passport: property({ type: STRING, icon: "far-passport" }),
        ssn: property({ type: STRING, icon: "far-id-card" }),
        dln: property({ type: STRING, icon: "far-id-card" }),

        mother: property({ type: JSON_VARIANT, icon: "far-female" }),
        father: property({ type: JSON_VARIANT, icon: "far-male" }),
    }

    constructor() {
        super(INT);
        this.declare(this.declaredProperty);

        import('./Person').then((a) => {
            this.property.mother.type = a.Person.TYPE
            this.property.father.type = a.Person.TYPE
        });
    }
}

export default PartyTypeInfo;

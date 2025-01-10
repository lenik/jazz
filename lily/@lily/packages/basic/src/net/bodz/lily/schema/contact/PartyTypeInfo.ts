import { property } from 'skel01-dba/src/net/bodz/lily/entity/EntityType';
import CoImagedTypeInfo from '../../concrete/CoImagedTypeInfo';
import PartyValidators from './PartyValidators';
import { ARRAY, BOOLEAN, CHAR, INT, STRING } from 'skel01-core/src/lang/baseinfo';
import { JSON_VARIANT } from 'skel01-core/src/lang/bas-info';
import LocalDate from 'skel01-core/src/lang/time/LocalDate';
import PartyCategory from './PartyCategory';
import Person from './Person';
import Contact from './Contact';

export class PartyTypeInfo extends CoImagedTypeInfo {

    readonly validators = new PartyValidators(this);

    constructor() {
        super(INT);
    }

    override preamble() {
        super.preamble();
        this.declare({
            category: property({
                type: PartyCategory.TYPE, icon: "far-crow",
                validator: this.validators.validateCategory
            }),
            birthday: property({
                type: LocalDate.TYPE, icon: "fa-baby",
                validator: this.validators.validateBirthday
            }),
            locale: property({
                type: STRING, icon: "far-globe",
                validator: this.validators.validateLocale
            }),
            timeZoneId: property({
                type: STRING, icon: "fa-map",
                validator: this.validators.validateTimeZoneId
            }),
            peer: property({
                type: BOOLEAN, nullable: false, icon: "fa-user-group",
            }),
            customer: property({
                type: BOOLEAN, nullable: false, icon: "fa-hand-holding-dollar",
            }),
            supplier: property({
                type: BOOLEAN, nullable: false, icon: "far-industry",
            }),
            tags: property({
                type: ARRAY(STRING), icon: "fa-tags",
                validator: this.validators.validateTags
            }),
            subject: property({
                type: STRING, icon: "fa-quote-left",
                validator: this.validators.validateSubject
            }),
            contacts: property({
                type: ARRAY(Contact.TYPE), icon: "far-address-book",
                validator: this.validators.validateContacts
            }),
            bank: property({
                type: STRING, icon: "far-building-columns",
                validator: this.validators.validateBank
            }),
            account: property({
                type: STRING, icon: "far-id-card",
                validator: this.validators.validateAccount
            }),
            bankCount: property({
                type: INT, icon: "far-hashtag",
            }),
            roleCount: property({
                type: INT, icon: "far-hashtag",
            }),

            // person properties, list here just to define the default icon.
            employee: property({ type: BOOLEAN, icon: "far-user-tie", }),
            gender: property({ type: CHAR, icon: "far-transgender", }),
            pronoun: property({ type: STRING, icon: "far-genderless", }),
            sexualOrientation: property({ type: STRING, icon: "far-venus-mars", }),
            homeland: property({ type: STRING, icon: "far-home", }),
            passport: property({ type: STRING, icon: "far-passport", }),
            ssn: property({ type: STRING, icon: "far-id-card", }),
            dln: property({ type: STRING, icon: "far-id-card", }),
            mother: property({ type: this, icon: "far-female", }),
            father: property({ type: this, icon: "far-male", }),
        });
    }

    static readonly INSTANCE = new PartyTypeInfo();

}

export default PartyTypeInfo;
export const Party_TYPE = PartyTypeInfo.INSTANCE;

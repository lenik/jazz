import { BOOLEAN, INT, STRING } from '@skeljs/core/src/lang/baseinfo';
import { property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import Gender from '../../src/net/bodz/lily/schema/contact/Gender';

import PartyTypeInfo from './PartyTypeInfo';
import PersonValidators from './PersonValidators';
import Person from './Person';

// Type Info

export class PersonTypeInfo extends PartyTypeInfo {

    get name() { return "net.bodz.lily.schema.contact.Person"; }
    get icon() { return "fa-user"; }
    get label() { return "Contact Person"; }
    get description() { return "A human being regarded as an individual."; }

    validators = new PersonValidators(this);

    override create() {
        return new Person();
    }

    override preamble() {
        super.preamble();
        this.declare({
            label: property({ type: STRING, icon: "fa-user", label: 'Name' }),

            gender: property({ type: Gender.TYPE, icon: "fa-venus-mars", label: 'Gender', validator: this.validators.validateGender }),
            father: property({ type: this, icon: "fa-male", validator: this.validators.validateFather }),
            mother: property({ type: this, icon: "fa-female", validator: this.validators.validateMother }),

            roleCount: property({ type: INT }),
            employee: property({ type: BOOLEAN }),
            bankCount: property({ type: STRING }),
            homeland: property({ type: STRING, validator: this.validators.validateHomeland }),
            passport: property({ type: STRING, validator: this.validators.validatePassport }),
            ssn: property({ type: STRING, icon: "far-address-card", label: 'Social Security Number', validator: this.validators.validateSsn }),
            dln: property({ type: STRING, icon: "far-car", label: "Driver License Number", validator: this.validators.validateDln }),
        });
    }

    constructor() {
        super();
    }

    static readonly INSTANCE = new PersonTypeInfo();

}

export default PersonTypeInfo;
export const Person_TYPE = PersonTypeInfo.INSTANCE;

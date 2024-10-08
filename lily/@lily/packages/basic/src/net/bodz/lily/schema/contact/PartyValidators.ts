import { Moment } from 'moment-timezone';
import CoImagedValidators from '../../concrete/CoImagedValidators';
import type PartyTypeInfo from './PartyTypeInfo';
import type PartyCategory from "./PartyCategory";
import type Contact from "./Contact";

export class PartyValidators extends CoImagedValidators {

    constructor(type: PartyTypeInfo) {
        super(type);
    }

    validateCategory(val: PartyCategory) { }

    validateBirthday(val: Moment) { }

    validateLocale(val: string) { }

    validateTimeZoneId(val: string) { }

    validateTags(val: string[]) { }

    validateSubject(val: string) { }

    validateContacts(val: Contact[]) { }

    validateBank(val: string) { }

    validateAccount(val: string) { }

}


export default PartyValidators;

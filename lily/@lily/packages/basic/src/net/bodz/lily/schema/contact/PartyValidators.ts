import { Moment } from 'moment';

import IdEntityValidators from "../../concrete/IdEntityValidators";

import Contact from "./Contact";
import PartyCategory from "./PartyCategory";

export class PartyValidators extends IdEntityValidators {

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

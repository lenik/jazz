import { Moment } from 'moment';

import { IdEntityValidators } from '@skeljs/dba/src/net/bodz/lily/concrete/IdEntityValidators';

export abstract class PartyValidators extends IdEntityValidators {

    validateCategory(val: any) { }

    validateBirthday(val: Moment) { }

    validateLocale(val: string) { }

    validateTimeZoneId(val: string) { }

    validateTags(val: string[]) { }

    validateSubject(val: string) { }

    validateContacts(val: any[]) { }

    validateBank(val: string) { }

    validateAccount(val: string) { }


}

export default PartyValidators;

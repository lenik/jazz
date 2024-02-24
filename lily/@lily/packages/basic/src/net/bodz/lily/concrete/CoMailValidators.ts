import { } from '@skeljs/dba/src/net/bodz/lily/entity';
import CoMessageValidators from './CoMessageValidators';

export class CoMailValidators extends CoMessageValidators {

    validateRecipient(val: User) { }

    validateRecipients(val: User[]) { }

    validateBcc(val: User[]) { }

}

export default CoMailValidators;

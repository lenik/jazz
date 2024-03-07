import { IEntityType } from '@skeljs/dba/src/net/bodz/lily/entity/IEntityType';
import CoMessageValidators from './CoMessageValidators';
import User from '../schema/account/User';
import CoMailTypeInfo from './CoMailTypeInfo';

export class CoMailValidators extends CoMessageValidators {

    constructor(type: CoMailTypeInfo) {
        super(type);
    }

    get type(): CoMailTypeInfo {
        return this._type as CoMailTypeInfo;
    }

    validateRecipient(val: User) { }

    validateRecipients(val: User[]) { }

    validateBcc(val: User[]) { }

}

export default CoMailValidators;

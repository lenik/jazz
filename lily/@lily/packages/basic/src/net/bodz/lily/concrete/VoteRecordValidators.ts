import { IEntityType } from '@skeljs/dba/src/net/bodz/lily/entity/IEntityType';
import IdEntityValidators from './IdEntityValidators';
import User from '../schema/account/User';
import VoteRecordTypeInfo from './VoteRecordTypeInfo';

export class VoteRecordValidators extends IdEntityValidators {

    constructor(type: VoteRecordTypeInfo) {
        super(type);
    }

    get type(): VoteRecordTypeInfo {
        return this._type as VoteRecordTypeInfo;
    }

    validateUser(val: User) { }

    validateVoteCount(val: number) { }

}

export default VoteRecordValidators;
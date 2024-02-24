import { } from '@skeljs/dba/src/net/bodz/lily/entity';
import IdEntityValidators from '@skeljs/dba/src/net/bodz/lily/concrete/IdEntityValidators';

export class VoteRecordValidators extends IdEntityValidators {

    validateUser(val: any) { }
    validateVoteCount(val: number) { }

}

export default VoteRecordValidators;
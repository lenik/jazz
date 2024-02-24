import { } from '@skeljs/dba/src/net/bodz/lily/entity';
import IdEntityValidators from '@skeljs/dba/src/net/bodz/lily/concrete/IdEntityValidators';

export class BackrefRecordValidators extends IdEntityValidators {

    validateUser(val: any) { }
    validateVoteCount(val: number) { }

}

export default BackrefRecordValidators;
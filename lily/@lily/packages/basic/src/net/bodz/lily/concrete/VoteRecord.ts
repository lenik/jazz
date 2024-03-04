import { long } from '@skeljs/core/src/lang/basetype';
import IdEntity from './IdEntity';
import VoteRecordTypeInfo from './VoteRecordTypeInfo';
import User from '../schema/account/User';

export abstract class VoteRecord<This> extends IdEntity<long> {
    static TYPE = new VoteRecordTypeInfo();
    
    user: User
    voteCount: long
    
    constructor(o: any) {
        super(o);
    }
}

export default VoteRecord;
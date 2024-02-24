import { long } from '@skeljs/core/src/lang/type';
import IdEntity from '@skeljs/dba/src/net/bodz/lily/concrete/IdEntity';
import VoteRecordTypeInfo from './VoteRecordTypeInfo';

export abstract class VoteRecord<This> extends IdEntity<long> {
    static TYPE = new VoteRecordTypeInfo();
    
    user: any
    voteCount: long
    
    constructor(o: any) {
        super(o);
    }
}

export default VoteRecord;
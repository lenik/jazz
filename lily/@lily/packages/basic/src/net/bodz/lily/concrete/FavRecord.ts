import { long } from '@skeljs/core/src/lang/basetype';
import IdEntity from './IdEntity';
import FavRecordTypeInfo from './FavRecordTypeInfo';

export abstract class FavRecord<This> extends IdEntity<long> {

    static readonly TYPE = FavRecordTypeInfo.INSTANCE;

    user: any
    voteCount: long

    constructor(o: any) {
        super(o);
    }
}

export default FavRecord;
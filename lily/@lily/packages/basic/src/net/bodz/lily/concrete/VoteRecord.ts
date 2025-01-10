import { long } from 'skel01-core/src/lang/basetype';
import IdEntity from './IdEntity';
import VoteRecordTypeInfo from './VoteRecordTypeInfo';
import User from '../schema/account/User';

export abstract class VoteRecord<This> extends IdEntity<long> {

    static _typeInfo: VoteRecordTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = VoteRecordTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    user: User
    voteCount: long

    constructor(o?: any) {
        super(o);
    }
}

export default VoteRecord;
import { long } from 'skel01-core/src/lang/basetype';
import IdEntity from './IdEntity';
import FavRecordTypeInfo from './FavRecordTypeInfo';

export abstract class FavRecord extends IdEntity<long> {

    static _typeInfo: FavRecordTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = FavRecordTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    user: any
    voteCount: long

    constructor(o: any) {
        super(o);
    }
}

export default FavRecord;
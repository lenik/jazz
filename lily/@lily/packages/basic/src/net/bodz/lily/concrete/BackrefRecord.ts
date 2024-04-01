import { long } from '@skeljs/core/src/lang/basetype';
import IdEntity from './IdEntity';
import BackrefRecordTypeInfo from './BackrefRecordTypeInfo';

export abstract class BackrefRecord<This> extends IdEntity<long> {

    static _typeInfo: BackrefRecordTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = BackrefRecordTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    site: string

    constructor(o: any) {
        super(o);
    }
}

export default BackrefRecord;
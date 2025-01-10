import { Moment } from "moment-timezone";
import { int, long } from 'skel01-core/src/lang/basetype';
import DocRecordTypeInfo from './DocRecordTypeInfo';
import CoMessage from './CoMessage';

export abstract class DocRecord extends CoMessage<long> {

    static _typeInfo: DocRecordTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = DocRecordTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
    }
}

export default DocRecord;
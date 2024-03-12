import { Moment } from "moment-timezone";
import { int, long } from '@skeljs/core/src/lang/basetype';
import DocRecordTypeInfo from './DocRecordTypeInfo';
import CoMessage from './CoMessage';

export abstract class DocRecord extends CoMessage<long> {

    static readonly TYPE = DocRecordTypeInfo.INSTANCE;

    constructor(o: any) {
        super(o);
    }
}

export default DocRecord;
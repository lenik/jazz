import { Moment } from 'moment';
import { integer, long } from '@skeljs/core/src/lang/type';
import DocRecordTypeInfo from './DocRecordTypeInfo';
import CoMessage from './CoMessage';

export abstract class DocRecord extends CoMessage<long> {
    static TYPE = new DocRecordTypeInfo();

    constructor(o: any) {
        super(o);
    }
}

export default DocRecord;
import { long } from '@skeljs/core/src/lang/basetype';
import IdEntity from './IdEntity';
import BackrefRecordTypeInfo from './BackrefRecordTypeInfo';

export abstract class BackrefRecord<This> extends IdEntity<long> {
    static TYPE = new BackrefRecordTypeInfo();
    
    site: string
    
    constructor(o: any) {
        super(o);
    }
}

export default BackrefRecord;
import { long } from '@skeljs/core/src/lang/type';
import IdEntity from '@skeljs/dba/src/net/bodz/lily/concrete/IdEntity';
import BackrefRecordTypeInfo from './BackrefRecordTypeInfo';

export abstract class BackrefRecord<This> extends IdEntity<long> {
    static TYPE = new BackrefRecordTypeInfo();
    
    site: string
    
    constructor(o: any) {
        super(o);
    }
}

export default BackrefRecord;
import { IEntityType } from '@skeljs/dba/src/net/bodz/lily/entity';
import IdEntityValidators from './IdEntityValidators';
import BackrefRecordTypeInfo from './BackrefRecordTypeInfo';

export class BackrefRecordValidators extends IdEntityValidators {

    constructor(type: BackrefRecordTypeInfo) {
        super(type);
    }

    get type(): BackrefRecordTypeInfo {
        return this._type as BackrefRecordTypeInfo;
    }

    validateUser(val: any) { }

    validateSite(val: string) { }

}

export default BackrefRecordValidators;
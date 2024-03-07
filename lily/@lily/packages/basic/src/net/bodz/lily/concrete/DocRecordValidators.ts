import { IEntityType } from '@skeljs/dba/src/net/bodz/lily/entity/IEntityType';
import CoMessageValidators from './CoMessageValidators';
import DocRecordTypeInfo from './DocRecordTypeInfo';

export class DocRecordValidators extends CoMessageValidators {

    constructor(type: DocRecordTypeInfo) {
        super(type);
    }

    get type(): DocRecordTypeInfo {
        return this._type as DocRecordTypeInfo;
    }

}

export default DocRecordValidators;

import { IEntityType } from '@skeljs/dba/src/net/bodz/lily/entity/IEntityType';
import CoNodeValidators from './CoNodeValidators';
import CoCategoryTypeInfo from './CoCategoryTypeInfo';

export class CoCategoryValidators extends CoNodeValidators {

    constructor(type: CoCategoryTypeInfo) {
        super(type);
    }

    get type(): CoCategoryTypeInfo {
        return this._type as CoCategoryTypeInfo;
    }

}

export default CoCategoryValidators;

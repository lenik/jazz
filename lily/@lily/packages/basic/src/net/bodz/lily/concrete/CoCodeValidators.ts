import { IEntityType } from 'skel01-dba/src/net/bodz/lily/entity/IEntityType';
import CoNodeValidators from './CoNodeValidators';
import CoCodeTypeInfo from './CoCodeTypeInfo';

export class CoCodeValidators extends CoNodeValidators {

    constructor(type: CoCodeTypeInfo) {
        super(type);
    }

    get type(): CoCodeTypeInfo {
        return this._type as CoCodeTypeInfo;
    }

    validateCode(val: string) { }

}

export default CoCodeValidators;
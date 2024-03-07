
import { IEntityType } from '@skeljs/dba/src/net/bodz/lily/entity/IEntityType';
import CoCodeValidators from './CoCodeValidators';
import CoParameterTypeInfo from './CoParameterTypeInfo';

export class CoParameterValidators extends CoCodeValidators {

    constructor(type: CoParameterTypeInfo) {
        super(type);
    }

    get type(): CoParameterTypeInfo {
        return this._type as CoParameterTypeInfo;
    }

}

export default CoParameterValidators;
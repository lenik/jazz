import CoCodeValidators from './CoCodeValidators';
import CoParameterTypeInfo from './CoParameterTypeInfo';
import UomRow from '../schema/util/UomRow';

export class CoParameterValidators extends CoCodeValidators {

    constructor(type: CoParameterTypeInfo) {
        super(type);
    }

    get type(): CoParameterTypeInfo {
        return this._type as CoParameterTypeInfo;
    }

    validateType(val: string) { }

    validateUom(val: UomRow) { }

    validateValues(val: string) { }

}

export default CoParameterValidators;

import { IEntityType } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoCodeValidators from './CoCodeValidators';
import CoPhaseTypeInfo from './CoPhaseTypeInfo';

export class CoPhaseValidators extends CoCodeValidators {

    constructor(type: CoPhaseTypeInfo) {
        super(type);
    }

    get type(): CoPhaseTypeInfo {
        return this._type as CoPhaseTypeInfo;
    }

}

export default CoPhaseValidators;
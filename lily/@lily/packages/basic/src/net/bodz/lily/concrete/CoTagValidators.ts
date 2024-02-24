import { IEntityType } from '@skeljs/dba/src/net/bodz/lily/entity';
import CoCodeValidators from './CoCodeValidators';
import CoTagTypeInfo from './CoTagTypeInfo';

export class CoTagValidators extends CoCodeValidators {

    constructor(type: CoTagTypeInfo) {
        super(type);
    }

    get type(): CoTagTypeInfo {
        return this._type as CoTagTypeInfo;
    }

}

export default CoTagValidators;
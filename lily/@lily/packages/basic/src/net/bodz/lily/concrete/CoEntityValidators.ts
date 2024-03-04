
import { IEntityType } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoObjectValidators from './CoObjectValidators';
import CoEntityTypeInfo from './CoEntityTypeInfo';

export class CoEntityValidators extends CoObjectValidators {

    constructor(type: CoEntityTypeInfo) {
        super(type);
    }

    get type(): CoEntityTypeInfo {
        return this._type as CoEntityTypeInfo;
    }

}

export default CoEntityValidators;
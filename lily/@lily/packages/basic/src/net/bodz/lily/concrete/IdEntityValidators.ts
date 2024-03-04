
import { IEntityType } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import { CoObjectValidators } from './CoObjectValidators';

export class IdEntityValidators extends CoObjectValidators {

    constructor(type: IEntityType) {
        super(type);
    }

    validateId(val: any) {
    }

}

export default IdEntityValidators;

import { IEntityType } from 'skel01-dba/src/net/bodz/lily/entity/IEntityType';
import { CoObjectValidators } from './CoObjectValidators';
import IdEntityTypeInfo from './IdEntityTypeInfo';

export class IdEntityValidators extends CoObjectValidators {

    constructor(type: IdEntityTypeInfo) {
        super(type);
    }

    validateId(val: any) {
    }

}

export default IdEntityValidators;
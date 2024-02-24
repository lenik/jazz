import { integer } from '@skeljs/core/src/lang/type';
import { IEntityType } from '@skeljs/dba/src/net/bodz/lily/entity';
import IdEntityValidators from './IdEntityValidators';
import CoNodeTypeInfo from './CoNodeTypeInfo';

export class CoNodeValidators extends IdEntityValidators {

    constructor(type: CoNodeTypeInfo) {
        super(type);
    }

    get type(): CoNodeTypeInfo {
        return this._type as CoNodeTypeInfo;
    }

    validateParent(val: integer) {

    }

    validateRefCount(val: integer) {

    }

    validateDepth(val: integer) {

    }

}

export default CoNodeValidators;
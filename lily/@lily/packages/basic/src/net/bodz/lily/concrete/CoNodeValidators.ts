import { int } from '@skeljs/core/src/lang/basetype';
import { IEntityType } from '@skeljs/dba/src/net/bodz/lily/entity/IEntityType';
import IdEntityValidators from './IdEntityValidators';
import CoNodeTypeInfo from './CoNodeTypeInfo';

export class CoNodeValidators extends IdEntityValidators {

    constructor(type: CoNodeTypeInfo) {
        super(type);
    }

    get type(): CoNodeTypeInfo {
        return this._type as CoNodeTypeInfo;
    }

    validateParent(val: int) {

    }

    validateRefCount(val: int) {

    }

    validateDepth(val: int) {

    }

}

export default CoNodeValidators;
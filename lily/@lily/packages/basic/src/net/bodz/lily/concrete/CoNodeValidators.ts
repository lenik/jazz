import { int } from 'skel01-core/src/lang/basetype';
import { IEntityType } from 'skel01-dba/src/net/bodz/lily/entity/IEntityType';
import IdEntityValidators from './IdEntityValidators';
import CoNodeTypeInfo from './CoNodeTypeInfo';
import { JsonVariant } from 'skel01-core/src/lang/bas-type';

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

    validateProperties(val: JsonVariant) {

    }

}

export default CoNodeValidators;
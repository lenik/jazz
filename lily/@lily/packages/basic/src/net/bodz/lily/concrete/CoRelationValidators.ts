import { IEntityType } from 'skel01-dba/src/net/bodz/lily/entity/IEntityType';
import IdEntityValidators from './IdEntityValidators';
import CoRelationTypeInfo from './CoRelationTypeInfo';

export class CoRelationValidators extends IdEntityValidators {

    constructor(type: CoRelationTypeInfo) {
        super(type);
    }

    get type(): CoRelationTypeInfo {
        return this._type as CoRelationTypeInfo;
    }

}

export default CoRelationValidators;
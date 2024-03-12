import { int } from '@skeljs/core/src/lang/basetype';
import { IEntityType } from '@skeljs/dba/src/net/bodz/lily/entity/IEntityType';
import StructRowValidators from './StructRowValidators';
import CoObjectTypeInfo from './CoObjectTypeInfo';
import CoObject from './CoObject';

export class CoObjectValidators extends StructRowValidators {

    constructor(type: CoObjectTypeInfo) {
        super(type);
    }

    get type(): CoObjectTypeInfo {
        return this._type as CoObjectTypeInfo;
    }

    validate(val: CoObject) {

    }

    validateLabel(val: string) {

    }

    validateDescription(val: string) {

    }

    validateIcon(val: string) {

    }

    validateFlags(val: int) {

    }

    validatePriority(val: int) {

    }

    validateState(val: int) {

    }

    validateOwnerUser(val: string) {

    }

    validateOwnerGroup(val: string) {

    }

    validateAcl(val: string) {

    }

    validateAccessMode(val: string) {

    }

}

export default CoObjectValidators;
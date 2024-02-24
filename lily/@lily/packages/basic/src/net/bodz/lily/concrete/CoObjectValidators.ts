import { integer } from '@skeljs/core/src/lang/type';
import { IEntityType } from '@skeljs/dba/src/net/bodz/lily/entity';
import StructRowValidators from './StructRowValidators';
import CoObjectTypeInfo from './CoObjectTypeInfo';

export class CoObjectValidators extends StructRowValidators {

    constructor(type: CoObjectTypeInfo) {
        super(type);
    }

    get type(): CoObjectTypeInfo {
        return this._type as CoObjectTypeInfo;
    }

    validateLabel(val: string) {

    }

    validateDescription(val: string) {

    }

    validateComment(val: string) {

    }

    validateImage(val: string) {

    }

    validateImageAlt(val: string) {

    }

    validateFlags(val: integer) {

    }

    validatePriority(val: integer) {

    }

    validateState(val: integer) {

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
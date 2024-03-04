import { int } from '@skeljs/core/src/lang/basetype';
import { IEntityType } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import { Moment } from "moment-timezone";
import StructRowTypeInfo from './StructRowTypeInfo';

export class StructRowValidators {

    _type: IEntityType

    constructor(type: StructRowTypeInfo) {
        this._type = type;
    }

    get type(): StructRowTypeInfo {
        return this._type as StructRowTypeInfo;
    }

    validateCreationDate(val: Moment) {

    }

    validateLastModifiedDate(val: Moment) {

    }

    validateVersion(val: int) {

    }

}

export default StructRowValidators;
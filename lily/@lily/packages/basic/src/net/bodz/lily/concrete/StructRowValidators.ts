import { int } from '@skeljs/core/src/lang/basetype';
import { IEntityType } from '@skeljs/dba/src/net/bodz/lily/entity/IEntityType';
import StructRowTypeInfo from './StructRowTypeInfo';
import { OffsetDateTime } from '@skeljs/core/src/lang/time';

export class StructRowValidators {

    _type: IEntityType

    constructor(type: StructRowTypeInfo) {
        this._type = type;
    }

    get type(): StructRowTypeInfo {
        return this._type as StructRowTypeInfo;
    }

    validateCreationDate(val: OffsetDateTime) {

    }

    validateLastModified(val: OffsetDateTime) {

    }

    validateVersion(val: int) {

    }

}

export default StructRowValidators;
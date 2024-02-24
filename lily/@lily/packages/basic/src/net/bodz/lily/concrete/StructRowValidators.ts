import { integer } from '@skeljs/core/src/lang/type';
import { IEntityType } from '@skeljs/dba/src/net/bodz/lily/entity';
import { Moment } from 'moment';
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

    validateVersion(val: integer) {

    }

}

export default StructRowValidators;
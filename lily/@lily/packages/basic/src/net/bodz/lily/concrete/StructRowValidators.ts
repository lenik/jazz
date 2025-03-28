import type { int } from 'skel01-core/src/lang/basetype';
import type { IEntityType } from 'skel01-dba/src/net/bodz/lily/entity/IEntityType';
import StructRowTypeInfo from './StructRowTypeInfo';
import { OffsetDateTime } from 'skel01-core/src/lang/time';

const TYPE = Symbol('type');

export class StructRowValidators {

    constructor(type: StructRowTypeInfo) {
        this[TYPE] = type;
    }

    get _type(): StructRowTypeInfo {
        return this[TYPE] as StructRowTypeInfo;
    }

    get type(): StructRowTypeInfo {
        return this[TYPE] as StructRowTypeInfo;
    }

    validateCreationDate(val: OffsetDateTime) {

    }

    validateLastModified(val: OffsetDateTime) {

    }

    validateVersion(val: int) {

    }

}

export default StructRowValidators;
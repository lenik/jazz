import { int } from '@skeljs/core/src/lang/basetype';
import IdEntity from './IdEntity';
import CoEventTypeInfo from './CoEventTypeInfo';
import { OffsetDateTime } from "@skeljs/core/src/lang/time";

export abstract class CoEvent<Id> extends IdEntity<Id> {

    static _typeInfo: CoEventTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CoEventTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    beginTime?: OffsetDateTime
    endTime?: OffsetDateTime
    year?: int

    constructor(o: any) {
        super(o);
    }
}

export default CoEvent;
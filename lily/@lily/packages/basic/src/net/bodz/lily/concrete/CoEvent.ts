import { int } from '@skeljs/core/src/lang/basetype';
import IdEntity from './IdEntity';
import CoEventTypeInfo from './CoEventTypeInfo';
import { OffsetDateTime } from "@skeljs/core/src/lang/time";

export abstract class CoEvent<Id> extends IdEntity<Id> {

    static readonly TYPE = CoEventTypeInfo.INSTANCE;

    beginTime?: OffsetDateTime
    endTime?: OffsetDateTime
    year?: int

    constructor(o: any) {
        super(o);
    }
}

export default CoEvent;
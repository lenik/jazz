import { int } from '@skeljs/core/src/lang/basetype';
import IdEntity from './IdEntity';
import CoEventTypeInfo from './CoEventTypeInfo';
import { ZonedDateTime } from "@skeljs/core/src/lang/time";

export abstract class CoEvent<Id> extends IdEntity<Id> {

    static readonly TYPE = CoEventTypeInfo.INSTANCE;

    beginTime?: ZonedDateTime
    endTime?: ZonedDateTime
    year?: int

    constructor(o: any) {
        super(o);
    }
}

export default CoEvent;
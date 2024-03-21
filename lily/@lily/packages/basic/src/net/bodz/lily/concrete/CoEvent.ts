import { Moment } from "moment-timezone";
import { int } from '@skeljs/core/src/lang/basetype';
import IdEntity from './IdEntity';
import CoEventTypeInfo from './CoEventTypeInfo';

export abstract class CoEvent<Id> extends IdEntity<Id> {
    
    static readonly TYPE = CoEventTypeInfo.INSTANCE;

    beginTime?: Moment
    endTime?: Moment
    year: int
    
    constructor(o: any) {
        super(o);
    }
}

export default CoEvent;
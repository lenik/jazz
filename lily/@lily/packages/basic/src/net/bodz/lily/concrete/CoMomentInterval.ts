import { Moment } from "moment-timezone";
import { int } from '@skeljs/core/src/lang/basetype';
import IdEntity from './IdEntity';
import CoMomentIntervalTypeInfo from './CoMomentIntervalTypeInfo';

export abstract class CoMomentInterval<Id> extends IdEntity<Id> {
    
    beginTime?: Moment
    endTime?: Moment
    year: int
    
    constructor(o: any) {
        super(o);
    }
}

export default CoMomentInterval;
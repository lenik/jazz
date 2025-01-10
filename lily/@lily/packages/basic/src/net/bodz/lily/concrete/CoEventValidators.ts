import { Moment } from "moment-timezone";
import { IEntityType } from 'skel01-dba/src/net/bodz/lily/entity/IEntityType';
import IdEntityValidators from './IdEntityValidators';
import CoEventTypeInfo from './CoEventTypeInfo';

export class CoEventValidators extends IdEntityValidators {

    constructor(type: CoEventTypeInfo) {
        super(type);
    }

    get type(): CoEventTypeInfo {
        return this._type as CoEventTypeInfo;
    }

    validateBeginTime(val: Moment) { }

    validateEndTime(val: Moment) { }

}

export default CoEventValidators;

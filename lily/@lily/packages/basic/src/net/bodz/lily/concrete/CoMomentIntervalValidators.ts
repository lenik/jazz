import { Moment } from 'moment';
import { IEntityType } from '@skeljs/dba/src/net/bodz/lily/entity';
import IdEntityValidators from './IdEntityValidators';
import CoMomentIntervalTypeInfo from './CoMomentIntervalTypeInfo';

export class CoMomentIntervalValidators extends IdEntityValidators {

    constructor(type: CoMomentIntervalTypeInfo) {
        super(type);
    }

    get type(): CoMomentIntervalTypeInfo {
        return this._type as CoMomentIntervalTypeInfo;
    }

    validateBeginTime(val: Moment) { }

    validateEndTime(val: Moment) { }

}

export default CoMomentIntervalValidators;

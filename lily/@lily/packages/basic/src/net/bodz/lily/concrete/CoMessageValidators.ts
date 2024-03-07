import { Moment } from "moment-timezone";
import { IEntityType } from '@skeljs/dba/src/net/bodz/lily/entity/IEntityType';
import CoMomentIntervalValidators from './CoMomentIntervalValidators';
import User from '../schema/account/User';
import FormDef from '../schema/meta/FormDef';
import CoMessageTypeInfo from './CoMessageTypeInfo';

export class CoMessageValidators extends CoMomentIntervalValidators {

    constructor(type: CoMessageTypeInfo) {
        super(type);
    }

    get type(): CoMessageTypeInfo {
        return this._type as CoMessageTypeInfo;
    }

    validateOp(val: User) { }

    validateSubject(val: string) { }

    validateRaw(valText: string) { }

    validateForm(val: FormDef) { }

    validateParameters(val: any) { }

    // validateClick(valInfo: UserClickInfo) { }

    validateSent(valTime: Moment) { }

    validateReceived(valTime: Moment) { }

}

export default CoMessageValidators;

import { Moment } from "moment-timezone";
import { IEntityType } from 'skel01-dba/src/net/bodz/lily/entity/IEntityType';
import CoEventValidators from './CoEventValidators';
import User from '../schema/account/User';
import FormDef from '../schema/meta/FormDef';
import CoMessageTypeInfo from './CoMessageTypeInfo';

export class CoMessageValidators extends CoEventValidators {

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

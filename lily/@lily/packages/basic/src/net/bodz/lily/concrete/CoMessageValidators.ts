import { Moment } from 'moment';
import { } from '@skeljs/dba/src/net/bodz/lily/entity';
import CoMomentIntervalValidators from '@skeljs/dba/src/net/bodz/lily/concrete/CoMomentIntervalValidators';
import User from '../schema/account/User';
import FormDef from '../schema/meta/FormDef';

export class CoMessageValidators extends CoMomentIntervalValidators {

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

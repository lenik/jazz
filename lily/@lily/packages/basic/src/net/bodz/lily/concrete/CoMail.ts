import { Moment } from "moment-timezone";
import { int, long } from '@skeljs/core/src/lang/basetype';
import CoMailTypeInfo from './CoMailTypeInfo';
import CoMessage from './CoMessage';
import User from '../schema/account/User';

export abstract class CoMail extends CoMessage<long> {
    static TYPE = new CoMailTypeInfo();

    recipient?: User
    recipients: User[] = []
    bcc: User[] = []

    read: boolean

    constructor(o: any) {
        super(o);
    }
}

export default CoMail;
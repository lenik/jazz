import moment from "moment-timezone";
import { Moment } from "moment-timezone";

import { int } from '@skeljs/core/src/lang/basetype';

import StructRow from './StructRow';
import CoObjectTypeInfo from './CoObjectTypeInfo';
import User from '../schema/account/User';
import Group from '../schema/account/Group';

export abstract class CoObject extends StructRow {

    static readonly TYPE = CoObjectTypeInfo.INSTANCE;

    // UI

    label?: string
    description?: string
    icon?: string

    // state

    flags: int = 0
    priority: int = 0
    state: int = 0

    // access control

    ownerUser: User
    ownerUserId: int
    ownerGroup: Group
    owenrGroupId: int
    acl: int
    accessMode: int

    constructor(o: any) {
        super(o);
    }
}

export default CoObject;

import moment from "moment-timezone";
import type { Moment } from "moment-timezone";

import type { int } from 'skel01-core/src/lang/basetype';

import StructRow from './StructRow';
import CoObjectTypeInfo from './CoObjectTypeInfo';
import User from '../schema/account/User';
import Group from '../schema/account/Group';
import Attachment from "skel01-core/src/net/bodz/lily/entity/Attachment";
import IAttachment from "skel01-core/src/net/bodz/lily/entity/IAttachment";

export abstract class CoObject extends StructRow {

    static _typeInfo: CoObjectTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CoObjectTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    //

    name?: string
    properties?: any
    files?: any

    get images() {
        this.files ||= {};
        return (this.files['images'] ||= []) as IAttachment[];
    }
    get videos() {
        this.files ||= {};
        return (this.files['videos'] ||= []) as IAttachment[];
    }
    get docs() {
        this.files ||= {};
        return (this.files['docs'] ||= []) as IAttachment[];
    }

    // UI

    label?: string;
    description?: string
    icon?: string

    // state

    flags: int = 0
    priority: int = 0
    state: int = 0

    // access control

    ownerUser?: User
    private _ownerUserId?: int
    get ownerUserId() {
        return this.ownerUser != null ? this.ownerUser.id : this._ownerUserId;
    }
    set ownerUserId(val: int | undefined) {
        this.ownerUserId = val;
    }

    ownerGroup?: Group
    private _ownerGroupId?: int
    get ownerGroupId() {
        return this.ownerGroup != null ? this.ownerGroup.id : this._ownerGroupId;
    }
    set ownerGroupId(val: int | undefined) {
        this.ownerGroupId = val;
    }

    acl: int = 0
    accessMode: int = 0

    constructor(o: any) {
        super(o);
    }
}

export default CoObject;

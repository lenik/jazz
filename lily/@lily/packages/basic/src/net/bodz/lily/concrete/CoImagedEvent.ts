import { int } from 'skel01-core/src/lang/basetype';
import Attachment, { TYPE_IMAGE, TYPE_VIDEO } from 'skel01-core/src/net/bodz/lily/entity/Attachment';
import AttachmentManifest from 'skel01-core/src/net/bodz/lily/entity/AttachmentManifest';
import CoEvent from './CoEvent';
import CoImagedEventTypeInfo from './CoImagedEventTypeInfo';

export abstract class CoImagedEvent<Id> extends CoEvent<Id> {

    static _typeInfo: CoImagedEventTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CoImagedEventTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
    }

    get images(): Attachment[] {
        return this.files?.filter(TYPE_IMAGE) || [];
    }

    set images(val: Attachment[]) {
        this.createFiles().filter(TYPE_IMAGE, val);
    }

    get image(): Attachment | undefined {
        return this.files?.find(0, TYPE_IMAGE);
    }

    set image(val: Attachment | undefined) {
        if (val == null) {
            this.images = [];
        } else {
            this.createFiles().find(0, TYPE_IMAGE, val, true);
        }
    }

    get videos(): Attachment[] {
        return this.files?.filter(TYPE_VIDEO) || [];
    }

    set videos(val: Attachment[]) {
        this.createFiles().filter(TYPE_VIDEO, val);
    }

    get video(): Attachment | undefined {
        return this.files?.find(0, TYPE_VIDEO);
    }

    set video(val: Attachment | undefined) {
        if (val == null) {
            this.videos = [];
        } else {
            this.createFiles().find(0, TYPE_VIDEO, val, true);
        }
    }

}

export default CoImagedEvent;
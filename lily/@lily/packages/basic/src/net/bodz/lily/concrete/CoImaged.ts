// import type { int } from 'skel01-core/src/lang/basetype';
import IdEntity from './IdEntity';
import Attachment, { TYPE_IMAGE, TYPE_VIDEO } from 'skel01-core/src/net/bodz/lily/entity/Attachment';
import AttachmentManifest from 'skel01-core/src/net/bodz/lily/entity/AttachmentManifest';
import CoImagedTypeInfo from './CoImagedTypeInfo';

export abstract class CoImaged<Id> extends IdEntity<Id> {

    static _typeInfo: CoImagedTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CoImagedTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
    }

    get images(): Attachment[] {
        return this.files?.filter(TYPE_IMAGE) || [];
    }

    set images(val: Attachment[]) {
        this.attachmentManifest.filter(TYPE_IMAGE, val);
    }

    get image(): Attachment | undefined {
        return this.files?.find(0, TYPE_IMAGE);
    }

    set image(val: Attachment | undefined) {
        if (val == null) {
            this.images = [];
        } else {
            this.attachmentManifest.find(0, TYPE_IMAGE, val, true);
        }
    }

    get videos(): Attachment[] {
        return this.files?.filter(TYPE_VIDEO) || [];
    }

    set videos(val: Attachment[]) {
        this.attachmentManifest.filter(TYPE_VIDEO, val);
    }

    get video(): Attachment | undefined {
        return this.files?.find(0, TYPE_VIDEO);
    }

    set video(val: Attachment | undefined) {
        if (val == null) {
            this.videos = [];
        } else {
            this.attachmentManifest.find(0, TYPE_VIDEO, val, true);
        }
    }

}

export default CoImaged;
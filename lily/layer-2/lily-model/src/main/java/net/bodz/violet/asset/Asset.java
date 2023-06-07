package net.bodz.violet.asset;

import javax.persistence.Table;

import net.bodz.bas.site.file.UploadHint;

@UploadHint
@Table(schema = "violet", name = "asset")
public class Asset
        extends _Asset_stuff {

    private static final long serialVersionUID = 1L;

}

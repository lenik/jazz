package net.bodz.violet.schema.art;

import javax.persistence.Table;

@Table(schema = ArtifactCategory.SCHEMA_NAME, name = ArtifactCategory.TABLE_NAME)
public class ArtifactCategory
        extends _ArtifactCategory_stuff<ArtifactCategory> {

    private static final long serialVersionUID = 1L;

}

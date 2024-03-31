#\import lily.security

column-property {
    art:                artifactId
    validsince:         validSince
    validuntil:         validUntil
}

column-type {
}

class-map {
    net.bodz.lily.concrete.CoCategory: \
        artcat
    net.bodz.lily.concrete.CoImaged: \
        art
    net.bodz.lily.concrete.CoImagedEvent: \
        artmodel
    net.bodz.lily.concrete.CoParameter: \
        artparm
    net.bodz.lily.concrete.CoPhase: \
        artphase
    net.bodz.lily.concrete.CoTag: \
        arttag
    net.bodz.lily.concrete.BackrefRecord: \
        art_backref
    net.bodz.lily.concrete.DocRecord: \
        art_doc
    net.bodz.lily.concrete.VoteRecord: \
        art_vote
}

table-name {
    art:                net.bodz.violet.schema.art.Artifact
    artcat:             net.bodz.violet.schema.art.ArtifactCategory
    artmodel:           net.bodz.violet.schema.art.ArtifactModel
    artparm:            net.bodz.violet.schema.art.ArtifactParameter
    artphase:           net.bodz.violet.schema.art.ArtifactPhase
    arttag:             net.bodz.violet.schema.art.ArtifactTag
    art_backref:        net.bodz.violet.schema.art.ArtifactBackref
    art_doc:            net.bodz.violet.schema.art.ArtifactDoc
    art_vote:           net.bodz.violet.schema.art.ArtifactVote
    artpack:            net.bodz.violet.schema.art.Packaging
}

table art {
    column sku {
        javaName: skuCode
    }
    column barcode {
        javaName: barCode
    }
    column rfid {
        javaName: rfidCode
    }
    column model {
        javaName: modelName
    }
}

table artmodel {
    column model {
        javaName: modelName
    }
}


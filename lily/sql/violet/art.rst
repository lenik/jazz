#\import lily.security

column-property {
    art:                artifactId
    validsince:         validSince
    validuntil:         validUntil
}

column-type {
    validsince:         java.time.ZonedDateTime
    validuntil:         java.time.ZonedDateTime
}

class-map {
    net.bodz.lily.template.CoCategory: \
        artcat
    net.bodz.lily.template.CoParameter: \
        artparm
    net.bodz.lily.template.CoPhase: \
        artphase
    net.bodz.lily.template.CoTag: \
        arttag
    net.bodz.lily.template.BackrefRecord: \
        art_backref
    net.bodz.lily.template.DocRecord: \
        art_doc
    net.bodz.lily.template.VoteRecord: \
        art_vote
}

table-name {
    art:                net.bodz.violet.art.Artifact
    artcat:             net.bodz.violet.art.ArtifactCategory
    artmodel:           net.bodz.violet.art.ArtifactModel
    artparm:            net.bodz.violet.art.ArtifactParameter
    artphase:           net.bodz.violet.art.ArtifactPhase
    arttag:             net.bodz.violet.art.ArtifactTag
    art_backref:        net.bodz.violet.art.ArtifactBackref
    art_doc:            net.bodz.violet.art.ArtifactDoc
    art_vote:           net.bodz.violet.art.ArtifactVote
    artpack:            net.bodz.violet.art.Packaging
    uom:                net.bodz.violet.art.UOM
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

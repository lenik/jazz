package net.bodz.bas.codegen;

public enum UpdateMethod {

    NO_UPDATE,

    OVERWRITE,

    DIFF_MERGE,

    /**
     * create new patches by compare the current file against the generated file if no patch exist.
     */
    DIFF_PATCH_CREATE,

    /**
     * overwrite any existing content if no patch exist.
     */
    DIFF_PATCH_UPGRADE,

    ;

//    UpdateMethod override(UpdateMethod def) {
//        switch (this) {
//        case NO_UPDATE:
//
//        case OVERWRITE:
//
//        case DIFF_MERGE:
//
//        }
//    }

}

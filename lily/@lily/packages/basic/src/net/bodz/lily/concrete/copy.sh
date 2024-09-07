#!/bin/bash
    : ${RCSID:=$Id: - @VERSION@ @DATE@ @TIME@ - $}
    : ${PACKAGE:=@PACKAGE@}
    : ${PROGRAM_TITLE:=}
    : ${PROGRAM_SYNTAX:=[OPTIONS] [--] ...}

    . shlib-import cliboot
    option -q --quiet
    option -v --verbose
    option -h --help
    option    --version

function setopt() {
    case "$1" in
        -h|--help)
            help $1; exit;;
        -q|--quiet)
            LOGLEVEL=$((LOGLEVEL - 1));;
        -v|--verbose)
            LOGLEVEL=$((LOGLEVEL + 1));;
        --version)
            show_version; exit;;
        *)
            quit "invalid option: $1";;
    esac
}

function main() {
    local src="$1"
    local dest="$2"
    
    cp "${src}.ts" "${dest}.ts"
    cp "${src}TypeInfo.ts" "${dest}TypeInfo.ts"
    cp "${src}Validators.ts" "${dest}Validators.ts"
    
    repl "$src" "$dest" "${dest}.ts"
    repl "$src" "$dest" "${dest}TypeInfo.ts"
    repl "$src" "$dest" "${dest}Validators.ts"
    
}

boot "$@"

#!/bin/bash
    : ${RCSID:=$Id: - @VERSION@ @DATE@ @TIME@ - $}
    : ${PACKAGE:=@PACKAGE@}
    : ${PROGRAM_TITLE:=}
    : ${PROGRAM_SYNTAX:=[OPTIONS] [--] ...}

    . shlib-import cliboot strfn
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
    code=
    while read s; do
        if [ "$s" == '--' ]; then continue; fi
        if [ "${s:0:2}" = '<i' ]; then
            s="${s%</*}"
            s="${s##*>}"
            code="$s"
            code="${code#\&\#x}"
        else
            # name="${s#fa-}"
            name="$s"
            name="${name//-/_}"
            name=$( toupper "$name" )
            echo "char $name = '\u$code'; "
        fi
    done < <(grep -A1 'fa fa-fw' index.html)
}

boot "$@"

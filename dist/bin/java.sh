#!/bin/bash

JAVA="${JAVA=/opt/jre/bin/java}"

if [ ! -x "$JAVA" ]; then
    echo "No Java Runtime Environment found, please reinstall JRE (For Lenix)"
    exit 1
fi

if [ $# -lt 2 ]; then
    echo "Usage: "
    echo "    java.sh <jarfile> <main-class> arguments"
    exit 1
fi

jarFile="$1"
mainClass="$2"
bootClass=
shift 2

if [ -z "$LIBPATH" ]; then
    LIBPATH=/lib:./lib
fi
LIBS=$jarFile

function loadLibraries() {
    local k v
    if [ -z "$_lib_loaded" ]; then
        if [ -f /lib/libraries.ini ]; then
            while read k _ v; do
                eval "lib_$k=\"$v\""
            done </lib/libraries.ini
        fi
        _lib_loaded=1
    fi
}

function addLibrary() {
    local ext path var
    for f in "$@"; do
        ext="${f##*.}"
        if [ -f "$f" ]; then
            path="$f"
        else
            if [ $ext = "jar" ]; then
                for libdir in ${LIBPATH/:/ }; do
                    if [ -f "$libdir/$f" ]; then
                        path="$libdir/$f"
                    fi
                done
            else
                loadLibraries
                var="lib_$f"
                if [ -n "${!var}" ]; then
                    pvar="libadded_$f"
                    if [ -z "${!pvar}" ]; then
                        addLibrary "${!var}"
                        eval "$pvar=1"
                    fi
                fi
                return
            fi
        fi
        if [ -n "$path" ]; then
            echo Path: $path
            LIBS="$LIBS:$path"
        fi
    done
}

function collectClassPath() {
    local PI
    local k nk v
    if [ -f "$1.PI" ]; then
        PI="$1.PI"
    elif [ -f "$1" ]; then
        PI=/tmp/PACKINFO-$$.PI
        unzip -p "$1" META-INF/PACKINFO.PI >$PI
    elif [ -f "$1/META-INF/PACKINFO.PI" ]; then
        PI="$1/META-INF/PACKINFO.PI"
    else
        return
    fi
    while read k v; do
        if [ -n "$k" ]; then
            k="${k%:}"
            k="${k//-/_}"
            case "$k" in
              booter)
                bootClass="$v";;
              lib)
                for i in ${v//,/ }; do
                    addLibrary "$i"
                done;;
              require)
                for i in ${v//,/ }; do
                    collectClassPath "$i"
                done;;
              vmargs)
                JAVA_OPTS="$JAVAOPTS $v";;
            esac
        fi
    done <"$PI"
}

collectClassPath "$jarFile"

# echo "LIBS=$LIBS"
export CLASSPATH="$LIBS"

"$JAVA" $JAVA_OPTS $bootClass $mainClass $*

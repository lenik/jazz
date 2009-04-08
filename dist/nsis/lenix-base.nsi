;!define DEBUG 1

!include "EnvVarUpdate.nsh"
!include "lenix.nsh"
!define __HOME__ ${__DIR__}\..

Name "Lenix Runtime"
OutFile "${__HOME__}\dist\lenix-base.exe"
InstallDir ${LENIX}

; Page custom...
Page instfiles

Section "-Lenix Core Runtime"
    ${EnvVarUpdate} $0 "LABJA_ROOT" "A" "HKLM" $INSTDIR
    !insertmacro DirRec $INSTDIR ${__HOME__} bin
    !insertmacro DirRec $INSTDIR ${__HOME__} etc
SectionEnd

Section "Cygwin Runtime (1.7.0)"
    !cd ${cygwin_home}
    !insertmacro DirRec $INSTDIR . dev
    SetOutPath $INSTDIR\bin
!ifndef DEBUG
        File \
            /x cygclam* \
            /x cygicu* \
            /x cyg*magick* \
            /x cygoct* \
            /x cygxaw* \
            bin\*.dll
!endif
        File bin\bash.exe
        File bin\bzip2.exe
        File bin\bunzip2.exe
        File bin\diff.exe
        File bin\echo.exe
        File bin\grep.exe
        File bin\gunzip
        File bin\gzip.exe
        File bin\m4.exe
        File bin\md5sum.exe
        File bin\mount.exe
        File bin\sh.exe
        File bin\sha1sum.exe
        File bin\tac.exe
        File bin\tar.exe
        File bin\umount.exe
        File bin\unzip.exe
        File bin\wget.exe
        File bin\zip.exe
        File /nonfatal \
            /x brl* \
            /x fstab \
            etc\*
SectionEnd

Section "Java Runtime Environment (1.7.0)"
!ifndef DEBUG
    !insertmacro DirRec $INSTDIR\opt ${jdk_home} jre
!endif
SectionEnd

Section "Java Extra Libraries"
    !cd ${LAPIOTA}\usr\lib\java
    SetOutPath $INSTDIR\lib
!ifndef DEBUG
    File \
        /x net.bodz.* \
        *.jar
!endif
    File libraries.ini
SectionEnd

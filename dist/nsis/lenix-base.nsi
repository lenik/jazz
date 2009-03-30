!define DEBUG 1

!include "lenix.nsh"

Name "Lenix Runtime"
OutFile "${__DIR__}\..\dist\lenix-base.exe"
InstallDir ${LENIX}

; Page custom...
Page instfiles

Section "-Lenix Core Runtime"
    !insertmacro DirRec $INSTDIR . dev
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
        File bin\bash.exe
        File bin\diff.exe
        File bin\echo.exe
        File bin\grep.exe
        File bin\m4.exe
        File bin\md5sum.exe
        File bin\mount.exe
        File bin\sh.exe
        File bin\sha1sum.exe
        File bin\tac.exe
        File bin\umount.exe
!endif
        File bin\wget.exe
SectionEnd

Section "Java Runtime Environment (1.7.0)"
!ifndef DEBUG
    !insertmacro DirRec $INSTDIR\opt ${jdk_home} jre
!endif
SectionEnd

Section "Java Extra Libraries"
    ; !insertmacro Files $INSTDIR\lib ${LAPIOTA}\usr\lib\java *
SectionEnd

; !define DEBUG 1

!include "EnvVarUpdate.nsh"
!include "lenix.nsh"
!define __HOME__ ${__DIR__}\..

Name "Lenix Runtime"
OutFile "$%DIST_HOME%\lenix-base.exe"
InstallDir ${LENIX}

; Page custom...
Page instfiles

Section "-Lenix Core Runtime"
    !insertmacro EnvSet "LABJA_ROOT"    "$INSTDIR"
    !insertmacro EnvSet "CYGWIN"        "nodosfilewarning noenvcache title"
    !insertmacro EnvSet "LANG"          "zh_CN.UTF-8"
    
    ${EnvVarUpdate} $0 "PATH"           "A" "HKCU" $INSTDIR\bin
    
    !insertmacro DirRec $INSTDIR ${LAPIOTA} bin
    !insertmacro DirRec $INSTDIR ${LAPIOTA} lib
    
    !insertmacro DirRec $INSTDIR ${__HOME__} bin
    !insertmacro DirRec $INSTDIR ${__HOME__} etc
    
    WriteRegStr HKCR .xj  "" Lenix.XJ
    WriteRegStr HKCR .xjw "" Lenix.XJW
    WriteRegExpandStr HKCR Lenix.XJ  "" "Lenix Java Console Application"
    WriteRegExpandStr HKCR Lenix.XJW "" "Lenix Java Application"
    WriteRegExpandStr HKCR Lenix.XJ\DefaultIcon  "" "%SystemRoot%\System32\shell32.dll,-153"
    WriteRegExpandStr HKCR Lenix.XJW\DefaultIcon "" "%SystemRoot%\System32\shell32.dll,-3"
    ;; WriteRegExpandStr HKCR Lenix.XJW\DefaultIcon "" "%SystemRoot%\System32\shell32.dll,-274"
    
    ; "...\bash.exe" -c "env LAM_KALA=/ xj.sh $(cygpath -u '%0') %*"
    ; due to the path problem of $LAM_KALA/ => //, so let it be empty string. 
    WriteRegStr HKCR Lenix.XJ\Shell\open\Command "" \
        "$\"$INSTDIR\bin\bash.exe$\"  -c $\"env LAM_KALA= xj.sh  $$(cygpath -u '%0') %*$\""
    WriteRegStr HKCR Lenix.XJW\Shell\open\Command "" \
        "$\"$INSTDIR\bin\bashw.exe$\" -c $\"env LAM_KALA= xjw.sh $$(cygpath -u '%0') %*$\""
    System::Call "shell32.dll::SHChangeNotify(i, i, i, i) v (0x08000000, 0, 0, 0)"
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
        File bin\bashw.exe
        File bin\bzip2.exe
        File bin\bunzip2.exe
        File bin\cygcheck.exe
        File bin\cygpath.exe
        File bin\diff.exe
        File bin\echo.exe
        File bin\env.exe
        File bin\grep.exe
        File bin\gunzip
        File bin\gzip.exe
        File bin\id.exe
        File bin\m4.exe
        File bin\md5sum.exe
        File bin\mount.exe
        File bin\rm.exe
        File bin\sed.exe
        File bin\sh.exe
        File bin\sha1sum.exe
        File bin\tac.exe
        File bin\tar.exe
        File bin\tr.exe
        File bin\umount.exe
        File bin\unzip.exe
        File bin\wget.exe
        File bin\which.exe
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

@echo off

    setlocal
    set _strict=1
    goto init

:start
    
    echo Creating %_target%...
    cd /d "%~dp0.."
    rem cpnew -f -r . "%_target%\"
    svn export . "%_target%"
    
    echo Do template substituting...
    
    exit /b 0

:init
    set  _verbose=0
    set      _ret=
    set     _rest=
    set _startdir=%~dp0
    set  _program=%~dpnx0

:prep1
    if "%~1"==""            goto prep2
    if "%~1"=="--version"   goto version
    if "%~1"=="-h"          goto help
    if "%~1"=="--help"      goto help
    if "%~1"=="--" (
        shift
        goto prep2
    )
    set _arg=%~1
    
    if "%~1"=="-q" (
        set /a _verbose = _verbose - 1
    ) else if "%~1"=="--quiet" (
        set /a _verbose = _verbose - 1
    ) else if "%~1"=="-v" (
        set /a _verbose = _verbose + 1
    ) else if "%~1"=="--verbose" (
        set /a _verbose = _verbose + 1
    ) else if "%_arg:~0,1%"=="-" (
        if "%_strict%"=="1" (
            echo Invalid option: %1
            exit /b 1
        ) else (
            set _%_arg:~1%=%~2
            if %_verbose% geq 1 echo _%_arg:~1%=%~2
            shift
        )
    ) else (
        goto prep2
    )
    shift
    goto prep1

:prep2
    set _ws=C:\lam\ws.java\_
    if "%~1"=="" (
        echo Please specify target workspace directory: 
        echo Variable: 
        echo    ~=%HOME%
        echo    $=%_ws%
        set /p _target=
    ) else (
        set _target=%~1
        shift
    )
    
    if "%_target%"=="" exit /b 1
    
    set _target=%_target:/=\%
    if "%_target:~0,1%"=="~" set _target=%HOME%\%_target:~1%
    if "%_target:~0,1%"=="$" set _target=%_ws%\%_target:~1%
    
:prep3

:init_ok
    if %_verbose% geq 1 (set _ | tabify -b -d==)
    goto start

:version
    set _id=$Id: .bat 784 2008-01-15 10:53:24Z lenik $
    for /f "tokens=3-6" %%i in ("%_id%") do (
        set   _version=%%i
        set      _date=%%j
        set      _time=%%k
        set    _author=%%l
    )
    echo [new] Create new eclipse workspace
    echo Written by %_author%,  Version %_version%,  Last updated at %_date%
    exit /b 0

:help
    call :version
    echo.
    echo Syntax: 
    echo    %_program% [OPTION] ...
    echo.
    echo Options: 
    echo    -q, --quiet         repeat to get less info
    echo    -v, --verbose       repeat to get more info
    echo        --version       show version info
    echo    -h, --help          show this help page
    exit /b 0

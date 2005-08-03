@echo off

set _ori=%~dp0phpfixes.php
set _arg1=%1
if "%1"=="" set _arg1=..\..

pushd "%_arg1%"

for /r /d %%i in (*) do (
	if exist "%%i\_Phpfixes.php" (
	    echo Copy to %%i
	    copy /y "%_ori%" "%%i\_Phpfixes.php" >nul
	)
)
repl -r -x _Phpfixes.php "\$((Id|Log).*?)\$" "($1)"

popd

set _ori=
set _arg1=

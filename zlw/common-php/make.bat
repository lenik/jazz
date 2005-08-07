@echo off

if "%M4PAPTH%"=="" set M4PATH=%~dp0..\common-m4:%~dp0..\config

for %%i in (*.m4) do (
    if exist "%%~dpni.php" attrib -r "%%~dpni.php"
)

echo configure mail api ...
    m4 -P mail.m4 > mail.php

echo configure string utilities ...
    m4 -P string.m4 > string.php

echo configure default DBI connection ...
    m4 -P dbi.m4 > dbi.php

for %%i in (*.m4) do (
    if exist "%%~dpni.php" attrib +r "%%~dpni.php"
)


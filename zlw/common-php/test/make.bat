@echo off

if "%M4PAPTH%"=="" set M4PATH=%~dp0..\..\common-m4

for %%i in (*.m4) do (
    if exist "%%~dpni.php" attrib -r "%%~dpni.php"
)

echo configure pdo test ...
    m4 -P t-pdo1.m4 > t-pdo1.php

for %%i in (*.m4) do (
    if exist "%%~dpni.php" attrib +r "%%~dpni.php"
)


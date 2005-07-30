@echo off

if "%M4PAPTH%"=="" set M4PATH=%~dp0..\common-m4

echo configure smtp api ...
smtp.m4 > smtp.php

echo configure string utilities ...
string.m4 > string.php


@echo off

    set lam_svn=C:\lam\repo.svn
    call %lam_svn%\bin\svnenv
    
    set PATH=%~dp0;%PATH%
    
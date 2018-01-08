set ProjectPath=%~dp0
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*
java %projectLocation%\bin\executionEngine\RunTestscript.class
pause
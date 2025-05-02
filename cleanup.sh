#!/bin/bash -v

find HIF_FT_dotnet -name ".idea" -exec rm -rfv {} \;
find HIF_FT_dotnet -name "bin" -exec rm -rfv {} \;
find HIF_FT_dotnet -name "obj" -exec rm -rfv {} \;

find HIF_FT_java -name ".idea" -exec rm -rfv {} \;
find HIF_FT_java -name "target" -exec rm -rfv {} \;

#!/bin/bash -v

zip -v -r HIF_FT_dotnet.zip HIF_FT_dotnet/ \
  -x "HIF_FT_dotnet/**/target/**" "HIF_FT_dotnet/.idea/**" "HIF_FT_dotnet/.git/**" "HIF_FT_dotnet/**/bin/**" "HIF_FT_dotnet/**/obj/**"

zip -v -r HIF_FT_java.zip HIF_FT_java/ \
  -x "HIF_FT_java/**/target/**" "HIF_FT_java/.idea/**" "HIF_FT_java/.git/**"

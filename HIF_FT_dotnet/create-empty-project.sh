#!/bin/bash -v

# A shell script to create the solution and
# project structure

dotnet new sln \
  --name HIF_FT_dotnet

mkdir -p src/Aufgabe1_ORMapping
mkdir -p src/Aufgabe2_BusinessServices
mkdir -p src/Aufgabe3_RestfulApi
mkdir -p tests/Aufgabe1_ORMapping.Test
mkdir -p tests/Aufgabe2_BusinessServices.Test
mkdir -p tests/Aufgabe3_RestfulApi.Test

dotnet new classlib \
  --name Aufgabe1_ORMapping \
  --output src/Aufgabe1_ORMapping
dotnet new classlib \
  --name Aufgabe2_BusinessServices \
  --output src/Aufgabe2_BusinessServices
dotnet new webapi \
  --name Aufgabe3_RestfulApi \
  --output src/Aufgabe3_RestfulApi

dotnet sln add src/Aufgabe1_ORMapping
dotnet sln add src/Aufgabe2_BusinessServices
dotnet sln add src/Aufgabe3_RestfulApi

dotnet new xunit \
  --name Aufgabe1_ORMapping.Test \
  --output tests/Aufgabe1_ORMapping.Test
dotnet new xunit \
  --name Aufgabe2_BusinessServices.Test \
  --output tests/Aufgabe2_BusinessServices.Test
dotnet new xunit \
  --name Aufgabe3_RestfulApi.Test \
  --output tests/Aufgabe3_RestfulApi.Test

dotnet sln add tests/Aufgabe1_ORMapping.Test
dotnet sln add tests/Aufgabe2_BusinessServices.Test
dotnet sln add tests/Aufgabe3_RestfulApi.Test

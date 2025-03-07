#!/bin/bash -v

# A shell script to create the solution and
# project structure

dotnet new sln \
  --name HIF_FT_dotnet

mkdir -p src/Aufgabe1_ORMapping
mkdir -p src/Aufgabe2_Services
mkdir -p src/Aufgabe3_RestfulApi
mkdir -p test/Aufgabe1_ORMapping.Test
mkdir -p test/Aufgabe2_Services.Test
mkdir -p test/Aufgabe3_RestfulApi.Test

dotnet new classlib \
  --name Aufgabe1_ORMapping \
  --output src/Aufgabe1_ORMapping
dotnet new classlib \
  --name Aufgabe2_Services \
  --output src/Aufgabe2_Services
dotnet new webapi \
  --name Aufgabe3_RestfulApi \
  --output src/Aufgabe3_RestfulApi

dotnet sln add src/Aufgabe1_ORMapping
dotnet sln add src/Aufgabe2_Services
dotnet sln add src/Aufgabe3_RestfulApi

dotnet new xunit \
  --name Aufgabe1_ORMapping.Test \
  --output test/Aufgabe1_ORMapping.Test
dotnet new xunit \
  --name Aufgabe2_Services.Test \
  --output test/Aufgabe2_Services.Test
dotnet new xunit \
  --name Aufgabe3_RestfulApi.Test \
  --output test/Aufgabe3_RestfulApi.Test

dotnet sln add test/Aufgabe1_ORMapping.Test
dotnet sln add test/Aufgabe2_Services.Test
dotnet sln add test/Aufgabe3_RestfulApi.Test

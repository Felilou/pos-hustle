# Create the solution
dotnet new sln --name HIF_FT_dotnet

# Create directories
$directories = @(
    "src/Aufgabe1_ORMapping",
    "src/Aufgabe2_Services",
    "src/Aufgabe3_RestfulApi",
    "tests/Aufgabe1_ORMapping.Test",
    "tests/Aufgabe2_Services.Test",
    "tests/Aufgabe3_RestfulApi.Test"
)

foreach ($dir in $directories) {
    if (!(Test-Path $dir)) {
        New-Item -ItemType Directory -Path $dir | Out-Null
    }
}

# Create projects
dotnet new classlib --name Aufgabe1_ORMapping --output src/Aufgabe1_ORMapping
dotnet new classlib --name Aufgabe2_Services --output src/Aufgabe2_Services
dotnet new webapi --name Aufgabe3_RestfulApi --output src/Aufgabe3_RestfulApi

# Add projects to solution
dotnet sln add src/Aufgabe1_ORMapping
dotnet sln add src/Aufgabe2_Services
dotnet sln add src/Aufgabe3_RestfulApi

# Create test projects
dotnet new xunit --name Aufgabe1_ORMapping.Test --output tests/Aufgabe1_ORMapping.Test
dotnet new xunit --name Aufgabe2_Services.Test --output tests/Aufgabe2_Services.Test
dotnet new xunit --name Aufgabe3_RestfulApi.Test --output tests/Aufgabe3_RestfulApi.Test

# Add test projects to solution
dotnet sln add tests/Aufgabe1_ORMapping.Test
dotnet sln add tests/Aufgabe2_Services.Test
dotnet sln add tests/Aufgabe3_RestfulApi.Test

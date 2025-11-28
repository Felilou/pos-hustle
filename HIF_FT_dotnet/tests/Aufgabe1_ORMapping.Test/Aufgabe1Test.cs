using Aufgabe1_ORMapping.Infrastructure;
using Microsoft.EntityFrameworkCore;
using Xunit.Abstractions;

namespace Aufgabe1_ORMapping.Test;


[Collection("Sequential")]
public class Aufgabe1Test
{
    private readonly ITestOutputHelper output;

    public Aufgabe1Test(ITestOutputHelper output)
    {
        this.output = output;
    }

    private AppDbContext GetEmptyDbContext()
    {
        var options = new DbContextOptionsBuilder<AppDbContext>()
            .UseSqlite(@"Data Source=:memory:")
            .LogTo(output.WriteLine)
            .EnableSensitiveDataLogging()
            .EnableDetailedErrors()
            .Options;

        var db = new AppDbContext(options);
        db.Database.EnsureDeleted();
        db.Database.EnsureCreated();
        return db;
    }

    [Fact]
    public void CreateDatabaseTest()
    {
        using var db = GetEmptyDbContext();
    }
}
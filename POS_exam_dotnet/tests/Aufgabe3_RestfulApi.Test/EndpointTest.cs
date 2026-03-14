using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Threading.Tasks;
using Xunit;

namespace SPG_Fachtheorie.Aufgabe3.Test;

/// <summary>
/// Testklasse. Verwende _factory, um die Methoden
/// _factory.InitializeDatabase, _factory.GetHttpContent<T>, etc. aufzurufen.
[Collection("Sequential")]
public class EndpointTests : IClassFixture<TestWebApplicationFactory>
{
    private readonly TestWebApplicationFactory _factory;

    public EndpointTests(TestWebApplicationFactory factory)
    {
        _factory = factory;
    }

    [Fact]
    public async Task GetXXXItemsReturns200Test()
    {
        // Der gesamte Test muss angepasst werden, je nach Aufgabenstellung!
        // GetHttpContent<string> ... hier passt string nicht!
        // "/endpoint" ... wird sicher anders heissen!

        //_factory.InitializeDatabase(db =>
        //{
        //    // ...
        //    db.SaveChanges();
        //});

        var (statusCode, data) = await _factory.GetHttpContent<string>("/endpoint");
        Assert.True(statusCode == HttpStatusCode.NotFound);
        // ...
    }

}

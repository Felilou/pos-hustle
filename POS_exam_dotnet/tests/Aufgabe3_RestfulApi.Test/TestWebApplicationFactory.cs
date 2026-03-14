using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Mvc.Testing;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;
using System.Net;
using System.Text;
using System.Text.Json;

namespace SPG_Fachtheorie.Aufgabe3.Test;

// Todo: Class AppDbContext is only for demonstration, must be replaced!
public class AppDbContext : DbContext { }


public class TestWebApplicationFactory : WebApplicationFactory<Program>
{
    private readonly JsonSerializerOptions _jsonOptions = new()
    {
        PropertyNameCaseInsensitive = true
    };
    protected override void ConfigureWebHost(IWebHostBuilder builder)
    {
        builder.ConfigureServices(services =>
        {
            #region Ersetze die DB durch eine Test DB
            // Muss ersetzt werden durch den entsprechenden Context
            //var descriptor = services.First(d => d.ServiceType == typeof(DbContextOptions<AppDbContext>));
            //services.Remove(descriptor);
            //services.AddDbContext<AppDbContext>(options =>
            //{
            //    options.UseSqlite("DataSource=aufgabe3_test.db");
            //});
            #endregion
        });
        builder.UseEnvironment("Testing");
    }
    public void InitializeDatabase(Action<AppDbContext> action)
    {
        using var scope = Services.CreateScope();
        using var db = scope.ServiceProvider.GetRequiredService<AppDbContext>();
        db.Database.EnsureDeleted();
        db.Database.EnsureCreated();
        action(db);
    }
    

    public Tout QueryDatabase<Tout>(Func<AppDbContext, Tout> query)
    {
        using var scope = Services.CreateScope();
        using var db = scope.ServiceProvider.GetRequiredService<AppDbContext>();
        return query(db);
    }

    /// <summary>
    /// Send a GET Request and return the deserialized response.
    /// Useful for strongly typed JSON data with DTOs.
    /// </summary>
    public async Task<(HttpStatusCode, T?)> GetHttpContent<T>(string requestUrl) where T : class
    {
        using var client = CreateClient();
        var response = await client.GetAsync(requestUrl);
        if (!response.IsSuccessStatusCode) return (response.StatusCode, default);
        var dataString = await response.Content.ReadAsStringAsync();
        var data = JsonSerializer.Deserialize<T>(dataString, _jsonOptions);
        if (data is null) throw new Exception("Deserialization failed");
        return (response.StatusCode, data);
    }

    /// <summary>
    /// Send a GET Request and return the response as a JsonElement. Useful for dynamic JSON data without DTOs.
    /// </summary>
    /// <param name="requestUrl"></param>
    /// <returns></returns>
    public async Task<(HttpStatusCode, JsonElement)> GetHttpContent(string requestUrl)
    {
        using var client = CreateClient();
        var response = await client.GetAsync(requestUrl);
        if (!response.IsSuccessStatusCode) return (response.StatusCode, new JsonElement());
        var dataString = await response.Content.ReadAsStringAsync();
        var data = JsonDocument.Parse(dataString);
        return (response.StatusCode, data.RootElement);
    }

    /// <summary>
    /// Send a POST Request with a strongly typed payload and return the deserialized response.
    /// </summary>
    public async Task<(HttpStatusCode, JsonElement)> PostHttpContent<Tcmd>(string requestUrl, Tcmd payload) where Tcmd : class
    {
        using var client = CreateClient();
        var jsonBody = new StringContent(JsonSerializer.Serialize(payload), Encoding.UTF8, "application/json");
        var response = await client.PostAsync(requestUrl, jsonBody);
        if (!response.IsSuccessStatusCode) return (response.StatusCode, new JsonElement());

        var dataString = await response.Content.ReadAsStringAsync();
        if (string.IsNullOrEmpty(dataString)) return (response.StatusCode, new JsonElement());
        var data = JsonDocument.Parse(dataString);
        return (response.StatusCode, data.RootElement);
    }

    /// <summary>
    /// Send a PATCH Request with a strongly typed payload and return the deserialized response.
    /// </summary>
    public async Task<(HttpStatusCode, JsonElement)> PatchHttpContent<Tcmd>(string requestUrl, Tcmd payload) where Tcmd : class
    {
        using var client = CreateClient();
        var jsonBody = new StringContent(JsonSerializer.Serialize(payload), Encoding.UTF8, "application/json");
        var response = await client.PatchAsync(requestUrl, jsonBody);
        if (!response.IsSuccessStatusCode) return (response.StatusCode, new JsonElement());

        var dataString = await response.Content.ReadAsStringAsync();
        if (string.IsNullOrEmpty(dataString)) return (response.StatusCode, new JsonElement());
        var data = JsonDocument.Parse(dataString);
        return (response.StatusCode, data.RootElement);
    }

    /// <summary>
    /// Send a PUT Request with a strongly typed payload and return the deserialized response.
    /// </summary>
    public async Task<(HttpStatusCode, JsonElement)> PutHttpContent<Tcmd>(string requestUrl, Tcmd payload) where Tcmd : class
    {
        using var client = CreateClient();
        var jsonBody = new StringContent(JsonSerializer.Serialize(payload), Encoding.UTF8, "application/json");
        var response = await client.PutAsync(requestUrl, jsonBody);
        if (!response.IsSuccessStatusCode) return (response.StatusCode, new JsonElement());

        var dataString = await response.Content.ReadAsStringAsync();
        if (string.IsNullOrEmpty(dataString)) return (response.StatusCode, new JsonElement());
        var data = JsonDocument.Parse(dataString);
        return (response.StatusCode, data.RootElement);
    }

    /// <summary>
    /// Send a DELETE Request with a strongly typed payload and return the deserialized response.
    /// </summary>
    public async Task<HttpStatusCode> DeleteHttpContent(string requestUrl)
    {
        using var client = CreateClient();
        var response = await client.DeleteAsync(requestUrl);
        return response.StatusCode;
    }

    public HttpClient Client => CreateClient();
}

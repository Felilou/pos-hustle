using Bogus;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Aufgabe1_ORMapping.Infrastructure;

// Todo: Entity Person only for demonstration, should be replaced with actual entities!
public class Person
{
    public int Id { get; set; }
    public string? Name { get; set; }
}

public class AppDbContext : DbContext
{
    public AppDbContext(DbContextOptions<AppDbContext> options) : base(options) { }

    // Todo: only for demonstration, should be replaced!
    public DbSet<Person> People => Set<Person>();

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        //modelBuilder.Entity<Person>()...



        base.OnModelCreating(modelBuilder);
    }
}
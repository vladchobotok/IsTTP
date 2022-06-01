using Microsoft.EntityFrameworkCore;
using Lab2.Models;

namespace Lab2;

public class Lab2Context : DbContext
{
    public Lab2Context()
    {
    }

    public Lab2Context(DbContextOptions<Lab2Context> options)
        : base(options)
    {
        Database.EnsureCreated();
    }

    public virtual DbSet<CarType> CarTypes { get; set; }
    public virtual DbSet<Manafacturer> Manafacturers { get; set; }
    public virtual DbSet<User> Users { get; set; }
    public virtual DbSet<Order> Orders { get; set; }
    public virtual DbSet<Car> Cars { get; set; }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        base.OnModelCreating(modelBuilder);

        modelBuilder.Entity<CarType>(entity =>
        {
            entity.Property(e => e.Id)
            .UseIdentityColumn();
            entity.HasIndex(e => e.TypeName)
            .IsUnique();

            entity.HasMany(d => d.Cars)
            .WithOne(e => e.CarType)
            .HasForeignKey(p => p.TypeID)
            .OnDelete(DeleteBehavior.Cascade);
        });

        modelBuilder.Entity<Manafacturer>(entity =>
        {
            entity.Property(e => e.Id)
            .UseIdentityColumn();
            entity.HasIndex(e => e.ManafacturerName)
            .IsUnique();

            entity.HasMany(d => d.Cars)
            .WithOne(e => e.Manafacturer)
            .HasForeignKey(e => e.ManafacturerID)
            .OnDelete(DeleteBehavior.Cascade);
        }); 

        modelBuilder.Entity<User>(entity =>
        {
            entity.Property(e => e.Id)
            .UseIdentityColumn();
            entity.HasIndex(e => e.Login)
            .IsUnique();

            entity.HasMany(d => d.Orders)
            .WithOne(e => e.User)
            .HasForeignKey(e => e.UserID)
            .OnDelete(DeleteBehavior.Cascade);
        });

        modelBuilder.Entity<Order>(entity =>
        {
            entity.Property(e => e.Id)
            .UseIdentityColumn();
            entity.Property(e => e.DeliveryCost)
            .HasMaxLength(20);
        });

        modelBuilder.Entity<Car>(entity =>
        {
            entity.Property(e => e.Id)
            .UseIdentityColumn();
            entity.HasIndex(e => e.CarName)
            .IsUnique();
            entity.Property(e => e.Price)
            .HasMaxLength(20);

            entity.HasMany(d => d.Orders)
            .WithOne(e => e.Car)
            .HasForeignKey(e => e.CarID)
            .OnDelete(DeleteBehavior.Cascade);
        });
    }
}

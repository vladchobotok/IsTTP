using System.ComponentModel.DataAnnotations;

namespace Lab2.Models
{
    public class Car
    {
        public Car()
        {
            Orders = new HashSet<Order>();
        }
        public int Id { get; set; }
        public string CarName { get; set; }
        public long Price { get; set; }
        public int TypeID { get; set; }
        public int ManafacturerID { get; set; }

        public CarType CarType { get; set; }
        public Manafacturer Manafacturer { get; set; }

        public ICollection<Order> Orders { get; set; }

    }
}

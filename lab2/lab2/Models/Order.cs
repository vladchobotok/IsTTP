using System.ComponentModel.DataAnnotations;

namespace Lab2.Models
{
    public class Order
    {
        public int Id { get; set; }
        public int CarID { get; set; }
        public int UserID { get; set; }
        public long DeliveryCost { get; set; }

        public Car Car { get; set; }
        public User User { get; set; }       
    }
}


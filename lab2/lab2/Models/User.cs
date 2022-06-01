using System.ComponentModel.DataAnnotations;

namespace Lab2.Models
{
    public class User
    {
        public User()
        {           
            Orders = new HashSet<Order>();
        }
        public int Id { get; set; }
        public string Login { get; set; }

        public ICollection<Order> Orders { get; set; }
    }
}

using System.ComponentModel.DataAnnotations;

namespace Lab2.Models
{
    public class Manafacturer
    {
        public Manafacturer()
        {
            Cars = new HashSet<Car>();
        }

        public int Id { get; set; }
        public string ManafacturerName { get; set; }

        public ICollection<Car> Cars { get; set; }
    }
}

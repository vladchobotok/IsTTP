using System.ComponentModel.DataAnnotations;

namespace Lab2.Models
{
    public class CarType
    {
        public CarType()
        {
            Cars = new HashSet<Car>();            
        }
        public int Id { get; set; }
        public string TypeName { get; set; }
        public ICollection<Car> Cars { get; set; }
    }
}

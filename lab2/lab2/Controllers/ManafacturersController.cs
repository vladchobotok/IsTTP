using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Lab2;
using Lab2.Models;

namespace lab2.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ManafacturersController : ControllerBase
    {
        private readonly Lab2Context _context;

        public ManafacturersController(Lab2Context context)
        {
            _context = context;
        }

        // GET: api/Manafacturers
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Manafacturer>>> GetManafacturers()
        {
          if (_context.Manafacturers == null)
          {
              return NotFound();
          }
            return await _context.Manafacturers.ToListAsync();
        }

        // GET: api/Manafacturers/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Manafacturer>> GetManafacturer(int id)
        {
          if (_context.Manafacturers == null)
          {
              return NotFound();
          }
            var manafacturer = await _context.Manafacturers.FindAsync(id);

            if (manafacturer == null)
            {
                return NotFound();
            }

            return manafacturer;
        }

        // PUT: api/Manafacturers/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutManafacturer(int id, Manafacturer manafacturer)
        {
            if (id != manafacturer.Id)
            {
                return BadRequest();
            }

            _context.Entry(manafacturer).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ManafacturerExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/Manafacturers
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Manafacturer>> PostManafacturer(Manafacturer manafacturer)
        {
          if (_context.Manafacturers == null)
          {
              return Problem("Entity set 'Lab2Context.Manafacturers'  is null.");
          }
            _context.Manafacturers.Add(manafacturer);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetManafacturer", new { id = manafacturer.Id }, manafacturer);
        }

        // DELETE: api/Manafacturers/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteManafacturer(int id)
        {
            if (_context.Manafacturers == null)
            {
                return NotFound();
            }
            var manafacturer = await _context.Manafacturers.FindAsync(id);
            if (manafacturer == null)
            {
                return NotFound();
            }

            _context.Manafacturers.Remove(manafacturer);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool ManafacturerExists(int id)
        {
            return (_context.Manafacturers?.Any(e => e.Id == id)).GetValueOrDefault();
        }
    }
}

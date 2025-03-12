package com.cg.mtb.ctrl;
 
import java.util.List;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.cg.mtb.dto.SeatDto;
import com.cg.mtb.exception.SeatNotFoundException;
import com.cg.mtb.exception.TierNotFoundException;
import com.cg.mtb.service.SeatService;
import com.cg.mtb.service.TheatreService;
 
@RestController
@RequestMapping("/api/seats")
public class SeatCtrl {
	private final SeatService seatService;
 
    public SeatCtrl(SeatService seatService) {
        this.seatService = seatService;
    }
    @GetMapping
    public ResponseEntity<List<SeatDto>> getAllSeats() throws SeatNotFoundException {
        List<SeatDto> seats = seatService.getAllSeats();
        return ResponseEntity.ok(seats);
    }
    @GetMapping("/{seatId}")
    public ResponseEntity<SeatDto> getSeatById(@PathVariable("seatId") int seatId) throws SeatNotFoundException {
        SeatDto seat = seatService.getSeatById(seatId);
        return ResponseEntity.ok(seat);
    }
    @GetMapping("/theatre/{theatreId}")
    public ResponseEntity<List<SeatDto>> getSeatsByTheatreId(@PathVariable("theatreId") int theatreId) throws SeatNotFoundException {
        List<SeatDto> seats = seatService.getSeatsByTheatreId(theatreId);
        return ResponseEntity.ok(seats);
    }
    @GetMapping("/tier/{tierId}")
    public ResponseEntity<List<SeatDto>> getSeatsByTierId(@PathVariable("tierId") int tierId) throws SeatNotFoundException {
        List<SeatDto> seats = seatService.getSeatsByTierId(tierId);
        return ResponseEntity.ok(seats);
    }
    @PostMapping
    public ResponseEntity<SeatDto> addSeat(@RequestBody SeatDto seatDto) {
        System.out.println("Received request to add seat: " + seatDto);  // ✅ Debug log
        try {
            SeatDto newSeat = seatService.addSeat(seatDto);
            System.out.println("Successfully added seat: " + newSeat);  
            return ResponseEntity.status(HttpStatus.CREATED).body(newSeat);
        } catch (TierNotFoundException e) {
            System.out.println("Error: Tier not found for ID: " + seatDto.getTierId());  
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @DeleteMapping("/{seatId}")
    public ResponseEntity<String> deleteSeat(@PathVariable("seatId") int seatId) {
        try {
            seatService.deleteSeat(seatId);
            return ResponseEntity.ok("Seat with ID " + seatId + " deleted successfully");
        } catch (SeatNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
 
}
        


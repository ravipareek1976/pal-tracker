package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    TimeEntryRepository timeEntryRepository;
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository= timeEntryRepository;;
    }
@PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
    TimeEntry updatedTimeEntry = timeEntryRepository.update(id, timeEntry);
    if (updatedTimeEntry != null) {
        return new ResponseEntity<>(updatedTimeEntry, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    }
@PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {

        ResponseEntity<TimeEntry> r=
                new ResponseEntity<>(timeEntryRepository.create(timeEntryToCreate),HttpStatus.CREATED);

        return r;
       //return ResponseEntity<TimeEntry>(timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);
       //return ResponseEntity.created();
    }
    @GetMapping ("/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry timeEntry = timeEntryRepository.find(id);
        if (timeEntry != null) {
            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        //return ResponseEntity.ok(timeEntryRepository.find(timeEntryId));
    }
    @GetMapping("")
    public ResponseEntity<List<TimeEntry>> list() {
        ResponseEntity<List<TimeEntry>> r=
                new ResponseEntity<>(timeEntryRepository.list(),HttpStatus.OK);
        return r;
    }
@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete( @PathVariable long id) {
    timeEntryRepository.delete(id);
    ResponseEntity r=            new ResponseEntity(HttpStatus.NO_CONTENT);
    return r;
    }
}

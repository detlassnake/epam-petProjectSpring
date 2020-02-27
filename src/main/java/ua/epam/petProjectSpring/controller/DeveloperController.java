package ua.epam.petProjectSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.epam.petProjectSpring.model.Developer;
import ua.epam.petProjectSpring.service.DeveloperService;

import java.util.List;

@Controller
@RequestMapping("/developers")
public class DeveloperController {
    private DeveloperService developerService;

    @Autowired
    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping(params = "id")
    public @ResponseBody Developer getDeveloper(Long id) {
        return developerService.readById(id);
    }

    @GetMapping
    public @ResponseBody List<Developer> getDevelopers() {
        return developerService.read();
    }

    @PostMapping
    public ResponseEntity postDeveloper(@RequestBody Developer developer) {
        developerService.create(developer);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity putDeveloper(@RequestBody Developer developer) {
        developerService.update(developer.getId(), developer);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteDeveloper(@RequestBody Developer developer) {
        developerService.delete(developer.getId());
        return new ResponseEntity(HttpStatus.OK);
    }
}

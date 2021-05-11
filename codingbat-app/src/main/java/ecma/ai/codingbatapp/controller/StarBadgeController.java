package ecma.ai.codingbatapp.controller;

import ecma.ai.codingbatapp.entity.StarBadge;
import ecma.ai.codingbatapp.payload.ApiResponse;
import ecma.ai.codingbatapp.payload.StarBadgeDto;
import ecma.ai.codingbatapp.service.StarBadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/starBadge")
public class StarBadgeController {

    @Autowired
    StarBadgeService starBadgeService;

    @GetMapping
    public ResponseEntity<List<StarBadge>> getstarBadge() {
        List<StarBadge> starBadge = starBadgeService.getStarBadge();
        return ResponseEntity.ok(starBadge);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StarBadge> getstarBadge(@PathVariable Integer id) {
        StarBadge starBadge = starBadgeService.getStarBadgeById(id);
        return ResponseEntity.ok(starBadge);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addstarBadge(@Valid @RequestBody StarBadgeDto starBadgeDto) {
        ApiResponse apiResponse = starBadgeService.addStarBadge(starBadgeDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> editstarBadge(@PathVariable Integer id, @Valid @RequestBody StarBadgeDto starBadgeDto) {
        ApiResponse apiResponse = starBadgeService.editStarBadge(id, starBadgeDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteStarBadge(@PathVariable Integer id) {
        ApiResponse apiResponse = starBadgeService.deleteStarBadge(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

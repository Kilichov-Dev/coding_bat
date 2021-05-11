package ecma.ai.codingbatapp.controller;

import ecma.ai.codingbatapp.entity.ProgLang;
import ecma.ai.codingbatapp.payload.ApiResponse;
import ecma.ai.codingbatapp.service.ProgLangService;
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
@RequestMapping("/api/progLang")
public class ProgLangController {
    @Autowired
    ProgLangService progLangService;

    @GetMapping
    public ResponseEntity<List<ProgLang>> getprogLAng() {
        List<ProgLang> progLAng = progLangService.getProgLang();
        return ResponseEntity.ok(progLAng);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgLang> getprogLAng(@PathVariable Integer id) {
        ProgLang progLAng = progLangService.getProgLangById(id);
        return ResponseEntity.ok(progLAng);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addProgLAng(@Valid @RequestBody ProgLang progLAngDto) {
        ApiResponse apiResponse = progLangService.addProgLang(progLAngDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> editProgLAng(@PathVariable Integer id, @Valid @RequestBody ProgLang progLAngDto) {
        ApiResponse apiResponse = progLangService.editProgLAng(id, progLAngDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteResponse(@PathVariable Integer id) {
        ApiResponse apiResponse = progLangService.deleteProgLang(id);
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

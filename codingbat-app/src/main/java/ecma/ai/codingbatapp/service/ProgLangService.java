package ecma.ai.codingbatapp.service;

import ecma.ai.codingbatapp.entity.ProgLang;
import ecma.ai.codingbatapp.payload.ApiResponse;
import ecma.ai.codingbatapp.repository.ProgLangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgLangService {

    @Autowired
    ProgLangRepository progLangRepository;

    public List<ProgLang> getProgLang() {
        List<ProgLang> all = progLangRepository.findAll();
        return all;
    }

    public ProgLang getProgLangById(Integer id) {
        Optional<ProgLang> optionalProgLang = progLangRepository.findById(id);
        return optionalProgLang.orElse(null);
    }

    public ApiResponse addProgLang(ProgLang progLang) {
        ProgLang progLang1 = new ProgLang();
        if (progLangRepository.existsByName(progLang.getName())) {
            return new ApiResponse("This Programming language already exists!", false);
        }
        progLang1.setName(progLang.getName());
        progLangRepository.save(progLang1);
        return new ApiResponse("Programming Language added Successfully!!!", true);
    }

    public ApiResponse editProgLAng(Integer id, ProgLang progLang) {
        Optional<ProgLang> optionalProgLang = progLangRepository.findById(id);
        if (optionalProgLang.isPresent()) {
            ProgLang progLang1 = optionalProgLang.get();
            if (progLangRepository.existsByName(progLang.getName())) {
                return new ApiResponse("This Programming language already exists!", false);
            }
            progLangRepository.save(progLang1);
            return new ApiResponse("Programming language editing!", true);
        }
        return new ApiResponse("Programming language not found!", false);
    }

    public ApiResponse deleteProgLang(Integer id) {
        try {
            progLangRepository.deleteById(id);
            return new ApiResponse("Prog Lang deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Programming langiage not found!", false);
        }
    }

}

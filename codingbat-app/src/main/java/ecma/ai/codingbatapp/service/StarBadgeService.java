package ecma.ai.codingbatapp.service;

import ecma.ai.codingbatapp.entity.ProgLang;
import ecma.ai.codingbatapp.entity.StarBadge;
import ecma.ai.codingbatapp.entity.enums.StarBadgeValue;
import ecma.ai.codingbatapp.payload.ApiResponse;
import ecma.ai.codingbatapp.payload.StarBadgeDto;
import ecma.ai.codingbatapp.repository.ProgLangRepository;
import ecma.ai.codingbatapp.repository.StarBadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StarBadgeService {

    @Autowired
    StarBadgeRepository starBadgeRepository;
    @Autowired
    ProgLangRepository progLangRepository;

    public List<StarBadge> getStarBadge() {
        return starBadgeRepository.findAll();
    }

    public StarBadge getStarBadgeById(Integer id) {
        Optional<StarBadge> optionalStarBadge = starBadgeRepository.findById(id);
        return optionalStarBadge.orElse(null);
    }

    public ApiResponse addStarBadge(StarBadgeDto starBadgeDto) {
        StarBadge starBadge = new StarBadge();
        Optional<ProgLang> optionalProgLang = progLangRepository.findById(starBadgeDto.getLanguageId());
        if (!optionalProgLang.isPresent()) {
            return new ApiResponse("Programming language not found!", false);
        }
        starBadge.setLanguage(optionalProgLang.get());

        starBadge.setValue(starBadgeDto.getValue());
        starBadgeRepository.save(starBadge);
        return new ApiResponse("Star badge added!", false);
    }

    public ApiResponse editStarBadge(Integer id, StarBadgeDto starBadgeDto) {
        Optional<StarBadge> optionalStarBadge = starBadgeRepository.findById(id);
        if (optionalStarBadge.isPresent()) {
            StarBadge starBadge = optionalStarBadge.get();
            Optional<ProgLang> optionalProgLang = progLangRepository.findById(starBadgeDto.getLanguageId());
            if (!optionalProgLang.isPresent()) {
                return new ApiResponse("Programming language not found!", false);
            }
            starBadge.setLanguage(optionalProgLang.get());
            starBadge.setValue(starBadgeDto.getValue());
            starBadgeRepository.save(starBadge);
            return new ApiResponse("Star badge editing!", false);
        }
        return new ApiResponse("Star badge not found!", false);
    }

    public ApiResponse deleteStarBadge(Integer id) {
        try {
            starBadgeRepository.deleteById(id);
            return new ApiResponse("Star badge deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Star badge not found!", false);
        }
    }

}

package com.koreait.cgvproject.service.admin.price;

import com.koreait.cgvproject.dto.PriceDTO;
import com.koreait.cgvproject.entity.Theater;
import com.koreait.cgvproject.repository.PriceRepository;
import com.koreait.cgvproject.repository.TheaterRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminPriceService {

    private PriceRepository priceRepository;
    private TheaterRepository theaterRepository;

    @Transactional
    public void create(PriceDTO priceDTO) {
        Optional<Theater> theater = theaterRepository.findById(priceDTO.getTcode());
        theater.ifPresent(thea -> {
            priceRepository.save(priceDTO.toEntityForCreateTheaterRequired(thea));
        });
    }
}

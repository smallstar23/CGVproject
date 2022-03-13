package com.koreait.cgvproject.service.admin.price;

import com.koreait.cgvproject.dto.PriceDTO;
import com.koreait.cgvproject.entity.Price;
import com.koreait.cgvproject.entity.Theater;
import com.koreait.cgvproject.repository.PriceRepository;
import com.koreait.cgvproject.repository.TheaterRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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

    @Transactional
    public List<PriceDTO> read(Long tcode){
        List<PriceDTO> priceDTOList = new ArrayList<>();
        Optional<Theater> theater = theaterRepository.findById(tcode);
        List<Price> priceList = new ArrayList<>();
        if(theater.isPresent()){
            priceList = priceRepository.findAllByTheaterOrderByWeekDescStartTimeAsc(theater.get());
        }
        if(priceList != null){
            priceList.forEach(price -> priceDTOList.add(price.toDTO()));
        }
        return priceDTOList;
    }

    @Transactional
    public boolean isExist(Long tcode){
        Optional<Theater> theater = theaterRepository.findById(tcode);
        Optional<Price> price = Optional.empty();
        if(theater.isPresent()){
            price = priceRepository.findFirstByTheater(theater.get());
        }
        return price.isPresent();
    }

    @Transactional
    public void update(PriceDTO priceDTO){
        Optional<Price> price = priceRepository.findById(priceDTO.getPcode());
        price.ifPresent(priceEntity -> {
            priceEntity.setAdultPrice(priceDTO.getAdultPrice());
            priceEntity.setStuPrice(priceDTO.getStuPrice());
            priceRepository.save(priceEntity);
        });
    }

    @Transactional
    public void delete(Long tcode){
        Optional<Theater> theaterOptional = theaterRepository.findById(tcode);
        theaterOptional.ifPresent(theater -> {
            priceRepository.deleteAllByTheater(theater);
        });
    }
}

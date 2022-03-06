package com.koreait.cgvproject.service.user.store;

import com.koreait.cgvproject.dto.GiftDTO;
import com.koreait.cgvproject.entity.Gift;
import com.koreait.cgvproject.repository.GiftRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserStoreService {

    private GiftRepository giftRepository;

    public List<GiftDTO> getGiftDTOList(String cno){
        List<GiftDTO> giftDTOList = new ArrayList<>();
        List<Gift> giftList = giftRepository.findByCategoryOrderByGcode(cno);
        for(Gift gift : giftList) giftDTOList.add(gift.toDTO());
        return giftDTOList;
    }

}

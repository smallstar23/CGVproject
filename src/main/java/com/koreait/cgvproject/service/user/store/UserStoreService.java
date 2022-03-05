package com.koreait.cgvproject.service.user.store;

import com.koreait.cgvproject.dto.GiftDTO;
import com.koreait.cgvproject.entity.Gift;
import com.koreait.cgvproject.repository.GiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserStoreService {

    @Autowired
    private GiftRepository giftRepository;

    public List<GiftDTO> getGiftDTOList(String cno){
        List<Gift> giftList = giftRepository.findAll();
        System.out.println(giftList);
        return giftList.stream().map(Gift::toDTO)
                .collect(Collectors.toList());
    }

}

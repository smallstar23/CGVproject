package com.koreait.cgvproject.service.user.store;

import com.koreait.cgvproject.model.dto.GiftDTO;
import com.koreait.cgvproject.model.entity.Gift;
import com.koreait.cgvproject.repository.GiftRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public GiftDTO getGiftDTO(Long gcode){
        Optional<Gift> select = giftRepository.findById(gcode);
        GiftDTO giftDTO = new GiftDTO();
        if(select.isPresent()) giftDTO = select.get().toDTO();
        return giftDTO;
    }

    public Map<String ,List<GiftDTO>> getMainDTOList(){
        List<Gift> giftList = giftRepository.findAllByMainonGreaterThan(0);
        List<GiftDTO> movGifDTOList = new ArrayList<>(); List<GiftDTO> comGifDTOList = new ArrayList<>();
        List<GiftDTO> popGifDTOList = new ArrayList<>(); List<GiftDTO> bevGifDTOList = new ArrayList<>();
        List<GiftDTO> snaGifDTOList = new ArrayList<>();
        for(Gift gift: giftList){
            if(gift.getCategory().equals("2")) movGifDTOList.add(gift.toDTO()); if(gift.getCategory().equals("4")) comGifDTOList.add(gift.toDTO()); if(gift.getCategory().equals("5")) popGifDTOList.add(gift.toDTO());
            if(gift.getCategory().equals("6")) bevGifDTOList.add(gift.toDTO()); if(gift.getCategory().equals("7")) snaGifDTOList.add(gift.toDTO());
        }
        Map<String, List<GiftDTO>> giftDTOMap = new HashMap<>();
        giftDTOMap.put("movie", movGifDTOList); giftDTOMap.put("combo", comGifDTOList); giftDTOMap.put("popcorn", popGifDTOList);
        giftDTOMap.put("drink", bevGifDTOList); giftDTOMap.put("snack", snaGifDTOList);

        return giftDTOMap;
    }
}

package com.lagou.edu.ad.remote;

import com.lagou.edu.ad.entity.PromotionSpace;
import com.lagou.edu.ad.service.IPromotionSpaceService;
import com.lagou.edu.dto.PromotionSpaceDTO;
import com.lagou.edu.remote.AdRemoteService;
import com.lagou.edu.util.ConverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/ad")
public class AdRemoteServiceImpl implements AdRemoteService {


    @Autowired
    private IPromotionSpaceService promotionSpaceService;

    @GetMapping("/space/getAllSpace")
    @Override
    public List<PromotionSpaceDTO> getAllSpaces() {
        List<PromotionSpace> spaceList = promotionSpaceService.list();
        return ConverUtil.convertList(spaceList, PromotionSpaceDTO.class);
    }
}

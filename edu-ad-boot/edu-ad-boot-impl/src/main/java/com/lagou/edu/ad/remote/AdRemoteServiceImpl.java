package com.lagou.edu.ad.remote;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lagou.edu.ad.entity.PromotionAd;
import com.lagou.edu.ad.entity.PromotionSpace;
import com.lagou.edu.ad.service.IPromotionAdService;
import com.lagou.edu.ad.service.IPromotionSpaceService;
import com.lagou.edu.dto.PromotionAdDTO;
import com.lagou.edu.dto.PromotionSpaceDTO;
import com.lagou.edu.remote.AdRemoteService;
import com.lagou.edu.util.ConverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/ad")
public class AdRemoteServiceImpl implements AdRemoteService {


    @Autowired
    private IPromotionSpaceService promotionSpaceService;

    @Autowired
    private IPromotionAdService promotionAdService;

    @Override
    @GetMapping("/space/getAllSpace")
    public List<PromotionSpaceDTO> getAllSpaces() {
        List<PromotionSpace> spaceList = promotionSpaceService.list();
        return ConverUtil.convertList(spaceList, PromotionSpaceDTO.class);
    }

    @Override
    @GetMapping("/getAdBySpaceKey")
    public List<PromotionSpaceDTO> getAdBySpaceKey(String[] spaceKey) {
        List<PromotionSpaceDTO> spaceDTOList = new ArrayList<>();

        for (String key : spaceKey) {
            QueryWrapper<PromotionSpace> spaceQueryWrapper = new QueryWrapper<>();
            spaceQueryWrapper.eq("spaceKey", key);
            // 获取广告位对象
            PromotionSpace promotionSpace = promotionSpaceService.getOne(spaceQueryWrapper);

            // 获取当前广告位对应的广告
            QueryWrapper<PromotionAd> adQueryWrapper = new QueryWrapper<>();
            adQueryWrapper.eq("spaceId", promotionSpace.getId());
            // 添加条件，状态为上架状态
            adQueryWrapper.eq("status", 1);
            // 当前日期处于有效期之内
            Date now = new Date();
            adQueryWrapper.lt("startTime", now);
            adQueryWrapper.gt("endTime", now);


            List<PromotionAd> adList = promotionAdService.list(adQueryWrapper);
            // adList转换为adDTOList
            List<PromotionAdDTO> adDTOList = ConverUtil.convertList(adList, PromotionAdDTO.class);


            // 转换为PromotionSpaceDTO对象
            PromotionSpaceDTO promotionSpaceDTO = ConverUtil.convert(promotionSpace, PromotionSpaceDTO.class);
            promotionSpaceDTO.setAdDTOList(adDTOList);

            spaceDTOList.add(promotionSpaceDTO);
        }

        return spaceDTOList;
    }
}

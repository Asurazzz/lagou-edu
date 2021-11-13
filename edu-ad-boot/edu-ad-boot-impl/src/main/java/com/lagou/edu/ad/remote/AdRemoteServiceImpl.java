package com.lagou.edu.ad.remote;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lagou.edu.ad.entity.PromotionAd;
import com.lagou.edu.ad.entity.PromotionSpace;
import com.lagou.edu.ad.service.IPromotionAdService;
import com.lagou.edu.ad.service.IPromotionSpaceService;
import com.lagou.edu.dto.PromotionAdDTO;
import com.lagou.edu.dto.PromotionSpaceDTO;
import com.lagou.edu.remote.AdRemoteService;
import com.lagou.edu.response.ResponseDTO;
import com.lagou.edu.util.ConverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Override
    @PostMapping("/space/saveOrUpdateSpace")
    public ResponseDTO saveOrUpdateSpace(PromotionSpaceDTO spaceDTO) {
        PromotionSpace space = ConverUtil.convert(spaceDTO, PromotionSpace.class);
        if (space.getId() == null) {
            space.setCreateTime(new Date());
            space.setUpdateTime(new Date());
            space.setIsDel(0);
        } else {
            space.setUpdateTime(new Date());
        }
        // 保存或者编辑广告位
        ResponseDTO responseDTO = null;
        try {
            promotionSpaceService.saveOrUpdate(space);
            responseDTO = ResponseDTO.success();
        } catch (Exception ex) {
            responseDTO = ResponseDTO.ofError(ex.getMessage());
        }
        return responseDTO;
    }

    @Override
    @GetMapping("/space/getSpaceById")
    public PromotionSpaceDTO getSpaceById(Integer id) {
        PromotionSpace space = promotionSpaceService.getById(id);
        return ConverUtil.convert(space, PromotionSpaceDTO.class);
    }

    @Override
    @GetMapping("/getAllAds")
    public List<PromotionAdDTO> getAllAds() {
        List<PromotionAd> adList = promotionAdService.list();
        return ConverUtil.convertList(adList, PromotionAdDTO.class);
    }

    @Override
    @PostMapping("/saveOrUpdateAd")
    public ResponseDTO saveOrUpdateAd(PromotionAdDTO adDTO) {
        PromotionAd ad = ConverUtil.convert(adDTO, PromotionAd.class);
        if (ad.getId() == null) {
            ad.setCreateTime(new Date());
            ad.setUpdateTime(new Date());
            ad.setStatus(1);
        } else {
            ad.setUpdateTime(new Date());
        }

        ResponseDTO responseDTO = null;
        try {
            promotionAdService.saveOrUpdate(ad);
            responseDTO = ResponseDTO.success();
        } catch (Exception ex) {
            responseDTO = ResponseDTO.ofError(ex.getMessage());
        }
        return responseDTO;
    }

    @Override
    @GetMapping("/getAdById")
    public PromotionAdDTO getAdById(Integer id) {
        PromotionAd ad = promotionAdService.getById(id);
        return ConverUtil.convert(ad, PromotionAdDTO.class);
    }
}

package com.lagou.edu.ad.controller;


import com.lagou.edu.dto.PromotionAdDTO;
import com.lagou.edu.dto.PromotionSpaceDTO;
import com.lagou.edu.remote.AdRemoteService;
import com.lagou.edu.response.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private AdRemoteService adRemoteService;

    @RequestMapping("/space/getAllSpaces")
    public ResponseDTO getAllSpaces() {
        List<PromotionSpaceDTO> spaceDTOList = adRemoteService.getAllSpaces();
        return ResponseDTO.success(spaceDTOList);
    }


    /**
     * 更新
     * @param spaceDTO
     * @return
     */
    @PostMapping("/space/saveOrUpdateSpace")
    public ResponseDTO saveOrUpdateSpace(@RequestBody PromotionSpaceDTO spaceDTO) {
        ResponseDTO responseDTO = adRemoteService.saveOrUpdateSpace(spaceDTO);
        return responseDTO;
    }

    /**
     * 根据id获取对应的广告位
     * @param id
     * @return
     */
    @GetMapping("/space/getSpaceById")
    public ResponseDTO getSpaceById(@RequestParam("id")Integer id) {
        PromotionSpaceDTO spaceDTO = adRemoteService.getSpaceById(id);
        return ResponseDTO.success(spaceDTO);
    }

    /**
     * 获取所有的广告位
     * @return
     */
    @GetMapping("/getAllAds")
    public ResponseDTO getAllAds() {
        List<PromotionAdDTO> adDTOList = adRemoteService.getAllAds();
        return ResponseDTO.success(adDTOList);
    }

    /**
     * 编辑或者添加广告
     * @param adDTO
     * @return
     */
    @PostMapping("/saveOrUpdateAd")
    public ResponseDTO saveOrUpdateAd(@RequestBody PromotionAdDTO adDTO) {
        ResponseDTO responseDTO = adRemoteService.saveOrUpdateAd(adDTO);
        return responseDTO;
    }


    /**
     * 根据id获取广告
     * @param id
     * @return
     */
    @GetMapping("/getAdById")
    public ResponseDTO getAdById(@RequestParam("id") Integer id) {
        PromotionAdDTO adDTO = adRemoteService.getAdById(id);
        return ResponseDTO.success(adDTO);
    }
}

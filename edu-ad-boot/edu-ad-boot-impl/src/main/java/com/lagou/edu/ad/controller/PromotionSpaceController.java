package com.lagou.edu.ad.controller;


import com.lagou.edu.ad.entity.PromotionSpace;
import com.lagou.edu.ad.service.IPromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author felix
 * @since 2021-11-13
 */
@RestController
@RequestMapping("/ad/space")
public class PromotionSpaceController {

    @Autowired
    private IPromotionSpaceService promotionSpaceService;

    @RequestMapping("/getAllSpaces")
    public List<PromotionSpace> getAllSpaces() {
        List<PromotionSpace> spaceList = promotionSpaceService.list();
        return spaceList;
    }

}

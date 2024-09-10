package com.heima.search.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.search.dtos.UserSearchDto;
import com.heima.model.search.pojos.ApUserSearch;
import com.heima.model.user.pojos.ApUser;

import com.heima.search.mapper.ApUserSearchMapper;
import com.heima.search.service.ApUserSearchService;

import com.heima.utils.thread.AppThreadLocalUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Log4j2
public class ApUserSearchServiceImpl extends ServiceImpl<ApUserSearchMapper, ApUserSearch> implements ApUserSearchService {

    @Override
    public ResponseResult findUserSearch(UserSearchDto userSearchDto) {
        //1.检查数据
        if(userSearchDto.getPageSize() > 50){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        ApUser user = AppThreadLocalUtils.getUser();

        //3.分页查询，默认查询5条数据返回
        IPage pageParam = new Page(0, userSearchDto.getPageSize());
        IPage page = page(pageParam, new LambdaQueryWrapper<ApUserSearch>().eq(ApUserSearch::getEntryId, user.getId())
                .eq(ApUserSearch::getStatus, 1).orderByDesc(ApUserSearch::getCreatedTime));

        return ResponseResult.okResult(page.getRecords().stream().limit(10));
    }



    @Override
    @Async("taskExecutor")
    public void insert(String key , Integer userId) {
//        int a = 1/0;
        //1.查询当前搜索记录
        ApUserSearch apUserSearch = getOne(Wrappers.<ApUserSearch>lambdaQuery().eq(ApUserSearch::getEntryId, userId).eq(ApUserSearch::getKeyword, key));

        //2.如果存在 更新状态
        if(apUserSearch != null && apUserSearch.getStatus() == 1){
            log.info("当前关键字已存在，无需再次保存");
            return;
        }else if(apUserSearch != null && apUserSearch.getStatus() == 0){
            apUserSearch.setStatus(1);
            updateById(apUserSearch);
            return;
        }

        //3.如果不存在，保存新的数据
        apUserSearch = new ApUserSearch();
        apUserSearch.setEntryId(userId);
        apUserSearch.setStatus(1);
        apUserSearch.setKeyword(key);
        apUserSearch.setCreatedTime(new Date());
        save(apUserSearch);
    }
}

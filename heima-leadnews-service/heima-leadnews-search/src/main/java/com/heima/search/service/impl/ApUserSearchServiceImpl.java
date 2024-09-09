package com.heima.search.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.search.dtos.UserSearchDto;
import com.heima.model.search.pojos.ApUserSearch;
import com.heima.model.user.pojos.ApUser;
import com.heima.search.service.ApUserSearchService;
import com.heima.utils.thread.AppThreadLocalUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@Log4j2
public class ApUserSearchServiceImpl implements ApUserSearchService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Async
    @Override
    public void insert(String key, Integer userId) {
        Query q1 =Query.query(Criteria.where("keyword").is(key).and("userId").is(userId));
         ApUserSearch apUserSearch =mongoTemplate.findOne(q1, ApUserSearch.class);
         if(apUserSearch != null){
             apUserSearch.setCreatedTime(new Date());
             mongoTemplate.save(apUserSearch);
             return ;
         }
         apUserSearch = new ApUserSearch();
         apUserSearch.setUserId(userId);
         apUserSearch.setKeyword(key);
         apUserSearch.setCreatedTime(new Date());

         Query q2 =Query.query(Criteria.where("userId").is(userId))
                 .with(Sort.by(Sort.Direction.ASC,"createdTime"));

        List<ApUserSearch> searchList   =mongoTemplate.find(q2 ,ApUserSearch.class);
        if(searchList ==null || searchList.size() <10){
            mongoTemplate.save(apUserSearch);
        }
        else{
            ApUserSearch ap=searchList.get(0) ;
            mongoTemplate.findAndReplace(Query.query(Criteria.where("id").is(ap.getId())),
                    apUserSearch);
        }
    }

    @Override
    public ResponseResult findUserSearch() {
        ApUser user = AppThreadLocalUtils.getUser();
        if(user ==null) {
            return ResponseResult.errorResult(404,"未登录") ;
        }

//        3. 根据行为实体id查询 历史记录
//        默认查询10条历史记录，并且按照时间降序排序
        Query query = Query.query(Criteria.where("userId").is(user.getId()))
                .with(Sort.by(Sort.Direction.DESC, "createdTime"))
                .limit(10);
        List<ApUserSearch> apUserSearchList = mongoTemplate.find(query, ApUserSearch.class);

        return ResponseResult.okResult(apUserSearchList);
    }
}
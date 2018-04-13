package com.sundy.service.impl;

import com.github.pagehelper.Page;
import com.sundy.common.redis.impl.RedisManagerImpl;
import com.sundy.dao.ShareFileMapper;
import com.sundy.model.entity.ShareFile;
import com.sundy.service.ShareFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sundy
 * @date 2018年04月08日 11:41
 */
@Service
@Transactional
public class ShareFileServiceImpl extends BaseServiceImpl<ShareFile> implements ShareFileService {


    @Value("${tb_share_file_findEntityAll}")
    private String tb_share_file_findEntityAll; //tb_share_file表的数据存储redis中的key

    @Autowired
    private RedisManagerImpl redis;

    @Autowired
    private ShareFileMapper shareFileMapper;

    @Override
    public Page<ShareFile> findEntityAll() {
        Boolean exists = redis.exists(tb_share_file_findEntityAll);
        Page<ShareFile> entityAll = null;
//        if(exists){
//            long time1 = System.currentTimeMillis();
//            String text = redis.get(tb_share_file_findEntityAll);
//            List<ShareFile> shareFiles = JSON.parseArray(text, ShareFile.class);
//            entityAll = new Page<>();
//            entityAll.addAll(shareFiles);
//            long time2 = System.currentTimeMillis();
//            System.out.println(entityAll.size()+"  耗时1 ： "+(time2-time1));//耗时1 ： 91
//            long time3 = System.currentTimeMillis();
//            System.out.println(shareFileMapper.selectAll().size()+" 耗时1 ： "+(time3-time1));//耗时2 ： 722
//            return super.findEntityAll();
//        }else{
//            long time1 = System.currentTimeMillis();
//            entityAll = super.findEntityAll();
//            redis.set(tb_share_file_findEntityAll,JSON.toJSONString(entityAll));
//            long time2 = System.currentTimeMillis();
//            System.out.println(entityAll.size()+" 耗时2 ： "+(time2-time1));//耗时2 ： 722
//            long time3 = System.currentTimeMillis();
//            System.out.println(shareFileMapper.selectAll().size()+" 耗时2 ： "+(time3-time1));//耗时2 ： 722
//            return super.findEntityAll();
//        }
        return super.findEntityAll();
    }
}

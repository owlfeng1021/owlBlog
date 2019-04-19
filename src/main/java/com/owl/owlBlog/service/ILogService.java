package com.owl.owlBlog.service;

import com.owl.owlBlog.dao.LogDao;
import com.owl.owlBlog.pojo.Log;
import com.owl.owlBlog.util.DateKit;
import com.owl.owlBlog.util.IdWorker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

@Service
public class ILogService {
    @Resource
    LogDao logDao;
    /**
     * 保存操作日志
     *
     * @param logVo
     */
    public void insertLog(Log logVo){
        logVo.setId(new IdWorker().nextId()+"");
        logDao.save(logVo);
    }

    /**
     *  保存
     * @param action
     * @param data
     * @param ip
     * @param authorId
     */
    public void insertLog(String action, String data, String ip, String authorId){
        Log log = new Log();
        log.setAction(action);
        log.setData(data);
        log.setIp(ip);
        log.setAuthorId(authorId);
//      格式化unix时间 Long.toString(new Date().getTime())
        log.setCreated(DateKit.getCurrentUnixTime()); // new Data()  getTime()/1000L
        // 使用IdWorker 生成id
        log.setId(new IdWorker().nextId()+"");
        logDao.save(log);
    }

    /**
     * 获取日志分页
     * @param page 当前页
     * @param limit 每页条数
     * @return 日志
     */
    public  List<Log> getLogs(int page, int limit){
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        Pageable pageable = PageRequest.of(page-1, limit,sort);
        Page<Log> all = logDao.findAll(pageable);
        return  all.getContent() ;
    }

}

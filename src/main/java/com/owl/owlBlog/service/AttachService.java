package com.owl.owlBlog.service;

import com.owl.owlBlog.dao.AttachDao;
import com.owl.owlBlog.pojo.Attach;
import com.owl.owlBlog.pojo.Comment;
import com.owl.owlBlog.util.DateKit;
import com.owl.owlBlog.util.IdWorker;
import com.owl.owlBlog.util.Page4Navigator;
import com.owl.owlBlog.util.TaleUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AttachService {
    @Resource
    AttachDao attachDao;
    @Resource
    IdWorker idWorker;

    @Cacheable(value = "AttachList")
    public List<Attach> list(){
        return  attachDao.findAll();
    }
    @Cacheable(value = "AttachById",key = "#p0")
    public Attach get(String id){
        return  attachDao.findById(id).get();
    }

    public Page4Navigator<Attach> getAttachs(int page, int limit) {
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        Pageable pageable = PageRequest.of(page-1, limit, sort);
        Page<Attach> byAuthorIdNot = attachDao.findAll(pageable);
        return  new Page4Navigator<Attach>(byAuthorIdNot,5);
}
    @Cacheable(value = "AttachById",key = "#p0")
    public Attach selectById(String id){
        return attachDao.findById(id).get();
    }

    @CacheEvict(value = "AttachById")
    public void deleteById(String id){
         attachDao.deleteById(id);
    }


    public void save(String fname, String fkey, String ftype, String uid) {
        Attach attach = new Attach();
        attach.setFname(fname);
        attach.setFkey(fkey);
        attach.setFtype(ftype);
        attach.setAuthorId(uid);
        attach.setCreated(DateKit.getCurrentUnixTime());
        attach.setId(idWorker.nextId()+"");
        attachDao.save(attach);
    }
}

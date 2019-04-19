package com.owl.owlBlog.service;

import com.owl.owlBlog.dao.AttachDao;
import com.owl.owlBlog.pojo.Attach;
import com.owl.owlBlog.pojo.Comment;
import com.owl.owlBlog.util.Page4Navigator;
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

    public List<Attach> list(){
        return  attachDao.findAll();
    }
    public Attach get(String id){
        return  attachDao.getOne(id);
    }

    public Page4Navigator<Attach> getAttachs(int page, int limit) {
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        Pageable pageable = PageRequest.of(page-1, limit, sort);
        Page<Attach> byAuthorIdNot = attachDao.findAll(pageable);
        return  new Page4Navigator<Attach>(byAuthorIdNot,5);
    }
}

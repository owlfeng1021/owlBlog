package com.owl.owlBlog.service;

import com.owl.owlBlog.dao.AttachDao;
import com.owl.owlBlog.pojo.Attach;
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

}

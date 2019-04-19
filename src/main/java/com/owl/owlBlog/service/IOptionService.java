package com.owl.owlBlog.service;

import com.owl.owlBlog.dao.OptionDao;
import com.owl.owlBlog.pojo.Option;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IOptionService {
    @Resource
    OptionDao optionDao;

    public Option getOptionByName(String name){
        return  optionDao.findByName(name);
    }
    public List<Option> getOptionList(){
        return  optionDao.findAll();
    }
}

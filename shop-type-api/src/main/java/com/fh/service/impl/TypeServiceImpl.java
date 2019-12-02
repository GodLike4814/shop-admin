package com.fh.service.impl;

import com.fh.bean.TypeBean;
import com.fh.dao.ITypeDao;
import com.fh.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TypeServiceImpl implements ITypeService {
    @Autowired
    ITypeDao typeDao;

    @Override
    public List<Map<String, Object>> queryTypeList() {
        List<TypeBean> categoryBeans = typeDao.queryTypeList();
        return getCategory(0, categoryBeans);
    }

    private List<Map<String, Object>> getCategory(Integer pid, List<TypeBean> categoryBeans) {
        List<Map<String, Object>> list = new ArrayList<>();
        categoryBeans.forEach(cate -> {
            Map<String, Object> map = null;
            if (pid.equals(cate.getPid())) {
                map = new HashMap<>();
                map.put("id", cate.getId());
                map.put("name", cate.getName());
                map.put("pid", cate.getPid());
                map.put("children", getCategory(cate.getId(), categoryBeans));
            }
            if (map != null) {
                list.add(map);
            }
        });
        System.out.println();
        return list;
    }

}

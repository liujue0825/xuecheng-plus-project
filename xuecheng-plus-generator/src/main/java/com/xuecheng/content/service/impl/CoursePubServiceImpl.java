package com.xuecheng.content.service.impl;

import com.xuecheng.content.model.po.CoursePub;
import com.xuecheng.content.mapper.CoursePubMapper;
import com.xuecheng.content.service.CoursePubService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 课程发布 服务实现类
 * </p>
 *
 * @author liujue
 */
@Slf4j
@Service
public class CoursePubServiceImpl extends ServiceImpl<CoursePubMapper, CoursePub> implements CoursePubService {

}

package com.tensquare.service.impl;


import com.tensquare.model.Article;
import com.tensquare.model.Channel;
import com.tensquare.repository.ChannelRepository;
import com.tensquare.service.ChannelService;
import com.tensquare.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    IdWorker idWorker;

    @Override
    public List<Channel> findAll() {
        return channelRepository.findAll();
    }

    @Override
    public Channel findById(String channelId) {
        return channelRepository.findById(channelId).get();
    }

    @Override
    public void save(Channel channel) {

        channel.setId(idWorker.nextId()+"");
        channelRepository.save(channel);
    }

    @Override
    public void update(Channel channel) {

        channelRepository.save(channel);
    }

    @Override
    public void deleteById(String channelId) {
        channelRepository.deleteById(channelId);
    }

    @Override
    public List<Channel> search(Channel channel) {
        List<Channel> list = channelRepository.findAll(new Specification<Channel>() {

            @Nullable
            @Override
            public Predicate toPredicate(Root<Channel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;
                List <Predicate> lists = new ArrayList<>();
                if (channel != null && !"".equals(channel.getName())){
                    predicate = criteriaBuilder.like(root.get("title").as(String.class), "%" + channel.getName() + "%");
                    lists.add(predicate);
                }
                if (channel != null && !"".equals(channel.getId())){

                    predicate = criteriaBuilder.like(root.get("id").as(String.class), "%" + channel.getId() + "%");
                    lists.add(predicate);
                }
                Predicate[] array = lists.toArray(new Predicate[lists.size()]);
                return criteriaBuilder.and(array);
            }
        });
        return list;
    }

    @Override
    public Page<Channel> searchPage(Channel channel, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo-1, size);
        Page<Channel> page1 = channelRepository.findAll(new Specification <Channel>() {
            Predicate predicate = null;
            @Nullable
            @Override
            public Predicate toPredicate(Root <Channel> root, CriteriaQuery <?> query, CriteriaBuilder criteriaBuilder) {
                List <Predicate> listl = new ArrayList <>();
                if (channel != null && !"".equals(channel.getName())){
                    predicate = criteriaBuilder.like(root.get("title").as(String.class), "%" + channel.getName() + "%");
                    listl.add(predicate);
                }
                if (channel != null && !"".equals(channel.getId())){

                    predicate = criteriaBuilder.like(root.get("id").as(String.class), "%" + channel.getId() + "%");
                    listl.add(predicate);
                }
                Predicate[] array = listl.toArray(new Predicate[listl.size()]);
                return criteriaBuilder.and(array);
            }
        },pageable);
        return page1;
    }
}

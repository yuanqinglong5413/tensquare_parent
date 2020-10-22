package com.tensquare.service;


import com.tensquare.model.Channel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ChannelService {

    List<Channel> findAll();

    Channel findById(String channelId);

    void save(Channel channel);

    void update(Channel channel);

    void deleteById(String channelId);

    List<Channel> search(Channel channel);

    Page<Channel> searchPage(Channel channel, Integer pageNo, Integer size);
}

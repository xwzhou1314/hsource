package com.hsource.search.repository;

import com.hsource.search.pojo.Invitations;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface InvitationsRepository extends ElasticsearchRepository<Invitations, Long> {
}

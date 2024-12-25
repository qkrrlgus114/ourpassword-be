package com.park.ourpassword.domain.member.repository;

import com.park.ourpassword.domain.member.entity.VisitorHistory;

import java.util.List;

public interface VisitorJDBCRepository {

    void bulkInsert(List<VisitorHistory> visitorHistories);

}

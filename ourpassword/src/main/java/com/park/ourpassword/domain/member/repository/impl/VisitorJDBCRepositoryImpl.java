package com.park.ourpassword.domain.member.repository.impl;

import com.park.ourpassword.domain.member.entity.VisitorHistory;
import com.park.ourpassword.domain.member.repository.VisitorJDBCRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class VisitorJDBCRepositoryImpl implements VisitorJDBCRepository {

    private final DataSource dataSource;
    private static final String SQL = "INSERT INTO visitor_history (ip, access_at) VALUES(?, ?)";

    @Override
    @Transactional
    public void bulkInsert(List<VisitorHistory> visitorHistories) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
            int batchSize = 100;
            int count = 0;

            for (VisitorHistory visitorHistory : visitorHistories) {
                ps.setString(1, visitorHistory.getIp());
                ps.setTimestamp(2, Timestamp.valueOf(visitorHistory.getAccessAt()));
                ps.addBatch();
                count++;

                if (batchSize % count == 0) {
                    ps.executeBatch();
                }
            }
            ps.executeBatch();
        } catch (Exception e) {
            log.error("Visitor BulkInsert ERROR : {}", e.getMessage());

            try (Connection conn = dataSource.getConnection();
                 PreparedStatement ps = conn.prepareStatement(SQL)) {

                for (VisitorHistory visitorHistory : visitorHistories) {
                    ps.setString(1, visitorHistory.getIp());
                    ps.setTimestamp(2, Timestamp.valueOf(visitorHistory.getAccessAt()));
                    ps.execute();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

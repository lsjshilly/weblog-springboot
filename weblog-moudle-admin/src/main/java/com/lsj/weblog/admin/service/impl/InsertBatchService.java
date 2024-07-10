package com.lsj.weblog.admin.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class InsertBatchService<T> {


    private static final int BatchSize = 1000;

    private final SqlSessionFactory sqlSessionFactory;

    public int insertBatch(List<T> records, String statementId) {
        // 检查records是否为空，避免不必要的操作
        if (records == null || records.isEmpty()) {
            return 0;
        }

        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        int val = 0;
        try {
            for (int index = 1; index <= records.size(); index++) {
                val += sqlSession.insert(statementId, records.get(index - 1));
                if (index % BatchSize == 0 || index == records.size()) {
                    sqlSession.flushStatements();
                }
            }
            sqlSession.commit(true);
        } catch (Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return val;
    }

}

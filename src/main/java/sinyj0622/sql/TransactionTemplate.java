package sinyj0622.sql;

public class TransactionTemplate {

	PlatformTransactionManager txManager;

	public TransactionTemplate(PlatformTransactionManager txManager) {
		this.txManager = txManager;
	}

	public Object execute(TransactionCallback action) throws Exception {
		// 트랜잭션 시작
		txManager.beginTransaction();

		try {
			Object result = null;
			// 트랜잭션으로 묶을 부분, 리턴값이 있다면 저장
			result = action.doInTransaction();
			// 정상적으로 실행한다면 
			txManager.commit();
			return result;
			
		} catch (Exception e) {
			txManager.rollback();
			
			throw e;
		}
	}
}

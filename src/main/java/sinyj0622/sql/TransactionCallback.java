package sinyj0622.sql;

public interface TransactionCallback {

	Object doInTransaction() throws Exception;
}

package sinyj0622.sql;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MybatisDaoFactory {

	InvocationHandler invocationHander;

	public MybatisDaoFactory(SqlSessionFactory sqlSessionFactory) {

		invocationHander = (proxy, method, args) -> {

			// sql id 알아내기
			Class<?> clazz = proxy.getClass(); // 프록시 객체의 정보 얻기
			Class<?> daoInterface = clazz.getInterfaces()[0]; // 프록시 객체가 구현한 인터페이스 정보 얻기
			String interfaceName = daoInterface.getName();
			String methodName = method.getName();
			String sqlId = String.format("%s.%s",interfaceName, methodName);
			System.out.println(sqlId);

			// 리턴 타입에 따라 메서드 호출한다
			try(SqlSession sqlSession = sqlSessionFactory.openSession()){
				Class<?> returnType = method.getReturnType();
				if (returnType == List.class) {
					return (args == null) ? sqlSession.selectList(sqlId) :
						sqlSession.selectList(sqlId, args[0]);
				} else if (returnType == int.class || returnType == void.class) {
					return (args == null) ? sqlSession.update(sqlId) :
						sqlSession.update(sqlId, args[0]);  // update()는 insert(), delete() 과 같다
				} else {
					return (args == null) ? sqlSession.selectOne(sqlId) : 
						sqlSession.selectOne(sqlId, args[0]);
				}
			}
		};

	}


	@SuppressWarnings("unchecked")
	public <T> T createDao(Class<T> daoInterface) {
		return (T)Proxy.newProxyInstance(this.getClass().getClassLoader(),
				new Class[] {daoInterface}, invocationHander);
	}
}

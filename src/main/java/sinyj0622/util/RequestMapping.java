package sinyj0622.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 실행중 애노테이션 정보 추출
@Retention(RetentionPolicy.RUNTIME)

// 사용범위 : 메서드
@Target({ElementType.METHOD})
public @interface RequestMapping {
  // 서블릿 메서드와 명령어를 연결하기 위해
  // 명령어를 저장할 프로퍼티 정의
  String value();
}

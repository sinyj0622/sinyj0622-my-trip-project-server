package sinyj0622.mytrip.dao.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public abstract class AbstractJsonFileDao<T> {

  protected String filename;
  protected List<T> list;

  public AbstractJsonFileDao(String filename) {
    this.filename = filename;
    this.list = new ArrayList<>();
    loadData();
  }

  @SuppressWarnings("unchecked")
  protected void loadData() {

    try (BufferedReader in = new BufferedReader(new FileReader(filename))) {

      // 현재 클래스 정보
      Class<?> currType = this.getClass();
      // 제네릭 타입의 수퍼 클래스 정보
      Type parentType = currType.getGenericSuperclass();
      // 수퍼클래스의 타입 파라미터 중에서 T값을 추출
      ParameterizedType parentType2 = (ParameterizedType) parentType;
      // 수퍼클래스 정보로부터 타입 파라미터 목록 꺼내기
      Type[] typeParams = parentType2.getActualTypeArguments();

      Type itemType = typeParams[0];

      T[] arr = (T[]) Array.newInstance((Class) itemType, 0);

      T[] dataArr = (T[]) new Gson().fromJson(in, arr.getClass());
      for (T b : dataArr) {
        list.add(b);
      }

      System.out.printf("총 %d개의 게시글 데이터를 로딩했습니다.\n", list.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }

  }

  protected void saveData() {

    try (BufferedWriter out = new BufferedWriter(new FileWriter(filename))) {

      out.write(new Gson().toJson(list));
      System.out.printf("총 %d개의 게시글을 저장하였습니다.\n", list.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());

    }
  }

  protected abstract <K> int indexOf(K key);

}

package sinyj0622.mytrip.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractObjectFileDao<T> {

  protected String filename;
  protected List<T> list;

  public AbstractObjectFileDao(String filename) {
    this.filename = filename;
    this.list = new ArrayList<>();
    loadData();
  }

  @SuppressWarnings("unchecked")
  protected void loadData() {

    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {

      list = (List<T>) in.readObject();

      System.out.printf("총 %d개의 게시글 데이터를 로딩했습니다.\n", list.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }

  }

  protected void saveData() {

    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))) {

      out.writeObject(list);

      System.out.printf("총 %d개의 게시글을 저장하였습니다.\n", list.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());

    }
  }

  protected abstract <K> int indexOf(K key);

}

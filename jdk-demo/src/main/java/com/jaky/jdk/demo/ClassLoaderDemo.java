package com.jaky.jdk.demo;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaomo.wj
 * @date 2018/9/28.
 */
public class ClassLoaderDemo {

  public static void main(String[] args) throws Exception {
    Class<?> aClass = Class.forName("com.xiaomo.XString", true, new MyClassLoader());
    Class<?> bClass = Class.forName("com.xiaomo.XString", true, new MyClassLoader());
    System.out.println(aClass);
    System.out.println(bClass);
    System.out.println(aClass.equals(bClass));
    System.out.println(aClass.newInstance());
    System.out.println(bClass.newInstance());

    System.out.println(ClassLoaderDemo.class.getClassLoader());
  }

  private static class MyClassLoader extends ClassLoader {

    /**
     * 如果不想遵循双亲委派模型，则可直接覆盖 此方法
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
      return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
      return findXStringClass();
    }

    private Class<?> findXStringClass() throws ClassNotFoundException {
      try {
        FileInputStream fileInputStream = new FileInputStream(new File("/Users/jaky.wang/tmp/test/XString.class"));
        List<Byte> byteList = new ArrayList<>();
        byte[] oneBytes = new byte[1024];
        int size = 0;
        while ((size = fileInputStream.read(oneBytes)) > 0) {
          for (int i = 0; i < size; i++) {
            byteList.add(oneBytes[i]);
          }
        }

        byte[] allBytes = new byte[byteList.size()];
        for (int i = 0; i < allBytes.length; i++) {
          allBytes[i] = byteList.get(i);
        }
        return defineClass("com.xiaomo.XString", allBytes, 0, allBytes.length);
      } catch (Exception e) {
        e.printStackTrace();
      }
      throw new ClassNotFoundException();
    }
  }
}

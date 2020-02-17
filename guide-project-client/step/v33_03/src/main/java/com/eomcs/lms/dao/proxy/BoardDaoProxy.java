package com.eomcs.lms.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardDaoProxy implements BoardDao {
  DaoProxyHelper daoProxyHelper;

  public BoardDaoProxy(DaoProxyHelper daoProxyHelper) {
    this.daoProxyHelper = daoProxyHelper;
  }

  @Override
  public int insert(Board board) throws Exception {
    class InsertWorker implements Worker {
      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/board/add");
        out.writeObject(board);
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL"))
          throw new Exception(in.readUTF());

        return 1;
      }
    }

    InsertWorker worker = new InsertWorker();
    return (int) daoProxyHelper.request(worker);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Board> findAll() throws Exception {
    return (List<Board>) daoProxyHelper.request(new Worker() {
      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/board/list");
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL"))
          throw new Exception(in.readUTF());

        return in.readObject();
      }
    });
  }

  @Override
  public Board findByNo(int no) throws Exception {
    Worker worker = new Worker() {
      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/board/detail");
        out.writeInt(no);
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL"))
          throw new Exception(in.readUTF());

        return in.readObject();
      }      
    };
    return (Board) daoProxyHelper.request(worker);
     
  }

  @Override
  public int update(Board board) throws Exception {
    return (int) daoProxyHelper.request(new Worker() {
      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/board/update");
        out.writeObject(board);
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL"))
          throw new Exception(in.readUTF());

        return 1;
      }
    });      
  }

  @Override
  public int delete(int no) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/board/delete");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL"))
        throw new Exception(in.readUTF());

      return 1;
    });
  }
}

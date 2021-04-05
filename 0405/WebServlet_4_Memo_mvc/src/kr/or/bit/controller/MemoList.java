package kr.or.bit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.dao.memodao;
import kr.or.bit.dto.memo;


@WebServlet("/MemoList")
public class MemoList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MemoList() {
        super();
        
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("[목록보기]");
    	
    	//요청을 받고 서비스를 수행하는 걸 servlet이 다 할거다
    	//전체 조회 요청이 들어왔다
    	
    	//무슨 서비스?(DB 작업 서비스) : DAO 만들어놨으니
    	memodao dao = new memodao();
    	
    	try {
    		
			List<memo> memolist =dao.getMemoList();
			
			//servlet은 화면에 출력해서 client에 전달
			//View 사용 (별도의 JSP)를 사용
			
			//데이터를 저장
			request.setAttribute("memolist", memolist);
			
			//view 페이지 설정
			RequestDispatcher dis = request.getRequestDispatcher("/memolist.jsp");
			
			
			//view페이지 forward 
			dis.forward(request, response);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
		
	}
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
		
	}

}

package kr.or.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.bit.dto.memo;
import kr.or.bit.utils.SingletonHelper;

/*
 1.db연결
 2.CRUD 함수 생성(1개의 테이블에 대해서 ) >> memo테이블에서
 2.1 전체조회 : select id, email,content from memo
 2.2 조건조회 : select id, email,content from memo where id=? (id = pk)데이터 한건
 2.3 데이터 삽입 : insert into memo(id,email,content) values(?,?,?)
 2.4 데이터 수정 : update memo set email = ? , content=? where id =?
 2.5 데이터 삭제 : delete from memo where id=?
 +데이터 검색 +데이터 LIKE : where email like '%naver@%'

ArrayList HashMap 복습 필요
*/

public class memodao {
	Connection conn = null;
	
	public memodao() {
		conn = SingletonHelper.getConnection("oracle"); //singleton 
	}
	
	//전체조회
	public List<memo> getMemoList() throws SQLException{
		PreparedStatement pstmt = null;
		String sql = "select id, email, content from memo";
		
		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<memo> memolist = new ArrayList<memo>(); //POINT
		//[new memo()][new memo()][new memo()]
		while(rs.next()) {
			memo m = new memo();
			m.setId(rs.getString("id"));
			m.setEmail(rs.getString("email"));
			m.setContent(rs.getString("content"));
			
			memolist.add(m);
		}
		
		SingletonHelper.close(rs);
		SingletonHelper.close(pstmt);
		
		return memolist;
	}
	
	
	//조건조회(where id = ? : 데이터가 1건을 보장 그 이유는 id 컬럼이 제약(pk)걸려있어서
	public memo getMemoListById(String id) {
		//select id, email,content from memo where id=? 
		//memo m = new memo();
		//데이터 넣고
		//return m;
		
		return null;
	}
	
	
	//삽입
	//public int insertMemo(String id , String email, String content)
	//이렇게 파라미터로 받지 말고 객체로 받아라 틀린 표현은 아니다
	public int insertMemo(memo m) {
		
		System.out.println(m.toString());
		int resultrow = 0;
		
		PreparedStatement pstmt = null;
		String sql = "insert into memo(id,email,content) values(?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getContent());
			
			resultrow = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			SingletonHelper.close(pstmt);
			
		}
		
		return resultrow;
	}
	
	
	//수정
	public int updateMemo(memo m) {
		return 0;
	}
	
	
	//삭제
	public int deleteMemo(String id) {
		return 0;
	}
	
	
	//검색
	public memo idSearchByEmail(String email) {
		return null;
	}
	
	//ID유무 함수
	public String isCheckById(String id) {
		
		String ismemoid = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select id from memo where id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			 rs = pstmt.executeQuery();
			 if(rs.next()) {
				 //같은 ID가 존재 한다
				 ismemoid = "false";
			 }else {
				 //사용 가능한 아이디
				 ismemoid = "true";
			 }
			 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
		
		return ismemoid;
	}
	
}

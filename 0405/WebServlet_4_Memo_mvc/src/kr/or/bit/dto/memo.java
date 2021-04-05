package kr.or.bit.dto;

/*
 memo 테이블
 select id, email, content from memo where id=? 데이터 1건을 담을수 있는 클래스
 >>honh , hong@naver.com , 방가방가,
 
 DTO는 DB에 있는 테이블 구조와 동일하게(컬럼명까지) >>자동화
 DTO는 테이블과 1대1로 설계 


 */

public class memo {
	private String id;
	private String email;
	private String content;
	
	public memo() {}

	
	public memo(String id, String email, String content) {
		super();
		this.id = id;
		this.email = email;
		this.content = content;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	@Override
	public String toString() {
		return "memo [id=" + id + ", email=" + email + ", content=" + content + "]";
	}
	
	
	
}

package com.sds.openapp.xml;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//처리할 xml 파일을 읽어들여, 각 태그마다 이벤트를 발생시키기 위한 객체를 상속받자
//DefaultHandler 상속받는 순간부터, 우리가 다루려고 하는 xml파일에 대해 이벤트를
//처리할 수 있다. 마치 이벤트를 처리하는 리스너와 비슷
public class MyHandler2 extends DefaultHandler{
	
	//태그의 이벤트가 종료되고 나면, 아래의 ArrayList는 DTO들로 채워져 있게 처리할 예정
	ArrayList<Member> list;
	Member member;
	
	boolean isEmpno;
	boolean isEname;
	boolean isSal;
	
	//문서가 시작될때 메서트 
	public void startDocument() throws SAXException {
		System.out.println("문서를 시작합니다.");
		//ArrayList을 생성할 계획
		list = new ArrayList<Member>();
	}
	
	//시작 태그를 만날때 메서드
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
		System.out.println("<"+tag+">");
		//시작하는 태그를 만나면, 한 사람에 대한 정보를 담을 준비를 해야 하므로 DTO의 인스턴스 1개를 생성하자
		if(tag.equals("member")) { // <memeber> 여는태그를 만나면
			member = new Member();
		} else if (tag.equals("empno")) {
			isEmpno = true; //지나갔음 표시
		} else if (tag.equals("ename")) {
			isEname = true;
		} else if (tag.equals("sal")) {
			isSal = true;
		}
	}
	
	//태그 사이 문자열 만날때 호출 메서드
	public void characters(char[] ch, int start, int length) throws SAXException {
		String data = new String(ch, start, length);
		System.out.println(data);
		
		if(isEmpno) {
			member.setEmpno(Integer.parseInt(data)); //사원번호
		} else if(isEname) {
			member.setEname(data); //사원이름
		}else if(isSal) {
			member.setSal(Integer.parseInt(data)); // 사원 급여
		}
		
	}
	
	//닫는 태그 만날때 메서드
	public void endElement(String uri, String localName, String tag) throws SAXException {
		System.out.println("</"+tag+">");
		
		if(tag.equals("member")) { // <memeber> 닫는태그를 만나면
			list.add(member);
		} else if (tag.equals("empno")) {
			isEmpno = false; //원상복구
		} else if (tag.equals("ename")) {
			isEname = false;
		} else if (tag.equals("sal")) {
			isSal = false;
		}
	}
	
	//문서 끝날때 메서드
	@Override
	public void endDocument() throws SAXException {
		System.out.println("문서의 끝이네요");
		System.out.println("문서 검색 후, 메모리에 올라갈 list의 수는"+list.size()+"명 담아졌습니다.");

	}
}

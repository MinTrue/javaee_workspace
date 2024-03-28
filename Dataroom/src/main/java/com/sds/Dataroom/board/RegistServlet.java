package com.sds.Dataroom.board;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sds.Dataroom.common.FileManager;

//바이너리 파일이 포함된, 글쓰기 요청을 처리하는 서블릿
public class RegistServlet extends HttpServlet{
	DataroomDAO dataroomDAO = new DataroomDAO();
	
	//클라이언트가 post 방식으로 전송하므로, dopost()재정의
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//주의) 클라이언트가 바이너리 파일을 첨부하여 보낼 경우 encoding 이 multipart/form-data 이기
		//때문에 기존의 request.getParameter() 객체 만으로는 문자열만 처리할 수 있으므로, 
		//복합된 인코딩에 대한 파라미터 받기가 불가능 따라서 업로드 컴포넌트를 통해 파라미터들을 처리해야 한다.
		System.out.println("접속자 감지");
		
		/*
		 * 업로드와 관련된 설정: 저장경로, 파일용량 DiskFileItemFactory
		 * 파일업로드를 담당하는 클래스 : ServletFileUpload
		 * */
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1*1024*1024); //용량 1M
		/*
		 * jsp의 내장객체의 종류
		 * request (요청 정보를 가진 객체) : HttpServletRequest
		 * response (응답 정보를 가진 객체) : HttpServletResponse
		 * session (세션 정보를 객체) : HttpSession
		 * out (응답할 문자열을 담고 있는 출력스트림 객체) : PrintWriter
		 * application (어플리케이션 자체 정보를 가진 객체) :ServletContext
		 * */
		
		ServletContext context = request.getServletContext(); //어플리케이션의 정보를 가진 객체르 얻는다
		//jsp 에서는 이미 application 이라는 이름의 내장 객체로 지원한다.
		String realPath = context.getRealPath("/data/"); //해당 플랫폼을 기준으로 실제 하드디스크상
																				 //경로를 반환
		
		//C:\javaee_workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\Dataroom\data
		System.out.println(realPath);
		
		//파일이 저장될 서버측 경로
		File file = new File(realPath); 
		factory.setRepository(file);
		
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> itemList = upload.parseRequest(request); //업로드 분석
			
			String title = null;
			String writer = null;
			String content = null;
			String filename=null;
			
			//업로드된 파라미터 추출
			for(FileItem item : itemList) {
				if(item.isFormField()) { //html의 text박스 라면
					if(item.getFieldName().equals("title")) { //text 컴포넌트가 제목이라면
						title = item.getString("utf-8");
					} else if (item.getFieldName().equals("writer")){
						writer = item.getString("utf-8");
					} else if (item.getFieldName().equals("content")){
						content = item.getString("utf-8");
					}
				}else { //html file 업로드 컴포넌트라면 파일을 선택했다면
					if(item.getName().length()>=5) {; //파일명의 길이가 5이상이면 a.jpg
						//서버에 저장
						System.out.println("업로드 한 파일명은 "+item.getName());
						filename = FileManager.getNameByTime(FileManager.getExt(item.getName()));
						try {
							//하드 디스크에 파일저장
							item.write(new File(realPath+filename)); 
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
				}
				
			}
			System.out.println("title is"+title);
			System.out.println("writer is"+writer);
			System.out.println("content is"+content);
			
			Dataroom dto = new Dataroom(); //빈 상태
			dto.setTitle(title);
			dto.setWriter(writer);
			dto.setContent(content);
			dto.setFilename(filename);
			
			//DAO 에게 일시키기
			int result = dataroomDAO.insert(dto);
			
			if(result >0) { //성공이면, 목록을 재요청
				response.sendRedirect("/board/list.jsp"); //클라이언트로 하여금, 지정한 url로 재접속을 유도함
													   // js의 location.href=""; 동일
			}else {
				out.print("<script>");
				out.print("history.back();");
				out.print("</script>");
			}
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
}

















